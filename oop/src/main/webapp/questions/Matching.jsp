<%@ page import="objects.questions.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%
    Question question = (Question)request.getAttribute("current");
%>

<form class="form-container">
    <h3>Matching</h3>

    <div class="matching-question">
        <div class="left-side">
            <% for (String left : question.getOptions()) {
            %>
            <div class="left" name="left"><%=left%></div>
            <%
            }
            %>
        </div>

        <div class="options">
            <%
            String[] rights = question.getCorrectAnswers();
            for (String right : rights) {
            %>
                <div class="left box" draggable="true" id="box-a">
                <span class="right" name="right" value="<%=right%>"><%=right%></span></div>
            <%
                }
            %>
        </div>
    <button type="button" onclick="getMatchingAnswers(this)">Submit</button>
    </div>
</form>
<script src="questions/js/Matching.js"></script>
<script>
    function getMatchingAnswers(button) {
        let rights = document.getElementsByName("right");
        for (let i = 0; i < rights.length; i++) {
            console.log(rights[i].textContent);
        }
        let lefts = document.getElementsByName("left");
        for (let i = 0; i < lefts.length; i++) {
            console.log(lefts[i].textContent);
        }
    }
</script>