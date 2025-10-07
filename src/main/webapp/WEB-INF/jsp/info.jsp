<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ExnesioRefTables-ws Information</title>
</head>
<body>
<h1>Application</h1>
<ul>
    <li><strong>version:</strong> ${applicationVersion}</li>
</ul>

<h1>Databases</h1>
<h2>Application</h2>
<ul>
    <li><strong>URL:</strong> ${applicationDataSourceInformation.url}</li>
    <li><strong>Username:</strong> ${applicationDataSourceInformation.username}</li>
</ul>

<h2>DataWarehouse</h2>
<ul>
    <li><strong>URL:</strong> ${datawarehouseDataSourceInformation.url}</li>
    <li><strong>Username:</strong> ${datawarehouseDataSourceInformation.username}</li>
</ul>

<h2>LotG</h2>
<ul>
    <li><strong>URL:</strong> ${lotgDataSourceInformation.url}</li>
    <li><strong>Username:</strong> ${lotgDataSourceInformation.username}</li>
    <li>
        <strong>SQL Query</strong>
        <ul>
            <li><strong>Version</strong>: ${lotgDataSourceInformation.dbQuery == null ? "N/A" : lotgDataSourceInformation.dbQuery.version}</li>
            <li><strong>Query</strong>: ${lotgDataSourceInformation.dbQuery == null ? "N/A" : lotgDataSourceInformation.dbQuery.query}</li>
        </ul>
    </li>
</ul>

<h1>Links</h1>
<ul>
    <li><a href="<c:url value="/api-docs" />">Swagger</a></li>
</ul>

</body>
</html>
