<%@ page import="objects.questions.Question" %>

<form class="form-container" method="post" id="QuestionResponse">

    <%
        Question question = (Question) session.getAttribute("current");
    %>
    <div class="question">
        <h2><%= question.getQuestion() %></h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <label>
            <input type="text" name="userAnswer" required>
        </label>
    </div>
</form>
