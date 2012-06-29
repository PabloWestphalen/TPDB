<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin" script="add_album">
  <tpdb:content type="admin">
  <form id="coverForm" method="post" enctype="multipart/form-data" action="/uploader">
    <input type="file" name="coverUp" id="coverUp" class="testeUp" />
  </form>
  <form method="post" action="/contribute/album/add">
      <fieldset id="fAlbum">
        <legend>Album Info</legend>
        <div class="coverLeft">
          <p>
            <label for="artist">Artist</label>
            <select name="artist" id="artist" tabindex="1">  
              <c:forEach var="artist" items="${artists}">
                <option value="${artist.id}">${artist.name}</option>
              </c:forEach>  
            </select>  
          </p>
          <p>
            <label for="date">Release date</label>
            <select name="month" id="month" tabindex="2">
              <option value="1">Jan</option>
              <option value="2">Novembro</option>
              <option value="3">Mar</option>            
            </select>
            <select name="year" id="year" tabindex="3">
              <option value="1">2012</option>
              <option value="2">2011</option>
              <option value="3">2010</option>           
            </select>            
          </p>
        </div>          
        <p>
          <img src="/images/blank_cover.png" width="85" height="85" alt="Cover" class="cover" tabindex="3" id="coverUploadButton" />
        </p>          
        <p class="clrl">
          <label for="name" id="albumName">Album name</label>
          <input id="name" name="name" type="text" tabindex="4">
        </p>
        <p>
          <label for="description">Description</label>
          <textarea name="description" id="description" tabindex="5"></textarea>
        </p>
        <p>
          <label for="label">Label</label>
          <input id="label" name="label" type="text" tabindex="6">
        </p>
      </fieldset>
      <fieldset id="fTracks" name="fTracks">
        <legend>Tracks</legend>
        <p>
          <input type="text" value="1" disabled>
          <input name="tracks[]" type="text" tabindex="7" onfocus="$('html,body').animate({scrollTop: $('#fTracks').offset().top}, 1000);">
          <input name="tracks_length[]" type="text" tabindex="8" >
        </p>
        <p class="newTrack">
          <input type="text" value="2" disabled >
          <input name="tracks[]" type="text" tabindex="9">
          <input name="tracks_length[]" type="text" tabindex="10">
        </p>
      </fieldset>
      <input type="submit" value="Add this album" id="albumBtn" />
    </form>
    <div id="visualizar"></div>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>