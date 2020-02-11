<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Fornecedor</title>
<script type="text/javascript">
	function find() {
		document.getElementById("newAction").value = "find";
		document.getElementById("frm").submit();		
	}
	function clean() {
		document.getElementById("newAction").value = "goFind";
		document.getElementById("frm").submit();
	}
	function deleteList(id) {
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
	Supplier supplierFilter = (Supplier) request.getSession().getAttribute("filterSupplier");
	if (supplierFilter == null) {
		supplierFilter = new Supplier();
	}
	Object list[] = (Object[]) request.getAttribute("list");
%>
</head>
<body>
<div id="title">Consultar Fornecedor</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction"/>
		<input type="hidden" name="entityName" id="entityName" value="Supplier"/>
		<input type="hidden" name="id" id="id"/>
		<div class="label">Nome fantasia:</div>	
		<input class="field" type="text" name="fantasyname" id="fantasyname" maxlength="100" value="<%=(supplierFilter.getFantasyName() != null) ? supplierFilter.getFantasyName() : ""%>"/>
		<div class="label">CNPJ:</div>
		<input class="field" type="text" name="cnpj" id="cnpj" maxlength="100" value="<%=(supplierFilter.getCNPJ() == null) ? "" : supplierFilter.getCNPJ()%>" />
		<div class="label">Registro Estadual:</div>
		<input class="field" type="text" name="stateregistration" id="stateregistration" maxlength="100" value="<%=(supplierFilter.getStateRegistration() == null) ? "" : supplierFilter.getStateRegistration()%>" />
		<div  id="menu" align="left" >
			<ul>
				<li ><a href="javascript:find();">Consultar</a></li>
				<li><a href="javascript:goNew();">Cadastrar</a></li>
				<li><a href="javascript:clean();">Limpar</a></li>
			</ul>
		</div>
			<table id="table" cellspacing="0" cellpadding="0">
				<tr>
					<th style="width:40px;text-align: left;">Código</th>
					<th style="width:100px;text-align: left;">Nome Fantasia</th>
					<th style="width:100px;text-align: left;">CNPJ</th>
					<th style="width:100px;text-align: left;">Telefone</th>
					<th style="width:100px;text-align: left;">Email</th>
					<th></th>
				</tr>
<%
    
	if (list != null) {			
		for(int i=0; i<list.length; i++) {
			Supplier supplierAux = (Supplier) list[i];
%>
				<tr>
					<td><%=supplierAux.getIdSupplier()%></td>
					<td><%=supplierAux.getFantasyName()%></td>
					<td><%=supplierAux.getCNPJ()%></td>
					<td><%=supplierAux.getPhone()%></td>
					<td><%=supplierAux.getEmail()%></td>
					<td style="text-align:center;">
						<a class="link" href="javascript:detail(<%=supplierAux.getIdSupplier()%>);"><img src="./img/edit.png"></a>
						&nbsp;
						<a class="link" href="javascript:deleteList(<%=supplierAux.getIdSupplier()%>);"><img src="./img/delete.png"></a>
					</td>
				</tr>
<%
		}
	}
%>
			</table>
	</form>
</body>
</html>