<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<String> logEntries = (List<String>) request.getAttribute("logEntries");
    String error = (String) request.getAttribute("error");
%>
<h1>Log Entries</h1>
<% if (error != null) { %>
    <div class="alert alert-danger" role="alert">
        Error: <%= error %>
    </div>
<% } else if (logEntries != null && !logEntries.isEmpty()) { %>
    <ul class="list-group">
        <% for (String entry : logEntries) { %>
            <li class="list-group-item"><%= entry %></li>
        <% } %>
    </ul>
<% } else { %>
    <p>No log entries found.</p>
<% } %>