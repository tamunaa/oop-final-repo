<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container">
<h3>Picture Response Question</h3>
<div class="question">
<h2><%=question.getQuestion()%></h2>
    <div style="display: flex; justify-content: center">
        <img src="<%=question.getOptions()[0]%>" width="200px">
    </div>
</div>

<div class="user-answer">
    <label>Your Answer:</label>
    <label>
        <input type="text" name="userAnswer" required>
    </label>
</div>

</form>
