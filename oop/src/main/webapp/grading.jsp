<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> <!-- Add this import -->

<!DOCTYPE html>
<html>
<%
    List<List<String>> questionResponse = (List<List<String>>) request.getSession().getAttribute("questions");
%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/grading.css">
    <script src="js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <meta charset="UTF-8">
    <title>Grade Questions</title>
    <script>
        function gradeQuestion(questionId) {
            var scoreInput = document.getElementById('score_' + questionId);
            var score = scoreInput.value;

            // Create a form to submit the grade
            var form = document.createElement('form');
            form.method = 'post';
            form.action = 'grading';
            form.innerHTML = '<input type="hidden" name="action" value="grade">' +
                             '<input type="hidden" name="questionId" value="' + questionId + '">' +
                             '<input type="hidden" name="score" value="' + score + '">';

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

    <h1>Grade Questions</h1>
    <table border="1">
        <tr>
            <th>Question Text</th>
            <th>Response</th>
            <th>Score</th>
        </tr>
        <c:forEach var="questionResponse" items="${questions}">
            <tr>
                <td>${questionResponse[0]}</td> <!-- Question Text -->
                <td>${questionResponse[1]}</td> <!-- Response Text -->
                <td> <!-- Score Input -->
                    <input type="number" id="score_${questionResponse[2]}" min="0" max="100" required>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="finishGrading" method="post">
        <input type="hidden" name="historyId" value="${requestScope.historyId}">
        <input type="submit" value="Finish Grading">
    </form>
</body>
</html>
