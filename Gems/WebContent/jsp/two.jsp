<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.tg {
	border-collapse: collapse;
	border-spacing: 0;
	border-color: #999;
}

.tg td {
	font-family: Arial, sans-serif;
	font-size: 14px;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #999;
	color: #444;
	background-color: #F7FDFA;
}

.tg th {
	font-family: Arial, sans-serif;
	font-size: 14px;
	font-weight: normal;
	padding: 10px 5px;
	border-style: solid;
	border-width: 1px;
	overflow: hidden;
	word-break: normal;
	border-color: #999;
	color: #fff;
	background-color: #26ADE4;
}

.tg .tg-phtq {
	background-color: #D2E4FC;
	border-color: inherit;
	text-align: left;
	vertical-align: top
}

.tg .tg-0pky {
	border-color: inherit;
	text-align: left;
	vertical-align: top
}
</style>
<html>
<head>
<title>Submitted parameters</title>
</head>
<body>
	<p>Here are the submitted parameters value</p>
	<table class="tg">
		<tr>
			<th class="tg-0pky" rowspan="2">gem_id</th>
			<th class="tg-0pky" rowspan="2">gem_type</th>
			<th class="tg-0pky" rowspan="2">name</th>
			<th class="tg-0pky" rowspan="2">origin</th>
			<th class="tg-0pky" colspan="3">visual_parameters</th>
			<th class="tg-0pky" rowspan="2">weight</th>
			<th class="tg-0pky" rowspan="2">price</th>
			<th class="tg-0pky" colspan="3">additional characteristics</th>
		</tr>
		<tr>
			<td class="tg-phtq">faces</td>
			<td class="tg-phtq">color</td>
			<td class="tg-phtq">clarity</td>
			<td class="tg-phtq">preciousness</td>
			<td class="tg-phtq">method</td>
			<td class="tg-phtq">creationDate</td>
		</tr>
		<tr>
			<c:forEach var="elem" items="${gems }" varStatus="status">
				<tr>
					<td class="tg-0pky"><c:out value="${ elem.gemId }" /></td>
					<td class="tg-0pky"><c:out
							value="${ elem['class'].simpleName }" /></td>
					<td class="tg-0pky"><c:out value="${ elem.name }" /></td>
					<td class="tg-0pky"><c:out value="${ elem.origin }" /></td>
					<td class="tg-0pky"><c:out
							value="${ elem.visualParameters.faces }" /></td>
					<td class="tg-0pky"><c:out
							value="${ elem.visualParameters.color }" /></td>
					<td class="tg-0pky"><c:out
							value="${ elem.visualParameters.clarity }" /></td>
					<td class="tg-0pky"><c:out value="${ elem.weight }" /></td>
					<td class="tg-0pky"><c:out value="${ elem.price }" /></td>
					<c:choose>
						<c:when test="${elem['class'].simpleName=='PreciousGem'}">
							<td class="tg-0pky"><c:out value="${ elem.preciousness }" /></td>
							<td class="tg-0pky"></td>
							<td class="tg-0pky"></td>
						</c:when>
						<c:otherwise>
							<td class="tg-0pky"></td>
							<td class="tg-0pky"><c:out value="${ elem.method }" /></td>
							<td class="tg-0pky"><c:out value="${ elem.creationDate.time}" /></td>

						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
	</table>
</body>
</html>