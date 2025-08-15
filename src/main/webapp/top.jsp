<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログインページ</title>
<style>
    body {
        font-family: sans-serif;
    }
    .form-container {
        width: 300px;
        margin: 100px auto; 
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color:#a0d8ef;
        box-shadow: 2px 2px 10px rgba(0,0,0,0.05);
    }
    label {
        display: block;
        margin-bottom: 5px;
    }
    input[type="text"], input[type="password"] {
        width: 100%;
        padding: 8px;
        margin-bottom: 15px;
        box-sizing: border-box;
    }
    .error-message {
        color: red;
        margin-bottom: 10px;
    }
</style>
</head>
<body>
<div class="form-container">
<h2>ログイン</h2>
<%
    String error = request.getParameter("error");
    if (error != null && !error.isEmpty()) {
%>
    <p style="color:red;"><%= java.net.URLDecoder.decode(error, "UTF-8") %></p>
<%
    }
%>
<%
    String message = request.getParameter("message");
    if (message != null && !message.isEmpty()) {
%>
    <p style="color:green;"><%= java.net.URLDecoder.decode(message, "UTF-8") %></p>
<%
    }
%>

    <form action="LoginServlet" method="post">
        <label for="code">ユーザーID：</label>
        <input type="text" id="usercode" name="usercode" placeholder="例：山田太郎" required><br><br>

        <label for="password">パスワード：</label>
        <input type="password" id="plainPass" name="plainPass" placeholder="4文字以上にしてください" required minlength="4"><br><br>

        <button type="submit" name="action" value="attendance">従業員画面</button>
        <button type="submit" name="action" value="admin">管理者画面</button>
    </form>
</div>    
</body>
</html>