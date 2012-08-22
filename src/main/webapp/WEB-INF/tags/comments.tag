<%@tag pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<%@attribute name="comments" description="The collection of comments"
	required="true" rtexprvalue="true" type="java.util.Collection"%>
<%@attribute name="id" description="ID of the item" required="true"
	rtexprvalue="true"%>
<%@attribute name="type" description="Type of the item. [album | news]"
	required="true"%>
<div id="comments_wrapper">
<c:forEach var="comment" items="${comments}">
	<div class="comment" id="cid${comment.id}">
		<div class="commentUser">
			<c:choose>
				<c:when test="${empty comment.user}">
					<img src="<c:url value="/images/unregisteredUser.png" />" />
				</c:when>
				<c:otherwise>
					<img
						src="<c:url value="/images/users/${comment.user.username}.jpg" />" />
				</c:otherwise>
			</c:choose>
		</div>
		<div class="arrow-left"></div>
		<div class="commentBody">
			<tpdb:format var="${comment.comment}" html="true" />
		</div>
		<span>${comment.userName} @ <fmt:formatDate
				value="${comment.date}" pattern="dd. MMM. yyyy" /></span>
	</div>
</c:forEach>
</div>
<div class="comment">
	<div class="commentUser">
		<img src="<c:url value="/images/unregisteredUser.png" />" />
	</div>
	<div class="arrow-left"></div>
	<form method="post" action="<c:url value="/comment" />"
		id="commentForm">
		<textarea tabindex="1" name="commentBody"></textarea>
		<p>
			<input type="hidden" name="${type}" value="${id}" /> <input
				tabindex="1" type="text" name="userName" maxlength="12" value="${session.userName}" /> <input
				tabindex="2" type="text" name="email" value="${session.email}" /> <input tabindex="3"
				type="submit" value="Comment" />
		</p>
	</form>
</div>
