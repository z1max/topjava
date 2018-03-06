<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<table>
    <thead>
    <th>Date</th>
    <th>Time</th>
    <th>Description</th>
    <th>Calories</th>
    </thead>
    <tbody>
    <c:forEach items="${mealsWithExceeded}" var="meal">
        <tr style="color: ${meal.isExceed() ? "red" : "green"}">
            <td>${meal.getDateTime().toLocalDate()}</td>
            <td>${meal.getDateTime().toLocalTime()}</td>
            <td>${meal.getDescription()}</td>
            <td>${meal.getCalories()}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
