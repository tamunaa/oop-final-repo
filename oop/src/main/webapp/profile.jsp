<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">


    <script src="js/script.js"></script>
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">

    <title>Profile</title>
</head>
<body>
    <section>
        <jsp:include page="navbar.jsp" />
    </section>

    <div class="page">
        <div class="d-flex flex-column  d-flex justify-content-evenly ">
            <div class="profile d-flex flex-column  d-flex justify-content-evenly">
                <div class="d-flex justify-content-center"><h1>User Profile</h1></div>
                <div><h2>Username: JohnDoe</h2></div>
                <div class="actions d-flex justify-content-evenly">
                    <button id="send-friend-request">Send Friend Request</button>
                    <button id="send-message">Send Message</button>
                </div>
            </div>
            <div><h2>Interactions</h2></div>
            <div class="interactions d-flex justify-content-around">
                    <div><a href="#" onclick="showCreatedQuizzes()">Created Quizzes</a></div>
                    <div><a href="#" onclick="showTakenQuizzes()">Taken Quizzes</a></div>
                    <div><a href="#" onclick="showAchievements()">Achievements</a></div>
            </div>

            <div class="d-flex justify-content-around">
                <div id="dynamic-content" class="d-flex justify-content-start">
                    <%--dinamic content--%>
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
