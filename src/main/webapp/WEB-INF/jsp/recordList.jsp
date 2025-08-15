<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, model.Record" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>勤務記録</title>
<style>
    table {
        border-collapse: collapse;
    }
    th {
        border: 1px solid #ccc;
        padding: 8px;
        background-color: #a0d8ef;
    }
</style>
</head>
<body>
<h1>勤務記録</h1>

<table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>名前</th>
            <th>勤務日</th>
            <th>出勤時間</th>
            <th>退勤時間</th>
        </tr>
        <%
            List<Record> list = (List<Record>) request.getAttribute("recordList");
            if (list != null && !list.isEmpty()) {
                for (Record rec : list) {
        %>
        <tr>
            <td><%= rec.getUserCode() %></td>
            <td><%= rec.getUserName() %></td>
            <td><%= rec.getWorkDate() %></td>
            <td><%= rec.getClockIn() %></td>
            <td><%= rec.getClockOut() %></td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4">データがありません</td>
        </tr>
        <% } %>
    </table>
</body>
</html>