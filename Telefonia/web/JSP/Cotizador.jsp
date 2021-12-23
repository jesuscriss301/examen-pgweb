<%-- 
    Document   : Cotizador
    Created on : 22/12/2021, 07:18:58 PM
    Author     : jesus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado Planes</title>
    </head>
    <body>
        <h1>Planes registrados:</h1>
        
        <%
            String planes=(String)request.getSession().getAttribute("listado");
        %>
        
        <%=planes%>
        
    </body>
</html>

