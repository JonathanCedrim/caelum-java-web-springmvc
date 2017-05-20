<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta lang="en" charset="UTF-8" />
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>TAREFA</title>
</head>
<body>

	<h3>Altera Tarefa - ${tarefa.id}</h3>

	<form action="alteraTarefa" method="post">

		<input type="hidden" name="id" value="${tarefa.id}" />

		<p>Descrição:</p>
		<textarea name="descricao" rows="5" cols="100">
        	${tarefa.descricao}
        </textarea>
		<p>Finalizado?</p>
		<input type="checkbox" name="finalizado" value="true"
			${tarefa.finalizado? 'checked' : ''} />
		<p>Data de finalização</p>
		<input type="text" name="dataFinalizacao"
			value="<fmt:formatDate 
			value="${tarefa.dataFinalizacao.time}"
			pattern="dd/MM/yyyy" />" />
		<br /> <input type="submit" value="Alterar" />
	</form>
</body>
</html>