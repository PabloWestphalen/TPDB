<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="news">
  <tpdb:content>
    <c:choose>
      <c:when test="${not empty param.id}">
        <article><h2>${news.title}</h2>
        <p>${news.content}</p>
        </article>
      </c:when>
      <c:otherwise>
        <div id="wrapper">
        <c:forEach var="news" items="${newsList}">
          <p>
            <fmt:formatDate value="${news.date}" type="date" />
            - <a href="news?id=${news.id}">${news.title}</a>
          </p>
        </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>