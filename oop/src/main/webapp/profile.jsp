<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/profile.css">


    <script src="js/script.js"></script>
    <title>Profile</title>

</head>
<body>
    <section>
        <jsp:include page="navbar.jsp" />
    </section>

    <div id="wrapper" class="wrapper d-flex justify-content-evenly">
        <div id="page" class="page">

        <div class="profile">
            <div id="user-profile" class="text-center">
                <h1>User Profile</h1>
            </div>
            <div class="user-details">
                <div class="user-info">
                    <div class="user-info-item">
                        <span class="label">Username:</span>
                        <span class="value">JohnDoe</span>
                    </div>
                    <div class="user-info-item">
                        <span class="label">Email:</span>
                        <span class="value">JohnDoe@gmail.com</span>
                    </div>
                    <div class="user-info-item">
                        <span class="label">Account Created:</span>
                        <span class="value">01.09.2003</span>
                    </div>
                </div>
                <div class="user-actions">
                    <button class="btn btn-primary" id="send-friend-request">Send Friend Request</button>
                    <button class="btn btn-primary" id="send-message">
                        <i class="bi bi-envelope-at-fill"></i> send message
                    </button>
                </div>
            </div>
        </div>

        <br><br>
        <div>
            <div id="activities" class="interactions d-sm-flex justify-content-around border border-secondary p-2 rounded">
                    <div><a class="btn btn-primary btn-lg" href="#" onclick="showCreatedQuizzes()">
                        <i class="bi bi-file-earmark-richtext"></i> Created Quizzes</a></div>
                    <div><a class="btn btn-primary btn-lg" href="#" onclick="showTakenQuizzes()">
                        <i class="bi bi-check2-circle"></i>  Taken Quizzes</a></div>
                    <div><a class="btn btn-primary btn-lg" href="#" onclick="showAchievements()">
                        <i class="bi bi-trophy"></i>  Achievements</a></div>
            </div>

            <div>
                <div class="d-flex justify-content-around">
                    <div id="dynamic-content" class="d-flex justify-content-start">
                        <%--dinamic content--%>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <script>
        const currentUser = 'asdf'; // Replace with actual current user's username
        const toUser = 'JohnDoe'

        const sendFriendRequestButton = document.getElementById('send-friend-request');
        const sendMessageButton = document.getElementById('send-message');

        if (currentUser === toUser) {
            // Viewing own profile
            sendFriendRequestButton.style.display = 'none';
            sendMessageButton.style.display = 'none';
        } else {
            // Viewing someone else's profile
            sendFriendRequestButton.style.display = 'block';
            sendMessageButton.style.display = 'block';
        }
    </script>
</body>
</html>



