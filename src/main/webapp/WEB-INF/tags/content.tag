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
      <li class="home"><a href="/">Home</a></li>
      <li class="news"><a href="/news">News</a></li>
      <li class="artists"><a href="/artists">Artists</a></li>
      <li class="contribute"><a href="/contribute_index.jsp">Contribute</a></li>
      <li class="requests"><a href="#">Requests</a></li>
      <li class="community"><a href="#">Community</a></li>
    </ul>
  </nav>
  <c:choose>
    <c:when test="${type == 'admin'}">
      <div id="adminContainer">
        <nav id="adminNav">
          <h3>Contribute</h3>
          <a href="/contribute/news/add">Add news</a> <a href="#">Add an Artist</a> <a href="#">Add an Album</a>
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
    This document was written by <a href="#">Pablo "Jin" Westphalen</a>
  </footer>
</section>
