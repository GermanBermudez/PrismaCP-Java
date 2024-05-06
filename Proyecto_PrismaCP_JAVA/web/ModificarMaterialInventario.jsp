<%-- 
    Document   : ModificarMaterialInventario
    Created on : 12/04/2024, 9:16:41 p. m.
    Author     : SHERMAN
--%>

<%@ page import="ModeloDAO.InventarioDao" %>
<%@ page import="ModeloVO.InventarioVo" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modificación de Inventario</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            margin-top: 30px;
            color: #333;
        }
        
        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        .form-container {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        form {
            margin-top: 20px;
        }

        input[type="text"] {
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            width: 300px;
            font-size: 16px;
            margin-right: 10px;
            text-align: left;
        }

        button {
            padding: 10px 20px;
            border-radius: 5px;
            border: none;
            background-color: #4CAF50;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .success-message {
            color: green;
            margin-top: 20px;
        }
    </style>
</head>
<body>
    
    <h1>Modificación de Inventario</h1>
    
    <form method="get" action="InventarioControlador">
        <label for="textNombreMat">Nombre Material: </label>
        <input type="text" id="textNombreMatAct" name="textNombreMatAct" required><br><br>
        <label for="Cantidad">Cantidad: </label>
        <input type="text" id="textCantidadAct" name="textCantidadAct" required><br><br>
        <label for="Descripcion">Descripción: </label>
        <input type="text" id="textDescripcionAct" name="textDescripcionAct" required><br><br>
        <label for="Tamano">Tamaño: </label>
        <input type="text" id="textTamanoAct" name="textTamanoAct" required><br><br>        
        <input type="hidden" name="idMaterialAct" id="idMaterialInput" required>
        
        <button>Guardar</button>               
         
        <input type="hidden" value="2" name="opcionInv">
    </form>
    
    <div class="suces-message">
    <% if ("guardadoExitoso".equals(request.getParameter("accion"))) { %>
        Datos guardados con éxito!
    <% } %>
    </div>
    
    <!-- Mensaje de error -->
    <div class="error-message">
        <% if (request.getAttribute("mensajeError")!=null) { %>
            ${mensajeError}
        <% } %>
    </div>
    
    <script>
    // Función para mostrar el popup cuando se cumpla la condición
    function mostrarPopup() {
        // Verificar si el parámetro 'accion' es igual a 'guardadoExitoso'
        var urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('accion') === 'guardadoExitoso') {
            // Mostrar el popup con el mensaje
            alert("Datos guardados con éxito!");
        }
    }

    // Llamar a la función cuando la página se cargue
    window.onload = mostrarPopup;
    </script>
    
    
    <!-- JavaScript para la funcionalidad de búsqueda y guardar -->
    <script>
        var queryString = window.location.search;
        var idMaterial = queryString.substring(queryString.indexOf('id=') + 3);
        document.getElementById("idMaterialInput").value = idMaterial;    
    
    function guardar() {
            
            alert("Guardado exitoso");
        }
        
    function cerrarVentanaEmergente() {
            alert("Guardado exitoso");
            window.opener.location.reload(); // Recargar la página principal
            window.close(); // Cerrar la ventana emergente
        }
    </script>
</body>
</html>
