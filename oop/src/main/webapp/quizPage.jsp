<%@ page import="objects.Quiz" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="objects.questions.Question" %>
<%@ page import="objects.questions.QuestionResponse" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.dbcp2.BasicDataSource" %>
<%@ page import="com.sun.tools.javac.Main" %>
<%@ page import="javax.imageio.stream.ImageInputStream" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page import="objects.History" %>
<%@ page import="objects.User" %>
<%@ page import="dataBase.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="css/quiz.css">

    <title>Quiz</title>
</head>
<body>

<%
    String quizName = request.getParameter("searchInput");
    UserDAO userDAO = ((UserDAO)request.getServletContext().getAttribute("userDAO"));
    QuizDAO quizDAO = ((QuizDAO)request.getServletContext().getAttribute("quizDAO"));
    Quiz curQuiz = quizDAO.getQuizByQuizName(quizName).get(0);
    String author = userDAO.getUsernameByID(curQuiz.getAuthor());
    User currUser = (User) request.getSession().getAttribute("currUser");
%>

<jsp:include page="navbar.jsp" />

<div class="quiz-container">

    <div class="upp">
        <div class="left-side">
            <div class="quiz-info">
                <h1><%=curQuiz.getQuizName()%></h1>
                <p>author: <a href="profile.jsp?self=false&&username=<%=author%>"><%=author%></a></p>
                <p>Time for Quiz: <%=curQuiz.getTimer()%> minutes</p>
                <p>Created Date: <%=new SimpleDateFormat("dd MMMM, yyyy HH:mm").format(curQuiz.getDateCreated())%></p>
                <p>Description: <%=curQuiz.getDescription()%></p>
            </div>

        <div class="quiz-options">
        <form><a href="loadQuizQuestions?quizId=<%=curQuiz.getID()%>" class="take-btn">Take Quiz</a></form>
        <%if (true) {%>
        <form><a class="practice-btn"> practice </a></form>
        <%}%>
        <%if (currUser.getId() == curQuiz.getAuthor()){%>
            <form><a href = "editQuiz?quizId=<%=curQuiz.getID()%>" class="edit-btn"> Edit Quiz </a></form>
        <%}%>


                <button id="challengeFriendBtn" class="btn btn-secondary">Challenge Friend</button>
            </div>
        </div>
        <div class="right-side">
        <%
            List<User> friends = ((FriendsDAO)request.getServletContext().getAttribute("friendsDAO")).getAllFriends(currUser);
        %>
            <div class="friend-list" id="friendList" style="display: none;">
                <% for (int i = 0; i < friends.size(); i++) { %>
                <form action="NotificationServlet" method="POST" class="challenge">
                    <input type="hidden" name="recipient" value="<%= friends.get(i).getUsername() %>">
                    <input type="hidden" name="type" value="CHALLENGE">
                    <input type="hidden" name="content" value="content">
                    <input type="hidden" name="quizName" value="<%= curQuiz.getQuizName() %>">
                    <div class="friend-item">
                        <p><%= friends.get(i).getUsername() %></p>
                        <button type="submit">Challenge</button>
                    </div>
                </form>
                <% } %>
            </div>

            <script>
            document.addEventListener("DOMContentLoaded", function () {
                const challengeFriendBtn = document.getElementById("challengeFriendBtn");
                const friendListContainer = document.getElementById("friendList");
                challengeFriendBtn.addEventListener("click", () => {
                    friendListContainer.style.display = friendListContainer.style.display === "none" ? "block" : "none";
                });
            });
        </script>

    </div>
    </div>



    <div class="down">
    <%
        HistoryDAO historyDAO = (HistoryDAO) request.getServletContext().getAttribute("historyDAO");
        int curQuizId = curQuiz.getID();
        List<History> historyList =  historyDAO.getHistoryByQuizId(curQuiz.getID());
        double averageScore = 0;
        double averageTime = 0;
        double sumScore = 0;
        double sumTime = 0;
        for(int i = 0; i < historyList.size(); i++){
            History currHistory = historyList.get(i);
            sumScore += currHistory.getScore();
            sumTime += currHistory.getTimeRelapsed();
        }
        averageTime = sumTime/historyList.size();
        averageScore = sumScore/historyList.size();
        List<History> topScorersHistory = historyDAO.sortedHistory(curQuizId,"score",10);
        List<History> recentScorersHistory = historyDAO.sortedHistory(curQuizId,"Date_taken",10);
        List<History> myHistory = historyDAO.UserRecentHistory(curQuizId,currUser.getId(),10);


    %>

    <div class="quiz-history">
        <h2>Quiz History</h2>



        <input class="tabs" type="button" name="Showdiv1" value="Top Scorers" onclick="showDiv('1')"/>
        <input class="tabs" type="button" name="Showdiv2" value="My Scores" onclick="showDiv('2')"/>
        <input class="tabs" type="button" name="Showdiv3" value="Recent Scores" onclick="showDiv('3')"/>

        <div id="div1">
            <table>
                <tr>
                    <th>When</th>
                    <th>Who</th>
                    <th>Time</th>
                    <th>Score</th>
                </tr>
                <%
                    for (int i = 0; i < topScorersHistory.size(); i++) {
                        History history = topScorersHistory.get(i);
                        String who = userDAO.getUserByUserId(history.getUserId()).getUsername();
                        String path = "profile.jsp?self=false&&username="+who;
                %>
                <tr>
                    <td><%= history.getDateTaken()%></td>
                    <td><a href= '<%=path%>'> <%=who%> </a> </td>
                    <td><%= history.getTimeRelapsed()%></td>
                    <td><%= history.getScore()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <div id=div2>
            <table>
                <tr>
                    <th>When</th>
                    <th>Time</th>
                    <th>Score</th>
                </tr>
                <%
                    for (int i = 0; i < myHistory.size(); i++) {
                        History history = myHistory.get(i);
                %>
                <tr>
                    <td><%= history.getDateTaken()%></td>
                    <td><%= history.getTimeRelapsed()%></td>
                    <td><%= history.getScore()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <div id=div3>
            <table>
                <tr>
                    <th>When</th>
                    <th>Who</th>
                    <th>Time</th>
                    <th>Score</th>
                </tr>
                <%
                    for (int i = 0; i < recentScorersHistory.size(); i++) {
                        History history = recentScorersHistory.get(i);
                        String who = userDAO.getUserByUserId(history.getUserId()).getUsername();
                        String path = "profile.jsp?self=false&&username="+who;
                %>
                <tr>
                    <td><%= history.getDateTaken()%></td>
                    <td><a href= '<%=path%>'> <%=who%> </a> </td>
                    <td><%= history.getTimeRelapsed()%></td>
                    <td><%= history.getScore()%></td>
                </tr>
                <%
                    }
                %>
            </table>
        </div>
        <script type="text/javascript">
            function showDiv(num) {
                document.getElementById('div1').style.display = 'none';
                document.getElementById('div2').style.display = 'none';
                document.getElementById('div3').style.display = 'none';
                document.getElementById('div' + num).style.display = 'block'
            }
        </script>
        <br>

        <div class="w3-container">
            <span class="w3-tag w3-padding w3-round-large w3-red w3-center">Average Score: <%=averageScore%></span>
            <span class="w3-tag w3-padding w3-round-large w3-red w3-center">Average Time Spent in Minutes: <%=averageTime%></span>
        </div>
        <input name="quiz_id" type="hidden" value="<%=curQuizId%>"/>
        <br>
        <%
            if (curQuiz.isPractice()){%>

        <label class="container">Practice Mode
            <input type="checkbox" value="YES" name="IsPracticed" >
            <span class="checkmark"></span>
        </label>

        <%}%>
    </div>
    </div>
</div>

</body>
</html>