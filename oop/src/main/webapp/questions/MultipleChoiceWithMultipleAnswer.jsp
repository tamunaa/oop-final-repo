<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
%>
<form class="form-container" method="post" action="">
    <h3>Multiple Choice With Multiple Answers</h3>
    <div class="question"><h2>Please mark each statement below which is true:</h2></div>
    <div class="user-answer">
        <%
            for (String option : question.getOptions()) {
                %>
        <label><input type="checkbox" name="userAnswer" value="true"><%=option%></label><br>
        <%
            }
        %>
    </div>
</form>
