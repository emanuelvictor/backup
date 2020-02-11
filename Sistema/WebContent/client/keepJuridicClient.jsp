<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Cliente Pessoa Jurídica</title>
<script type="text/javascript">
	function save() {
		if ((document.getElementById("fantasyname").value != "")) {
			if((document.getElementById("corporatename").value != "")){
				if ((document.getElementById("cnpj").value != "")) {
					document.getElementById("newAction").value = "save";
					document.getElementById("frm").submit();
				} else {
					alert("Não pode salvar um CNPJ em branco");
				}
			}else{
				alert("Não pode salvar uma razão social em branco");
			}
		} else
			alert("Não pode salvar um nome fantasia em branco");
	}
	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}
	function remover() {
		if ((document.getElementById("idJuridicClient").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idJuridicClient").value;
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
	<div id="title">Cadastro de Cliente Pessoa Jurídica</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="JuridicClient" /> 
		<input type="hidden" name="id" id="id" />
		<div class="label">Código:</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idjuridicclient" id="idjuridicclient" value="<%=(client.getIdClient() == null) ? "" : client.getIdClient()%>" />
		</div>
		<div class="label">Nome Fantasia:*</div>
		<div>
			<input class="field" type="text" name="fantasyname" id="fantasyname" maxlength="100" value="<%=(client.getFantasyName() == null) ? "" : client.getFantasyName()%>" />
		</div>
		<div class="label">Razão Social:*</div>
		<div>
			<input class="field" type="text" name="corporatename" id="corporatename" maxlength="100" value="<%=(client.getCorporateName() == null) ? "" : client.getCorporateName()%>" />
		</div>
		<div class="label">CNPJ:*</div>
		<div>
			<input class="field" type="text" name="cnpj" id="cnpj" maxlength="100" value="<%=(client.getCNPJ() == null) ? "" : client.getCNPJ()%>" />
		</div>
		<div class="label">Registro estadual:</div>
		<div>
			<input class="field" type="text" name="stateregistration" id="stateregistration" maxlength="100" value="<%=(client.getStateRegistration() == null) ? "" : client.getStateRegistration()%>" />
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