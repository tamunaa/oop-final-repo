<%--
  Created by IntelliJ IDEA.
  User: mariamtsikarishvili
  Date: 22.08.23
  Time: 23:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="objects.Quiz" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="objects.questions.Question" %>
<%@ page import="objects.questions.QuestionResponse" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dataBase.QuizDAO" %>
<%@ page import="dataBase.DbQuizDAO" %>
<%@ page import="org.apache.commons.dbcp2.BasicDataSource" %>
<%@ page import="com.sun.tools.javac.Main" %>
<%@ page import="javax.imageio.stream.ImageInputStream" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mysql.cj.xdevapi.JsonArray" %>
<%@ page import="dataBase.UserDAO" %>
<%@ page import="objects.History" %>
<%@ page import="dataBase.HistoryDAO" %>
<%@ page import="objects.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Objects" %>
<%@ page import="dataBase.questionsDAOs.ResponseDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
  <link rel="stylesheet" type="text/css" href="css/navbar.css">
  <script src="js/navbar.js"></script>
  <link rel="stylesheet" type="text/css" href="css/quiz.css">

  <title>Quiz Result</title>
</head>
<body>
  <jsp:include page="navbar.jsp" />
  <jsp:include page="notificationbar.jsp" />
  <%
    QuizDAO quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
    Quiz currQuiz = (Quiz) quizDAO.getQuizByID(Integer.parseInt(request.getParameter("quizId")));
    User currUser = (User) request.getSession().getAttribute("currUser");
    HistoryDAO historyDAO = (HistoryDAO) request.getServletContext().getAttribute("historyDAO");

    int quizId = Integer.parseInt(request.getParameter("quizId"));
    Question[] questions = (Question[]) request.getSession().getAttribute("questions");
    String[] answers = request.getParameterValues("answers");

    int score = 0;
    int max_score = 0;

    for (int i = 0; i < answers.length; i++){

      if(!Objects.equals(questions[i].getQuestionType(), "Graded")){
  %>
      <div>
        <label><%= questions[i].getQuestion()%></label>
        <p>
          Correct Answer : <%= Arrays.toString(questions[i].getCorrectAnswers())%>
          <br> Your Answer: <%= answers[i]%>
        </p>
        <%
          int curScore = questions[i].evaluate(answers[i]);
          int cur_max_score = questions[i].getCorrectAnswers().length;
          score += curScore;
          max_score += cur_max_score;
        %>
        Score: <%=curScore%> / <%=cur_max_score%>
      </div>
  <%  }
    } %>

  <header>
    Overall Score: <%=score%> / <%=max_score%>
    <br>
    <%long time = ((((Timestamp)request.getSession().getAttribute("endTime")).getTime()-
            ((Timestamp) request.getSession().getAttribute("startTime")).getTime()) / 1000) % 60;%>
    Time: <%=time%>seconds
    <br>
    <% History history = new History(currQuiz.getID(), currUser.getId(), score, time, (Timestamp)request.getSession().getAttribute("endTime"));
    historyDAO.insertHistory(history);
      for (int i = 0; i < questions.length; i++) {
        if (Objects.equals(questions[i].getQuestionType(), "Graded")) {
          ResponseDAO responseDAO = (ResponseDAO) request.getServletContext().getAttribute("responseDAO");
          responseDAO.addResponse(questions[i].getQuestionId(), history.getId(), score, false, answers[i]);
        }
      }
    %>
  </header>
</body>
</html>
