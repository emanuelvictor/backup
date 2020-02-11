<script type='text/javascript'>
	function execute(entityName, newAction) {
		document.getElementById('newAction').value = newAction;
		document.getElementById('entityName').value = entityName;
		document.getElementById('form').submit();
	}
</script>
<link rel="stylesheet" type="text/css" href="./css/style-menu.css">
<form name="form" id="form" action="./dispatcher" method="post" target="main">
	<input type="hidden" name="newAction" id="newAction">
	<input type="hidden" name="entityName" id="entityName">
</form>
<div id="menu" align="center" >
	<nav>
		<ul>
			<li><a href="javascript:execute('Brand','goFind');">Marca</a></li>
			<li><a href="javascript:execute('Model','goFind');">Modelo</a></li>
			<li><a href="javascript:execute('Vehicle','goFind');">Veículo</a></li>
			<li><a href="javascript:execute('Client','goFind');">Cliente</a></li>
			<li><a href="javascript:execute('OS','goFind');">Ordem de Serviço</a></li>
			<li><a href="javascript:execute('Product','goFind');">Produto</a></li>
			<li><a href="javascript:execute('Supplier','goFind');">Fornecedor</a></li>
		</ul>
	</nav>
</div>
