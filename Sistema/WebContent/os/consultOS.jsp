<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Consultar Ordem de Serviço</title>
<script type="text/javascript">
	function find() {
		document.getElementById("newAction").value = "find";
		document.getElementById("frm").submit();
	}
	function clean() {
		document.getElementById("newAction").value = "goFind";
		document.getElementById("frm").submit();
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
	Product productFilter = (Product) request.getSession().getAttribute("filterProduct");
	if (productFilter == null) {
		productFilter = new Product();
	}
	Object list[] = (Object[]) request.getAttribute("list");
%>
</head>
<body>
	<div id="title">Consulta de Ordem de Serviço</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="OS" />
		<input type="hidden" name="id" id="id" />
		<div class="label">Descrição:</div>
			<input class="field" type="text" name="name" id="name" maxlength="100" value="<%=(productFilter.getDescription() != null) ? productFilter.getDescription() : ""%>" />
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
					<th style="width: 200px;text-align: left;">Descrição</th>
					<th style="width: 200px;text-align: left;">Modelo</th>
					<th style="width: 100px;text-align: left;">Marca</th>
					<th style="width: 100px;text-align: left;">Unidades</th>
					<th style="width: 100px;text-align: left;">Valor</th>
					<th></th>
				</tr>
				<%
				
				// TODO recriar filtros para que não haja atributo auxiliar nas tabelas veículo e produto
					if (list != null) {
						for (int i = 0; i < list.length; i++) {
							Product productAux = (Product) list[i];
				%>
				<tr>
					<td><%=productAux.getIdProduct()%></td>
					<td><%=productAux.getDescription()%></td>
					<td><%=productAux.getModel()%></td>
					<td><%=productAux.getModel().getBrand()%></td>
					<td><%=productAux.getUnits()%></td>
					<td><%=productAux.getValue()%></td>
					<td style="text-align: center;">
					<a class="link" href="javascript:detail(<%=productAux.getIdProduct()%>);"><img src="./img/edit.png"></a>
 					&nbsp;
 				    <a class="link" href="javascript:remove(<%=productAux.getIdProduct()%>);"><img src="./img/delete.png"></a></td>
				</tr>

				<%
					}
					}
				%>
			</table>
			</form>
</body>
</html>