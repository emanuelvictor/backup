<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Serviço</title>

<script type="text/javascript">
	function save() {
		if ((document.getElementById("description").value != "")) {
			document.getElementById("newAction").value = "save";
			document.getElementById("frm").submit();
		} else
			alert("Não pode salvar em branco");
	}

	function add() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}
	
	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}

	function remover() {
		if ((document.getElementById("idss").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idss").value;
			document.getElementById("newAction").value = "delete";
			document.getElementById("frm").submit();
		}
	}

	function goFind() {
		document.getElementById("newAction").value = "goFind";
		document.getElementById("frm").submit();
	}
</script>

<%
	SS sS = (SS) request.getAttribute("object");
	if (sS == null) {
		sS = new SS();
	}
%>

</head>
<body>
	<div id="title">Cadastro de Serviço</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" /> <input
			type="hidden" name="entityName" id="entityName" value="Service" /> <input
			type="hidden" name="id" id="id" />
		<div class="label">Código</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idss" id="idss"/>
		</div>
		<div class="label">Descrição *</div>
		<div>
		<div>
			<input class="field" type="text" name="description" id="description" maxlength="100" />
		</div>
		</div>
			<input class="button" type="button" value="Buscar Veículo" onclick="find();"/>
			<table id="table" cellspacing="0" cellpadding="0">
				<tr>
					<th style="width:50px;text-align: left;">Código</th>
					<th style="width:150px;text-align: left;">Placa</th>
					<th style="width:125px;text-align: left;">Chassí</th>
					<th style="width:100px;text-align: left;">Modelo</th>
					<th style="width:300px;text-align: left;">Cliente</th>
				</tr>
				<tr>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
				</tr>
		</table>
		<br/>
		<div class="label">Cadastrar Produtos</div>
		<select class="field" name="brand" id="brand">
				<option value="">&nbsp;</option>
				<option > </option>
			</select>
			Quantidade
			<input type="text" >
			Em estoque: 
			<input type="text" >
			<input class="button" type="button" value="Adicionar" onclick="add();">
			<br/>
		</form>
		<form name="frmproduct" id="frmproduct" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Product" />
		<input type="hidden" name="id" id="id" />
			<table id="table" cellspacing="0" cellpadding="0">
				<tr>
					<th style="width: 30px;text-align: left;">Código</th>
					<th style="width: 150px;text-align: left;">Descrição</th>
					<th style="width: 150px;text-align: left;">Modelo</th>
					<th style="width: 130px;text-align: left;">Marca</th>
					<th style="width: 140px;text-align: left;">Unidades</th>
					<th style="width: 140px;text-align: left;">Valor</th>
					<td style="text-align: center;"></td>
				</tr>
				<tr>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <td>
					  <a class="link" ><img src="./img/delete.png"></a></td>
				</tr>
				<tr>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <th>&nbsp;</th>
					  <td>
					  <a class="link" style="text-align: center;"><img src="./img/delete.png"></a></td>
				</tr>
			</table>
			</form>
		 	<div  id="menu" align="left" >
			<ul>
				<li ><a href="javascript:save();">Salvar</a></li>
				<li><a href="javascript:clean();">Limpar</a></li>
				<li><a href="javascript:remover();">Excluir</a></li>
				<li><a href="javascript:goFind();">Voltar</a></li>
			</ul>
		</div>
</body>
</html>