<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: MarDem
  Date: 2019-09-01
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona główna</title>
</head>
<body>
<h1>Dzień dobry ${username}</h1>
<h2>Lista tweetów</h2>
<table>
    <th>Użytkownicy</th>
    <th>Tekst</th>
    <c:forEach items="${tweets}" var="tweet">
        <tr>
            <td>${tweet.user.id}</td>
            <td>${tweet.text}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
