<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Website with Announcements</title>
    <style>
        /* Your existing styles here */
    </style>
</head>
<body>
<jsp:include page="navbar.jsp" />

<%--<nav>--%>
<%--    <ul class="navbar">--%>
<%--        <li><a href="/">Home</a></li>--%>
<%--        <li><a href="/about">About</a></li>--%>
<%--        <li><a href="/contact">Contact</a></li>--%>
<%--        <li><a href="/announcements">Announcements</a></li>--%>
<%--    </ul>--%>
<%--</nav>--%>

<h1>Announcements</h1>
<!-- Your main content here -->
<div class="announcements">
    <ul>
        <!-- Loop through your announcements and render them -->
        <li>Announcement A: Lorem ipsum dolor sit amet, consectetur adipiscing elit.</li>
        <li>Announcement B: Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</li>
        <!-- Add more announcements as needed -->
    </ul>
</div>

<footer>
    <!-- Your footer content here -->
</footer>
</body>
</html>
