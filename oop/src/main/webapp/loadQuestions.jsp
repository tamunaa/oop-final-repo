<%@ page import="objects.questions.Question" %>
<%@ page import="java.security.Timestamp" %>
<%@ page import="java.util.List" %>

<%
    List<Question> questions = (List<Question>) request.getSession().getAttribute("questions");
    int total = questions.size();
    int index = Integer.parseInt(request.getParameter("index"));
    request.setAttribute("index", index);
    if (index>=total){
        index%=total;
    }
    out.print("<h1> Question "+(index+1) +"/" + total +"</h1>");

    String type = questions.get(index).getQuestionType();
    String path = "questions/" + type + ".jsp";
    request.setAttribute("current", questions.get(index));
%>
<jsp:include page="<%= path %>" />
