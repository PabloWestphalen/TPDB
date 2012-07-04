<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="Utils" prefix="Utils" %>
<%@attribute name="tabindex" description="The tab index of the fields" required="false"%>
<select name="month" id="month" tabindex="${tabindex}">
  <option>Month</option>
  <optgroup>  
  <c:forEach var="month" items="${Utils:getMonths()}" varStatus="n">
    <option value="${n.index}">${month}</option>
  </c:forEach>
  </optgroup>  
</select>
<select name="year" id="year" tabindex="${tabindex+1}">
  <option>Year</option>
  <optgroup>  
  <c:forEach var="i" begin="1990" end="${Utils:getYear()}">
    <option value="${i}">${i}</option>
  </c:forEach>
  </optgroup>        
</select>