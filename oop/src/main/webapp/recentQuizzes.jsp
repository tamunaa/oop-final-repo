<%@ page import="dataBase.UserDAO" %>
<%@ page import="dataBase.QuizDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="objects.Quiz" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/quizzes.css">
    <script src="js/navbar.js"></script>

    <title>List of Recent Quizzes</title>
</head>

<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />


<%
    UserDAO userDAO = ((UserDAO)request.getServletContext().getAttribute("userDAO"));
    QuizDAO quizDAO = ((QuizDAO)request.getServletContext().getAttribute("quizDAO"));

    List<Quiz> quizzList = quizDAO.getAllQuizzes();
%>
<div id="wrapper" class="wrapper">
    <div id="page" class="page">
        <h1 class="text-center">List of Recent Quizzes</h1>


        <div class="search-area">
            <div class="search-container">
                <form action="SearchServlet" method="GET">
                    <input type="hidden" name="type" value="quizName">
                    <input class="input-field" type="text" name="searchInput" placeholder="Search by quiz name...">

                    <button type="submit" class="btn btn-secondary">
                        <i class="bi bi-search-heart"></i>
                        Search
                    </button>
                </form>
            </div>

            <div class="search-container">
                <form action="SearchServlet" method="GET">
                    <input type="hidden" name="type" value="quizTag">
                    <input  class="input-field type=text" name="searchInput" placeholder="Search by quiz tag...">
                    <button type="submit" class="btn btn-secondary">
                        <i class="bi bi-search-heart"></i>
                        Search
                    </button>
                </form>
            </div>
        </div>

        <div>
            <%
                for (Quiz quiz : quizzList) {
                    String quizName = quiz.getQuizName();
                    String author = userDAO.getUserByUserId(quiz.getAuthor()).getUsername();
                    String pathToProfile = "profile.jsp?self=false&&username="+author;
                    String pathToQuizPage = "quizPage.jsp?searchInput="+quizName;
                    String description = quiz.getDescription();
                    int timer = quiz.getTimer();


                    boolean isRandom = quiz.isRandom();
                    boolean isPractice = quiz.isPractice();
                    boolean isOnePage = quiz.isOnOnePage();
                    boolean correctImmediately = quiz.correctImmediately();
                    String category = quiz.getCategory();
            %>
                <div class="quiz-card">
                    <p> <a href= "<%=pathToQuizPage%>" > <%=quizName%> </a> </p>
                    <p>Author:  <a href="<%=pathToProfile%>"> <%=author%></a> </p>
                    <p><%=description%></p>

                    <p> category: <%=category%> </p>

                    <p><i class="bi bi-clock"></i> <%=timer%></p>
                    <p>Is Random:
                        <%if (isRandom){%>
                            <i class="bi bi-plus-lg"></i>
                        <%}else{%>
                            <i class="bi bi-x-lg"></i>
                        <%}%>
                    </p>


                    <p>Corrects Immediately:
                        <%if (correctImmediately){%>
                        <i class="bi bi-plus-lg"></i>
                        <%}else{%>
                        <i class="bi bi-x-lg"></i>
                        <%}%>
                    </p>

                    <p>Is Practice:
                        <%if (isPractice){%>
                        <i class="bi bi-plus-lg"></i>
                        <%}else{%>
                        <i class="bi bi-x-lg"></i>
                        <%}%>
                    </p>
                </div>
            <%}%>

        </div>
    </div>
</div>

</body>
</html>