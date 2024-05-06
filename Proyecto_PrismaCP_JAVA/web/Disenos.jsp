<%-- 
    Document   : Disenos
    Created on : 11/04/2024, 8:53:58 p. m.
    Author     : SHERMAN
--%>

<%@page import="ModeloDAO.DisenosDao"%>
<%@page import="ModeloVO.DisenosVo"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Disenos</title>
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
    <h1>Gestión de Disenos</h1>    
    <!-- Formulario para subir archivos PDF -->
    <form action="DisenosControlador" method="post" enctype="multipart/form-data">
        <label for="archivo">Selecciona un archivo PDF:</label>
        <input type="file" id="archivodiseno" name="archivodiseno" accept=".pdf" required>
        <label for="NombreDiseno">Nombre del Diseño</label>
        <input type="text" id="textNombreDiseno" name="textNombreDiseno">
        <label for="TamanoDiseno">Tamaño del Diseño</label>
        <input type="text" id="textTamanoDiseno" name="textTamanoDiseno">
        <label for="TipoDis">Tipo de Diseño:</label>
        <select id ="tipodisenos" name="tipodisenos">
                    <%  
                        DisenosVo DisVO = new DisenosVo();
                        DisenosDao DisDao = new DisenosDao(DisVO);                        
                        List <DisenosVo> tiposDiseno = DisDao.consultarTipoDisenos();
                        for (DisenosVo tipoDisen : tiposDiseno) {
                    %>
                        <option value="<%= tipoDisen.getTipoDiseno() %>"><%= tipoDisen.getTipoDiseno() %></option>
                    <% } %>            
        </select>
        <label for="DescripcionDiseno">Detalle del Diseño cargado:</label>
        <input type="text" id="textDescripcionDiseno" name="textDescripcionDiseno">
        <button>Cargar Diseño</button>        
        <input type="hidden" value="1" name="opcionDiseno">
    </form>
    
    
    
    <h1>Tabla de Disenos</h1>
    <% 
        String IdDisenos = request.getParameter("IdDisenos");
        String NombreEmpleado = request.getParameter("NombreEmpleado");
        String NombreDiseno = request.getParameter("NombreDiseno");
        String DescDiseno = request.getParameter("DescDiseno");
        String TipoDiseno = request.getParameter("TipoDiseno");           
        byte[] ArchivoDiseno = new byte[0];
        
        DisenosVo disVo = new DisenosVo(IdDisenos, NombreEmpleado, NombreDiseno, DescDiseno, TipoDiseno, ArchivoDiseno);
        DisenosDao disenosDao = new DisenosDao(disVo);
        
        List<DisenosVo> disenos = disenosDao.consultarInformacion();
    
    if (!disenos.isEmpty()) {
%>
    <table border="1">
        <thead>
            <tr>
                <th>ID Diseno</th>
                <th>Nombre Empleado</th>
                <th>Nombre Diseno</th>
                <th>Descripcion del Diseño</th>
                <th>Tipo Diseño</th>
                <th>Archivo Diseño</th>
            </tr>
        </thead>
        <tbody>
        <% 
        for (DisenosVo disenosObj : disenos) {
        %>
        <tr>
            <td><%= disenosObj.getIdDiseno() %></td>
            <td><%= disenosObj.getEmpleado()%></td>
            <td><%= disenosObj.getNombreDiseno() %></td>
            <td><%= disenosObj.getDescDiseno() %></td>            
            <td><%= disenosObj.getTipoDiseno() %></td>
            <td>
                <!-- Enlace para descargar el archivo PDF -->
                    <a href="DisenosControlador?action=descargar&idDiseno=<%= disenosObj.getIdDiseno()%>">Descargar</a>                              
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
