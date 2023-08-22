<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
        <!-- Other styles and scripts -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="js/navbar.js"></script>
        <link rel="stylesheet" href="css/message.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">

        <title>Quiz Messaging</title>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<div id="messagediv" class="d-flex justify-content-around">
        <div class="message-container">
                <div class="user-list" id="user-list">
                        <div class="user" data-user="User 1">User 1</div>
                        <div class="user" data-user="User 2">User 2</div>
                        <div class="user" data-user="User 3">User 3</div>
                </div>
                <div class="chat-container" id="chat-container">
                        <div class="chat-messages" id="chat-messages">
                                <!-- Chat messages will appear here -->
                        </div>
                        <div class="input-container">
                                <input type="text" id="message-input" placeholder="Type your message...">
                                <button id="send-button">Send</button>
                        </div>
                </div>
        </div>
</div>
<script src="js/messages.js"></script>

</body>
</html>
