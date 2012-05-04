<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
  <tpdb:content>
    <h2>${news.title}</h2>
    <p>${news.content}</p>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>