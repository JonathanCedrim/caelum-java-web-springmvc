<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Tarefas</title>
</head>
<body>
	<a href="novaTarefa">Adicionar</a>

	<table>
		<tr>
			<th>ID</th>
			<th>DESCRIÇÃO</th>
			<th>FINALIZADO</th>
			<th>DATA DE FINALIZAÇÃO</th>			
			<th>ALTERAR</th>
			<th>REMOVER</th>
		</tr>
		<c:forEach items="${tarefas}" var="tarefa">
			<tr>
				<td>${tarefa.id}</td>
				<td>${tarefa.descricao}</td>
				<td><c:if test="${tarefa.finalizado eq true}">
					 	${tarefa.finalizado}
					 </c:if> <c:if test="${tarefa.finalizado eq false }">
						<strong>NAO</strong>
					</c:if></td>
				<td><c:choose>
						<c:when test="${not empty tarefa.dataFinalizacao.time}">
							<td><fmt:formatDate value="${tarefa.dataFinalizacao.time}"
									pattern="dd/MM/yyyy" /></td>

						</c:when>
						<c:otherwise>
						VAZIO	
						</c:otherwise>
					</c:choose></td>
					<td><a href="mostraTarefa?id=${tarefa.id}">Alterar</a></td>
					<td><a href="removeTarefa?id=${tarefa.id}">Remover</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>