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
<%@taglib prefix="tag" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="fmt" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<meta lang="en">
<title>formulario</title>
</head>
<body>
	<h1>
		<spring:message code="tarefa.formulario.titulo" />
	</h1>
	<fieldset>
		<form action="adicionaTarefa" method="post">
			<strong> <spring:message code="tarefa.formulario.descricao" />
			</strong>
			<textarea name="descricao" rows="5" cols="100">
			</textarea>
			<br />
			<strong><spring:message code="tarefa.formulario.data" /></strong>
			<p><fmt:errors path="tarefa.descricao" /></p>
			<tag:campoData id="dataFinalizacao"/>
			<br /> 
			<input type="submit" value="salvar">
		</form>
	</fieldset>
</body>
</html>