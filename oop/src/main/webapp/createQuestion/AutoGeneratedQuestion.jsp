<%--
  Created by IntelliJ IDEA.
  User: mariamtsikarishvili
  Date: 20.08.23
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="objects.questions.Question" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Auto Generated Question</title>
    <link rel="stylesheet" type="text/css" href="css/createQuestion/createQuestionResponse.css">
</head>
<body>
<%
    Question question = (Question) request.getAttribute("question");
%>
<h1>Create Auto Generated Question</h1>

<form action="addQuestions/addMultipleChoice?quizId=<%=request.getParameter("quizId")%>" method="POST">
    <label for="questionText">Question Text:</label><br>
    <textarea id="questionText" name="questionText" rows="4" cols="50" style="resize: none" readonly><%=question.getQuestion()%></textarea><br><br>

    <div id="options-container">
        <label>Options:</label><br>
            <%
      for (String opt : question.getOptions()) {
        %>
        <div class="input-group">
            <input type="text" class="option" name="options" value="<%=opt%>" readonly>
        </div>
            <%
      }
    %>
        <br>
        <div class="answer">
            <label>Correct answer:</label><br>
            <input type="text" class="option" name="answer" value="<%=question.getCorrectAnswers()[0]%>" readonly>
        </div>
        <br>
        <div/>

            <%
    boolean timerIsAllowed = Boolean.parseBoolean(request.getAttribute("timerIsAllowed").toString());
    if (!timerIsAllowed) {
      out.println("<p class=\"warning-text\">Timed questions are only allowed in one-question-per-page format quizzes</p>");
    }
  %>
        <label for="timer">Timer (in seconds):</label><br>
        <input type="number" id="timer" name="timer" min="1" <%if(!timerIsAllowed) out.print("readonly");%>><br><br>

        <!-- Buttons -->
        <button type="submit">Add Question</button>
        <a href="/editQuiz?quizId=<%=request.getParameter("quizId")%>"><button type="button">Cancel</button></a>
</form>

</body>
</html>

