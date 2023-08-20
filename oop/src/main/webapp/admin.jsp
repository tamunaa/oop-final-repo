<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Area</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/admin.css">
    <script src="https://kit.fontawesome.com/532c01b704.js" crossorigin="anonymous"></script>
</head>
<body>
<header class="text-center py-4">
    <p class="text-center">Admin Area</p>
    <a class="text-center go-back" href="home.jsp">
        <i class="fa-solid fa-person-walking-arrow-loop-left"></i>
    </a>
</header>

<div class="admin-section scrollable">
    <h2>Manage Users</h2>
    <div>
        <table class="quizzes-table">
            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">User 1</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">User 2</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">User 3</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>


<div class="admin-section scrollable">
    <h2>Manage Quizzes</h2>
    <div>
        <table class="quizzes-table">
            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">Quiz 1</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>

            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">Quiz 2</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="d-flex justify-content-between align-items-center">
                        <span class="me-3">Quiz 3</span>
                        <i class="fas fa-trash-alt" onclick="deleteQuiz('quiz1')"></i>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>


<section class="admin-section p-4">
    <h2 class="mb-4">Make Announcements</h2>
    <form id="announcement-form" onsubmit="makeAnnouncement(); return false;">
        <label for="announcement-text">Announcement:</label>
        <textarea id="announcement-text" class="form-control mb-3" required></textarea>
        <button type="submit" class="btn btn-primary">Make Announcement</button>
    </form>
</section>

<script>
    function deleteQuiz(quizId) {
        // Replace with actual logic to delete the quiz with the provided ID
        console.log('Deleted quiz:', quizId);
    }

    function deleteUser(userId) {
        // Replace with actual logic to delete the user with the provided ID
        console.log('Deleted user:', userId);
    }

    function makeAnnouncement() {
        const announcementText = document.getElementById('announcement-text').value;
        // Replace with actual logic to create an announcement
        console.log('Announcement:', announcementText);
    }
</script>

</body>
</html>

