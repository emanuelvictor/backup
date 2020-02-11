<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Product,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand, br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Supplier"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Consultar Produto</title>
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
			productFilter.setModel(new Model());
			productFilter.getModel().setBrand(new Brand());
			productFilter.setSupplier(new Supplier());
		}
		if (productFilter.getModel() == null) {
			productFilter.setModel(new Model());
		}
		if (productFilter.getModel().getBrand() == null) {
			productFilter.getModel().setBrand(new Brand());
		}
		if (productFilter.getSupplier() == null) {
			productFilter.setSupplier(new Supplier());
		}
		Object list[] = (Object[]) request.getAttribute("list");
		Object listModel[] = (Object[]) request.getAttribute("listModel");
		Object listSupplier[] = (Object[]) request.getAttribute("listSupplier");
		Object listBrand[] = (Object[]) request.getAttribute("listBrand");
	%>
</head>
<body>
	<div id="title">Consulta de Produto</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" /> 
		<input type="hidden" name="entityName" id="entityName" value="Product" />
		<input type="hidden" name="id" id="id" />
		<div class="label">Descrição:</div>
			<input class="field" type="text" name="description" id="description" maxlength="100" value="<%=(productFilter.getDescription() != null) ? productFilter.getDescription() : ""%>" />
		<div class="label">Fornecedor:</div>			
			<select class="field" name="supplier" id="supplier">
					<option value="">&nbsp;</option>
					<%	if (listSupplier != null) {
		 						for(int i=0; i<listSupplier.length; i++) {
		 							Supplier supplier = (Supplier) listSupplier[i];
		 							boolean bSelected = productFilter.getSupplier().getIdSupplier() == supplier.getIdSupplier(); 
					%>
							<option <%=bSelected ? "selected" : "" %> value="<%	plier.getIdSupplier()%>"><%=supplier.getFantasyName()%></option>
					<%
		 						}
							}
					%>
			</select>
		<div class="label">Modelo:</div>			
		<select class="field" name="model" id="model">
			<option value="">&nbsp;</option>
			<%
					if (listModel != null) {			
						for(int i=0; i<listModel.length; i++) {
							Model model= (Model) listModel[i];
							boolean bSelected = productFilter.getModel().getIdModel() == model.getIdModel(); 
			%>
					<option <%=bSelected ? "selected" : "" %> value="<%=model.getIdModel()%>"><%=model.getDescription()%></option>
			<%
						}
					}
			%>
			</select>
			<div class="label">Marca:</div>			
			<select class="field" name="brand" id="brand">
					<option value="">&nbsp;</option>
					<%
							if (listBrand != null) {
		 						for(int i=0; i<listBrand.length; i++) {
		 							Brand brand = (Brand) listBrand[i];
		 							boolean bSelected = productFilter.getModel().getBrand().getIdBrand() == brand.getIdBrand(); 
					%>
							<option <%=bSelected ? "selected" : "" %> value="<%=brand.getDescription()%>"><%=brand.getDescription()%></option>
					<%
		 						}
							}
					%>
			</select>
		
		
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
					<th style="width: 100px;text-align: left;">Descrição</th>
					<th style="width: 100px;text-align: left;">Modelo</th>
					<th style="width: 100px;text-align: left;">Marca</th>
					<th style="width: 100px;text-align: left;">Fornecedor</th>
					<th style="width: 100px;text-align: left;">Unidades</th>
					<th style="width: 100px;text-align: left;">Valor</th>
					<th style="width: 40px;text-align: left;"></th>
				</tr>
				<%
					if (list != null) {
						for (int i = 0; i < list.length; i++) {
							Product productAux = (Product) list[i];
				%>
				<tr>
					<td><%=productAux.getIdProduct()%></td>
					<td><%=productAux.getDescription()%></td>
					<td><%=productAux.getModel().getDescription()%></td>
					<td><%=productAux.getModel().getBrand().getDescription()%></td>
					<td><%=productAux.getSupplier().getFantasyName()%></td>
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