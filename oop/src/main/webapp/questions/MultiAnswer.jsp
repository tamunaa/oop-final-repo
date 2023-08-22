<%@ page import="objects.questions.Question" %>

<form class="form-container">
<%--    <h1>Multi Answer</h1>--%>
    <div class="question">
<%--        <h2> <%= ((Question)session.getAttribute("current")).getQuestion()%> </h2>--%>
        <h2>questionnn</h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <label>
            <input type="text" name="userAnswer" required>
        </label>
    </div>
</form>
