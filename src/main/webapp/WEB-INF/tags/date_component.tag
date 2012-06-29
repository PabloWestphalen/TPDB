<%@tag language="java" pageEncoding="UTF-8"%>
<%@page import="java.util.Calendar, java.text.DateFormatSymbols" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@attribute name="tabindex" description="The tab index of the fields" required="false"%>
<%
String[] months = new DateFormatSymbols().getMonths();
int year = Calendar.getInstance().get(Calendar.YEAR);
%>
<select name="month" id="month" tabindex="${tabindex}">
  <c:forEach var="month" items="${months}" varStatus="n">
    <option value="${n}">${month}</option>
  </c:forEach>  
</select>
<select name="year" id="year" tabindex="${tabindex+1}">
  <c:forEach var="i" begin="1950" end="${year}">
    <option value="${i}">${i}</option>
  </c:forEach>        
</select>