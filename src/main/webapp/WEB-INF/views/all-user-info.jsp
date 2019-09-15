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
    <title>Strona użytkownika</title>
</head>
<body>
<a href='<c:url value="/"/>'>Strona główna</a>
<h1>List wszystkich wpisów użytkownika</h1>
<table class="user-tweets table-striped">
    <th>Wpis</th>
    <th>Utworzony</th>
    <c:forEach items="${tweets}" var="tweet">
        <tr>
            <td>${tweet.text}</td>
            <td>${tweet.created}</td>
        </tr>
    </c:forEach>
</table>
<table class="messages">
    <tr>
        <td>
            <table class="received table-striped">
                <th>Odebrana</th>
                <th>Nadawca</th>
                <th>Treść wiadomości</th>
                <th>Przeczytana</th>
                <c:forEach items="${received}" var="messageReceive">
                    <tr>
                        <td>${messageReceive.created}</td>
                        <td>${messageReceive.senderName}</td>
                        <td>${messageReceive.content}</td>
                        <td>${messageReceive.read}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <td>
            <table class="posted table-striped">
                <th>Wysłana</th>
                <th>Adresat</th>
                <th>Treść wiadomości</th>
                <c:forEach items="${posted}" var="messagePost">
                    <tr>
                        <td>${messagePost.created}</td>
                        <td>${messagePost.recipientName}</td>
                        <td>${messagePost.content}</td>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
</table>
<a href='<c:url value=""/>' class="button">Wyślij wiadomość</a>
</body>
</html>
