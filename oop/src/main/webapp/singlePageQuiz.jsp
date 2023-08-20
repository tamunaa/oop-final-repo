<%@ page import="objects.questions.Question" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">

<%--    <%--%>
<%--        String cssFolderPath = "questions/css/"; // Path to your CSS folder--%>
<%--        String[] cssFiles = new java.io.File(application.getRealPath(cssFolderPath)).list();--%>
<%--        if (cssFiles != null) {--%>
<%--            for (String cssFile : cssFiles) {--%>
<%--                if (cssFile.endsWith(".css")) {--%>
<%--    %>--%>
<%--    <link rel="stylesheet" type="text/css" href="<%= cssFolderPath + cssFile %>">--%>
<%--    <%--%>
<%--                }--%>
<%--            }--%>
<%--        }--%>
<%--    %>--%>

    <link rel="stylesheet" type="text/css" href="questions/css/base.css">
    <link rel="stylesheet" type="text/css" href="questions/css/Matching.css">

    <title>quiz</title>
</head>
<body>
    <%
        Question[] questions = (Question[]) request.getSession().getAttribute("questions");
    %>

<%--    <ul>--%>
<%--        <% for (Question question : questions) { %>--%>
<%--        <li><%= question.getQuestionType() %></li>--%>
<%--        <% } %>--%>
<%--    </ul>--%>

<%--    <jsp:include page="questions/QuizBar.jsp"/>--%>

    <div class="question-page">
        <%
        int i = 0;
        while (i < questions.length){
            Question current = questions[i];
            session.setAttribute("current", current);
            String type = current.getQuestionType();
            String path = "questions/"+type+".jsp";
    %>
        <div class="question-card">
            <jsp:include page="<%= path %>" />
        </div>
    <%
        i++;
    }
    %>
        <button class="finish">submit</button>
    </div>
</body>
</html>
