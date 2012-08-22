<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<c:choose>
<c:when test="${not empty param.id}">
<c:set var="title" value="${news.title}" />
</c:when>
<c:otherwise>
<c:set var="title" value="News" />
</c:otherwise>
</c:choose>
<tpdb:page name="news" title="${title}" script="comments">
  <tpdb:content>
    <c:choose>
      <c:when test="${not empty param.id}">
        <article><h2>${news.title}</h2>
        <p>${news.content}</p>
        <p>Tags: <c:forEach var="tag" items="${news.tags}"><span class="tag">${tag.name}</span> </c:forEach></p>
        <h3 id="comments">Comments</h3>
		<tpdb:comments type="news" comments="${news.comments}" id="${news.id}" />
        </article>
      </c:when>
      <c:otherwise>
        <div id="wrapper">
        <c:forEach var="news" items="${newsList}">
          <p>
            <fmt:formatDate value="${news.date}" type="date" />
            - <a href="<c:url value="/news/?id=${news.id}" />">${news.title}</a>
          </p>
        </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>
  </tpdb:content>
  <tpdb:sidebar />
  <script>

  
  </script>
</tpdb:page>