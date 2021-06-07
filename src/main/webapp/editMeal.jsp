<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">

    <title>Edit meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>

<h2>Edit meal</h2>
<c:set var="meal" value="${requestScope.meal}"/>
<form method="POST" action='' name="frmAddMeal">
    <table class="table">
        <tr>
            <td><label for="datetime">DateTime </label></td>
            <td><input type="datetime-local" id="datetime" name="datetime" value="${meal.dateTime}"/></td>
        </tr>
        <tr>
            <td><label for="description">Description </label></td>
            <td><input type="text" id="description" name="description" size="50" value="${meal.description}"/></td>
        </tr>
        <tr>
            <td><label for="calories">Calories </label></td>
            <td><input type="number" id="calories" name="calories" value="${meal.calories}"/></td>
        </tr>
    </table>
    <input type="submit" value="Save"/>
    <button type="button"><a href="meals" style="text-decoration: none;">Cancel</a></button>
    <input type="hidden" name="id" value="${meal.id}"/>
</form>
</body>
</html>
