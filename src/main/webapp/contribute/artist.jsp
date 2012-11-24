<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin">
  <tpdb:content type="admin">
     <form method="post" action="<c:url value="/contribute/artist/add" />" >
        <fieldset>
          <legend>Add Artist</legend>
          <p>
            <label for="artist_name">Name: </label>
            <input type="text" name="artist_name" id="name" />
          </p>
          <p>
            <label for="site">Site: </label>
            <input type="text" name="site" id="site" value="http://" />
          </p>
          <p>
            <input type="submit" value="Add" />
          </p>
        </fieldset>
    </form>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>