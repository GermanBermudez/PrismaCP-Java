<%-- 
    Document   : AgregarMaterialInventario
    Created on : 9/04/2024, 9:40:46 p. m.
    Author     : SHERMAN
--%>

<%@page import="ModeloVO.UsuarioVo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Nuevo Material</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f7f7f7;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        h1 {
            margin-top: 50px;
            color: #333;
        }

        form {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            padding: 20px;
            width: 300px;
            margin: 0 auto;
        }

        label {
            display: block;
            margin-bottom: 10px;
            color: #555;
        }

        input[type="text"],
        input[type="password"],
        button {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
            transition: border-color 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            outline: none;
            border-color: #4CAF50;
        }

        button {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }

        .error-message {
            color: red;
        }

        .success-message {
            color: green;
        }
    </style>
</head>
<body>
    
            <%
            HttpSession BuscarSesion = (HttpSession)request.getSession();
            String usuario ="";
            if (BuscarSesion.getAttribute("DatosDeSesion")==null) {
            request.getRequestDispatcher("Index.jsp").forward(request, response);
    
            }else {
            UsuarioVo usuVO = (UsuarioVo)BuscarSesion.getAttribute("DatosDeSesion");
            usuario = usuVO.getNombreUsuario();
        
            }
            %>
    
    <h1>Agregar Nuevo Material</h1>
    <form method="post" action="InventarioControlador">
        <label for="idMaterial">ID Material:</label>
        <input type="text" id="idMaterial" name="idMaterial" required><br>

        <label for="cantidad">Cantidad:</label>
        <input type="text" id="cantidad" name="cantidad" required><br>

        <label for="disponibilidad">Disponibilidad:</label>
        <input type="text" id="disponibilidad" name="disponibilidad" required><br>

        <label for="idMateriales">ID de Materiales:</label>
        <input type="text" id="idMateriales" name="idMateriales" required><br>

        <label for="nombreMaterial">Nombre de Material:</label>
        <input type="text" id="nombreMaterial" name="nombreMaterial" required><br>

        <label for="descripcion">Descripción:</label>
        <input type="text" id="descripcion" name="descripcion" required><br>

        <label for="tamano">Tamaño:</label>
        <input type="text" id="tamano" name="tamano" required><br>

        <label for="usuarioModificador">Usuario Modificador: <%=usuario%> </label>
        

        <label for="tipoModificacion">Tipo de Modificación:</label>
        <input type="text" id="tipoModificacion" name="tipoModificacion" required><br>

        <button>Registrar</button>
        
        <input type="hidden" value="1" name="opcionInv">
    </form><br><br>
           
    <!-- Mensaje de éxito -->
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
    
</body>
</html>
