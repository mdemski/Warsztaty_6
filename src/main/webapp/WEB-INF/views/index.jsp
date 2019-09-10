<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<a href='<c:url value="/">'>Strona główna</a>
<h1>Dzień dobry ${username}</h1>

<h2>Dodaj nowy post</h2>
<form:form method="post" modelAttribute="tweetDTO" action="/add-tweet">
    <p>
        <form:input path="text"/>
    </p>
    <p>
        <input type="submit" value="Dodaj">
    </p>
</form:form>

<a href='<c:url value="/user-tweets/"/>'>List moich tweetów</a>

<h2>Lista wszystkich tweetów</h2>
<table>
    <th>Użytkownik</th>
    <th>Tekst</th>
    <th>Szczegóły</th>
    <th>Utworzony</th>
    <c:forEach items="${tweets}" var="tweet">
        <tr>
            <td>${tweet.userName}</td>
            <td>${tweet.text}</td>
            <td><a href='<c:url value="/tweet/${tweet.id}"/>'>Szczegóły</a></td>
            <td>${tweet.created}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
