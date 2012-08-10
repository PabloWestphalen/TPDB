<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="news" script="comments">
  <tpdb:content>
    <c:choose>
      <c:when test="${not empty param.id}">
        <article><h2>${news.title}</h2>
        <p>${news.content}</p>
        <p>Tags: <c:forEach var="tag" items="${news.tags}"><span class="tag">${tag.name}</span> </c:forEach></p>
        <h3 id="comments">Comments</h3>
		<c:forEach var="comment" items="${newsRepository.getComments(news.id)}">
		<div class="comment" id="cid${comment.id}">
			<div class="commentUser">
				<c:choose>
				<c:when test="${empty comment.user}" > 
				<img src="<c:url value="/images/unregisteredUser.png" />" />
				</c:when>
				<c:otherwise>
				<img src="<c:url value="/images/users/${comment.user.username}.jpg" />" />
				</c:otherwise>
				</c:choose>
			</div>
			<div class="arrow-left"></div>
			<div class="commentBody">
				<tpdb:format var="${comment.comment}" html="true" />
			</div>
			<span>${comment.userName} @ <fmt:formatDate value="${comment.date}" pattern="dd. MMM. yyyy" /></span>
		</div>
		</c:forEach>
		<div class="comment">
			<div class="commentUser">
				<img src="<c:url value="/images/unregisteredUser.png" />" />
			</div>
			<div class="arrow-left"></div>
				<form method="post" action="<c:url value="/comment" />" id="commentForm" >
					<textarea tabindex="1" name="commentBody"></textarea>
					<p>
						<input type="hidden" name="newsId" value="${news.id}" />
						<input tabindex="1" type="text" name="userName" value="Your name" maxlength="12" />
						<input tabindex="2" type="text" name="email" value="Email" />
						<input tabindex="3" type="submit" value="Comment" />
					</p>
				</form>
			</div>	
        </article>
      </c:when>
      <c:otherwise>
        <div id="wrapper">
        <c:forEach var="news" items="${newsList}">
          <p>
            <fmt:formatDate value="${news.date}" type="date" />
            - <a href="<c:url value="/news/?id=${news.id}" />">${news.title}</a>
          </p>
        </c:forEach>
        </div>
      </c:otherwise>
    </c:choose>
  </tpdb:content>
  <tpdb:sidebar />
</tpdb:page>