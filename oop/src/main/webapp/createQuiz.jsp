<!DOCTYPE html>
<html>
<head>
    <title>Create Quiz</title>
    <link rel="stylesheet" type="text/css" href="css/createQuiz.css"></head>
<body>
<h1>Create a New Quiz</h1>
<form action="createQuiz" method="post">

    <label for="quizName">Quiz Name:</label>
    <input type="text" id="quizName" name="quizName" required><br>

    <label for="timer">Quiz Timer (in minutes):</label>
    <input type="number" id="timer" name="timer" min="0" value="0"><br>

    <label for="practice">Practice Mode:</label>
    <input type="checkbox" id="practice" name="practice"><br>

    <label for="questionDisplay">Question Display Mode:</label>
    <select id="questionDisplay" name="questionDisplay">
        <option value="singlePage">All Questions on a Single Page</option>
        <option value="onePerPage">One Question Per Page</option>
    </select><br>

    <label for="correction">Immediate Correction:</label>
    <input type="checkbox" id="correction" name="correction"><br>

    <label for="randomOrder">Random Question Order:</label>
    <input type="checkbox" id="randomOrder" name="randomOrder"><br>

    <label for="quizDescription">Quiz Description:</label>
    <textarea id="quizDescription" name="quizDescription" rows="4" cols="50"></textarea><br><br>

    <input type="submit" value="Create Quiz">
</form>
</body>
</html>
