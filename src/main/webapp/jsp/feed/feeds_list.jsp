<%--
  Created by IntelliJ IDEA.
  User: rparshin
  Date: 4/23/18
  Time: 12:33 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Feeds</title>
</head>
<body>
<p>Feeds</p>

<table border="1">
    <c:forEach items="${feedList}" var="feed">
        <tr>
            <td>${feed.id}</td>
            <td>${feed.feedUrl}</td>
            <td>${feed.status}</td>
            <td><a href="${feed.id}">edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
