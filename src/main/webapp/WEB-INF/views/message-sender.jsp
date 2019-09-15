<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: MarDem
  Date: 12.09.2019
  Time: 21:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Wyślij wiadomość</title>
</head>
<body>
<form:form method="post" modelAttribute="messageDTO" action="/posted">
    <form:label path="recipientName">Adresat:</form:label></br>
    <form:input path="recipientName"/>

    <form:label path="title">Tytuł:</form:label></br>
    <form:input path="title"/>

    <form:label path="content">Wiadomość:</form:label></br>
    <form:input path="content"/>

    <input type="submit" class="sendingButton">
</form:form>
</body>
</html>
