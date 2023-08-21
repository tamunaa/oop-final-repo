<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Grade Questions</title>
</head>
<body>
    <h1>Grade Questions</h1>
    <table border="1">
        <tr>
            <th>Question ID</th>
            <th>Question Text</th>
            <th>Response</th>
            <th>Score</th>
            <th>Grade</th>
        </tr>
        <c:forEach var="question" items="${questions}">
            <tr>
                <td>${question.questionId}</td>
                <td>${question.questionText}</td>
                <td>${question.response}</td>
                <td>${question.score}</td>
                <td>
                    <form action="grade" method="post">
                        <input type="hidden" name="questionId" value="${question.questionId}">
                        <input type="number" name="score" min="0" max="100" required>
                        <input type="submit" value="Grade">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="finishGrading" method="post">
        <input type="submit" value="Finish Grading">
    </form>
</body>
</html>
