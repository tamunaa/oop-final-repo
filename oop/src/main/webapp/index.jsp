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
    <form action="java/servlets/signIn" method="POST">
    <label for="username">Username</label>
    <input type="text" id="username" name="username" placeholder="Enter your username">

    <label for="password">Password</label>
    <input type="password" id="password" name="password" placeholder="Enter your password">

    <%
        if (request.getAttribute("loginError") != null) {
            out.println("Invalid username or password. Please try again.");
        }
    %>
    <button type="submit">Sign In</button>
    </form>
    <div><a href="signup.jsp">sign up</a></div>
</div>

</body>
</html>

