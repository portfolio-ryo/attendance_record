<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録完了</title>
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
    .message {
        color: green;
        margin-bottom: 10px;
    }
</style>
</head>
<body>
<h1>新規登録完了</h1>
<%
    String message = (String)request.getAttribute("message");
    if (message != null && !message.isEmpty()) {
%>
    <p style="message;"><%= message %></p>
<%
    }
%>
<a href="top.jsp">トップページ</a>
</body>
</html>