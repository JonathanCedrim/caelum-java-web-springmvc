<%@ tag language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="dao" required="true"%>


<c:forEach var="contato" items="${dao.getcontatos()}" varStatus="id">
	<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">
		<td>${id.count}</td>
		<td><c:if test="${not empty contato.getValue().nome}">
									${contato.getValue().nome}
			</c:if> <c:if test="${empty contato.getValue().nome}">
				<strong>Vazio</strong>
			</c:if></td>

		<td>${contato.getValue().email}</td>
		<td>${contato.getValue().endereco}</td>
		<td><fmt:formatDate
				value="${contato.getValue().dataNascimento.time}" pattern="dd/MM/y" /></td>
		<td><a href="mvc?logica=RemoveContatoLogica&id=${contato.id}">Remover</a>
		</td>
	</tr>
</c:forEach>
