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
	<div>
		<ol>
			<c:forEach var="song" items="${songs}">
				<li>${song.name} - ${song.length}</li>
			</c:forEach>
		</ol>
	</div>
</tpdb:page>


