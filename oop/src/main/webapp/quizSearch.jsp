<%@ page import="objects.Quiz" %>
<%@ page import="dataBase.UserDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
აქაც იგივე რაც search jsp-ში თავიდან
--%>
<% List<Quiz> quizzes = (List<Quiz>) request.getAttribute("searchResult");
    UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
%>
</head>
<body>
<div class="d-flex flex-column justify-content-center w-100 h-100"></div>
<div class="form">
    <h1>Search Results: </h1>
    <table class="topscorers">
        <tr>
            <th>Quiz Title</th>
            <th>Creator</th>
        </tr>
<%--        <%--%>
<%--            for (Quiz quiz : quizzes) {--%>
<%--                out.println("<tr><td>" + "<a href=\"ShowQuiz.jsp?quiz_id=" + quiz.getID() + "\">" + quiz.getQuizName() + "</a>" + "</td><td>" + userDAO.getUserByUserId(quiz.getAuthor()) + "</td></tr>");--%>
<%--            }--%>
<%--        %>--%>
    </table>
</div>
</body>
</html>