<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="artists">
  <tpdb:content>
    <h2>Artists</h2>
    <c:forEach var="artist" items="${artistsList}">
      <h3 ${artist.name}</h3>
      <c:forEach var="album" items="${artist.albums}">
        <a href="/album?id=${album.id}" title="${album.name}"><img src="${album.cover}" width="100"
          height="100" /></a>
        <br />
      </c:forEach>
    </c:forEach>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>