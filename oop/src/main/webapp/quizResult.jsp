        <%@ page import="objects.Quiz" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="objects.questions.Question" %>
<%@ page import="objects.History" %>
<%@ page import="objects.User" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Objects" %>
<%@ page import="dataBase.questionsDAOs.ResponseDAO" %>
<%@ page import="objects.Review" %>
<%@ page import="dataBase.*" %>
        <%@ page import="java.util.List" %>
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
  <link rel="stylesheet" type="text/css" href="css/quizResult.css">


  <title>Quiz Result</title>
</head>
<body>
  <jsp:include page="navbar.jsp" />
  <jsp:include page="notificationbar.jsp" />

<div class="review-section">
  <%
    QuizDAO quizDAO = (QuizDAO) request.getServletContext().getAttribute("quizDAO");
    Quiz currQuiz = (Quiz) quizDAO.getQuizByID(Integer.parseInt(request.getParameter("quizId")));
    User currUser = (User) request.getSession().getAttribute("currUser");
    HistoryDAO historyDAO = (HistoryDAO) request.getServletContext().getAttribute("historyDAO");

    int quizId = Integer.parseInt(request.getParameter("quizId"));
    List<Question> questions = quizDAO.getQuestions(quizId);
    String[] answers = request.getParameterValues("answers");
    String isPractice = request.getParameter("practice");


    int score = 0;
    int max_score = 0;

    for (int i = 0; i < answers.length; i++){

      if(!Objects.equals(questions.get(i).getQuestionType(), "Graded")){
  %>
  <div class="review-container">
    <label><%= questions.get(i).getQuestion()%></label>
    <p>
      Correct Answer : <%= Arrays.toString(questions.get(i).getCorrectAnswers())%>
      <br> Your Answer: <%= answers[i]%>
      <%
        int curScore = questions.get(i).evaluate(answers[i]);
        int cur_max_score = questions.get(i).getCorrectAnswers().length;
        score += curScore;
        max_score += cur_max_score;
      %>
      <br>Score: <%=curScore%> / <%=cur_max_score%>
    </p>
  </div>
  <%  }
  } %>

  Overall Score: <%=score%> / <%=max_score%>
</div>

  <% if (Objects.equals(isPractice, "true")){
      int time = 2;
      History history = new History(currQuiz.getID(), currUser.getId(), score, time, (Timestamp) request.getSession().getAttribute("endTime"));
      historyDAO.insertHistory(history);
        for (int i = 0; i < questions.size(); i++) {
          if (Objects.equals(questions.get(i).getQuestionType(), "Graded")) {
            ResponseDAO responseDAO = (ResponseDAO) request.getServletContext().getAttribute("responseDAO");
            responseDAO.addResponse(questions.get(i).getQuestionId(), history.getId(), score, false, answers[i]);
          }
        }
    }
  %>

  <div class="review-section">
    <form action="review?quizId=<%=request.getParameter("quizId")%>" method="post">
      <div class="elem">Review:  <input type="text" name="review"> </div>
      <label class="elem" for="rating">Rate Quiz between 1 and 10
        <input type="number" min="1" max="10" name="rating" id="rating">
      </label>
      <br>
      <button class="elem btn btn-secondary" type="submit"> Add Rating and Review</button>
    </form>
  </div>

</body>
</html>
