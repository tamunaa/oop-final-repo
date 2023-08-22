<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container">
    <h3>Multi Answer</h3>
    <div class="question">
        <h2> <%=question.getQuestion()%> </h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <%
            for (int i = 0; i < question.getNumFields(); i++) {
                %>
            <input type="text" name="userAnswer" required>
        <%
            }
        %>
    </div>
</form>
