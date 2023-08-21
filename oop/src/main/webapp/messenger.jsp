<%@ page import="objects.User" %>
<%@ page import="objects.messages.Message" %>
<%@ page import="dataBase.MessageDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="dataBase.UserDAO" %><%--

<a href="<%=request.getContextPath()%>/MessengerServlet?sender_id=<%=currUserId%>" class="btn btn-outline-primary">Message</a>
ეს იქნება საჭირო პროფილის გვერდზე

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
//    if (request.getSession().getAttribute("currUser") == null) response.sendRedirect("index.jsp");
    MessageDAO messageDAO = (MessageDAO) request.getServletContext().getAttribute("messageDAO");
    UserDAO userDAO = (UserDAO) request.getServletContext().getAttribute("userDAO");

    User currUser = (User)request.getSession().getAttribute("currUser");
//    User user = (User) request.getAttribute("user");
    User user = userDAO.getUserByUserId(2);
//    String username = "";
//    username = user.getUsername();
    List<Message> messageList = null;
    messageList = messageDAO.getChat(currUser.getId(), user.getId(),true);

%>
<%-- აქ navbar
--%>
<div class="chat_window">

    <div class="bottom_wrapper clearfix">
        <form action='MessengerServlet' method='POST'>
            <div class="message_input_wrapper">
                <input name="messageText" type="text" class="message_input" placeholder="Type your message here...">
                <input name="senderID" type="hidden" value="<%=currUser.getId()%>">
                <input id="recieverID" name="recieverID" type="hidden" value="<%=user.getId()%>">
            </div>
            <input type="submit" class="send_message" value="Send">
        </form>
    </div>


    <ul class="messages">

<%
    if (messageList != null) {
        for(Message m : messageList) {
            if (m.getSenderID() == user.getId()) {
                out.println("<li class='right message'>");
            } else {
                out.println("<li class='left message'>");
            }
            out.println("<div class='avatar'></div>");
            out.println("<div class='text_wrapper'>");
            out.println("<div class='text'>" + m.getContent() + "</div></div></li>");
        }
    }
%>