<%@tag language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="DateUtils" prefix="DateUtils" %>
<%@attribute name="tabindex" description="The tab index of the fields" required="false"%>
<select name="month" id="month" tabindex="${tabindex}">
  <option></option>
  <c:forEach var="month" items="${DateUtils:getMonths()}" varStatus="n">
    <option value="${n}">${month}</option>
  </c:forEach>  
</select>
<select name="year" id="year" tabindex="${tabindex+1}">
  <option></option>
  <c:forEach var="i" begin="${DateUtils:getYear()}" end="1990">
    <option value="${i}">${i}</option>
  </c:forEach>        
</select>