<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Ordem de Serviço</title>

<script type="text/javascript">
	function save() {
		if ((document.getElementById("description").value != "")) {
			document.getElementById("newAction").value = "save";
			document.getElementById("frm").submit();
		} else
			alert("Não pode salvar em branco");
	}

	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}

	function remover() {
		if ((document.getElementById("idproduct").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idproduct").value;
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
	Product product = (Product) request.getAttribute("object");
	if (product == null) {
		product = new Product();
	}
%>

</head>
<body>
	<div id="title">Cadastro de Ordem de Serviço</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" /> <input
			type="hidden" name="entityName" id="entityName" value="Product" /> <input
			type="hidden" name="id" id="id" />
		<div class="label">Código</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idproduct"
				id="idproduct"
				value="<%=(product.getIdProduct() == null) ? "" : product
					.getIdProduct()%>" />
		</div>
		<div class="label">Descrição *</div>
		<div>
			<input class="field" type="text" name="description" id="description"
				maxlength="100"
				value="<%=(product.getDescription() == null) ? "" : product.getDescription()%>" />
		</div>
		<div class="label">Modelo</div>
		<div>
			<input class="field" type="text" name="model" id="model" maxlength="100"
				value="<%=(product.getModel() == null) ? "" : product.getModel()%>" />
		</div>
		<div class="label">Marca</div>
		<div>
			<input class="field" type="text" name="brand" id="brand" maxlength="100"
				value="<%=(product.getBrand() == null) ? "" : product.getBrand()%>" />
		</div>
		<div class="label">Valor</div>
		<div>
			<input class="field" type="text" name="value" id="value"
				maxlength="100"
				value="<%=(product.getValue()== null) ? "" : product.getValue()%>" />
				
		</div>
		<div class="label">Unidades</div>
			<input class="field" type="text" name="units" id="units" maxlength="100" value="<%=(product.getUnits()== null) ? "" : product.getUnits()%>" />
		<div  id="menu" align="left" >
			<ul>
				<li ><a href="javascript:save();">Salvar</a></li>
				<li><a href="javascript:clean();">Limpar</a></li>
				<li><a href="javascript:remover();">Excluir</a></li>
				<li><a href="javascript:goFind();">Voltar</a></li>
			</ul>
		</div>
	</form>
</body>
</html>