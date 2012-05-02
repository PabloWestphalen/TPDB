<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
	<h2>${album.name} (${album.artist.name})</h2>
	<div class="entry">
		<p>${album.description}</p>
	</div>
	<style>
ol {
	list-style-position: inside;
}

ol li:nth-child(even) {
	background-color: #66A8CC;
}
</style>
	<h3>Tracks</h3>
	<ol>
		<c:forEach var="song" items="${songs}">
			<li>${song.name} - ${song.length}</li>
		</c:forEach>
	</ol>
	<div class="colmain_tracks">
		<h3 class="info_header">Tracks</h3>
		<ol>
			<li class="x1">Electric Lullaby - 2:54</li>
			<li class="x0">Inky Drips - 3:04</li>
			<li class="x1">Machine (Feat. Mr. Lif) - 3:11</li>
			<li class="x0">It's a Crush - 3:05</li>
			<li class="x1">Mosquito in The Closet - 4:54</li>
			<li class="x0">Bodies Offering - 3:01</li>
			<li class="x1">Picture Perfect - 4:54</li>
			<li class="x0">Lost Heroism - 2:44</li>
			<li class="x1">Privilege (Feat. Anna-Lynne Williams) - 4:15</li>
			<li class="x0">On TV - 3:08</li>
			<li class="x1">Lavander Days - 2:50</li>
			<li class="x0">Phantom - 4:09</li>
		</ol>
	</div>
</tpdb:page>


