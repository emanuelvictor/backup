<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Modelo</title>
<script type="text/javascript">
	function save() {
		if ((document.getElementById("description").value != "")){
			document.getElementById("newAction").value = "save";
			document.getElementById("frm").submit();
		}else
			alert("Não pode salvar em branco");
	}
	function clean() {
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();		
	}
	function deleteList(id) {
		if (confirm("Deseja apagar o registro?")) {
			document.getElementById("id").value = id;
			document.getElementById("newAction").value = "deleteList";
			document.getElementById("frm").submit();
		}		
	}
	function remover() {
		if ((document.getElementById("idmodel").value != "") && 
		    (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document.getElementById("idmodel").value;
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
	Model model = (Model) request.getAttribute("object");
	if (model == null) {
		model = new Model();
		model.setBrand(new Brand());
	}
	Object listBrand[] = (Object[]) request.getAttribute("listBrand");
%>
</head>
<body>
	<div id="title">Cadastro de Modelo</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction"/>
		<input type="hidden" name="entityName" id="entityName" value="Model"/>
		<input type="hidden" name="id" id="id"/>
		<div class="label">Código:</div>	
		<div><input class="fieldDisabled" readonly type="text" name="idmodel" id="idmodel" value="<%=(model.getIdModel() == null) ? "" : model.getIdModel()%>"/></div>
		<div class="label">Modelo:*</div>	
		<div><input class="field" type="text" name="description" id="description" maxlength="100" value="<%=(model.getDescription() == null) ? "" : model.getDescription()%>"/></div>
		<div class="label">Marca:</div>			
			<select class="field" name="brand" id="brand">
				<option value="">&nbsp;</option>
					<%
						if (listBrand != null) {			
							for(int i=0; i<listBrand.length; i++) {
								Brand brand = (Brand) listBrand[i];
								boolean bSelected = model.getBrand().getIdBrand() == brand.getIdBrand(); 
					%>
									<option <%=bSelected ? "selected" : "" %> value="<%=brand.getIdBrand()%>"><%=brand.getDescription()%></option>
					<%
							}
						}
					%>
			</select>
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