<%@ page import="objects.questions.Question" %>
<%
    Question question = (Question)request.getAttribute("current");
    String indexStr = request.getAttribute("index").toString();
    int index = Integer.parseInt(indexStr);
%>
<form class="form-container" method="post" id="QuestionResponse">

    <h3>Question Response</h3>
    <div class="question">
        <h2><%= question.getQuestion() %></h2>
    </div>

    <div class="user-answer">
        <label>Your Answer:</label>
        <label>
            <input type="text" name="userAnswer" required>
            <button type="button" onclick="getQuestionResponseAnswers(this)">Submit</button>
        </label>
    </div>
</form>

<script>
    function getQuestionResponseAnswers(button) {
        let inputField = button.previousElementSibling;
        let value = inputField.value;
        let answers = document.getElementById("answer<%=index%>");
        answers.value = value;
    }
</script>
