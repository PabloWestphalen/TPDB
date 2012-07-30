<%@ page isErrorPage="true" %>
<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="tpdb"%>
<tpdb:page name="error">
	<tpdb:content>
		<article>
			<h2>Houston, we have a problem.</h2>
			<p>We're sorry, but the server had a problem handling your
				request. A report has been generated and sent to the admin.</p>
			${Utils:reportError(request, pageContext.exception)}			
		</article>
	</tpdb:content>
	<tpdb:sidebar />
</tpdb:page>