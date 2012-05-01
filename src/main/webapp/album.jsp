<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
	<h2>${album.artist.name} - ${album.name}</h2>
	<div class="entry">
		<p>${album.description}</p>
	</div>
</tpdb:page>


