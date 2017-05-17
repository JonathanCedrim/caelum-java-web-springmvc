<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- jquery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- jquery-ui -->
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<meta lang="en"> 
<title>formulario</title>
</head>
<body>
	<h1>Adiciona Tarefa</h1>
	<fieldset>
		<form action="adicionaTarefa" method="post">
			<!-- <strong><fmt:message key="tarefa.tarefa-formulario.descricao"/></strong><br />-->
			<strong><spring:message code="tarefa.tarefa-formulario.descricao"></spring:message> </strong>
			
			<textarea name="descricao" rows="5" cols="100">
			</textarea>
			<tag:campoData id="dataFinalizacao"></tag:campoData>
			<br /> <input type="submit" value="salvar">
		</form>
	</fieldset>
</body>
</html>