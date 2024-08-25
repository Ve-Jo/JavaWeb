<%--
  Created by IntelliJ IDEA.
  User: vejod
  Date: 24.08.2024
  Time: 17:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String fromServlet = (String)request.getAttribute("fromServlet");
%>

<h1>JSP</h1>
<p>Java Server Pages - технологія створення веб-застосунків на Java</p>
<a href="<%= contextPath %>/hello.jsp">Hello world</a>
<p>
    fromServlet = <%= fromServlet %>
</p>
<%
    // блок коду - за синтаксисом Java
    int x = 10;
    double y = x / .01 ;
    int[] arr = {1,2,3,4,5,6,7};
%>
<p>x = <%= x %></p>
<p>y = <%= y %></p>
<% for(int a : arr) { %>
    <span>element = <%= a %></span> &emsp;
<% } %>