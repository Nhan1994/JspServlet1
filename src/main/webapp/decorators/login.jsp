<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><decorator:title default="Trang chủ" /></title>

	<!-- css -->
	<link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all" />
	<link href="<c:url value='/template/web/css/style.css' />" rel="stylesheet" type="text/css" media="all" />
	<link href="<c:url value='/template/login/style.css' />" rel="stylesheet" type="text/css" media="all" />
</head>
<body id="LoginForm">
	<div class="container">
		<decorator:body />
	</div>
</body>
</html>