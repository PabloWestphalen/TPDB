<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="var" description="The text to format." rtexprvalue="true" required="true"%>
<%@attribute name="maxlength" description="The limit of characters." rtexprvalue="true" type="java.lang.Integer" required="false" %>
<%@attribute name="html"  description="Wheter or not to convert to html." rtexprvalue="true" type="java.lang.Boolean" rtexprvalue="true"  required="false"%>
<c:if test="${not empty maxlength}">
<c:set var="var" value="${Utils:trim('aeae', maxlength}" />
</c:if>
<c:if test="${html}">
<c:set var="var" value="${Utils:cleanHtml(var)}" />
</c:if>
${var}