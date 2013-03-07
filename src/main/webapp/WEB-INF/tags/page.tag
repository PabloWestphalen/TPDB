<%@tag description="Standard page header and footer. Body goes inside." pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@attribute name="description" description="The page's description. Show's up in google"
  required="false"%>
  <%@attribute name="title" description="The page's title." required="false"%>
<%@attribute name="name" description="The page's name is used to mark as current in the navigation menu"
  required="false"%>
<%@attribute name="keywords" description="The page's keywords." required="false"%>
<%@attribute name="type" description="The page's type." required="false"%>
<%@attribute name="script" description="A JavaScript file for the page" required="false"%>
<%@attribute name="ogTitle" description="The OpenGraph title" required="false"%>
<%@attribute name="ogImage" description="The OpenGraph image url" required="false"%>
<%@attribute name="ogDescription" description="The OpenGraph description" required="false"%>
<!DOCTYPE html>
<html xmlns:og="http://opengraphprotocol.org/schema/"
	xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<link rel="shortcut icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />
<c:choose>
<c:when test="${not empty title}">
<title>${title} | The Trip-Hop Database</title>
</c:when>
<c:otherwise>
<title>The Trip-Hop Database</title>
</c:otherwise>
</c:choose>

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
<meta property="fb:admins" content="597985907" />
<c:if test="${not empty title}">
<meta property="og:title" content="${ogTitle}" />
</c:if>
<meta property="og:type" content="website" />
<c:if test="${name == 'album'}">
<meta property="og:url" content="http://pablow-tpdb.rhcloud.com/album/?${pageContext.request.queryString}" />
<meta property="og:site_name" content="Trip-Hop Database" />
</c:if>
<meta property="og:image" content="https://pablow-tpdb.rhcloud.com/${ogImage}" />
<meta property="og:description" content="<c:out value="${ogDescription}" escapeXml="true" />" />



<link rel="alternate" type="application/rss+xml" title="RSS 2.0" href="<c:url value="/feed" />" />
<link rel="stylesheet" href="<c:url value="/css/layout.css" />" type="text/css">
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
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/all.js#xfbml=1";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));</script>
  <jsp:doBody />
  <script src="<c:url value="/js/jquery.js" />"></script>
  <c:if test="${not empty script}">
  	<c:set var="scriptsList" value="${fn:split(script, ', ')}" />
  	<c:forEach var="script" items="${scriptsList}" >
  	<script src="<c:url value="/js/${script}.js" />"></script>   	
  	</c:forEach>     
  </c:if>
  <br style="clear: both" />
</body>
</html>