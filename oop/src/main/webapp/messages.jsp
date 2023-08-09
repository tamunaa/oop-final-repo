<!DOCTYPE html>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/navbar.css">
        <link rel="stylesheet" type="text/css" href="css/base.css">

        <title>Quiz Messaging</title>
        <style>
                .container {
                        display: flex;
                        height: 50vh;
                }
                .user-list {
                        width: 200px;
                        background-color: #2a1a84;
                        padding: 10px;
                        overflow-y: auto;
                }
                .user {
                        cursor: pointer;
                        padding: 10px;
                        border-bottom: 1px solid #cd1f1f;
                }
                .chat-container {
                        flex: 1;
                        display: flex;
                        flex-direction: column;
                        padding: 10px;
                        background-color: #3dc30d;
                }
                .chat-messages {
                        flex: 1;
                        overflow-y: auto;
                        background-color: #e0098e; /* Light blue color */
                }
                .input-container {
                        display: flex;
                        align-items: center;
                        padding-top: 10px;
                }
                #message-input {
                        flex: 1;
                        padding: 5px;
                }
                #send-button {
                        margin-left: 10px;
                        background-color: #9200cc; /* Green color */
                        color: #000000;
                        border: none;
                        padding: 5px 10px;
                        border-radius: 5px;
                        cursor: pointer;
                }

                /* Rest of your styles */
        </style>
</head>
<body>
<jsp:include page="navbar.jsp" />


<h1>Quiz Messaging</h1>
<div class="container">
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

<script src="js/messages.js"></script>

</body>
</html>
