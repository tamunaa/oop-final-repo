function toggleEditUsername() {
    const usernameDisplay = document.getElementById('usernameDisplay');
    const usernameInput = document.getElementById('usernameInput');
    const editButton = document.getElementById('editUsernameButton');

    if (usernameDisplay.style.display === 'none') {
        usernameDisplay.style.display = 'block';
        usernameInput.style.display = 'none';
        editButton.textContent = 'Edit';
    } else {
        usernameDisplay.style.display = 'none';
        usernameInput.style.display = 'block';
        editButton.textContent = 'Cancel';
    }
}

let lastUserName;
function editUsername() {
    const usernameElement = document.getElementById('username');
    const currentUsername = usernameElement.textContent;
    lastUserName = currentUsername;

    usernameElement.innerHTML = `
        <div class="changes">
            <input style="outline: none" type="text" id="newUsername" value="${currentUsername}">
            <button style="border-color: white" class="update-button" onclick="saveUsername()">Save</button>
            <button style="border-color: white" class="update-button" onclick="cancelEdit()">Cancel</button>
        </div>
    `;

    const newUsernameInput = document.getElementById('newUsername');
    newUsernameInput.focus();
}

function saveUsername() {
    const newUsernameInput = document.getElementById('newUsername');
    const newUsername = newUsernameInput.value;

    console.log('new user namee ', newUsername)

    // Update the username
    const usernameElement = document.getElementById('username');
    usernameElement.textContent = newUsername;
    lastUserName = newUsername;

    // TODO: Implement logic to save the new username to the server (e.g., via AJAX)

    // Revert the username element back to normal
    const adminButton = document.getElementById('adminButton');
    const starIcon = document.getElementById('star');
    adminButton.style.display = 'block';
    starIcon.classList.add('bi-star-fill');

    // Clear the edit window
    // cancelEdit();
}

function cancelEdit() {
    const usernameElement = document.getElementById('username');
    usernameElement.innerHTML = lastUserName; // Restore the original username
}
