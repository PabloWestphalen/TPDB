<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin" script="add_news">
  <tpdb:content type="admin">
    <form method="post" action="/contribute/news/add" >
      <fieldset>
        <legend>Add News</legend>
        <p>
          <label for="title">Title: </label>
          <input type="text" name="title" id="title" />
        </p>
        <p>
          <label for="tags">Tags: </label>
          <!-- <textarea name="tags" id="tags"></textarea> -->
          <input type="text" name="tags" id="tags" />
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