<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container" method="post">
    <h3>Multiple Choice Question</h3>
    <div class="question"><h2><%=question.getQuestion()%></h2></div>
    <div class="user-answer">
        <%
            for (String option : question.getOptions()) {
            %>
        <label><input type="radio" name="userAnswer" value="A" required><%=option%></label><br>
        <%
        }
        %>
    </div>
</form>
