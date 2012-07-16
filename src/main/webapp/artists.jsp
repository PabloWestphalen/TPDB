<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="artists" script="artists">
  <tpdb:content>
    <div id="adminContainer">
        <nav id="artistsNav">
        	<c:forEach var="artist" items="${artistsList}">
        	<a href="<c:url value="/artists/${artist.name}/" />" class="art${artist.id}">${artist.name}</a>
        	</c:forEach>
        </nav>
        <div id="artistsBody">
	        <div id="default"></div>
	        <c:forEach var="artist" items="${artistsList}">
        	<div id="art${artist.id}">
        		<h3>Artists &raquo; ${artist.name}</h3>
        		<c:forEach var="album" items="${artist.albums}">
				<p>					
					<img src="<c:url value="/${album.cover}" />"  />
					<a href="<c:url value="/album/?id=${album.id}" />">${album.name} (<fmt:formatDate value="${album.releaseDate}" type="date" pattern="yyyy" />)</a>
					<tpdb:format var="${album.description}" maxlength="130" />
					<span>Length: ${album.length} | Tracks: 12 | Rating: ****</span>
				</p>
		        </c:forEach>        		
        	</div>
        	</c:forEach>        
        </div>
    </div>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>
