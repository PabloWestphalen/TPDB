<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="artists" script="artists, stars">
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
        	<div id="art${artist.id}" class="albumDiv">
        		<h3>Artists &raquo; ${artist.name}</h3>
        		<c:forEach var="album" items="${artist.albums}">
				<div>					
					<img src="<c:url value="/${album.cover}" />" class="coverImage"  />
					<a href="<c:url value="/album/?id=${album.id}" />">${album.name} (<fmt:formatDate value="${album.releaseDate}" type="date" pattern="yyyy" />)</a>
					<tpdb:format var="${album.description}" maxlength="115" html="true" />
					<span>
					<c:choose>
					<c:when test="${empty album.length}" >
					<strong>Length</strong>: N/A |	
					</c:when>
					<c:otherwise>
					<strong>Length</strong>: ${album.length} |
					</c:otherwise>
					</c:choose>
					<strong>Tracks:</strong> ${fn:length(album.songs)} | 
					<strong>Rating:</strong>
					</span>
					<div class="rateit" id="${album.id}" data-rateit-value="${album.averageRating}" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
				</div>
		        </c:forEach>        		
        	</div>
        	</c:forEach>        
        </div>
    </div>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>
