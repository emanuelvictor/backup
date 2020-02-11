<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Cadastro de Produto</title>

<script type="text/javascript">
	function save() {
		if ((document.getElementById("description").value != ""))
			if((document.getElementById("supplier").value != ""))
				if((document.getElementById("model").value != "")){
					if((document.getElementById("value").value != "")){
						document.getElementById("value").value = 0;
					}
					if((document.getElementById("units").value != "")){
						document.getElementById("units").value = 0;
					}
					document.getElementById("newAction").value = "save";
					document.getElementById("frm").submit();
				}else{
					alert("Tem que selecionar um modelo");
			}else{
				alert("Tem que selecionar um fornecedor");
		}else{
			alert("Não pode salvar a descrição em branco");
		}
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
	if (product.getModel() == null) {
		product.setModel(new Model());
	}
	if (product.getModel().getBrand() == null) {
		product.getModel().setBrand(new Brand());
	}
	if (product.getSupplier() == null) {
		product.setSupplier(new Supplier());
	}
	Object list[] = (Object[]) request.getAttribute("list");
	Object listModel[] = (Object[]) request.getAttribute("listModel");
	Object listSupplier[] = (Object[]) request.getAttribute("listSupplier");
	Object listBrand[] = (Object[]) request.getAttribute("listBrand");
%>

</head>
<body>
	<div id="title">Cadastro de Produto</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Product" /> 
		<input type="hidden" name="id" id="id" />
		<div class="label">Código</div>
		<div>
			<input class="fieldDisabled" readonly type="text" name="idproduct" id="idproduct" value="<%=(product.getIdProduct() == null) ? "" : product.getIdProduct()%>" />
		</div>
		<div class="label">Descrição:*</div>
		<div>
			<input class="field" type="text" name="description" id="description"
				maxlength="100"
				value="<%=(product.getDescription() == null) ? "" : product.getDescription()%>" />
		</div>
			<div class="label">Fornecedor:*</div>			
			<select class="field" name="supplier" id="supplier">
					<option value="">&nbsp;</option>
					<%
							if (listBrand != null) {
		 						for(int i=0; i<listSupplier.length; i++) {
		 							Supplier supplier = (Supplier) listSupplier[i];
		 							boolean bSelected = product.getSupplier().getIdSupplier() == supplier.getIdSupplier(); 
					%>
							<option <%=bSelected ? "selected" : "" %> value="<%=supplier.getIdSupplier()%>"><%=supplier.getFantasyName()%></option>
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
							Model model= (Model) listModel[i];
							boolean bSelected = product.getModel().getIdModel() == model.getIdModel(); 
			%>
					<option <%=bSelected ? "selected" : "" %> value="<%=model.getIdModel()%>"><%=model.getDescription() %>   |   <%=model.getBrand().getDescription()%></option>
			<%
						}
					}
			%>
			</select>
	
		<div class="label">Valor</div>
		<div>
			<input class="field" type="text" name="value" id="value" maxlength="100" value="<%=(product.getValue()== null) ? "" : product.getValue()%>" />
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