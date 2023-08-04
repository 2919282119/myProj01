<%--
  Created by IntelliJ IDEA.
  User: 阿飞
  Date: 2023/7/3
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.example.myproj01.JClass.Functions"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLOutput" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.myproj01.JClass.ChessPlayer" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .topbar{
            position: fixed;
            height:40px;
            width: 100%;
            left:0px;
            top:0px;
            text-align: center;
            background-color: #e8efef;
            z-index: 10;
        }
        .showtb{
            position: relative;
            width: 90%;
            top:45px;
            left:5%;
            z-index: 0;
        }
        .showtb tr th,td{
            height:30px;
            border-bottom:1px solid #ccc;
            border-collapse: collapse;
            text-align: center;
        }
        .id{
            width: 150px;
        }
        .rank{
            width: 150px;
        }
        .target{
            background-color: #eee;
        }
        .else{

        }
    </style>
<body>
        <%
            ChessPlayer cp = (ChessPlayer)session.getAttribute("player");
        %>
        <%-- 这里似乎可以用modena.css --%>
        <h2 class="topbar">
            <%="亲爱的"+cp.getPlayername()+"!恭喜您登陆成功！您的积分为:"+cp.getPoint()+"分,下面是所有玩家的积分和排名："%>
            <a href="logout.jsp">Log Out</a>
        </h2>
        <table class="showtb">
            <thead>
            <th>名次</th>
            <th>玩家编码</th>
            <th>玩家名称</th>
            <th>积分</th>
            </thead>
            <tbody>
        <%
            ArrayList<ChessPlayer> list = Functions.getAllPlayers();
            list.sort(new Comparator<ChessPlayer>() {
                @Override
                public int compare(ChessPlayer o1, ChessPlayer o2) {
                    return o2.getPoint()-o1.getPoint();
                }
            });

            for (int i = 0; i < list.size(); i++) {//这是一种在jsp中渲染的方法，先写一半花括号
        %>
            <tr class=<%=list.get(i).equals(cp)?"target":"else"%>>
                <td class="rank"><%=i+1%></td>
                <td class="id"><%=list.get(i).getPlayerid()%></td>
                <td><%=list.get(i).getPlayername()%></td>
                <td><%=list.get(i).getPoint()%></td>
            </tr>
        <%
            }//另一半在这里
        %>

            </tbody>
        </table>
</body>
</html>
