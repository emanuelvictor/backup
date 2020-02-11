<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" 	href="./css/bootstrap/css/bootstrap.min.css">
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
	Brand brandFilter = (Brand) request.getSession().getAttribute(
			"filterBrand");
	if (brandFilter == null) {
		brandFilter = new Brand();
	}
	Object list[] = (Object[]) request.getAttribute("list");
%>
</head>
<body>
<!-- TItle -->
	<div class="page-header">
  		<h1>Consultar Marca <!-- <small>Pesquise a marca pela descrição</small> --></h1>
 	</div>
 	
 <form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" /> 
		<input type="hidden" name="entityName" id="entityName" value="Brand" />
		<input type="hidden" name="id" id="id" />	
 	
 	
 	
 	<div class="panel panel-default">
  <div class="panel-heading">Marca</div>
  <div class="panel-body">
    
<div class="row">
  <div class="col-lg-6">
<!--    <span class="label label-default">Marca</span> -->
   <div class="input-group">
   		<input type="text" class="form-control" placeholder="Pesquisar por marca ..." name="description" id="description"
							value="<%=(brandFilter.getDescription() != null) ? brandFilter.getDescription() : ""%>"/>
      <span class="input-group-btn">
        <button type="button" class="btn btn-default" ><a href="javascript:find();">Consultar</a></button>
		<button type="button" class="btn btn-default"><a href="javascript:goNew();">Cadastrar</a></button>
		<button type="button" class="btn btn-default"><a href="javascript:clean();">Limpar</a></button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
 	<br/>
 	
<!-- Table -->
	
<!-- <br/> porque dois br ? :( -->
		<table class="table table-hover">
			<tr>
<!-- 			Usar isto para inadimplentes <td class="danger">...</td> -->
				<th>Código</th>
				<th style="width: 660px; text-align: left;">Descrição</th>
				<th></th>
			</tr>
			<%
				if (list != null) {
					for (int i = 0; i < list.length; i++) {
						Brand brandAux = (Brand) list[i];
			%>
			<tr>
				<td><%=brandAux.getIdBrand()%></td>
				<td><%=brandAux.getDescription()%></td>
				<td style="text-align: center;">
					<a href="javascript:detail(<%=brandAux.getIdBrand()%>);">
						<span class="glyphicon glyphicon-pencil"></span>
					</a> 
						&nbsp; 
					<a href="javascript:deleteList(<%=brandAux.getIdBrand()%>);">
						<span class="glyphicon glyphicon-trash"></span>
					</a>
				</td>
			</tr>
			<%
				}
				}
			%>
		</table>
		 </div>
</div>
	</form>
</body>
</html>