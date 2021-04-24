<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<h1 class="form-heading">login Form</h1>
	<div class="login-form">
		<div class="main-div">
			<c:if test="${not empty message}">
				<div class="alert alert-${alert}" role="alert">
						${message}
				</div>
			</c:if>
			<div class="panel">
				<h2>Admin Login</h2>
				<p>Please enter your email and password</p>
			</div>
			<form action = "<c:url value ='/login' />" id="Login" method="post">
				<div class="form-group">
					<input type="text" class="form-control" id="userName" name="userName"
						placeholder="Email Address">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="password" name="password"
						placeholder="Password">
				</div>
				<div class="forgot">
					<a href="reset.html">Forgot password?</a>
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
				<input type="hidden" value="login" id="action" name="action"/>
			</form>
		</div>
		<p class="botto-text">Designed by Sunil Rajput</p>
	</div>
</body>
</html>