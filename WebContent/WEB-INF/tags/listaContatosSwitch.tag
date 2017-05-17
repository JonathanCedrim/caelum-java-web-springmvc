<%@ tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ attribute name="dao" required="true"%>

<c:forEach var="contato" items="${dao.getContatos()}" varStatus="id">
	<tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }">

		<td>${id.count}</td>
		<c:choose>
			<c:when test="${not empty contato.getValue().nome}">
				<td>${contato.getValue().nome}</td>
			</c:when>
			<c:otherwise>
				<td><strong>Vazio </strong></td>
			</c:otherwise>
		</c:choose>

		<td>${contato.getValue().email}</td>
		<td>${contato.getValue().endereco}</td>
		<td><fmt:formatDate
				value="${contato.getValue().dataNascimento.time}" pattern="dd/MM/y" /></td>
		<td><a href="mvc?logica=RemoveContatoLogica&id=${contato.id}">Remover</a>
		</td>
	</tr>
</c:forEach>