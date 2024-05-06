<%-- 
    Document   : RegistrarUsuario
    Created on : 10/03/2024, 10:22:41 p. m.
    Author     : SHERMAN
--%>

<%@page import="ModeloDAO.UsuarioDao"%>
<%@page import="ModeloVO.UsuarioVo"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="ModeloDAO.InventarioDao" %>
<%@ page import="ModeloVO.InventarioVo" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Usuario</title>
        
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
        
        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 16px;
            transition: border-color 0.3s;
            appearance: none; 
            -webkit-appearance: none;
            -moz-appearance: none;
            background-image: url('data:image/svg+xml;utf8,<svg fill="black" height="24" viewBox="0 0 24 24" width="24" xmlns="http://www.w3.org/2000/svg"><path d="M7 10l5 5 5-5z"/><path d="M0 0h24v24H0z" fill="none"/></svg>'); 
            background-repeat: no-repeat;
            background-position: right 10px center;
        }
        
        select:focus {
            outline: none;
            border-color: #4CAF50;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"],
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
        input[type="password"]:focus,
        input[type="email"]:focus{
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
        <h1>Registrar Usuario</h1>
        <form method="post" action="EmpleadoControlador">
            <label for="textNombreEmp">Nombre Empleado: </label>
            <input type="text" id="textNombreEmp" name="textNombreEmp" required><br><br>
            <label for="Cedula">Cedula: </label>
            <input type="text" id="textCedula" name="textCedula" required><br><br>
            <label for="Correo">Correo: </label>
            <input type="email" id="textCorreo" name="textCorreo" required><br><br>
            <label for="Telefono">Número de Telefono/Celular: </label>
            <input type="text" id="textTelefono" name="textTelefono" required><br><br>
            <label for="textCargo">Seleccione un cargo </label>            
            <select id="cargos" name="cargos">
                    <%  
                        UsuarioVo usuVO = new UsuarioVo();
                        UsuarioDao usuDao = new UsuarioDao(usuVO);                        
                        List <UsuarioVo> cargos = usuDao.consultarCargos();
                        for (UsuarioVo cargo : cargos) {
                    %>
                        <option><%= cargo.getCargoEmpleado() %></option>
                    <% } %>
            </select>
            
            <label for="textNombre">Usuario: </label>
            <input type="text" id="textNombre" name="textNombre" required><br><br>
            <label for="textContrasena">Contraseña: </label>
            <input type="password" id="textContrasena" name="textContrasena" required><br><br>
            <button> Registrar </button>
            <input type="hidden" value="1" name="opcion">
        </form><br><br>

    </body>
</html>
