<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
	<h2>${album.name} (${album.artist.name})</h2>
	<div class="entry">
		<p>${album.description}</p>
	</div>
	<c:forEach var="song" items="${songs}">
		${song.name} - ${song.length} <br />
	</c:forEach>
</tpdb:page>


