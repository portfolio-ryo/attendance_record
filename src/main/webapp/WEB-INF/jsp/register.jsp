<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
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
<h2>新規登録</h2>
<%
    String error = (String)request.getAttribute("error");
    if (error != null && !error.isEmpty()) {
%>
    <p style="error-message"><%= error %></p>
<%
    }
%>

    <form action="RegisterServlet" method="post">
        <label for="code">ユーザー名：</label>
        <input type="text" id="name" name="name" placeholder="例：山田太郎"required><br><br>

        <label for="password">パスワード：</label>
        <input type="password" id="plainPass" name="plainPass" placeholder="4文字以上にしてください" required minlength="4"><br><br>
        
        <label><input type="checkbox" name="isAdmin" value="true">
        管理者として登録</label><br><br>
        
        <button type="submit">登録</button>
        <button type="button" onclick="history.back()">戻る</button>
    </form>
</div>
</body>
</html>