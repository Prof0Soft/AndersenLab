<%--
  Created by IntelliJ IDEA.
  User: Sergey
  Date: 20.11.2019
  Time: 11:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order by </title>
</head>
<body>
    <c:out value="${order.userId.nameUser}"></c:out>
    <b><c:out value="${order.price}"></c:out></b>
</body>
</html>
