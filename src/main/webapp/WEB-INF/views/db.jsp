<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String error = (String)request.getAttribute("error");
    List<String> databases = (List<String>) request.getAttribute("databases");
%>
<h1>Робота з БД</h1>
<ul>
    <li>Встановлюємо СУБД</li>
    <li>Створюємо БД та користувача для неї</li>
    <li>Додаємо залежність до драйвера БД (конектора, mysql-connector-j)</li>
    <li>Див. коментарі у DbServlet</li>
</ul>
<% if(error == null) { %>
    <p>Робота з БД успішна</p>
    <h2>Список таблиць:</h2>
    <% if(databases != null && !databases.isEmpty()) { %>
        <ul>
        <% for(String db : databases) { %>
            <li><%= db %></li>
        <% } %>
        </ul>
    <% } else { %>
        <p>Бази даних не знайдено.</p>
    <% } %>
<% } else { %>
    <b>Виникла помилка: <%= error %></b>
<% } %>