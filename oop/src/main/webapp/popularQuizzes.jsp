<!DOCTYPE html>
<html>
<head>
    <title>List of Quizzes</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

<jsp:include page="navbar.jsp" />

<h1>List of Quizzes</h1>
popular quizzes
<table>
    <tr>
        <th>Author</th>
        <th>Quiz Name</th>
        <th>Description</th>
        <th>Timer</th>
        <th>Is Random</th>
        <th>Display Type</th>
        <th>Corrects Immediately</th>
        <th>Is Practice</th>
    </tr>
    <!-- Sample data rows -->
    <tr>
        <td>1</td>
        <td><a href="quiz.jsp">Quiz 1</a></td>
        <td>Sample description for Quiz 1</td>
        <td>60</td>
        <td>true</td>
        <td>Single Choice</td>
        <td>true</td>
        <td>false</td>
    </tr>
    <tr>
        <td>2</td>
        <td><a href="quiz.jsp">Quiz 2</a></td>
        <td>Another description for Quiz 2</td>
        <td>120</td>
        <td>false</td>
        <td>Multiple Choice</td>
        <td>false</td>
        <td>true</td>
    </tr>
    <!-- Add more rows for other quizzes -->
</table>
</body>
</html>
