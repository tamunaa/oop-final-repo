<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/quiz.css">
    <script src="js/navbar.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <title>Create Quiz</title>
    <link rel="stylesheet" type="text/css" href="css/createQuiz.css"></head>
<body>
<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<h1 class="text-center" >Create a New Quiz</h1>

<form action="createQuiz" method="post" class="create-quiz-form">
    <div>
        <label for="quizName">Quiz Name:</label>
        <input type="text" id="quizName" name="quizName" required>
    </div>

    <div>
        <label for="timer">Quiz Timer (in minutes):</label>
        <input type="number" id="timer" name="timer" min="0" value="0">
    </div>

    <div>
        <label for="practice">Practice Mode:</label>
        <input type="checkbox" id="practice" name="practice">
    </div>

    <div>
        <label for="questionDisplay">Question Display Mode:</label>
        <select id="questionDisplay" name="questionDisplay" style="margin: 3px; padding: 2px">
            <option value="singlePage">All Questions on a Single Page</option>
            <option value="onePerPage">One Question Per Page</option>
        </select>
    </div>

    <div>
        <label for="correction">Immediate Correction:</label>
        <input type="checkbox" id="correction" name="correction">
    </div>

    <div>
        <label for="randomOrder">Random Question Order:</label>
        <input type="checkbox" id="randomOrder" name="randomOrder">
    </div>

    <div style="display: flex">
        <label for="quizDescription">Quiz Description:</label>
        <textarea id="quizDescription" name="quizDescription" rows="4" cols="50"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Create Quiz</button>
</form>
</body>
</html>
