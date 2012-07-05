<%@tag pageEncoding="UTF-8"%>
<%@taglib uri="Utils" prefix="Utils" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="var" description="The text to format." required="true"%>
<%@attribute name="maxlength" description="The limit of characters." type="java.lang.Integer" required="false" %>
<%@attribute name="html"  description="Wheter or not to convert to html." type="java.lang.Boolean" rtexprvalue="true"  required="false"%>
<c:if test="${not empty maxlength}">
<c:set var="var" value="${Utils:trim(var, maxlength}" />
</c:if>
<c:if test="${html}">
<c:set var="var" value="${Utils:cleanHtml(var)}" />
</c:if>
${var}