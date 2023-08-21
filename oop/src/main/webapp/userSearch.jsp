<%@ page import="objects.Quiz" %>
<%@ page import="objects.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
აქაც იგივე რაც search jsp-ში თავიდან
--%>
<% User user = (User) request.getAttribute("searchedUser");
    UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");
%>
</head>
<body>
<div class="d-flex flex-column justify-content-center w-100 h-100"></div>
<div class="form">
    <h1>Search Results: </h1>
    <table class="topscorers">
        <tr>
            <th>Username</th>
        </tr>
        <%
            out.println("<tr><td>" + "<a href=\"./profile?user=" + user + "\">" + user.getUsername() + "</a>" + "</td></tr>");

        %>
    </table>
</div>
</body>
</html>