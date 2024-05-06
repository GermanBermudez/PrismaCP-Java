<%-- 
    Document   : Facturas
    Created on : 11/04/2024, 8:59:30 p. m.
    Author     : SHERMAN
--%>

<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.FacturasDao"%>
<%@page import="ModeloVO.FacturasVo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Facturas</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            border-spacing: 0;
            margin-top: 20px;
            background-color: #fff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
            text-transform: uppercase;
            font-size: 12px;
            color: #333;
        }

        td {
            font-size: 14px;
            color: #666;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        h1 {
            text-align: center;
            margin-top: 30px;
            color: #007bff;
        }
        
        h2 {
            text-align: left;
        }

        form {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        input[type="file"],
        input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        button[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #0056b3;
        }

        ul {
            list-style: none;
            padding: 0;
        }

        ul li {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <h1>Gestión de Facturas</h1>    
    <!-- Formulario para subir archivos PDF -->
    <form action="FacturasControlador" method="post" enctype="multipart/form-data">
        <label for="archivo">Selecciona un archivo PDF:</label>
        <input type="file" id="archivofactura" name="archivofactura" accept=".pdf" required>
        <label for="TipoFact">Tipo de Factura:</label>
        <select id ="tipofacturas" name="tipofacturas">
                    <%  
                        FacturasVo FactVO = new FacturasVo();
                        FacturasDao FactDao = new FacturasDao(FactVO);                        
                        List <FacturasVo> tiposFactura = FactDao.consultarTipoFacturas();
                        for (FacturasVo tipoFactu : tiposFactura) {
                    %>
                        <option value="<%= tipoFactu.getTipoFactura() %>"><%= tipoFactu.getTipoFactura() %></option>
                    <% } %>            
        </select>
        <label for="DescripcionFactura">Detalle de la factura cargada:</label>
        <input type="text" id="textDescripcionFactura" name="textDescripcionFactura">
        <button>Cargar Factura</button>        
        <input type="hidden" value="1" name="opcionFact">
    </form>

    <h1>Tabla de Facturas</h1>
    <% 
        String IdFacturas = request.getParameter("IdFacturas");
        String IdEmpleado = request.getParameter("IdEmpleado");
        String descFacturas = request.getParameter("descFacturas");
        String idTipoFactura = request.getParameter("idTipoFactura");
        String TipoFactura = request.getParameter("TipoFactura");           
        byte[] ArchivoFactura = new byte[0];
        
        FacturasVo facVo = new FacturasVo(IdFacturas, IdEmpleado, descFacturas, TipoFactura, idTipoFactura, ArchivoFactura);
        FacturasDao facturasDao = new FacturasDao(facVo);
        
        List<FacturasVo> facturas = facturasDao.consultarInformacion();
    
    if (!facturas.isEmpty()) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>ID Factura</th>
                <th>ID Empleado</th>
                <th>Descripción</th>                                
                <th>Tipo Factura</th>
                <th>Archivo Factura</th>
            </tr>
        </thead>
        <tbody>
        <% 
        for (FacturasVo facturasObj : facturas) {
        %>
        <tr>
            <td><%= facturasObj.getIdFacturas() %></td>
            <td><%= facturasObj.getIdEmpleado() %></td> 
            <td><%= facturasObj.getDescFacturas() %></td>            
            <td><%= facturasObj.getTipoFactura() %></td>
            <td>
                <!-- Enlace para descargar el archivo PDF -->
                <a href="FacturasControlador?action=descargar&idFactura=<%= facturasObj.getIdFacturas() %>">Descargar</a>                
            </td>
        </tr>
        <% 
        } // Fin del bucle for
        %>       
        </tbody>
    </table>
<% } %>
</body>
</html>
