<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <jsp:forward page="login.jsp"></jsp:forward>
    <%--跳到另一个jsp的方法--%>
<%--    <form action="hello" method="post">--%>
<%--        <button>Click Me</button>--%>
<%--    </form>--%>
</body>
</html>