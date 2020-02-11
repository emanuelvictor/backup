<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Consultar Serviço</title>
<script type="text/javascript">
	function find() {
		document.getElementById("newAction").value = "find";
		document.getElementById("frm").submit();
	}
	function findProduct() {
		document.getElementById("newAction").value = "find";
		document.getElementById("frmproduct").submit();
	}
	function clean() {
		document.getElementById("newAction").value = "goFind";
		document.getElementById("frm").submit();
		document.getElementById("frmproduct").submit();
	}
	function remove(id) {
		if (confirm("Deseja apagar o registro?")) {
			document.getElementById("id").value = id;
			document.getElementById("newAction").value = "delete";
			document.getElementById("frm").submit();
		}
	}
	function detail(id) {
		document.getElementById("id").value = id;
		document.getElementById("newAction").value = "detail";
		document.getElementById("frm").submit();
	}
	function goNew() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}
</script>
<%
	SS sSFilter = (SS) request.getSession()
	.getAttribute("filterService");
	if (sSFilter == null) {
		sSFilter = new SS();
	}
	Object list[] = (Object[]) request.getAttribute("list");
%>
</head>
<body>
	<div id="title">Consulta de Serviço</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Service" />
		<input type="hidden" name="id" id="id" />
		<div class="label">Código:</div>
			<input class="field" type="text" name="name" id="name" maxlength="100" value="<%=(sSFilter.getIdSS() != null) ? sSFilter.getIdSS() : ""%>" />
		<div class="label">Descrição:</div>
			<input class="field" type="text" name="name" id="name" maxlength="100" value="<%=(sSFilter.getDescription() != null) ? sSFilter.getDescription() : ""%>" />
		<div  id="menu" align="left" >
			<ul>
				<li ><a href="javascript:find();">Consultar</a></li>
				<li><a href="javascript:goNew();">Cadastrar</a></li>
				<li><a href="javascript:clean();">Limpar</a></li>
			</ul>
		</div>
			<table id="table" cellspacing="0" cellpadding="0">
				<tr>
					<th style="width: 40px;text-align: left;">Código</th>
					<th style="width: 295px;text-align: left;">Descrição</th>
					<th style="width: 325px;text-align: left;">Valor/hora</th>
					<th></th>
				</tr>
				<%
					if (list != null) {
								for (int i = 0; i < list.length; i++) {
									SS sSAux = (SS) list[i];
				%>
				<tr>
					<td><%=sSAux.getIdSS()%></td>
					<td><%=sSAux.getDescription()%></td>
					<td><%=sSAux.getValueHour()%></td>
					<td style="text-align: center;">
					<a class="link" href="javascript:detail(<%=sSAux.getIdSS()%>);"><img src="./img/edit.png"></a>
 					&nbsp;
 				    <a class="link" href="javascript:findProduct(<%=sSAux.getProduct()%>);"><img src="./img/search.png"></a>
					&nbsp;
					<a class="link" href="javascript:remove(<%=sSAux.getIdSS()%>);"><img src="./img/delete.png"></a></td>
			</tr>

				<%
					}
							}
				%>
			</table>
			</form>
	
	<div id="label">Lista de produtos</div>
	<form name="frmproduct" id="frmproduct" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Product" />
		<input type="hidden" name="id" id="id" />
			<table id="table" cellspacing="0" cellpadding="0">
				<tr>
					<th style="width:40px;text-align: left;">Código</th>
					<th style="width:150px;text-align: left;">Placa</th>
					<th style="width:125px;text-align: left;">Chassí</th>
					<th style="width:100px;text-align: left;">Modelo</th>
					<th style="width:300px;text-align: left;">Cliente</th>
				</tr>
				<%
					if (list != null) {
						for (int i = 0; i < list.length; i++) {
							SS sSAux = (SS) list[i];
				%>
				<tr>
					<td><%=sSAux.getIdSS()%></td>
					<td><%=sSAux.getDescription()%></td>
					<td><%=sSAux.getValueHour()%></td>
					<td style="text-align: center;">
				</tr>

				<%
					}
					}
				%>
			</table>
			</form>
</body>
</html>