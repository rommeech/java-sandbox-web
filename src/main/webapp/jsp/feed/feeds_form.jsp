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
    <title>Feed ${feed.id}</title>
</head>
<body>
<p><b>Feed ${feed.id}</b></p>
<p>URL: ${feed.feedUrl}</p>
<p>Status: ${feed.status}</p>

</body>
</html>
