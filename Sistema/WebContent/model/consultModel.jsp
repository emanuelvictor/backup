<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<title>Consultar Modelo</title>
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
	Model modelFilter = (Model) request.getSession().getAttribute("filterModel");
	if (modelFilter == null) {
		modelFilter = new Model();
		modelFilter.setBrand(new Brand());
	}
	if (modelFilter.getBrand() == null) {
		modelFilter.setBrand(new Brand());
	}
	Object list[] = (Object[]) request.getAttribute("list");
	Object listBrand[] = (Object[]) request.getAttribute("listBrand");
%>
</head>
<body>
	<div id="title">Consultar Modelo</div>
	<Form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction"/>
		<input type="hidden" name="entityName" id="entityName" value="Model"/>
		<input type="hidden" name="id" id="id"/>
		<div class="label">Modelo:</div>
		<div><input class="field" type="text" name="description" id="description" maxlength="100" value="<%=(modelFilter.getDescription() != null) ? modelFilter.getDescription() : ""%>"/></div>
		<div class="label">Marca:</div>			
		<select class="field" name="brand" id="brand">
			<option value="">&nbsp;</option>
			<%
					if (listBrand != null) {			
						for(int i=0; i<listBrand.length; i++) {
							Brand brand = (Brand) listBrand[i];
							boolean bSelected = modelFilter.getBrand().getIdBrand() == brand.getIdBrand(); 
			%>
					<option <%=bSelected ? "selected" : "" %> value="<%=brand.getIdBrand()%>"><%=brand.getDescription()%></option>
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
					<th >Código</th>
					<th style="width:360px;text-align: left;">Modelo</th>
					<th style="width:360px;text-align: left;">Marca</th>
					<th></th>
				</tr>			
				<%
					if (list != null) {			
						for(int i=0; i<list.length; i++) {
							Model modelAux = (Model) list[i];
				%>	
				<tr>
					<td><%=modelAux.getIdModel()%></td>
					<td><%=modelAux.getDescription()%></td>
					<td><%=modelAux.getBrand().getDescription()%></td>
					<td style="text-align:center;">
						<a class="link" href="javascript:detail(<%=modelAux.getIdModel()%>);"><img src="./img/edit.png"></a>
						&nbsp;
						<a class="link" href="javascript:deleteList(<%=modelAux.getIdModel()%>);"><img src="./img/delete.png"></a>
					</td>
				</tr>
<%
		}
	}
%>		
			</table>
	</Form>
</body>
</html>