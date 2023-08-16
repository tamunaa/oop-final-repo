<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>
    <link rel="stylesheet" type="text/css" href="css/home.css">

    <title>Quiz Page - Quiz Name</title>
    <style>
        /* Add your styles here */
    </style>
</head>
<body>
<h1>Quiz Name</h1>
<h2>Quiz info</h2>
<table>
    <tr>
        <th>Timer</th>
        <th>Display Type</th>
        <th>Is Random</th>
        <th>Corrects Immediately</th>
    </tr>
    <tr>
        <td>60</td>
        <td>Single Choice</td>
        <td>true</td>
        <td>false</td>
    </tr>
</table>



<p>Description: Sample description for the quiz goes here.</p>

<h2>Quiz History</h2>
<ul>
    <li> <a href="profile.jsp">User</a> 1 - Time: 10 minutes, Score: 8/10</li>
    <li>User 2 - Time: 15 minutes, Score: 9/10</li>
    <li>User 3 - Time: 12 minutes, Score: 7/10</li>
    <!-- Add more history entries as needed -->
</ul>

<button onclick="startQuiz()">Start Quiz</button>

<script>
    function startQuiz() {
        window.location.href = "quiz_questions.html";
    }
</script>
</body>
</html>
