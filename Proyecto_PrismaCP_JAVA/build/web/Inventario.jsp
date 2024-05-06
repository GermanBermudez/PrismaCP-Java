<%-- 
    Document   : Inventario
    Created on : 17/03/2024, 5:15:40 p. m.
    Author     : SHERMAN
--%>
<%@ page import="java.util.List" %>
<%@ page import="ModeloDAO.InventarioDao" %>
<%@ page import="ModeloVO.InventarioVo" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Inventario</title>
    <style>
        /* Estilos generales */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            position: relative;
            color: #272829;
        }

        a {
            color: #149ddd;
            text-decoration: none;
        }

        a:hover {
            color: #37b3ed;
            text-decoration: none;
        }

        h1, h2, h3, h4, h5, h6 {
            font-family: Arial, sans-serif;
        }

        /* Estilos de la tabla */
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

        /* Estilos de la barra de búsqueda */
        .search-bar-container {
            position: absolute;
            top: 20px;
            right: 20px;
        }

        .search-bar {
            width: 300px;
            background-color: #fff;
            border: 1px solid #ccc;
            border-radius: 20px;
            padding: 8px;
            text-align: left;
            display: flex;
            align-items: center;
        }

        .search-bar input {
            width: calc(100% - 30px);
            border: none;
            outline: none;
            padding-left: 10px;
            font-size: 14px;
        }

        .search-bar::placeholder {
            color: rgba(0, 0, 0, 0.4);
        }

        .search-button {
            background-color: #149ddd;
            color: #fff;
            border: none;
            border-radius: 20px;
            padding: 8px 15px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .search-button:hover {
            background-color: #37b3ed;
        }

        /* Estilos de los botones */
        .button {
            background-color: #149ddd;
            color: #fff;
            border: none;
            border-radius: 20px;
            padding: 8px 15px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #37b3ed;
        }
    </style>
</head>
<body>
    
<div class ="container">        
    <h1>Información del Inventario</h1>
    <% 
        String IdMaterial = request.getParameter("IdMaterial");
        String cantidadInventario = request.getParameter("cantidadInventrio");
        String disponibilidadInventario = request.getParameter("disponibilidadInventario");
        String idMaterialesInventario = request.getParameter("idMaterialesInventario");
        String NombreMaterial = request.getParameter("NombreMaterial");
        String DescripcionMaterial = request.getParameter("DescripcionMaterial");
        String TamanoMaterial = request.getParameter("TamanoMaterial");
        String UsuarioModInventario = request.getParameter("UsuarioModInventario");
        String TipoModificacion = request.getParameter("TipoModificacion");
        
        InventarioDao inventarioDao = new InventarioDao(new InventarioVo(IdMaterial, cantidadInventario, disponibilidadInventario, idMaterialesInventario, 
                NombreMaterial, DescripcionMaterial, TamanoMaterial, UsuarioModInventario, TipoModificacion));
        
        List<InventarioVo> inventarios = inventarioDao.consultarInformacion();
    
    if (!inventarios.isEmpty()) {
%>
    
    <div class ="info-message">La información fue encontrada.
        <div class="search-bar">
            <input type="text" id="busquedaMaterial" placeholder="Buscar por nombre de material" onkeyup="filtrarTabla()">
        </div>        
        <div class = "buttons-container">
            <button id="agregar-btn">Agregar</button>            
        </div>
    </div>    
    
    <script>
    console.log("Filtro de la tabla proximo a llamar");    
    function filtrarTabla() {
            console.log("Filtro de la tabla");
            var input, filtro, tabla, tr, td, i, txtValue;
            input = document.getElementById("busquedaMaterial");
            filtro = input.value.toUpperCase();
            tabla = document.getElementById("tablaInventario");
            tr = tabla.getElementsByTagName("tr");

            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[4]; // Cambia el índice si el nombre del material no está en la cuarta columna
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filtro) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>       
    
<table id="tablaInventario">
    <thead>
        <tr>
            <th>ID Material</th>
            <th>Cantidad</th>
            <th>Disponibilidad</th>            
            <th>Nombre de Material</th>
            <th>Descripción</th>
            <th>Tamaño</th>
            <th>Usuario Modificador</th>
            <th>Tipo de Modificación</th>
        </tr>
    </thead>
    <tbody>
        <% 
        for (InventarioVo inventario : inventarios) {
        %>
        <tr>
            <td><a href="#" onclick="abrirVentana('<%= inventario.getIdMaterial() %>')"><%= inventario.getIdMaterial() %></a></td>
            <td><%= inventario.getCantidadInventrio() %></td>
            <td><%= inventario.getDisponibilidadInventario() %></td>            
            <td><%= inventario.getNombreMaterial() %></td>
            <td><%= inventario.getDescripcionMaterial() %></td>
            <td><%= inventario.getTamanoMaterial() %></td>
            <td><%= inventario.getUsuarioModInventario() %></td>
            <td><%= inventario.getTipoModificacion() %></td>
        </tr>
        <% 
        } // Fin del bucle for
        %>
    </tbody>
</table>

<% }
else { %>
    <div class ="error-message">La información no fue encontrada.</div>    
<% } %>

<%--Al dar click en el boton "Agregar" se genera una ventana emergente--%>
    <script>
        document.getElementById("agregar-btn").addEventListener("click", function() {
        var opciones = "width=600,height=400,scrollbars=yes,resizable=yes";
        var url = "AgregarMaterialInventario.jsp";
        window.open(url, "_blank", opciones);
        });
    </script>  
</div>
</body>
</html>
