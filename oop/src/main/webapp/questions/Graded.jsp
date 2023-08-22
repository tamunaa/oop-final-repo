<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container" method="post" id="QuestionResponse">
    <h3>Graded Question</h3>

    <div class="question">
        <h2><%= question.getQuestion() %></h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <textarea type="text" name="userAnswer" required rows="5" cols="50"></textarea>
    </div>
</form>
