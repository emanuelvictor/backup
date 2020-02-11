<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.edu.udc.sistemas.ia6.emanuelvictor.entity.Vehicle,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Brand, br.edu.udc.sistemas.ia6.emanuelvictor.entity.Model,br.edu.udc.sistemas.ia6.emanuelvictor.entity.Client"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style-menu-usecase.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Consultar Veículo</title>
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
			Vehicle vehicleFilter = (Vehicle) request.getSession().getAttribute("filterVehicle");
			if (vehicleFilter == null) {
				vehicleFilter = new Vehicle();
				vehicleFilter.setModel(new Model());
				vehicleFilter.setClient(new Client());
			} if (vehicleFilter.getModel() == null) {
				vehicleFilter.setModel(new Model());
			} if(vehicleFilter.getModel().getBrand()==null){
				vehicleFilter.getModel().setBrand(new Brand());
			}
			if(vehicleFilter.getClient()==null){
				vehicleFilter.setClient(new Client());
			}
			Object list[] = (Object[]) request.getAttribute("list");
			Object listModel[] = (Object[]) request.getAttribute("listModel");
			Object listClient[] = (Object[]) request.getAttribute("listClient");
			Object listBrand[] = (Object[]) request.getAttribute("listBrand");
		%>
</head>
<body>
<div id="title">Consultar Veículo</div>
	<form name="frm" id="frm" method="post" action="./dispatcher">
		<input type="hidden" name="newAction" id="newAction"/>
		<input type="hidden" name="entityName" id="entityName" value="Vehicle"/>
		<input type="hidden" name="id" id="id"/>
		<div class="label">Chassí:</div>			
			<input class="field" type="text" name="chassis" id="chassis" maxlength="100" value="<%=(vehicleFilter.getChassis() != null) ? vehicleFilter.getChassis() : ""%>" />
		<div class="label">Placa:</div>			
			<input class="field" type="text" name="plaque" id="plaque" maxlength="100" value="<%=(vehicleFilter.getPlaque() != null) ? vehicleFilter.getPlaque() : ""%>" />
		
		
		<div class="label">Marca:</div>			
		<select class="field" name="brand" id="brand">
              <option value="">&nbsp;</option>
                        <%
                                if (listBrand != null) {
                                       for(int i = 0; i < listBrand.length; i++){
                                                System.out.println(listBrand[i]);
                                    }
                                     System.out.println(vehicleFilter.getModel().getBrand().getIdBrand());
                                     for(int i=0; i<listBrand.length; i++) {
                                               Brand brand = (Brand) listBrand[i];
                                              boolean bSelected = vehicleFilter.getModel().getBrand().getIdBrand() == brand.getIdBrand(); 
                        %>
                              <option <%=bSelected ? "selected" : "" %> value="<%=brand.getIdBrand()%>"><%=brand.getDescription()%></option>
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
							boolean bSelected = vehicleFilter.getModel().getIdModel() == model.getIdModel(); 
			%>
					<option <%=bSelected ? "selected" : "" %> value="<%=model.getIdModel()%>"><%=model.getDescription()%></option>
			<%
						}
					}
			%>
		</select>
		<div class="label">Cliente:</div>			
			<select class="field" name="client" id="client">
					<option value="">&nbsp;</option>
					<%
						if (listClient != null) {
		 						for(int i=0; i<listClient.length; i++) {
		 							Client client = (Client) listClient[i];
		 							boolean bSelected = vehicleFilter.getClient().getIdClient() == client.getIdClient(); 
					%>
							<option <%=bSelected ? "selected" : "" %> value="<%=client.getIdClient()%>"><%= client.getName() != null ? client.getName() : client.getFantasyName()  %></option>
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
					<th style="width:40px;text-align: left;">Código</th>
					<th style="width:100px;text-align: left;">Placa</th>
					<th style="width:150px;text-align: left;">Chassí</th>
					<th style="width:125px;text-align: left;">Modelo</th>
					<th style="width:100px;text-align: left;">Marca</th>
					<th style="width:250px;text-align: left;">Cliente</th>
					<th></th>
				</tr>
<%
	if (list != null) {			
		for(int i=0; i<list.length; i++) {
			Vehicle vehicleAux = (Vehicle) list[i];
%>
				<tr>
					<td><%=vehicleAux.getIdVehicle()%></td>
					<td><%=vehicleAux.getPlaque()%></td>
					<td><%=vehicleAux.getChassis()%></td>
					<td><%=vehicleAux.getModel().getDescription()%></td>
					<td><%=vehicleAux.getModel().getBrand().getDescription()%></td>
					<td><%=vehicleAux.getClient().getName()%></td>
					<td style="text-align:center;">
						<a class="link" href="javascript:detail(<%=vehicleAux.getIdVehicle()%>);"><img src="./img/edit.png"></a>
						&nbsp;
						<a class="link" href="javascript:deleteList(<%=vehicleAux.getIdVehicle()%>);"><img src="./img/delete.png"></a>
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