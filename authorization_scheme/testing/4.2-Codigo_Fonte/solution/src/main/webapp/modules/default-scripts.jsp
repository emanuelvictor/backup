<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->



<!-- JQuery -->
<script type="text/javascript" src="./webjars/jquery/<spring:eval expression="@environment.getProperty('jquery.version')"/>/jquery.min.js"></script>

<!-- AngularJS -->

<script type="text/javascript" src="./webjars/angularjs/<spring:eval expression="@environment.getProperty('angularjs.version')"/>/angular.js"></script>
<!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>-->

<script type="text/javascript" src="./webjars/angularjs/<spring:eval expression="@environment.getProperty('angularjs.version')"/>/angular-animate.min.js"></script>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular-animate.min.js"></script> -->

<script type="text/javascript" src="./webjars/angularjs/<spring:eval expression="@environment.getProperty('angularjs.version')"/>/angular-aria.min.js"></script>
<!--<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular-aria.min.js"></script>-->

<script type="text/javascript" src="./webjars/angular-material/<spring:eval expression="@environment.getProperty('angular-material.version')"/>/angular-material.min.js"/></script>

<!-- Cookies -->
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0/angular-cookies.js"></script>


<script type="text/javascript" src="./webjars/angular-ui-router/<spring:eval expression="@environment.getProperty('uirouter.version')"/>/angular-ui-router.min.js"></script>

<!-- moment js -->
<script type="text/javascript" src="./webjars/momentjs/<spring:eval expression="@environment.getProperty('momentjs.version')"/>/min/moment-with-locales.js"></script>

<!-- DWR -->
<script type="text/javascript" src="./broker/engine.js"/></script>
<script type="text/javascript" src="./broker/util.js"/></script>

<!-- EITS -->
<script type="text/javascript" src="./webjars/eits/<spring:eval expression="@environment.getProperty('eits-webjars.version')"/>/broker/dwr-broker.js"/></script>
<script type="text/javascript" src="./webjars/eits-md/<spring:eval expression="@environment.getProperty('eits-webjars.version')"/>/eits-md.js"/></script>

<!-- md-data-table -->
<script type="text/javascript" src="./static/js/provisory/lodash/dist/lodash.compat.js"></script>
<script type="text/javascript" src="./static/js/provisory/angular-uuid4/angular-uuid4.js"></script>
<script type="text/javascript" src="./static/js/provisory/md-data-table/dist/md-data-table.js"></script>
<script type="text/javascript" src="./static/js/provisory/md-data-table/dist/md-data-table-templates.js"></script>

<script type="text/javascript" src="./static/js/provisory/md-data-table/app/bower_components/angular-material-icons/angular-material-icons.js"></script>



