<%--
  Created by IntelliJ IDEA.
  User: 阿飞
  Date: 2023/7/10
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        session.invalidate();
    %>
    <jsp:forward page="login.jsp"></jsp:forward>
</body>
</html>
