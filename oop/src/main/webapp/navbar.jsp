<%@ page contentType="charset=UTF-8" %>
<nav class="navbar">
    <div class="container">
        <div class="d-flex justify-content-between mb-3">
            <form class="d-flex">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search"></input>
                <button class="btn btn-outline-secondary" type="submit">Search</button>
            </form>
        </div>


        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link" href="home.jsp">
                    <i class="bi bi-house-door"></i> Home
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="profile.jsp">
                    <i class="bi bi-person"></i> Profile
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="announcements.jsp">
                    <i class="bi bi-megaphone"></i> Announcements
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="messages.jsp">
                    <i class="bi bi-envelope"></i> Messages
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="popularQuizzes.jsp">
                    <i class="bi bi-star"></i> Popular Quizzes
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="recentQuizzes.jsp">
                    <i class="bi bi-clock"></i> Recent Quizzes
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">
                    <i class="bi bi-box-arrow-right"></i> Log Out
                </a>
            </li>
        </ul>
    </div>
</nav>
