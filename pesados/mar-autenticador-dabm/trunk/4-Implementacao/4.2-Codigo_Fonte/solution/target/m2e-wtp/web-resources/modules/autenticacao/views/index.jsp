<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns:ng="http://angularjs.org" lang="pt">

<head>
  <title>Autenticação do Usuário</title>

  <!-- Styles -->
  <jsp:include page="/modules/default-styles.jsp"/>
  <link rel="stylesheet" href="./static/css/theme-autenticacao.css">

  <!-- Scripts -->
  <jsp:include page="/modules/default-scripts.jsp"/>

  <!-- Main -->
  <script type="text/javascript" src="./modules/autenticacao/autenticacao-main.js?v=1.0.6-RELEASE"></script>

  <!-- Controllers -->
  <script type="text/javascript" src="./modules/autenticacao/controllers/signin-controller.js?v=1.0.6-RELEASE"></script>
</head>

<body layout-align="center" layout="row" style="background-image: url(./static/images/BG-LOGIN-MARINHA.jpg);background-size: cover">
<div class="color-bg">
  <div class="color-bg-blue"></div>
  <!--<div class="color-bg-grey"></div>-->
</div>
<!-- Main Panel -->
<div layout-align="center center" layout="column">

  <div ui-view></div>

</div>

</body>
</html>