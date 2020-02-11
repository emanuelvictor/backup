<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="./css/style-menu-usecase.css">
<title>Erro</title>
<script type="text/javascript">
	function errorKey() {
		alert("Outro objeto depende deste, você não pode exclui-lo. Exclua os objetos dependentes antes de efetuar a exclusão deste objeto.");
		history.back();
	}
</script>
</head>
<body>
	<%
		
		Exception e = (Exception) request.getAttribute("exception");
		if (e.getCause().toString()
				.contains("org.postgresql.util.PSQLException")) {
	%>
	Há um erro de SQL no banco de dados, verifique se você não esta excluindo um objeto que é requerido por outros.
	<br>
	<span style="color: red">
		LocalizedMessage: <%=e.getLocalizedMessage()%>
		<br />StackTrace: <%=e.getStackTrace()%>
		<br />Cause: <%=e.getCause()%>
		<br />Suppressed: <%=e.getSuppressed()%>
		<br>
	</span>
	
	<%
		} else {
	%>
	<%
		
	%>
	Erro:
	<br>
	<span style="color: red"><%=e.getLocalizedMessage()%>
		<br /> <%=e.getStackTrace()%>
		<br /> <%=e.getCause()%>
		<br /><%=e.getSuppressed()%>
		<br>
	</span>
	<%
		}
	%>
	<div id="menu" align="left">
		<ul>
			<li><a href="javascript:history.back();">Voltar</a></li>
		</ul>
	</div>
</body>
</html>