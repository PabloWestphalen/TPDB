<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page>
	<h2>News</h2>
	<c:forEach var="new" items="${news}">
		<div class="entry">
			<h3>
				<a href="news?id=${new.title}">${new.title}</a>
			</h3>
			<p>${new.content}</p>
			<p class="entry_info">
				Contributed by ${new.user.username} at
				<fmt:formatDate value="${new.date}" type="date" />
				| Comments
			</p>
		</div>
	</c:forEach>
	<h2>Latest Albums</h2>
	<c:forEach var="album" items="${albums}">
		<div class="entry">
			<h3>
				<a href="album?id=${album.id}">${album.artist.name} -
					${album.name}</a>
			</h3>
			<p>
				<img src="${album.cover}" alt="${album.name} cover" />
				${album.description}
			</p>
			<p class="entry_info">
				Contributed by ${album.user.username} at
				<fmt:formatDate value="${album.uploadDate}" type="date" />
				| ${album.totalComments} Comments
			</p>
		</div>
	</c:forEach>
</tpdb:page>