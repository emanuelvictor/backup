
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Vehicle,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand, br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Veículo</title>
<script type="text/javascript">
	function save() {
		if ((document.getElementById("chassis").value != "")&&(document.getElementById("plaque").value != "")
			&&(document.getElementById("plaque").value != null)&&(document.getElementById("plaque").value != null)){
			if((document.getElementById("client").value != "")&&(document.getElementById("client").value != null)
				&&(document.getElementById("model").value != "")&&(document.getElementById("model").value != null)){
					document.getElementById("newAction").value = "save";
					document.getElementById("frm").submit();	
				}
			else{
				alert("Selecione um cliente/proprietário e um modelo");
			}
		}else
			alert("Não pode salvar em branco");
	}
	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();		
	}
	function remover() {
		if ((document.getElementById("idveiculo").value != "") && 
		    (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document.getElementById("idvehicle").value;
			document.getElementById("newAction").value = "delete";
			document.getElementById("frm").action = "consultVehicle.jsp";
			document.getElementById("frm").submit();
		}
	}
	function goFind() {
		document.getElementById("newAction").value = "goFind";
		document.getElementById("frm").submit();
	}
</script>
<%
	Vehicle veiculo = (Vehicle) request.getAttribute("object");
	if (veiculo == null) {
		veiculo = new Vehicle();
		veiculo.setModel(new Model());
		veiculo.setClient(new Client());
	}
	Object listModel[] = (Object[]) request.getAttribute("listModel");
	Object listClient[] = (Object[]) request.getAttribute("listClient");
%>
</head>
<body>
	<div id="title">Cadastro de Veículo</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction"/>
		<input type="hidden" name="entityName" id="entityName" value="Vehicle"/>
		<input type="hidden" name="id" id="id"/>
		<div class="label">Código:</div>	
		<div><input class="fieldDisabled" readonly type="text" name="idvehicle" id="idvehicle" value="<%=(veiculo.getIdVehicle() == null) ? "" : veiculo.getIdVehicle()%>"/></div>
		<div class="label">Placa:*</div>	
		<div><input class="field" type="text" name="plaque" id="plaque" maxlength="100" value="<%=(veiculo.getPlaque() == null) ? "" : veiculo.getPlaque()%>"/></div>
		<div class="label">Chassí:*</div>	
		<div><input class="field" type="text" name="chassis" id="chassis" maxlength="100" value="<%=(veiculo.getChassis() == null) ? "" : veiculo.getChassis()%>"/></div>
		<div class="label">Cliente:*</div>		
			<select class="field" name="client" id="client">
				<option value="">&nbsp;</option>
<%
	if (listClient != null) {			
		for(int i=0; i<listClient.length; i++) {
			Client client = (Client) listClient[i];
			boolean bSelected = veiculo.getClient().getIdClient() == client.getIdClient(); 
%>
				<option <%=bSelected ? "selected" : "" %> value="<%=client.getIdClient()%>"><%= client.getName() != null ? client.getName() : client.getFantasyName()  %></option>
<%
		}
	}
%>
			</select>
		<div class="label">Modelo:*</div>		
		<select class="field" name="model" id="model">
				<option value="">&nbsp;</option>
<%
	if (listModel != null) {			
		for(int i=0; i<listModel.length; i++) {
			Model model = (Model) listModel[i];
			boolean bSelected = veiculo.getModel().getIdModel() == model.getIdModel(); 
%>
				<option <%=bSelected ? "selected" : "" %> value="<%=model.getIdModel()%>"><%=model.getDescription() %>   |   <%=model.getBrand().getDescription()%></option>
<%
		}
	}
%>
			</select>
		<br/>
		<div  id="menu" align="left" >
			<ul>
				<li ><a href="javascript:save();">Salvar</a></li>
				<li><a href="javascript:clean();">Limpar</a></li>
				<li><a href="javascript:remover();">Excluir</a></li>
				<li><a href="javascript:goFind();">Voltar</a></li>
			</ul>
		</div>
		<br/>
	</form>
</body>
</html>