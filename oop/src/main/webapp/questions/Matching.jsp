<%@ page import="objects.questions.Question" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%
    Question question = (Question)request.getAttribute("current");
    int total = question.getNumFields();
    String indexStr = request.getAttribute("index").toString();
    int index = Integer.parseInt(indexStr);
%>

<form class="form-container">
    <h3>Matching</h3>

    <div class="matching-question">
        <div class="left-side">
            <%
                int l = 0;
                for (String left : question.getOptions()) {
            %>
                <div class="left" id="question<%=index%>left<%=l%>"><%=left%></div>
            <%
                l++;
                }
            %>
        </div>

        <div class="options">
            <%
                String[] rights = question.getCorrectAnswers();
                List<String> rightsList = Arrays.asList(rights);
                Collections.shuffle(rightsList);
                String[] shuffledRights = rightsList.toArray(new String[0]);
                int r = 0;
                for (String right : shuffledRights) {
            %>
                <div class="left box" draggable="true" id="box-<%=r%>" ondragend="getMatchingAnswers(<%=index%>, <%=total%>)">
                    <span class="right" id="question<%=index%>right<%=r%>"><%=right%></span>
                </div>
            <%
                r++;
                }
            %>
        </div>
    </div>
</form>
<script src="questions/js/Matching.js"></script>