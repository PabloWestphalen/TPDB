<%@ tag pageEncoding="UTF-8"%>
<%@attribute name="currentPage" description="The page's description. Show's up in google"
  required="false"%>
  
  <section>
    <header>
      <h1>The Trip-Hop Database</h1>
    </header>
    <nav>
      <ul>
        <li class="home"><a href="/">Home</a></li>
        <li class="news"><a href="/news">News</a></li>
        <li class="artists"><a href="/artists">Artists</a></li>
        <li class="contribute"><a href="#">Contribute</a></li>
        <li class="requests"><a href="#">Requests</a></li>
        <li class="community"><a href="#">Community</a></li>
      </ul>
    </nav>
    <article>
      <jsp:doBody />
    </article>
    <footer>
      This document was written by <a href="#">Pablo "Jin" Westphalen</a>
    </footer>
  </section>
  