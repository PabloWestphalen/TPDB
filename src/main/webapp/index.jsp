<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="javax.persistence.*" %>
<%@ page import="com.jin.tpdb.entities.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="In this page i write html">
<meta name="keywords" content="html5, first, draft">
<script type="text/javascript" src="js/cookie.js"></script>
<script type="text/javascript" src="js/change_themes.js"></script>
<link rel="stylesheet" href="css/layout.css" type="text/css">
<link rel="stylesheet" href="css/blue.css" type="text/css">
<title>The Trip-Hop Database</title>
<style type="text/css">
/*.album_entry p {*/
.entry p {
	margin: 10px;
	padding: 0;
}

#featured_albums {
	margin: 0;
	padding: 0;
}

#sidebar input[name="Search"] {
	border-radius: 6px;
	-moz-border-radius: 6px;
	-webkit-border-radius: 6px;
}

#featured_albums img {
	margin-right: 5px;
	width: 50px;
	height: 50px;
	text-align: center;
}

.entry .entry_info {
	font-size: 0.9em;
	margin-right: 15px;
	text-align: right;
}

.entry a {
	text-decoration: none;
}

.entry { //
	padding: 15px 15px 15px 15px;
	text-align: justify;
}

.entry p img {
	float: right;
	margin-left: 5px;
	border: none;
	display: block;
	width: 100px;
	height: 100px;
}
</style>
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
					<!---<li class="ajax"><a href="ajax.php">About the Project</a></li>
	<li class="ajax"><a href="ajax.php">FAQ</a></li>
	<li class="ajax"><a href="ajax.php">Contact</a></li>-->
				</ul>
			</div>
			<!--- start content -->
			<div id="content">
				<h2>News</h2>
				<c:forEach var="new" items="${news}">
				<div class="entry">
					<h3><a href="#">${new.title}</a></h3>
					<p>${new.content}</p>
					<p class="entry_info">Contributed by ${new.user.username} at ${new.date} | Z Comments</p>
				</div>					
				</c:forEach>
				<h2>Latest Albums</h2>
				<c:forEach var="album" items="${albums}">
				<div class="entry">
				<h3><a href="#">${album.artist.name} - ${album.name}</a></h3>
				<p>
					<img src="images/albums/${album.cover}" alt="${album.name} cover" />
					${album.description}
				</p>
				<p class="entry_info">Contributed by ${album.user.username} at ${album.releaseDate} | Z Comments</p>
				</div>

				<!--- end content -->
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
				<img src="images/albums/cover_esthero_breath_from_another.jpg"
					alt="Quioxic" /> <img
					src="images/albums/cover_tricky_maxinquaye.jpg"
					alt="Breath from Another" /> <img
					src="images/albums/cover_martina_topley_bird_quixotic.jpg"
					alt="Quioxic" />
			</div>
			<h3>Featured Songs</h3>
			<h3>Choose a theme</h3>
			<ul>
				<li><a href="#" class="changeBlue" title="Change to Blue">Blue</a></li>
				<li><a href="#" class="changeOrange" title="Change to Orange">Orange</a></li>
				<li><a href="#" class="changeGreen" title="Change to Green">Green</a></li>
				<li><a href="#" class="changeNone" title="No color">None</a></li>
			</ul>
		</div>
	</div>
</body>
</html>
