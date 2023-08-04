        <%@ page import="java.util.Arrays" %>
<%@ page import="com.example.myproj01.JClass.ChessPlayer" %>
<%@ page import="com.example.myproj01.JClass.Functions" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.util.ArrayList" %>
        <%@ page import="com.mysql.cj.log.Log" %>
        <%--
          Created by IntelliJ IDEA.
          User: 阿飞
          Date: 2023/7/2
          Time: 16:01
          To change this template use File | Settings | File Templates.
        --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //从数据库请求数据
        ArrayList<ChessPlayer> rows = Functions.getAllPlayers();
        ChessPlayer cpr = new ChessPlayer("", username, password, 0);
        boolean flag = rows.contains(cpr);//判断数据库中是否包含playername为username且password为password的玩家
        ChessPlayer cpr1 = null;
        if (flag) {
            cpr1 = rows.get(rows.indexOf(cpr));
            session.setAttribute("player", cpr1);
            request.getRequestDispatcher("mainpage.jsp").forward(request, response);//请求转发
//            response.sendRedirect("mainpage.jsp");//重定向
        }else{
            out.print("<h2>账号或密码错误</h2>");
        }

    %>
    <h2><a href="login.jsp">返回登录</a></h2>
</body>
</html>
