<?xml version="1.0" encoding="UTF-8"?>
<%@ page language="java" contentType="text/xml; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<fmt:setLocale value="en_US" scope="page" />

<rss version="2.0"
	xmlns:dc="http://purl.org/dc/elements/1.1/"
	xmlns:content="http://purl.org/rss/1.0/modules/content/">
<channel>
	<title>The Trip-hop Databse</title>
	<link>http://pablow-tpdb.rhcloud.com/feed</link>
	<language>en-us</language>
	<description>Discover and share Trip-Hop music.</description>
	<c:forEach var="news" items="${newsList}">
	<item>
		<title>${news.title}</title>
		<link>http://pablow-tpdb.rhcloud.com/news/?id=${news.id}</link>
		<pubDate>
		<fmt:formatDate value="${news.date}" type="both" pattern="E, dd MMM yyyy HH:mm:ss Z"  />
		</pubDate>
		<dc:creator>${news.user.username}</dc:creator>
		<c:forEach var="tag" items="${news.tags}">
		<category>${tag.name}</category>
		</c:forEach>		
		<description><![CDATA[${news.content}]]></description>
	</item>
	</c:forEach>
	<c:forEach var="album" items="${albums}">
	<item>
		<title>${album.name} - ${album.artist.name}</title>
		<link>http://pablow-tpdb.rhcloud.com/album/?id=${album.id}</link>
		<pubDate>
		<fmt:formatDate value="${album.uploadDate}" type="both" pattern="E, dd MMM yyyy HH:mm:ss Z"  />
		</pubDate>
		<category>${tag.name}</category>
		<description>
		<![CDATA[<tpdb:format var="${album.description}" maxlength="350" html="true" />]]>
		</description>
		<content:encoded>
		<![CDATA[
		<img src="${album.cover}" />
		${Utils:cleanHtml(album.description)}
		]]>
		</content:encoded>
	</item>
	</c:forEach>
	</channel>
</rss>
    