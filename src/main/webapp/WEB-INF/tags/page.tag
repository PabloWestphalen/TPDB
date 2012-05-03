<%@tag description="Standard page header and footer. Body goes inside."
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@attribute name="description"
	description="The page's description. Show's up in google"
	required="false"%>
<%@attribute name="keywords" description="The page's keywords."
	required="false"%>
<c:set var="sidebarContent" scope="request" />
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
<script type="text/javascript" src="js/cookie.js"></script>
<script type="text/javascript" src="js/change_themes.js"></script>
<link rel="stylesheet" href="css/layout.css" type="text/css">
<link rel="stylesheet" href="css/blue.css" type="text/css">
<title>${title}</title>
</head>
<body id="home">
	<div id="container">
		<div id="main_wrapper">
			<div id="header">
				<h1>Trip-Hop Database</h1>
			</div>
			<div id="top_menu">
				<ul>
					<li class="home"><a href="/">Home</a></li>
					<li class="blackjack"><a href="#">News</a></li>
					<li class="forms"><a href="#">Artists</a></li>
					<li class="ajax"><a href="#">Contribute</a></li>
					<li class="ajax"><a href="#">Requests</a></li>
					<li class="ajax"><a href="#">Community</a></li>
				</ul>
			</div>
			<div id="content">
				<jsp:doBody />
			</div>
			<div id="footer">
				<p>
					This document was written by <a href="#">Pablo "Jin" Westphalen</a>
				</p>
			</div>
		</div>
		<div id="sidebar">
			<h3>Search</h3>
			<form method="get" action="search">
				<fieldset>
					<input type="text" name="Search" title="Search">
				</fieldset>
			</form>
			${sidebarContent}
			<div id="featured_albums">
				<h3>Featured Albums</h3>
				<c:forEach var="fAlbum" items="${featuredAlbums}">
					<img src="${fAlbum.cover}" alt="${fAlbum.artist.name} - Cover"
						title="${fAlbum.name}" />
				</c:forEach>
			</div>
			<h3>Featured Songs</h3>
			<h3>Choose a theme</h3>
			<ul>
				<li><a href="#" class="changeBlue" title="Change to Blue">Blue</a></li>
				<li><a href="#" class="changeGreen" title="Change to Green">Green</a></li>
				<li><a href="#" class="changeOrange" title="Change to Orange">Orange</a></li>
				<li><a href="#" class="changeNone" title="No color">None</a></li>
			</ul>
		</div>
	</div>
</body>
</html>