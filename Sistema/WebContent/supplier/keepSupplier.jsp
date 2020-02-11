<%@ page language="java" contentType="text	/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Fornecedor</title>
<script type="text/javascript">
	function save() {
		if ((document.getElementById("fantasyname").value != "")&&
		(document.getElementById("stateregistration").value != "")&&
		(document.getElementById("cnpj").value != "")&&
		(document.getElementById("corporatename").value != "")) {
			document.getElementById("newAction").value = "save";
			document.getElementById("frm").submit();
		} else
			alert("Não pode salvar em branco o nome fantasia, registro estadual, cnpj ou razão social");
	}
	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}
	function remover() {
		if ((document.getElementById("idsupplier").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idsupplier").value;
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
	Supplier supplier = (Supplier) request.getAttribute("object");
	if (supplier == null) {
		supplier = new Supplier();
	}
%>
</head>
<body>
	<div id="title">Cadastro de Fornecedor</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Supplier" /> 
		<input type="hidden" name="id" id="id" />
		<div class="label">Código:</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idsupplier" id="idsupplier" value="<%=(supplier.getIdSupplier() == null) ? "" : supplier.getIdSupplier()%>" />
		</div>
		<div class="label">Nome Fantasia:*</div>
		<div>
			<input class="field" type="text" name="fantasyname" id="fantasyname" maxlength="100" value="<%=(supplier.getFantasyName() == null) ? "" : supplier.getFantasyName()%>" />
		</div>
		<div class="label">Razão Social:*</div>
		<div>
			<input class="field" type="text" name="corporatename" id="corporatename" maxlength="100" value="<%=(supplier.getCorporateName() == null) ? "" : supplier.getCorporateName()%>" />
		</div>
		<div class="label">CNPJ:*</div>
		<div>
			<input class="field" type="text" name="cnpj" id="cnpj" maxlength="100" value="<%=(supplier.getCNPJ() == null) ? "" : supplier.getCNPJ()%>" />
		</div>
		<div class="label">Registro Estadual:*</div>
		<div>
			<input class="field" type="text" name="stateregistration" id="stateregistration" maxlength="100" value="<%=(supplier.getStateRegistration() == null) ? "" : supplier.getStateRegistration()%>" />
		</div>
		<div class="label">CEP:</div>
		<div>
			<input class="field" type="text" name="cep" id="cep" maxlength="100" value="<%=(supplier.getCEP() == null) ? "" : supplier.getCEP()%>" />
		</div>
		<div class="label">Endereço:</div>
		<div>
			<input class="field" type="text" name="address" id="address" maxlength="100" value="<%=(supplier.getAddress() == null) ? "" : supplier.getAddress()%>" />
		</div>
		<div class="label">Email:</div>
		<div>
			<input class="field" type="text" name="email" id="email" maxlength="100" value="<%=(supplier.getEmail() == null) ? "" : supplier.getEmail()%>" />
		</div>
		<div class="label">Telefone:</div>
		<div>
			<input class="field" type="text" name="phone" id="phone" maxlength="100" value="<%=(supplier.getPhone() == null) ? "" : supplier.getPhone()%>" />
		</div>
		<div class="label">Celular:</div>
		<div>
			<input class="field" type="text" name="cellular" id="cellular" maxlength="100" value="<%=(supplier.getCellular() == null) ? "" : supplier.getCellular()%>" />
		</div>
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