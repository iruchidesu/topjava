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
<p><a href="meals?action=insert">Add Meal</a></p>
<table>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${requestScope.mealToList}" var="mealTo">
        <tr data-mealExcess="${mealTo.excess}">
            <td>${mealTo.dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</td>
            <td>${mealTo.description}</td>
            <td>${mealTo.calories}</td>
            <td><a href="meals?action=update&id=${mealTo.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${mealTo.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
