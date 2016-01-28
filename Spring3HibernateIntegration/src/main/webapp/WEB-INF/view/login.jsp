<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
	<body onload='document.loginForm.username.focus();'>
		<h1 id="banner">Login to Adfly Traffic Exchange</h1>  
		
		<form name="loginForm" action="<c:url value='j_spring_security_check'/>"
					method="POST">
			<table>
				<tr>
					<td>Username:</td>
					<td><input type='text' name='j_username' /></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password'></td>
				</tr>
				<tr>
				<td><a href="signup">Register</a></a></td>
				<td><a href="">ForgetPassword</a></a></td>
				</tr>
				<tr>
					<td colspan='2'><input name="submit" type="submit">&nbsp;<input name="reset" type="reset"></td>
				</tr>
			</table>
			<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
			
		</form>
	</body>
</html>