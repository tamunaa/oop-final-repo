<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
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
