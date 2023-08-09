function showCreatedQuizzes() {
    const dynamicContent = document.getElementById('dynamic-content');
    dynamicContent.innerHTML = `
        <ul>
            <li>Quiz 1</li>
            <li>Quiz 2</li>
            <!-- Add more created quizzes here -->
        </ul>
    `;
}

function showTakenQuizzes() {
    console.log("showAchievements taken quizzes is called!"); // Add this line
    const dynamicContent = document.getElementById('dynamic-content');
    dynamicContent.innerHTML = `
        <ul>
            <li>Quiz A</li>
            <li>Quiz B</li>
            <!-- Add more taken quizzes here -->
        </ul>
    `;
}

function showAchievements() {
    console.log("showAchievements function is called!"); // Add this line

    const dynamicContent = document.getElementById('dynamic-content');
    dynamicContent.innerHTML = `
        <ul>
            <li>Achievement A</li>
            <li>Achievement B</li>
        </ul>
    `;
}