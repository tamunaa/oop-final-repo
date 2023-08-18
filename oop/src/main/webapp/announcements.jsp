<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>

    <link rel="stylesheet" type="text/css" href="css/announcements.css">

    <title>Website with Announcements</title>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<div class="feed">
    <div class="announcement">
        <h3>Announcement A</h3>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
        <p class="timestamp">Posted on: August 11, 2023</p>
    </div>
    <div class="announcement">
        <h3>Announcement B</h3>
        <p>Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
        <p class="timestamp">Posted on: August 12, 2023</p>
    </div>
    <!-- Add more announcements as needed -->
</div>
<footer>
    <!-- Your footer content here -->
</footer>
</body>
</html>
