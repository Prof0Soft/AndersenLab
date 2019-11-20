
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List All Tour</title>
</head>
<body>
<ul>
    <c:forEach var="tour" items="${tours}">
        <li>
            <c:out value="${tour.nameTour}"></c:out>
            <b><c:out value="${tour.idCountryTour.nameCountry}"></c:out></b>
            <a href="/orderByUser?idOrder=${tour.orders[0].id}"><b>Order</b></a>
        </li>
    </c:forEach>
</ul>
</body>
</html>
