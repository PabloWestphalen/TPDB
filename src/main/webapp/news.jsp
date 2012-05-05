<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="news">
  <tpdb:content>
    <c:if test="${not empty param.id}">
    <h2>${news.title}</h2>
    <p>${news.content}</p>
    </c:if>
    
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>