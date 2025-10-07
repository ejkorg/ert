<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Exensio Ref Tables</title>
</head>

<style><%@include file="/WEB-INF/css/style.css"%></style>
<script><%@include file="/WEB-INF/js/ertConf.js"%></script>
<script><%@include file="/WEB-INF/js/onFabConf.js"%></script>
<script><%@include file="/WEB-INF/js/onLot.js"%></script>
<script><%@include file="/WEB-INF/js/onLotProd.js"%></script>
<script><%@include file="/WEB-INF/js/onProd.js"%></script>
<script><%@include file="/WEB-INF/js/onScribe.js"%></script>
<script><%@include file="/WEB-INF/js/onSlice.js"%></script>
<script><%@include file="/WEB-INF/js/onWmap.js"%></script>
<script><%@include file="/WEB-INF/js/ppLot.js"%></script>
<script><%@include file="/WEB-INF/js/ppLotProd.js"%></script>
<script><%@include file="/WEB-INF/js/ppProd.js"%></script>

<script><%@include file="/WEB-INF/js/common.js"%></script>
<body>
    <jsp:include page="header.jsp"/>
    <hr>
    <br>
    <jsp:include page="category/ertConf.jsp"/>
    <jsp:include page="category/onFabConf.jsp"/>
    <jsp:include page="category/onLot.jsp"/>
    <jsp:include page="category/onLotProd.jsp"/>
    <jsp:include page="category/onProd.jsp"/>
    <jsp:include page="category/onScribe.jsp"/>
    <jsp:include page="category/onSlice.jsp"/>
    <jsp:include page="category/onWmap.jsp"/>
    <jsp:include page="category/ppLot.jsp"/>
    <jsp:include page="category/ppLotProd.jsp"/>
    <jsp:include page="category/ppProd.jsp"/>
</body>
</html>
