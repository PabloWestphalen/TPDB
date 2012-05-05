<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="artists">
  <tpdb:content>
    <h2>Artists</h2>
    <c:forEach var="a" items="artistsList">
      <p>${a.name}</p>
    </c:forEach>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>