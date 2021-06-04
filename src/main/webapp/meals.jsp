<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">

    <title>Meals list</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>

    <jsp:useBean id="mealToList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealToList}" var="mealTo">
        <tr data-mealExcess="<c:out value="${mealTo.excess ? 'true' : 'false'}" />">
            <td><c:out value="${mealTo.dateTime.format(DateTimeFormatter.ofPattern(\"dd-MM-yyyy HH:mm\"))}"/></td>
            <td><c:out value="${mealTo.description}"/></td>
            <td><c:out value="${mealTo.calories}"/></td>
            <td><a href="">Update</a></td>
            <td><a href="">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
