function toggleNotificationPanel() {
    const notificationPanel = document.getElementById('notificationPanel');
    notificationPanel.style.display = notificationPanel.style.display === 'block' ? 'none' : 'block';

    // Get the location of the notification icon
    const notificationIcon = document.querySelector('.notification-icon');
    const iconRect = notificationIcon.getBoundingClientRect();

    // Adjust the position of the notification panel
    notificationPanel.style.top = `calc(${iconRect.bottom}px + 10px)`;
    notificationPanel.style.left = `calc(${iconRect.left}px - 170px)` // Adjust as needed
}


let isAdmin = true; //bazidan wamova es info
function toggleAdmin() {
    isAdmin = !isAdmin;
    const starIcon = document.getElementById('star');

    if (isAdmin) {
        starIcon.classList.add('filled');
    } else {
        starIcon.classList.remove('filled');
    }
}
