<%@ page import="objects.Quiz" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="objects.questions.Question" %>
<%@ page import="objects.questions.QuestionResponse" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dataBase.QuizDAO" %>
<%@ page import="dataBase.DbQuizDAO" %>
<%@ page import="org.apache.commons.dbcp2.BasicDataSource" %>
<%@ page import="com.sun.tools.javac.Main" %>
<%@ page import="javax.imageio.stream.ImageInputStream" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/quiz.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script src="js/blabla.js"></script>
    <title>Quiz</title>
</head>
<body>

<%
    Quiz quiz = (Quiz) session.getAttribute("quiz");
    String author = (String) request.getAttribute("author");
%>


<section>
    <jsp:include page="navbar.jsp" />
</section>

<div class="quiz-container">
    <div class="quiz-info">
        <h1><%=quiz.getQuizName()%></h1>
        <p>author: <a href="profile.jsp?user=<%=author%>"><%=author%></a></p>
        <p>Time for Quiz: <%=quiz.getTimer()%> minutes</p>
        <p>Created Date: <%=new SimpleDateFormat("dd MMMM, yyyy HH:mm").format(quiz.getDateCreated())%></p>
        <p>Description: <%=quiz.getDescription()%></p>
    </div>

    <div class="quiz-options">
        <%
            if (quiz.isPractice()) {
                out.println("<a href=\"#\" class=\"practice-btn\">Practice</a>");
            }
        %>
        <a href="question" class="take-btn">Take Quiz</a>

    </div>

    <div class="quiz-history">
        <h2>Quiz History</h2>
        <table>
            <tr>
                <th>When</th>
                <th>Who</th>
                <th>Time</th>
                <th>Score</th>
            </tr>
            <%
                // iterate over quiz history {
            %>
                <tr>
                    <td></td>
                    <td></td>
                    <td><a href="profile.jsp?user="></a></td>
                    <td></td>
                    <td></td>
                </tr>
            <%
//                }
            %>
        </table>
    </div>

</div>
</body>
</html>
