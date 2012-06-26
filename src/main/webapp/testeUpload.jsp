<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute">
  <tpdb:content>
  <form method="post" enctype="multipart/form-data" action="/uploader">
  <input type="file" name="cover" id="cover" />
  <input type="submit" value="enviar" />
  </form>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>