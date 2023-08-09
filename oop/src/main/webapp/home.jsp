<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
<%--    <link rel="stylesheet" href="styles.css"> <!-- Link to your stylesheet -->--%>
    <link rel="stylesheet" href="/path/to/bootstrap.min.css">

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
<jsp:include page="navbar.jsp" />
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

</body>
</html>
