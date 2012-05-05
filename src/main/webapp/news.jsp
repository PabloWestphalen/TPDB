<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="news">
  <tpdb:content>
    <c:choose>
      <c:when test="${not empty param.id}">
        <h2>${news.title}</h2>
        <p>${news.content}</p>
      </c:when>
      <c:otherwise>
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
      </c:otherwise>
    </c:choose>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>