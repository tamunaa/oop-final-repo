<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <link rel="stylesheet" type="text/css" href="css/home.css">

</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<div class="feed">
    <h1 class="text-center">News Feed</h1>

    <div class="feed-container">
        <div class="feed-item">
            <p>
                <strong>Friend Name</strong> took <a href="quiz_page.html">Quiz Name</a> and scored 8/10.
                <a href="user_page.html">View Friend's Profile</a>
            </p>
        </div>
        <div class="feed-item">
            <p>
                <strong>Friend Name</strong> created a new quiz: <a href="quiz_creation.html">Quiz Name</a>.
                <a href="user_page.html">View Friend's Profile</a>
            </p>
        </div>
        <div class="feed-item">
            <p>
                <strong>Friend Name</strong> earned an achievement: "Quiz Enthusiast".
                <a href="user_page.html">View Friend's Profile</a>
            </p>
        </div>
    </div>
</div>
</body>
</html>
