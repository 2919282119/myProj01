<%--
  Created by IntelliJ IDEA.
  User: 阿飞
  Date: 2023/7/2
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        button{
            width: 150px;
            height: 40px;
            color:#fff;
            font-size: 1em;
            font-weight: bold;
            background-color: #3a3a3a;
            border-radius: 10px;
            position: relative;
            z-index: 1;
        }
        button::before{
            content: "";
            width: 158px;
            height:48px;
            position: absolute;
            top:-4px;
            left:-4px;
            border-radius: 10px;
            transition:all 0.35s;
            z-index:-1;
            background:linear-gradient(45deg,red,orange,yellow,green,cyan,blue,purple,red);
            filter: blur(5px);
            background-size: 1000%;
            animation: animate 40s linear infinite;
            opacity: 0;
        }
        button::after{
            content: "";
            position: absolute;
            left:0;
            top:0;
            width: 100%;
            height:100%;
            background:#3a3a3a;
            border-radius: 10px;
            z-index: -1;
            opacity: 1;
        }
        button:hover::before{
            opacity: 1;
        }
        button:active::after{
            opacity: 0;
        }
        button:active{
            color:#bebbbb;
        }
        @keyframes animate {
            0%{
                background-position: 0 0;
            }
            100%{
                background-position: 1000% 0;
            }

        }

        .board{
            width: 500px;
            height: 300px;
            border-radius: 10px;
            border:4px solid #343434;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-flow: column;
            font-size: 18px;
            margin:80px auto 0px;
            background-color: rgba(192, 194, 194, 0.881);
        }

        .board div{
            height: 50px;
        }
        .board div input{
            height: 24px;
            border: none;
            border-radius: 4px;
        }
        .title{
            font-size: 28px;
            color: rgb(26, 26, 26);
            font-family:cursive;
        }
    </style>
</head>
<body>
    <form class='board' action="checklog.jsp" method="post">
        <div class='title'>*五子棋积分管理系统*</div>
        <div>
            用户名：<input type="text" name="username" />
        </div>
        <div>
            密码：&nbsp;&nbsp; <input type="text" name="password" />
        </div>
        <button>登录</button>
    </form>
</body>
</html>
