
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="home">
  <tpdb:content>
    <div id="wrapper">
    <h2>News</h2>
    <c:forEach var="news" items="${newsList}">
      <article>
        <h3>
          <a href="news?id=${news.id}">${news.title}</a>
        </h3>
        <p>${news.content}</p>
        <p class="entry_info">
          Contributed by ${news.user.username} at
          <fmt:formatDate value="${news.date}" type="date" />
          | Comments
        </p>
      </article>
    </c:forEach>
    <h2>Latest Albums</h2>
    <c:forEach var="album" items="${albums}">
      <article>
        <h3>
          <a href="album?id=${album.id}">${album.artist.name} - ${album.name}</a>
        </h3>
        <p>
          <img src="${album.cover}" alt="${album.name} cover" class="coverImage" /> ${album.description}
        </p>
        <p class="entry_info">
          Contributed by ${album.user.username} at
          <fmt:formatDate value="${album.uploadDate}" type="date" />
          | ${album.totalComments} Comments
        </p>
      </article>
    </c:forEach>
    </div>
  </tpdb:content>
  <tpdb:sidebar>
    <div id="featured_albums">
      <h3>Featured Albums</h3>
      <c:forEach var="fAlbum" items="${featuredAlbums}">
        <a href="/album?id=${fAlbum.id}"><img src="${fAlbum.cover}"
          alt="${fAlbum.artist.name} - Cover" title="${fAlbum.name}" /></a>
      </c:forEach>
    </div>
    <h3>Featured Songs</h3>
    <h3>Choose a theme</h3>
    <ul id="themeChoser">
      <li><a href="#" class="changeBlue" title="Change to Blue">Blue</a></li>
      <li><a href="#" class="changeGreen" title="Change to Green">Green</a></li>
      <li><a href="#" class="changeOrange" title="Change to Orange">Orange</a></li>
      <li><a href="#" class="changeNone" title="No color">None</a></li>
    </ul>
  </tpdb:sidebar>
</tpdb:page>