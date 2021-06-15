<%@ page import="ru.javawebinar.topjava.web.SecurityUtil" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="ru">
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Users</h2>
<p>Current user: <%=SecurityUtil.authUserId() %>
</p>
</body>
</html>