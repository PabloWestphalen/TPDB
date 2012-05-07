<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin">
  <tpdb:content type="admin">
    <form>
      <fieldset>
        <legend>Add News</legend>
        <p>
          <label for="title">Title: </label> <input type="text" name="title" id="title" />
        </p>
        <p>
          <label for="tags">Tags: </label> <input type="text" name="tags" id="tags" />
        </p>
        <p>
          <label for="content">Content: </label>
          <textarea name="content" id="content"></textarea>
        </p>
        <p>
          <input type="submit" value="Add" />
        </p>
      </fieldset>
    </form>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>