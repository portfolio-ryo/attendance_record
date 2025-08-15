<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>出勤退勤画面</title>
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
	<h1>出勤・退勤ボタン</h1>
	<%
    String message = (String) request.getAttribute("message");
    %>
	<%if (message != null) {%>
	<p style="color: red;"><%=message%></p>
	<%}%>
	<form action="ClockInServlet" method="post">
		<button type="submit">出勤</button>
	</form>
	<form action="ClockOutServlet" method="post">
	    <button type="submit">退勤</button>
	</form>
	<form action="LogoutServlet" method="post">
        <button type="submit">ログアウト</button>
    </form>
</body>
</html>