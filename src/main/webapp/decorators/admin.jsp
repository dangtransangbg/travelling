
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 11/7/2019
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="/common/taglib.jsp"></jsp:include>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title><sitemesh:write property='title'></sitemesh:write></title>
    <!-- Google Font: Source Sans Pro -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="<c:url value='/plugins/fontawesome-free/css/all.min.css'/>">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
    <!-- Tempusdominus Bootstrap 4 -->
    <link rel="stylesheet" href="<c:url value='/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css'/>">
    <!-- iCheck -->
    <link rel="stylesheet" href="<c:url value='/plugins/icheck-bootstrap/icheck-bootstrap.min.css'/>">
    <!-- JQVMap -->
    <link rel="stylesheet" href="<c:url value='/plugins/jqvmap/jqvmap.min.css'/>">
    <!-- Theme style -->
    <link rel="stylesheet" href="<c:url value='/css/admin/adminlte.min.css'/>">
    <!-- overlayScrollbars -->
    <link rel="stylesheet" href="<c:url value='/plugins/overlayScrollbars/css/OverlayScrollbars.min.css'/>">
    <!-- Daterange picker -->
    <link rel="stylesheet" href="<c:url value='/plugins/daterangepicker/daterangepicker.css'/>">
    <!-- summernote -->
    <link rel="stylesheet" href="<c:url value='/plugins/summernote/summernote-bs4.min.css'/>">
</head>

<body>
<%@include file="/common/admin/header.jsp" %>
<div id="wrapper">
    <%@include file="/common/admin/menu.jsp" %>
    <sitemesh:write property='body'></sitemesh:write>
    <%@include file="/common/admin/footer.jsp" %>
</div>

<script src="<c:url value='/plugins/jquery/jquery.min.js'/>"></script>
<!-- jQuery UI 1.11.4 -->
<script src="<c:url value='/plugins/jquery-ui/jquery-ui.min.js'/>"></script>
<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
<script>
    $.widget.bridge('uibutton', $.ui.button)
</script>
<!-- Bootstrap 4 -->
<script src="<c:url value='/plugins/bootstrap/js/bootstrap.bundle.min.js'/>"></script>
<!-- ChartJS -->
<script src="<c:url value='/plugins/chart.js/Chart.min.js'/>"></script>
<!-- Sparkline -->
<script src="<c:url value='/plugins/sparklines/sparkline.js'/>"></script>
<!-- JQVMap -->
<script src="<c:url value='/plugins/jqvmap/jquery.vmap.min.js'/>"></script>
<script src="<c:url value='/plugins/jqvmap/maps/jquery.vmap.usa.js'/>"></script>
<!-- jQuery Knob Chart -->
<script src="<c:url value='/plugins/jquery-knob/jquery.knob.min.js'/>"></script>
<!-- daterangepicker -->
<script src="<c:url value='/plugins/moment/moment.min.js'/>"></script>
<script src="<c:url value='/plugins/daterangepicker/daterangepicker.js'/>"></script>
<!-- Tempusdominus Bootstrap 4 -->
<script src="<c:url value='/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js'/>"></script>
<!-- Summernote -->
<script src="<c:url value='/plugins/summernote/summernote-bs4.min.js'/>"></script>
<!-- overlayScrollbars -->
<script src="<c:url value='/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js'/>"></script>
<!-- AdminLTE App -->
<script src="<c:url value='/js/admin/adminlte.js'/>"></script>
<!-- AdminLTE for demo purposes -->
<script src="<c:url value='/js/admin/demo.js'/>"></script>
<!-- AdminLTE dashboard demo (This is only for demo purposes) -->
<script src="<c:url value='/js/admin/dashboard.js'/>"></script>
</body>
</html>
