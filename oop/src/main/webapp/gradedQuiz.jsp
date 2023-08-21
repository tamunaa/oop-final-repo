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
    <link rel="stylesheet" type="text/css" href="css/gradedQuiz.css">
    <script src="js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
    <meta charset="UTF-8">
    <title>Grade Questions</title>
    <script>
        function submitAnswers() {
            // Create a form to submit the answers
            var form = document.createElement('form');
            form.method = 'post';
            form.action = 'submitAnswers'; // Change the action URL

            // Loop through questionResponse to add input fields for responses
            <c:forEach var="questionResponse" items="${questions}">
                var responseInput = document.createElement('input');
                responseInput.type = 'text';
                responseInput.name = 'response_' + ${questionResponse[2]}; // Use a unique name for each response
                form.appendChild(responseInput);
            </c:forEach>

            form.innerHTML += '<input type="hidden" name="historyId" value="${requestScope.historyId}">' +
                              '<input type="hidden" name="action" value="submit">'; // Add action input

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
        </tr>
        <c:forEach var="questionResponse" items="${questions}">
            <tr>
                <td>asdfghjklasdfghjklsdfghjklsdfghjk      kdsnfkjv ca,m s fljc j scjo acljnasdjandjlcnadlc?</td> <!-- Question Text -->
                <td><input type="text" name="response_${questionResponse[2]}" required></td> <!-- Response Input -->
            </tr>
        </c:forEach>
    </table>

    <input type="button" value="Submit Answers" onclick="submitAnswers()">
</body>
</html>
