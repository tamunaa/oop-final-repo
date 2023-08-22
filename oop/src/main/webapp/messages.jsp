<%@ page import="dataBase.MessageDAO" %>
<%@ page import="objects.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dataBase.UserDAO" %>
<%@ page import="java.util.ArrayList" %>
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
<%
        MessageDAO messageDAO = (MessageDAO) request.getServletContext().getAttribute("messageDAO");
        UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");

        User currUser = (User) session.getAttribute("currUser");
        int curUserId = currUser.getId();

        List<Integer> interactions = messageDAO.getInteractions(curUserId);
        User[] interactedUsers = new User[interactions.size()];

        for (int i = 0; i < interactions.size(); i++){
                int curId = interactions.get(i);
                interactedUsers[i] = (User) userDAO.getUserByUserId(curId);
        }

%>
<div id="messagediv" class="d-flex justify-content-around">
        <div class="message-container">
                <div class="user-list" id="user-list">
                        <%
                                for(int i = 0; i < interactedUsers.length; i++){
                        %>
                        <div class="user" data-user="<%=interactedUsers[i].getUsername()%>">
                                <%=interactedUsers[i].getUsername()%>
                        </div>

                        <%
                                }
                        %>

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

<script>
        $(document).ready(function() {
                const userList = document.getElementById('user-list');
                const chatMessages = document.getElementById('chat-messages');
                const messageInput = document.getElementById('message-input');
                const sendButton = document.getElementById('send-button');

                userList.addEventListener('click', (event) => {
                        if (event.target.classList.contains('user')) {
                                const users = document.querySelectorAll('.user');
                                users.forEach((user) => {
                                        user.classList.remove('active');
                                });

                                const clickedUser = event.target;
                                clickedUser.classList.add('active');

                                const selectedUser = clickedUser.getAttribute('data-user');
                                updateChat(selectedUser);
                                console.log("userlists emateba listeneri");
                        }
                });

                sendButton.addEventListener('click', () => {
                        const selectedUser = document.querySelector('.user.active').getAttribute('data-user');
                        const messageText = messageInput.value;
                        if (messageText.trim() !== '') {
                                $.ajax({
                                        url: "MessengerServlet", // The URL of your servlet
                                        type: "POST",
                                        data: { selectedUser: selectedUser, messageText: messageText },
                                        success: function(response) {
                                                // Handle success, maybe update the chat interface
                                                updateChat(selectedUser);
                                        },
                                        error: function(error) {
                                                // Handle error
                                                console.error("Error sending message:", error);
                                        }
                                });
                                messageInput.value = '';
                        }
                });



                function updateChat(user) {
                        chatMessages.innerHTML = '<p>Loading messages...</p>';
                        $.ajax({
                                url: "loadMessages.jsp",
                                type: "POST",
                                data: { selectedUser: user },
                                success: function(data) {
                                        chatMessages.innerHTML = data;
                                        chatMessages.scrollTop = chatMessages.scrollHeight;
                                }
                        });
                }

                function sendMessage(user) {
                        const messageText = messageInput.value;
                        if (messageText.trim() !== '') {
                                // Implement your logic to send the message to the server
                                // and update the chat
                                // userChats[user].push(messageText);
                                // updateChat(user);
                                messageInput.value = '';
                        }
                }

                // Additional logic here
        });
</script>




</body>
</html>
