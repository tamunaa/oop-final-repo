<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>
    <script src="js/profile.js"></script>
    <link rel="stylesheet" href="css/profile.css">

    <title>Profile</title>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<div class="container mt-5">
    <div class="row">
        <div class="col-md-4">
            <div class="profile--card">
                <div class="profile-header">
                    <h4 id="username"><%= request.getParameter("username")%></h4>
                    <p>john.doe@example.com</p>
                    <p id="adminButton" onclick="toggleAdmin()"><i id="star" class="bi bi-star"></i> admin</p>

                    <p id="editUsername" class="edit-icon" onclick="editUsername()">
                        <i class="bi bi-pencil"></i> Edit Username
                    </p>
                </div>

                <div class="profile-info">
                    <p><strong>Registration Date:</strong> 01.09.2003</p>
                </div>
                <div class="profile-actions" id="actions">
                    <button class="custom-btn2">Send Friend Request</button>
                    <button class="custom-btn2">
                        <i class="bi bi-envelope-at-fill"></i> Send Message
                    </button>
                </div>
            </div>


            <!-- Friends List -->
            <div class="friends-list" style="height: 300px; overflow-y: auto;">
                <h4>Friends</h4>
                <div class="friend-item">
                    <a class="friend-name" href="profile.jsp">Friend 1</a>
                </div>
                <div class="friend-item">
                    <a class="friend-name" href="profile.jsp">Friend 2</a>
                </div>
                <div class="friend-item">
                    <a class="friend-name" href="profile.jsp">Friend 3</a>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <!-- Taken Quizzes -->
            <div class="profile-section">
                <div class="profile-card">
                    <h2>Taken Quizzes</h2>
                    <ul>
                        <li><a href="singlePageQuiz.jsp">Quiz 1</a></li>
                        <li><a href="singlePageQuiz.jsp">Quiz 2</a></li>
                        <li><a href="singlePageQuiz.jsp">Quiz 3</a></li>
                    </ul>
                </div>
            </div>

            <!-- Created Quizzes -->
            <div class="profile-section">
                <div class="profile-card">
                    <h2>Created Quizzes</h2>
                    <ul>
                        <li><a href="singlePageQuiz.jsp">Quiz 1</a></li>
                        <li><a href="singlePageQuiz.jsp">Quiz 2</a></li>
                        <li><a href="singlePageQuiz.jsp">Quiz 3</a></li>
                    </ul>
                </div>
            </div>

            <!-- Achievements -->
            <div class="profile-section">
                <div class="profile-card">
                    <h2>Achievements</h2>
                    <ul>
                        <li>Achievement 1</li>
                        <li>Achievement 2</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    const currentUser = 'JohnDoe';
    const toUser = 'JohnDoe'


    const actions = document.getElementById('actions')
    if (currentUser === toUser) {
        actions.style.display = 'none';
    } else {
        actions.style.display = 'block';
    }
</script>

</body>
</html>
