<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container">
    <h3>Fill In The Blank</h3>
    <div class="question">
        <h2> <%= question.getQuestion()%> </h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <label>
            <input type="text" name="userAnswer" required>
        </label>
    </div>
</form>

