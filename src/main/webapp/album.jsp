<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
  <tpdb:content>
  <h2>${album.name} (${album.artist.name})</h2>
    <p>${album.description}</p>
  <h3>Tracks</h3>
  <ol>
    <c:forEach var="song" items="${songs}">
      <li>${song.name} - ${song.length}</li>
    </c:forEach>
  </ol>
  </tpdb:content>
  <tpdb:sidebar>
    <h3>Information</h3>
    <ul>
      <li>Released: <fmt:formatDate value="${album.releaseDate}" dateStyle="long" type="date" />
      <li>Length: ${album.length }</li>
      <li>Label: ${album.label}</li>
      <li>Site: <a href="${album.artist.site}">${album.artist.site}</a></li>
    </ul>
    <h3>Download</h3>
    <ul>
      <li>Bitrate: ${album.bitrate}</li>
      <li>Size (rar): ${album.downloadSize}</li>
      <li>Downloaded: ${album.downloadCount} times</li>
      <li>Link: <a href="${album.downloadLink}">Here</a></li>
    </ul>
    <h3>Rating</h3>
    8 stars rs
  </tpdb:sidebar>
</tpdb:page>