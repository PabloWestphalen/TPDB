<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="artists" script="artists, stars">
  <tpdb:content>
    <div id="adminContainer">
        <nav id="artistsNav">
        	<c:forEach var="artist" items="${artistsList}">
        	<a href="<c:url value="/artists/${Utils:urlEncode(artist.name)}/" />" class="art${artist.id}">${artist.name}</a>
        	</c:forEach>
        </nav>
        <div id="artistsBody">
	        <c:choose>
	        <c:when test="${empty defaultArtist}" >
	        <div id="default"></div>
	        </c:when>
	        <c:otherwise>
	        <div id="default" class="hidden"></div>
	        </c:otherwise>
	        </c:choose>
	        <c:forEach var="artist" items="${artistsList}">
			<c:choose>
			<c:when test="${not empty defaultArtist and fn:contains(fn:toLowerCase(artist.name), defaultArtist)}">
			<div id="art${artist.id}" class="albumDiv visible">
			<c:set var="activeArtist" value="${artist.id}" />
			</c:when>
			<c:otherwise>
			<div id="art${artist.id}" class="albumDiv">
			</c:otherwise>
			</c:choose>	
        		<h3>Artists &raquo; ${artist.name}</h3>
        		<c:choose>
        		<c:when test="${not empty artist.albums}" >
        		<a href="<c:url value="/contribute/album/add?artist=${artist.id}" />" class="button" >New Album</a>
					<c:forEach var="album" items="${artist.albums}">
					<div class="albumListing">					
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
						<c:choose>
						<c:when test="${album.totalSongs eq 0}">
						<strong>Tracks:</strong>  N/A
						</c:when>
						<c:otherwise>
						<strong>Tracks:</strong>  ${album.totalSongs}
						</c:otherwise>
						</c:choose>
						 | 
						<strong>Rating:</strong>
						</span>
						<div class="rateit" id="${album.id}" data-rateit-value="${album.averageRating}" data-rateit-ispreset="true" data-rateit-readonly="true"></div>
					</div>
			        </c:forEach>
			        
        		</c:when>
        		<c:otherwise>
        			<div>There are no albums from this artist catalogued yet. Do you want to <a href="<c:url value="/contribute/album/add?artist=${artist.id}" />" >Add</a> one?</div>
        		</c:otherwise>
        		</c:choose>
        		
        		        		
        	</div>
        	</c:forEach>        
        </div>
    </div>
  </tpdb:content>
  <tpdb:sidebar />

<c:choose>
<c:when test="${not empty activeArtist}">
	<script>
	var active = "#art${activeArtist}";
	</script>
</c:when>
<c:otherwise>
	<script>
	var active = "#default";
	</script>
  </c:otherwise>
</c:choose>
</tpdb:page>
