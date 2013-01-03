<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin" script="add_album">
  <tpdb:content type="admin">
  <form id="coverForm" method="post" enctype="multipart/form-data" action="<c:url value="/uploader" />">
    <input type="file" name="coverUp" id="coverUp" class="testeUp" />
  </form>
  <div id="artistPanel">
    <span id="closeWindow">[ Close ]</span>
    <form method="post" action="<c:url value="/contribute/artist/add" />" id="addArtistForm" >
      <fieldset>
      <legend>New artist</legend>
      <p>
        <label for="artist_name">Artist name: </label>
        <input type="text" name="artist_name" id="artist_name" />
      </p>
      <p>
        <label for="site">Site: </label>
        <input type="text" name="site" value="http://" id="site" />
      </p>      
      <input type="submit" value="Add" />
      </fieldset>     
    </form>   
  </div>
  <div id="lightbox"></div>
  <form method="post" id="albumForm" action="<c:url value="/contribute/album/add" />" >
      <fieldset id="fAlbum">
        <legend>Album Info</legend>
        <div class="coverLeft">
          <p>
            <label for="artist">Artist</label>
            <select name="artist" id="artist" tabindex="1">
              <option>Select</option>
              <optgroup>  
              <c:forEach var="artist" items="${artists}">
                <c:choose>
                <c:when test="${not empty param.artist and artist.id eq param.artist}">
                <option value="${artist.id}" selected="selected">${artist.name}</option>
                </c:when>
                <c:otherwise>
                <option value="${artist.id}">${artist.name}</option>
                </c:otherwise>
                </c:choose>
                
                
              </c:forEach>
              </optgroup>  
            </select>  
            <input type="button" value="New" id="newArtist" />
          </p>
          <p>
          <label for="name" id="albumName">Album name</label>
          <input id="name" name="name" type="text" >
        </p>
        </div>          
               <p>
          <img src="<c:url value="/images/blank_cover.png" />" width="85" height="85" alt="Cover" class="cover"  id="coverUploadButton" />
        </p>          
        
        <p class="clrl">
          <label for="description">Description</label>
          <textarea name="description" id="description" ></textarea>
        </p>
          <p style="text-align: center;">
            <tpdb:date_component tabindex="2" />          
          </p>
      </fieldset>
      <fieldset id="fTracks" name="fTracks">
        <legend>Tracks</legend>
        <p>
          <input type="text" value="1" disabled>
          <input name="tracks[]" type="text">
          <input name="tracks_length[]" type="text"  >
        </p>
        <p class="newTrack">
          <input type="text" value="2" disabled >
          <input name="tracks[]" type="text" >
          <input name="tracks_length[]" type="text" >
        </p>
      </fieldset>
      <input type="submit" value="Add this album" id="albumBtn" />
    </form>
    <div id="visualizar"></div>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>