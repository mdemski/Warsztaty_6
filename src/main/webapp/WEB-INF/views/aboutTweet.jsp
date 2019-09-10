<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MarDem
  Date: 09.09.2019
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Informacje na temat wpisu</title>
</head>
<body>
<a href='<c:url value="/"/>'>Strona główna</a>
<h2>Dodaj komentarz do wpisu</h2>
<form:form method="post" modelAttribute="commentDTO" action="/tweet/add-comment/${tweet.id}">
    <p>
        <form:input path="comment"/>
    </p>
    <p>
        <input type="submit" value="Dodaj">
    </p>
</form:form>
<table class="about-tweet">
    <th>Utworzony</th>
    <th>Treść</th>
    <th>Dodany przez</th>
    <tr>
        <td>${tweet.created}</td>
        <td>${tweet.text}</td>
        <td>${tweet.userName}</td>
    </tr>
</table>
<section class="comments">
    <c:forEach items="${comments}" var="comment">
        <ul>
            <li class="comment">
                <section class="author">${comment.userName}:</section>
                <section class="comment-text">${comment.text}</section>
            </li>
        </ul>
    </c:forEach>
</section>
</body>
</html>
