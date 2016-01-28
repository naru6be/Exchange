<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<style>
body {
    background-color: lightblue;
}
</style>
</head>
<body>
  <p>${success}</p>
 <h2>Adfly Traffic Exchange Register Screen</h2>
 <c:if  test="${!empty success}">
 <p>${success}</p>
 
 </c:if>
<form:form method="post" action="addUserData" commandName="userdata">
 
 
    <table>
    <tr>
        <td><form:label path="username"><spring:message code="label.username"/></form:label></td>
        <td><form:input path="username" /></td>
    </tr>
   
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
        <td><form:input path="password" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.add"/>"/>
        </td>
    </tr>
</table> 
</form:form>
 

<p>Note: Please make sure your details are correct before submitting form and that all fields marked with * are completed!.</p>
 

</body>
</html>