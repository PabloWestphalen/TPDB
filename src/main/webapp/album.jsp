<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="Discover and share trip-hop music.">
<meta name="keywords" content="trip-hop, database, new, release, wiki">
<script type="text/javascript" src="js/cookie.js"></script>
<script type="text/javascript" src="js/change_themes.js"></script>
<link rel="stylesheet" href="css/layout.css" type="text/css">
<link rel="stylesheet" href="css/blue.css" type="text/css">
<title>The Trip-Hop Database</title>
</head>
<body id="home">
	<div id="container">
		<div id="main_wrapper">
			<div id="header">
				<h1>Trip-Hop Database</h1>
			</div>
			<div id="top_menu">
				<ul>
					<li class="home"><a href="#">Home</a></li>
					<li class="blackjack"><a href="#">News</a></li>
					<li class="forms"><a href="#">Artists</a></li>
					<li class="ajax"><a href="#">Contribute</a></li>
					<li class="ajax"><a href="#">Requests</a></li>
					<li class="ajax"><a href="#">Community</a></li>
				</ul>
			</div>
			<div id="content">
				<h2>${album.artist.name} - ${album.name}</h2>
					<div class="entry">
						<p>${album.description}</p>
					</div>
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
