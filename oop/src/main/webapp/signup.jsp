<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <link rel="stylesheet" type="text/css" href="css/authentication.css">--%>
<%--    <link rel="stylesheet" type="text/css" href="css/styles.css">--%>
<%--    <title>Main Page</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="form">--%>
<%--    <form action="java/servlets/signUp" method="POST">--%>
<%--        <label for="newUsername">Username</label>--%>
<%--        <input type="text" id="newUsername" name="newUsername" placeholder="Choose a username" required>--%>

<%--        <label for="newPassword">Password</label>--%>
<%--        <input type="password" id="newPassword" name="newPassword" placeholder="Choose a password" minlength="6" required>--%>
<%--        &lt;%&ndash;%>
<%--            if (request.getAttribute("userCreated") != null) {--%>
<%--                out.print("User created successfully");--%>
<%--            } else if (request.getAttribute("usernameIsInUse") != null) {--%>
<%--                out.println("Username is in use.");--%>
<%--            }--%>
<%--        %>--%>
<%--        <button type="submit">Sign Up</button>--%>
<%--    </form>--%>
<%--    <div><a href="index.jsp">go back to sign in</a></div>--%>
<%--</div>--%>

<%--<div class="form">--%>
<%--    <form action="java/servlets/signIn" method="POST">--%>
<%--        <label for="username">Username</label>--%>
<%--        <input type="text" id="username" name="username" placeholder="Enter your username">--%>

<%--        <label for="password">Password</label>--%>
<%--        <input type="password" id="password" name="password" placeholder="Enter your password">--%>

<%--        <%--%>
<%--            if (request.getAttribute("loginError") != null) {--%>
<%--                out.println("Invalid username or password. Please try again.");--%>
<%--            }--%>
<%--        %>--%>
<%--        <button type="submit">Sign In</button>--%>
<%--    </form>--%>
<%--    <div><a href="signup.jsp">sign up</a></div>--%>
<%--</div>--%>

<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="css/authentication.css">

    <title>Main Page</title>
</head>
<body>
<div class="form">
    <form action="java/servlets/signUp" method="POST">
        <label for="newUsername">Username</label>
        <input type="text" id="newUsername" name="newUsername" placeholder="Choose a username" required>

        <label for="newPassword">Password</label>
        <input type="password" id="newPassword" name="newPassword" placeholder="Choose a password" minlength="6" required>
        <%
            if (request.getAttribute("userCreated") != null) {
                out.print("User created successfully");
            } else if (request.getAttribute("usernameIsInUse") != null) {
                out.println("Username is in use.");
            }
        %>
        <button type="submit">Sign Up</button>
    </form>
<div>

<div><a href="index.jsp">go back to sign in</a></div>
</div>


</body>
</html>

