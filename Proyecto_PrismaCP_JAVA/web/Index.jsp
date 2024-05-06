<%-- 
    Document   : RegistrarUsuario
    Created on : 10/03/2024, 10:22:41 p. m.
    Author     : SHERMAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            position: relative;
        }
        .login-container {
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"],
        input[type="password"],
        button {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        button {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error-message {
            color: red;
            text-align: center;
        }
        .footer-logo {
            position: absolute;
            bottom: 10px;
            right: 10px;
            font-size: 12px;
            color: #888;
        }
        .header-logo {
            position: absolute;
            top: 10px;
            left: 10px;
            width: 249px;
            height: 141px;
        }
        footer {
            position: absolute;
            bottom: 0;
            width: 100%;
            text-align: center;
            
            padding: 10px 0;
        }
    </style>
</head>
<body>
    <img class="header-logo" src="logoempresa.png" alt="Logo">
    <div class="login-container">
        <h1>Iniciar Sesión</h1>
        <form method="post" action="EmpleadoControlador">
            <label for="textNombre">Usuario:</label>
            <input type="text" id="textNombre" name="textNombre" required>
            <label for="textContrasena">Contraseña:</label>
            <input type="password" id="textContrasena" name="textContrasena" required>
            <button type="submit">Iniciar sesión</button>
            <input type="hidden" value="4" name="opcion">
        </form>
        <div class="error-message">
            <% if (request.getAttribute("mensajeError") != null) { %>
                ${mensajeError}
            <% } else if (request.getAttribute("mensajeExito") != null) { %>
                ${mensajeExito}
            <% } %>
        </div>
    </div>
        
        <div class="footer-logo">PrismaCP-Aplication <span>&COPY;</span> </div>

        <footer>&copy; Grupo Softeam</footer>

</body>
</html>
