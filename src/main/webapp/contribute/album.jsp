<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="contribute" type="admin">
  <tpdb:content type="admin">
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
            <input id="date" name="date" value="26/12/1990" type="text" tabindex="2">
          </p>
        </div>          
        <p>
          <img src="#" width="85" height="85" alt="Cover" class="cover" tabindex="3"/>            
        </p>          
        <p class="clrl">
          <label for="name">Album name</label>
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
      <fieldset id="fTracks">
        <legend>Tracks</legend>
        <p>
          <input type="text" value="1" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="7">            
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="8" >
        </p>
        <p>
          <input type="text" value="2" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="9">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="10">
        </p>
        <p>
          <input type="text" value="3" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="11">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="12">
        </p>
        <p>
          <input type="text" value="4" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="13">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="14">
        </p>
        <p>
          <input type="text" value="5" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="15">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="16">
        </p>
        <p>
          <input type="text" value="6" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="17">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="18">
        </p>
        <p>
          <input type="text" value="7" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="19">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="20">
        </p>
        <p>
          <input type="text" value="8" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="21">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="22">
        </p>
        <p>
          <input type="text" value="9" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="23">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="24">
        </p>
        <p>
          <input type="text" value="10" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="25">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="26">
        </p>
        <p>
          <input type="text" value="11" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="27">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="28">
        </p>
        <p>
          <input type="text" value="12" disabled>
          <input id="tracks[]" name="tracks[]" type="text" tabindex="29">
          <input id="tracks_length[]" name="tracks_length[]" type="text" tabindex="30">
        </p>
        <p class="moreTracks">
        <input type="button" value="+ More" tabindex="31"/>
        </p>          
      </fieldset>
      <input type="submit" value="Add this album" id="albumBtn" tabindex="32"/>
    </form>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>