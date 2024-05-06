<%-- 
    Document   : OrdenProduccion
    Created on : 11/04/2024, 8:56:27 p. m.
    Author     : SHERMAN
--%>

<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ProduccionDao"%>
<%@page import="ModeloVO.ProduccionVo"%>
<%@page import="ModeloVO.UsuarioVo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    
    <title>Enviar órdenes a producción</title>
    <style>
        /* Estilos CSS para dar un diseño agradable */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            background-color: #f5f5f5;
        }
   
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        input[type="text"], select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
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
    
    <div class="container">
        <h2>Enviar órdenes a producción</h2>
        <form action="ProduccionControlador" method="post" enctype="multipart/form-data">
            <label for="tipoOrden">Tipo de Orden:</label>
            <select id ="tipoOrden" name="tipoOrden">
                    <%  
                        ProduccionVo ProdVO = new ProduccionVo();
                        ProduccionDao ProdDao = new ProduccionDao(ProdVO);                        
                        List <ProduccionVo> tiposProd = ProdDao.consultarTipoProd();
                        for (ProduccionVo tipoOrdProd : tiposProd) {
                    %>
                        <option value="<%= tipoOrdProd.getTipoOrden() %>"><%= tipoOrdProd.getTipoOrden() %></option>
                    <% } %>            
            </select>

            <label for="cantidad">Cantidad:</label>
            <input type="text" id="cantidad" name="cantidad">
            
            <label for="archivo">Selecciona un archivo PDF:</label>
            <input type="file" id="archivodiseno" name="archivodiseno" accept=".pdf" required>

            <label for="descripcionOrden">Descripción del trabajo:</label>
            <input type="text" id="descripcionOrden" name="descripcionOrden">

            <label for="Empleado">Empleado Solicitante: <%=usuario%> </label>                                    

            <input type="hidden" value="1" name="opcionProduccion">
            <br>
            <br>
            <input type="submit" value="Enviar">
        </form>
    </div>     
</body>
</html>
