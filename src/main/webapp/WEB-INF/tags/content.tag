<%@ tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="currentPage" description="The page's description. Show's up in google"
  required="false"%>
<%@attribute name="type" required="false"%>

<section>
  <header>
    <h1>The Trip-Hop Database</h1>
  </header>
  <nav id="mainNav">
    <ul>
      <li class="home"><a href="<c:url value="/" />">Home</a></li>
      <li class="news"><a href="<c:url value="/news/" />">News</a></li>
      <li class="artists"><a href="<c:url value="/artists/" />">Artists</a></li>
      <li class="contribute"><a href="<c:url value="/contribute/" />">Contribute</a></li>
      <li class="requests"><a href="#">Requests</a></li>
      <li class="community"><a href="#">Community</a></li>
      <li class="about"><a href="<c:url value="/about/" />">About</a></li>
    </ul>
  </nav>
  <c:choose>
    <c:when test="${type == 'admin'}">
      <div id="adminContainer">
        <nav id="adminNav">
          <h3>Contribute</h3>
          <a href="<c:url value="/contribute/news/add" />">Add news</a>
          <a href="<c:url value="/contribute/artist/add" />">Add an Artist</a>
          <a href="<c:url value="/contribute/album/add" />">Add an Album</a>
        </nav>
        <div id="adminBody">
          <jsp:doBody />
        </div>
      </div>
    </c:when>
    <c:otherwise>
      <jsp:doBody />
    </c:otherwise>
  </c:choose>
  <footer>
    Back on eclipse. Created by <a href="mailto:pablo.westphalen@gmail.com">Pablo Westphalen</a> - Some rights reserved.
  </footer>
</section>
