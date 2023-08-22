<%@ page import="objects.messages.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="objects.messages.ChallengeMessage"%>

<%
    Boolean[] notifications = new Boolean[2];
    notifications[0] = false;
    notifications[1] = true;

%>

<div class="notification-wrapper">
    <div class="notification-panel" id="notificationPanel">

        <%for (int i = 0; i < notifications.length; i++){

        %>

        <%if(notifications[i]){%>
            <form class="notification">You have a new friend request from <%=i%>
                <button> accept </button>
                <button> reject </button>
            </form>

        <%}else{%>
            <div class="notification">John commented on youuuuuuuuur post</div>
        <%}
        }%>
    </div>
</div>
