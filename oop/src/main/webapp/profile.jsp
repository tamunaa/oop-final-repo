<%@ page import="objects.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dataBase.FriendsDAO" %>
<%@ page import="dataBase.UserDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    <script src="js/navbar.js"></script>
    <script src="js/profile.js"></script>
    <link rel="stylesheet" href="css/profile.css">

    <title>Profile</title>
</head>
<body>

<jsp:include page="navbar.jsp" />
<jsp:include page="notificationbar.jsp" />

<%
    User currUser = (User) session.getAttribute("currUser");
    boolean isAdmin = currUser.isAdmin();
    boolean isSelf = Boolean.parseBoolean(request.getParameter("self"));

    if (!isSelf){
        String name = request.getParameter("username");
        UserDAO dao = (UserDAO) request.getServletContext().getAttribute("userDAO");
        currUser = dao.getUserByUsername(name);
    }
%>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-4">
            <div class="profile--card">
                <div class="profile-header">
                    <h4 id="username"><%= currUser.getUsername()%> </h4>
                    <p><%= currUser.getEmail()%></p>

                    <%
                        if(!isSelf){
                    %>
                            <%
                                if (currUser.isAdmin()){
                            %>
                                <p id="adminButton"><i name="star" class="star bi bi-star filled"></i> admin</p>
                            <%
                                }else{
                            %>
                                <p id="adminButton"><i name="star" class="star bi bi-star"></i> admin</p>
                            <%
                                }
                            %>

                            <%
                                if (isAdmin){
                            %>
                                <script>
                                    const currentUser = '<%= currUser.getUsername()%>';
                                </script>
                                <script src="js/adminStar.js"></script>
                            <%
                                }
                            %>
                    <%
                       }else if (isAdmin){
                    %>
                        <p id="adminButton"><i name="star" class="star bi bi-star filled"></i> admin</p>
                    <%
                        }else{
                    %>
                        <p id="adminButton"><i name="star" class="star bi bi-star"></i> admin</p>
                    <%
                        }
                    %>

                    <p id="editUsername" class="edit-icon" onclick="editUsername()">
                        <i class="bi bi-pencil"></i> Edit Username
                    </p>
                </div>

                <div class="profile-info">
                    <p><strong>Registration Date:</strong> <%= currUser.getDate()%></p>
                </div>
                <div class="profile-actions" id="actions">
                    <button class="custom-btn2">Send Friend Request</button>
                    <button class="custom-btn2">
                        <i class="bi bi-envelope-at-fill"></i> Send Message
                    </button>
                </div>
            </div>


            <%List<User> friends = ((FriendsDAO)request.getServletContext().getAttribute("friendsDAO")).getAllFriends(currUser);%>
            <div class="friends-list" style="height: 300px; overflow-y: auto;">
                <h4>Friends</h4>
                <%for(int i = 0; i < friends.size(); i++){
                    String friendName = friends.get(i).getUsername();
                    String path = "profile.jsp?self=false&&username="+friendName;
                %>
                    <div class="friend-item">
                        <a class="friend-name" href= "<%=path%>"><%=friendName%></a>
                    </div>
                <%}%>
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
    const currentUser = '<%= currUser.getUsername()%>';
    const toUser = 'tamar1'

    const actions = document.getElementById('actions')
    if (currentUser === toUser) {
        actions.style.display = 'none';
    } else {
        actions.style.display = 'block';
    }
</script>

</body>
</html>


