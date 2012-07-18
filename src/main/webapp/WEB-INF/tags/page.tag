<%@tag description="Standard page header and footer. Body goes inside." pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="description" description="The page's description. Show's up in google"
  required="false"%>
<%@attribute name="name" description="The page's name is used to mark as current in the navigation menu"
  required="false"%>
<%@attribute name="keywords" description="The page's keywords." required="false"%>
<%@attribute name="type" description="The page's type." required="false"%>
<%@attribute name="script" description="A JavaScript file for the page" required="false"%>
<!DOCTYPE html>
<html>
<head>
<title>The Trip-Hop Database</title>
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
<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="<c:url value="/feed" />" />
<c:if test="${not empty script && fn:contains(script, 'stars')}">
<link href="<c:url value="/css/stars.css" />" rel="stylesheet" type="text/css">
</c:if>
<link rel="stylesheet" href="<c:url value="/css/layout.css" />" type="text/css">
<link rel="stylesheet" href="<c:url value="/css/blue.css" />" type="text/css">
<c:if test="${type == 'admin'}">
  <link rel="stylesheet" href="<c:url value="/css/admin.css" />" type="text/css">
</c:if>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-33198824-1']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</head>
<body id="${name}">
  <jsp:doBody />
  <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js" />"></script>
  <script src="<c:url value="/js/jquery.form.js" />"></script>
  <c:if test="${not empty script}">
  	<c:set var="scriptsList" value="${fn:split(script, ', ')}" />
  	<c:forEach var="script" items="${scriptsList}" >
  	<script src="<c:url value="/js/${script}.js" />"></script>   	
  	</c:forEach>     
  </c:if>
</body>
</html>