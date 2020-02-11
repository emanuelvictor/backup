<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/angular_material/0.9.0/angular-material.min.css">-->

<link rel="stylesheet" href="./webjars/angular-material/<spring:eval expression="@environment.getProperty('angular-material.version')"/>/angular-material.min.css">

<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=RobotoDraft:300,400,500,700,400italic">

<link rel="stylesheet" href="./webjars/eits-md/<spring:eval expression="@environment.getProperty('eits-webjars.version')"/>/eits-md.css"/></link>

<link rel='stylesheet' href="./webjars/eits/<spring:eval expression="@environment.getProperty('eits-webjars.version')"/>/material-design-iconic-font/css/material-design-iconic-font.css">


<!-- md-data-table -->
<link rel="stylesheet" href="static/js/provisory/md-data-table/dist/md-data-table-style.css" />