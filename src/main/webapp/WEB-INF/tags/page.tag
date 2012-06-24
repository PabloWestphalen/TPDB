<%@tag description="Standard page header and footer. Body goes inside." pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="description" description="The page's description. Show's up in google"
  required="false"%>
<%@attribute name="name" description="The page's name is used to mark as current in the navigation menu"
  required="false"%>
<%@attribute name="keywords" description="The page's keywords." required="false"%>
<%@attribute name="type" description="The page's type." required="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<c:choose>
  <c:when test="${not empty description}">
    <meta name="description" content="${description}" />
  </c:when>
  <c:otherwise>
    <meta name="description" content="Discover and share trip-hop music.">
  </c:otherwise>
</c:choose>
<c:choose>
  <c:when test="${not empty keywords}">
    <meta name="keywords" content="${keywords}">
  </c:when>
  <c:otherwise>
    <meta name="keywords" content="trip-hop, database, new, release, wiki">
  </c:otherwise>
</c:choose>
<script type="text/javascript" src="/js/cookie.js"></script>
<script type="text/javascript" src="/js/change_themes.js"></script>
<link rel="stylesheet" href="/css/layout.css" type="text/css">
<link rel="stylesheet" href="/css/blue.css" type="text/css">
<c:if test="${type == 'admin'}">
  <link rel="stylesheet" href="/css/admin.css" type="text/css">
</c:if>
<title>The Trip-Hop Database</title>
</head>
<body id="${name}">
  <jsp:doBody />
</body>
</html>