<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MarDem
  Date: 04.09.2019
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wpisy użytkownika</title>
</head>
<body>
<a href='<c:url value="/"/>'>Strona główna</a>
<h1>List wszystkich wpisów użytkownika</h1>
<table class="user-tweets">
    <th>Wpis</th>
    <th>Utworzony</th>
    <c:forEach items="${tweets}" var="tweet">
        <tr>
            <td>${tweet.text}</td>
            <td>${tweet.created}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
