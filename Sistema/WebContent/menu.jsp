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

<div id="cssmenu" >
<ul>
   <li class="has-sub"><a href="javascript:execute('Brand','goFind');"><span>Atendimento</span></a>
   		<ul>
    	   <li class="has-sub"><a href="javascript:execute('Brand','goFind');"><span>Candidato</span></a>
    	   		<ul>
    	   			<li class="last"><a href="javascript:execute('Brand','goFind');"><span>Cadastrar</span></a></li>
           			<li class="last"><a href="javascript:execute('Brand','goNew');"><span>Consultar</span></a></li>
       			</ul>
    	   </li>
           <li class="has-sub"><a href="javascript:execute('Brand','goNew');"><span>Empresa</span></a>
           		<ul>
    	   			<li class="last"><a href="javascript:execute('Brand','goFind');"><span>Cadastrar</span></a></li>
           			<li class="last"><a href="javascript:execute('Brand','goNew');"><span>Consultar</span></a></li>
       			</ul>
           </li>
       </ul>
   </li>
    <li class="has-sub"><a href="javascript:execute('Brand','goFind');"><span>Financeiro</span></a>
   		<ul>
    	   <li class="last"><a href="javascript:execute('Brand','goFind');"><span>Inserir lançamento</span></a></li>
           <li class="last"><a href="javascript:execute('Brand','goNew');"><span>Consultar</span></a></li>
       </ul>
   </li>
</ul>
</div>