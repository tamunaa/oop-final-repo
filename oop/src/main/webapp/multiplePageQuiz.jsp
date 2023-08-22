<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="objects.questions.Question" %>

<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="questions/css/Matching.css">
    <link rel="stylesheet" type="text/css" href="questions/css/multi.css">

    <style>
        body {
            flex-direction: column;
        }
    </style>
</head>
<body>

<div class="question-space">
    <div class="question-card" id="dynamic-container">
        <h1 style="font-size: 50px">Good Luck</h1>
    </div>
</div>
<button class="finish" id="load-button">Next</button>
<%--<%--%>
<%--Question[] questions = (Question[]) request.getSession().getAttribute("questions");--%>
<%--%>--%>
<script>
    $(document).ready(function() {
        const dynamicContainer = $("#dynamic-container");
        const loadButton = $("#load-button");
        let currentIndex = 0;
        loadButton.click(function() {
            loadDynamicContent(currentIndex);
            currentIndex++;
        });
        function loadDynamicContent(index) {
            const path = "loadQuestions.jsp";
            dynamicContainer.fadeOut(300, function() {
                $.ajax({
                    url: path,
                    type: "POST",

                    data: { index: index},
                    success: function(data) {
                        dynamicContainer.html(data);
                        dynamicContainer.fadeIn(300);
                    }
                });
            });
        }
    });
</script>

</body>
</html>