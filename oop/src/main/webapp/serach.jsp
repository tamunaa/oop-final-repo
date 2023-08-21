
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--
 navbar here or smth just like in profile page

 <a href="/search.jsp">Search</a>

 line above will be in this section (in profile page)
--%>

</style>
</head>
<body>
<div class="d-flex flex-column justify-content-center w-100 h-100"></div>
<div class="form">
    <form id="form" action="SearchServlet" method="GET">
        <input type="text" name="searchInput" id="searchInput"><br><br>
<%--        <button class="button1" style="vertical-align:middle" name="type" type="submit" value="user"><span>Search Users </span>--%>
<%--        </button>--%>
        <button class="button2" style="vertical-align:middle" name="type" type="submit" value="quizName"><span>Search Quizzes By Name </span>
        </button>
        <button class="button3" style="vertical-align:middle" name="type" type="submit" value="quizTag"><span>Search Quizzes By Tag </span>
        </button>
    </form>
</div>
</body>
</html>
<style>

    <%--
buttons style here or smth
    --%>


