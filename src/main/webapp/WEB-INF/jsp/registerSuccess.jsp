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
        text-align: center;
	　　padding: 50px;
        
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