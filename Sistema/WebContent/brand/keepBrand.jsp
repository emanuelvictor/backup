<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" 	href="./css/bootstrap/css/bootstrap.min.css">
<script type="text/javascript">
	function save() {
		if ((document.getElementById("description").value != "")){
			document.getElementById("newAction").value = "save";
			document.getElementById("frm").submit();
		}else
			alert("Não pode salvar em branco");
	}
	function clean() {
// 		window.location.href = "/brand/keepBrand.jsp";		
		document.getElementById("newAction").value = "goNew";
		document.getElementById("frm").submit();
	}
	function remover() {
		if ((document.getElementById("idbrand").value != "")
				&& (confirm("Deseja apagar o registro?"))) {
			document.getElementById("id").value = document
					.getElementById("idbrand").value;
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
	Brand brand = (Brand) request.getAttribute("object");
	if (brand == null) {
		brand = new Brand();
	}
%>
</head>
<body>
	<!-- TItle -->
	<div class="page-header">
  		<h1>Cadastro Marca</h1>
 	</div>
 	
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction" />
		<input type="hidden" name="entityName" id="entityName" value="Brand" /> 
		<input type="hidden" name="id" id="id" />
		
		
<div class="panel panel-default">
  <div class="panel-heading">Marca</div>
  
  <div class="panel-body">
		<span class="label label-default">Código</span>
			<div class="input-group">
				<input class="form-control" id="disabledInput" type="text" placeholder="Código" disabled name="idbrand" id="idbrand" value="<%=(brand.getIdBrand() == null) ? "" : brand.getIdBrand()%>">
			</div>
		<br/>
		<span class="label label-default">Descrição</span>
			<div class="input-group">
				<input class="form-control" id="disabledInput" type="text" placeholder="Descrição" name="idbrand" id="idbrand" value="<%=(brand.getDescription() == null) ? "" : brand.getDescription()%>" name="description" id="description" maxlength="100" value="<%=(brand.getDescription() == null) ? "" : brand.getDescription()%>">
			</div>
		<br/>
	

		<button type="button" class="btn btn-default" ><a href="javascript:save();">Salvar</a></button>
		<button type="button" class="btn btn-default"><a href="javascript:clean();">Limpar</a></button>
		<button type="button" class="btn btn-default"><a href="javascript:remover();">Excluir</a></button>
		<button type="button" class="btn btn-default"><a href="javascript:goFind();">Voltar</a></button>
		
</div>
</div>
	</form>
</body>
</html>