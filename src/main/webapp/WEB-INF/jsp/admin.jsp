<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者画面</title>
<style>
body {
	font-family: sans-serif;
	text-align: center;
	padding: 50px;
}

button {
	font-size: 1.5em;
	padding: 10px 30px;
	margin: 20px;
	background-color:#a0d8ef;
	border-radius: 10px;
	border: 2px solid gray;
	box-shadow: 3px 3px 6px rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
<h1>管理者画面</h1>
    <form action="RegisterServlet" method="post">
        <button type="submit" name="action" value="register">新規登録</button>
    </form>
    <form action="RecordListServlet" method="get">
        <button type="submit" name="action" value="WorkHistory">勤務履歴</button>
    </form>
</body>
</html>