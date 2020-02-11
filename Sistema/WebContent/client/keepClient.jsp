<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Cliente Pessoa Física</title>
<script type="text/javascript">
	function save() {
		if ((document.getElementById("name").value != "")&&(document.getElementById("cpf").value != "")) {
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
		if ((document.getElementById("idclient").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idclient").value;
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
	Client client = (Client) request.getAttribute("object");
	if (client == null) {
		client = new Client();
	}
%>
</head>
<body>
	<div id="title">Cadastro de Cliente Pessoa Física</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Client" /> 
		<input type="hidden" name="id" id="id" />
		<div class="label">Código:</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idclient" id="idclient" value="<%=(client.getIdClient() == null) ? "" : client.getIdClient()%>" />
		</div>
		<div class="label">Nome:*</div>
		<div>
			<input class="field" type="text" name="name" id="name" maxlength="100" value="<%=(client.getName() == null) ? "" : client.getName()%>" />
		</div>
		<div class="label">RG:</div>
		<div>
			<input class="field" type="text" name="rg" id="rg" maxlength="100" value="<%=(client.getRg() == null) ? "" : client.getRg()%>" />
		</div>
		<div class="label">CPF:*</div>
		<div>
			<input class="field" type="text" name="cpf" id="cpf" maxlength="100" value="<%=(client.getCpf() == null) ? "" : client.getCpf()%>" />
		</div>
		<div class="label">Endereço:</div>
		<div>
			<input class="field" type="text" name="address" id="address" maxlength="100" value="<%=(client.getAddress() == null) ? "" : client.getAddress()%>" />
		</div>
		<div class="label">CEP:</div>
		<div>
			<input class="field" type="text" name="cep" id="cep" maxlength="100" value="<%=(client.getCep() == null) ? "" : client.getCep()%>" />
		</div>
		<div class="label">Email:</div>
		<div>
			<input class="field" type="text" name="email" id="email" maxlength="100" value="<%=(client.getEmail() == null) ? "" : client.getEmail()%>" />
		</div>
		<div class="label">Telefone:</div>
		<div>
			<input class="field" type="text" name="phone" id="phone" maxlength="100" value="<%=(client.getPhone() == null) ? "" : client.getPhone()%>" />
		</div>
		<div class="label">Celular:</div>
		<div>
			<input class="field" type="text" name="cellular" id="cellular" maxlength="100" value="<%=(client.getCellular() == null) ? "" : client.getCellular()%>" />
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