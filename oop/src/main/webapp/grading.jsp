<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<%!
    // Helper method to create an entry
    List<String> createEntry(String responseId, String questionText, String responseText) {
        List<String> entry = new ArrayList<String>();
        entry.add(responseId);
        entry.add(questionText);
        entry.add(responseText);
        return entry;
    }
%>

<!DOCTYPE html>
<html>
<%
    // Retrieve the questionResponse list from session or create it if not present
    List<List<String>> questionResponse = (ArrayList<List<String>>) request.getSession().getAttribute("questions");

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
    <script>
        function validateScore(input) {
            if (input.value < 0) {
                input.value = 0;
            } else if (input.value > 10) {
                input.value = 10;
            }
        }
    </script>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

    <h1>Grade Questions</h1>
<table border="1">
    <tr>
        <th>Question</th>
        <th>Response</th>
        <th>Score</th>
    </tr>
    <%
        for (List<String> entry : questionResponse) {
    %>
        <tr>
            <td><%= entry.get(1) %></td> <!-- Question Text -->
            <td><%= entry.get(2) %></td> <!-- Response Text -->
            <td> <!-- Score Input -->
                <input type="number" id="score_<%= entry.get(0) %>" min="0" max="10" required
                       oninput="validateScore(this)">
            </td>
        </tr>
    <%
        }
    %>
</table>

<form action="finishGrading" method="post" onsubmit="assignZeroScores()">
    <input type="hidden" name="historyId" value="${requestScope.historyId}">
    <input type="submit" value="Finish Grading">
</form>

<script>
    function assignZeroScores() {
        var scoreInputs = document.querySelectorAll('input[type="number"]');
        for (var i = 0; i < scoreInputs.length; i++) {
            if (scoreInputs[i].value === '') {
                scoreInputs[i].value = '0';
            }
        }
    }
</script>
</body>
</html>