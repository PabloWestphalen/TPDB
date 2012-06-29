<%@tag language="java" pageEncoding="UTF-8" import="java.util.Calendar, java.text.DateFormatSymbols"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@attribute name="tabindex" description="The tab index of the fields" required="false"%>
<%
String[] months = new DateFormatSymbols().getMonths();
int year = Calendar.getInstance().get(Calendar.YEAR);
String months2 = "i'm a string within a scriptlet";
int year2 = 1999;
%>
<p>
months[1]: ${months[1]} <br />
year: ${year} <br />
months2: ${months2} <br />
year2: ${year2} <br />

<%= months2 %>
</p>
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