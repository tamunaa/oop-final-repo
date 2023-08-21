<%@ page import="objects.User" %>
<%@ page import="java.util.List" %>
<%@ page import="dataBase.UserDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://kit.fontawesome.com/532c01b704.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="css/admin.css">

    <title>Admin Area</title>

</head>
<body>
<header class="text-center py-4">
    <p class="text-center">Admin Area</p>
    <a class="text-center go-back" href="home.jsp">
        <i class="fa-solid fa-person-walking-arrow-loop-left"></i>
    </a>
</header>

<div class="admin-section scrollable">

    <%
        List<User> userList = ((UserDAO)request.getServletContext().getAttribute("userDAO")).getAllUsers();
//        List<User> quizList = ((UserDAO)request.getServletContext().getAttribute("quizDAO")).getAllUsers();

    %>

    <h2>Manage Users</h2>
    <div>
        <table class="quizzes-table">
            <tbody>
            <%
                for(int i = 0; i < userList.size(); i++){
                    String curUserName =userList.get(i).getUsername();
            %>
            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3"><%=curUserName%></span>
                        <form action="" method=""><i class="fas fa-trash-alt"></i></form>
                    </div>
                </td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
    </div>
</div>


<div class="admin-section scrollable">
    <h2>Manage Quizzes</h2>
    <div>
        <table class="quizzes-table">
            <tbody>
            <%--            <%--%>
            <%--                for(int i = 0; i < quizList.size(); i++){--%>
            <%--                    String curQuizName =quizList.get(i).getUsername();--%>
            <%--            %>--%>
            <%--            <tr>--%>
            <%--                <td>--%>
            <%--                    <div class="d-flex justify-content-between align-items-center">--%>
            <%--                        <span class="me-3"><%=curQuizName%></span>--%>
            <%--                        <form action="" method=""><i class="fas fa-trash-alt"></i></form>--%>
            <%--                    </div>--%>
            <%--                </td>--%>
            <%--            </tr>--%>
            <%--            <%--%>
            <%--                }--%>
            <%--            %>--%>
            </tbody>
        </table>
    </div>
</div>


<section class="admin-section p-4">
    <h2 class="mb-4">Make Announcements</h2>
    <form id="announcement-form">
        <label for="announcement-text">Announcement:</label>
        <textarea id="announcement-text" class="form-control mb-3" required></textarea>

        <form action="" method="">
            <button type="submit" class="btn btn-primary">Make Announcement</button>
        </form>
    </form>
</section>


</body>
</html>
