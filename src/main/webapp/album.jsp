<%@taglib uri="Utils" prefix="Utils"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<c:set var="year">
<fmt:formatDate value="${album.releaseDate}" type="date" pattern="yyyy" />
</c:set>
<c:set var="ogDesc">
<tpdb:format var="${album.description}" maxlength="110" />
</c:set>
<tpdb:page name="album" script="stars"
	title="${album.name} - ${album.artist.name}"
	ogTitle="${album.artist.name} - ${album.name} (${year})"
	ogImage="${album.cover}"
	ogDescription="${ogDesc}">
  <tpdb:content>
  <article>
  <h2>${album.name} (${album.artist.name})</h2>
  <div class="fb-like" data-send="false" data-width="10" data-show-faces="false" data-font="arial" data-layout="button_count"></div>
  ${Utils:cleanHtml(album.description)}
  <h3>Tracks</h3>
  <ol>
    <c:forEach var="song" items="${album.songs}">
      <li>${song.name} - ${song.length}</li>
    </c:forEach>
  </ol>
  <h3 id="comments">Comments</h3>
  <c:forEach var="comment" items="${album.comments}">
	<div class="comment" id="cid${comment.id}">
			<div class="commentUser">
				<c:choose>
				<c:when test="${empty comment.user}" > 
				<img src="<c:url value="/images/unregisteredUser.png" />" />
				</c:when>
				<c:otherwise>
				<img src="<c:url value="/images/users/${comment.user.username}.jpg" />" />
				</c:otherwise>
				</c:choose>
			</div>
			<div class="arrow-left"></div>
			<div class="commentBody">
			<tpdb:format var="${comment.comment}" html="true" />
			</div>
			<span>${comment.userName} @ <fmt:formatDate value="${comment.date}" pattern="dd. MMM. yyyy" /></span>
	</div>
  </c:forEach>
	<div class="comment">
		<div class="commentUser">
			<img src="<c:url value="/images/unregisteredUser.png" />" />
		</div>
		<div class="arrow-left"></div>
		<form method="post" action="<c:url value="/comment" />" id="commentForm" >
			<textarea tabindex="1" name="commentBody"></textarea>
			<p>
				<input type="hidden" name="albumId" value="${album.id}" />
				<input tabindex="1" type="text" name="userName" value="Your name" maxlength="12" />
				<input tabindex="2" type="text" name="email" value="Email" />
				<input tabindex="3" type="submit" value="Comment" />
			</p>
		</form>
	</div>		 
  </article>
  </tpdb:content>
  <tpdb:sidebar>
    <img src="<c:url value="/${album.cover}" />" class="coverImage"  />
    
    <h3>Information</h3>
    <ul>
      <li>Released: <fmt:formatDate value="${album.releaseDate}" dateStyle="long" type="date" />
      <li>Length: ${album.length }</li>
      <li>Label: ${album.label}</li>
      <li><a href="${album.artist.site}" target="_blank">Official Site</a></li>
    </ul>
    <%--
    <h3>Download</h3>
    <ul>
      <li>Bitrate: ${album.bitrate} kbps</li>
      <li>Size (rar): ${album.downloadSize} MB</li>
      <li>Downloaded: ${album.downloadCount} times</li>
      <li>Link: <a href="${album.downloadLink}">Here</a></li>
    </ul>
     --%>
     <h3>Related Albums</h3>
     <c:forEach var="rAlbum" items="${relatedAlbums}">
	     <a href="<c:url value="/album/?id=${rAlbum.id}" />">
	     <img src="<c:url value="/${rAlbum.cover}" />" alt="${rAlbum.artist.name} - Cover" title="${rAlbum.name}" />
	     </a>
	 </c:forEach>
    <h3>Rating</h3>
	<div class="rateit" id="${album.id}" data-rateit-value="${album.averageRating}" data-rateit-ispreset="true"></div>
  </tpdb:sidebar>
</tpdb:page>