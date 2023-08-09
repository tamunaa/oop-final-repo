<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">


    <title>Website with Announcements</title>
    <style>
        /* Your existing styles here */
    </style>
</head>
<body>
    <section>
        <jsp:include page="navbar.jsp" />
    </section>

    <div>
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
    </div>
<footer>
    <!-- Your footer content here -->
</footer>
</body>
</html>
