<%-- 
    Document   : Sesiones
    Created on : 17/03/2024, 5:10:14 p. m.
    Author     : SHERMAN
--%>

<%@page import="ModeloVO.UsuarioVo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        
        <style>
            header {
                background-color: #333;
                color: white;
                padding: 10px;
                text-align: right;
            }
            form {
                display: inline-block;
                margin-top: 10px;
                margin-right: 10px;
            }
            input[type ="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 4px 2px;
                cursor: pointer;
                border-radius: 5px;
            }
            h2 {
                color: black;
                float: right;
                margin-top: 10px;
                margin-right: 10px;
            }
        </style>
    </head>
    
    <%
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setDateHeader("Expires", 0);
    %>
    
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
    
    <header>
            <h2>Bienvenido: <%=usuario%> 
                <form method="post" action="Sesiones" style="float: right;">
                    <input type="submit" value="Cerrar Sesión">
                </form>
            </h2>
    </header>
    
    <body>
        
              
    </body>
</html>