<%@taglib uri="Utils" prefix="Utils"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<c:set var="ogDesc">
<tpdb:format var="${album.description}" maxlength="110" />
</c:set>
<tpdb:page name="album" script="stars, comments, youtube"
	title="${album.name} - ${album.artist.name}"
	ogTitle="${album.artist.name} - ${album.name} (${year})"
	ogImage="${album.cover}"
	ogDescription="${ogDesc}">
  <tpdb:content>
  <article>
  <h2>${album.name} (${album.artist.name})</h2>
  <div class="fb-like" data-send="false" data-width="10" data-show-faces="false" data-font="arial" data-layout="button_count"></div>
  <p>
  ${Utils:cleanHtml(album.description)}
  </p>
  <h3>Tracks</h3>
    <a href="#" class="playAll">Play all</a>
  <ol id="tracksListing">
    <c:forEach var="song" items="${album.songs}">
      <c:choose>
      <c:when test="${not empty song.youtubeUrl}">
      <li class="has_video" data-video="${song.youtubeUrl}" data-trackname="${song.name}">
      <a href="http://www.youtube.com/watch?v=${song.youtubeUrl}" rel="nofollow">
      ${song.name} - ${song.length}
      </a>
      </li>
      </c:when>
      <c:otherwise>
      <li>${song.name} - ${song.length}</li>
      </c:otherwise>
      </c:choose>
    </c:forEach>
  </ol>
  <h3 id="comments">Comments</h3>
  <tpdb:comments type="album" comments="${album.comments}" id="${album.id}" />
  </article>
  </tpdb:content>
  <tpdb:sidebar>
  <div style="text-align: center;">
  <img src="<c:url value="/${album.cover}" />" class="coverImage" style="margin: auto;"  />
  </div>
    <ul>
      <li>Released: <fmt:formatDate value="${album.releaseDate}" pattern="MMM, yyyy" type="date" />
      <li>Length: ${album.length }</li>
      <%--<li>Label: ${album.label}</li> --%>
      <li>Rating: <div class="rateit" id="${album.id}" data-rateit-value="${album.averageRating}" data-rateit-ispreset="true"></div></li>
      <li><a href="${album.artist.site}" target="_blank">Official Site</a></li>
    </ul>
     <div id="sidebar_albums">
     <h3>Related Albums</h3>
     <c:forEach var="rAlbum" items="${relatedAlbums}">
	     <a href="<c:url value="/album/?id=${rAlbum.id}" />">
	     <img src="<c:url value="/${rAlbum.cover}" />" alt="${rAlbum.artist.name} - Cover" title="${rAlbum.name}" />
	     </a>
	 </c:forEach>
	 </div>
	 <div id="player"></div>
  </tpdb:sidebar>
</tpdb:page>