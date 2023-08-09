<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">

    <style>
        /* Add your styles here */
        .feed-container {
            max-height: 400px; /* Adjust the height as needed */
            overflow-y: auto;
            border: 1px solid #ccc;
            padding: 10px;
        }

        .feed-item {
            margin-bottom: 10px;
        }
    </style>

</head>
<body>
<section>
    <jsp:include page="navbar.jsp" />
</section>

<div class="feed">
    <h1>Home Page Feed</h1>

    <h2>Friend's Recent Activities</h2>
    <div class="feed-container">
    <ul>
        <li>
            <strong>Friend Name</strong> took <a href="quiz_page.html">Quiz Name</a> and scored 8/10.
            <a href="user_page.html">View Friend's Profile</a>
        </li>
        <li>
            <strong>Friend Name</strong> created a new quiz: <a href="quiz_creation.html">Quiz Name</a>.
            <a href="user_page.html">View Friend's Profile</a>
        </li>
        <li>
            <strong>Friend Name</strong> earned an achievement: "Quiz Enthusiast".
            <a href="user_page.html">View Friend's Profile</a>
        </li>

        <!-- Add more activity entries as needed -->
    </ul>
    </div>
</div>

</body>
</html>
