
<%@ page import="objects.questions.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
</head>
<body>
<div class="form">
    <div class="timer" onload="timer(10)">
        <div>Section</div>
        <div class="time">
            <strong>Time left: <span id="time">Loading...</span></strong>
        </div>
    </div>
    <%
        Boolean feedback = true;
        request.getSession().setAttribute("feedback", feedback);
        int curID = Integer.parseInt(request.getAttribute("index").toString());
        out.println("<h3>Question #" + (curID + 1) + "</h3>");
        Question question = (Question) request.getAttribute("current");
        String[] answers = (String[]) request.getSession().getAttribute("answers");
            out.println("<h2>Your Answer: " + answers[curID] + "</h2> <br>");
            out.println("<h2>Correct Answer: " + question.getCorrectAnswers() + "</h2> <br>");

    %>
    <form action="MultiPageServlet"  name="timeOut"  id="timeOut"  method="post">
        <input class="button1" type="hidden" id="timeLeft" name="timeLeft" value="">
        <input class="button1" type="submit" value="Next Question"/>
    </form>
</div>
</body>
</html>
