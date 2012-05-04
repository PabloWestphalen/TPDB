<%@ tag pageEncoding="UTF-8"%>
<%@attribute name="description" description="The page's description. Show's up in google"
  required="false"%>
<section>
  <header>
    <h1>The Trip-Hop Database</h1>
  </header>
  <nav>
    <ul>
      <li class="home"><a href="/">Home</a></li>
      <li class="blackjack"><a href="#">News</a></li>
      <li class="forms"><a href="#">Artists</a></li>
      <li class="ajax"><a href="#">Contribute</a></li>
      <li class="ajax"><a href="#">Requests</a></li>
      <li class="ajax"><a href="#">Community</a></li>
    </ul>
  </nav>
  <article>
    <jsp:doBody>
  </article>
  <footer>
    This document was written by <a href="#">Pablo "Jin" Westphalen</a>
  </footer>
</section>