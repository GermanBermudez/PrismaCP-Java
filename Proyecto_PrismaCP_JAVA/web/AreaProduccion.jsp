<%-- 
    Document   : AreaProduccion
    Created on : 19/04/2024, 8:37:49 p. m.
    Author     : SHERMAN
--%>

<%-- 
    Document   : Solicitudes
    Created on : 11/04/2024, 8:50:28 p. m.
    Author     : SHERMAN
--%>

<%@page import="java.util.List"%>
<%@page import="ModeloDAO.ProduccionDao"%>
<%@page import="ModeloVO.ProduccionVo"%>
<%@page import="ModeloVO.UsuarioVo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>AreaProduccion</title>
        <style>
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
        
        /* Posicionamiento de los botones */
        .botones {
            position: fixed;
            bottom: 20px;
            right: 20px;
        }
        
        .botones button {
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            margin-left: 10px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .botones button:hover {
            background-color: #0056b3;
        }
        
        .centrado {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        </style>
    </head>
    <body>
        <h1>Area de Producción</h1>
    <% 
        String IdOrdProd = request.getParameter("IdProduccion");
        String descOrdProd = request.getParameter("DescProd");
        String CantidadOrdProd = request.getParameter("Cantidad");
        String FechaCreaOrdProd = request.getParameter("FechaCreacion");
        String EstadoOrdProd = request.getParameter("Estado");
        String FechaFinOrdProd = request.getParameter("FechaFinal");
        String EmpSolicOrdProd = request.getParameter("EmpSolicitante");
        String EmpEjecOrdProd = request.getParameter("EmpEjecutor");
        String TipOrdProd = request.getParameter("TipoOrden");
        byte[] ArchivoDisenoOrdProd = new byte[0];        
        
        ProduccionVo ordProdVo = new ProduccionVo(IdOrdProd, descOrdProd, CantidadOrdProd, FechaCreaOrdProd, EstadoOrdProd, FechaFinOrdProd, EmpSolicOrdProd, EmpEjecOrdProd,TipOrdProd, ArchivoDisenoOrdProd);
        ProduccionDao ordProdDao = new ProduccionDao(ordProdVo);
        
        List<ProduccionVo> ordProd = ordProdDao.consultarInformacion();
    
    if (!ordProd.isEmpty()) {
%>
<form id="formularioTareas" method="post" action="ProduccionControlador">
    <table border="1">
        <thead>
            <tr>
                <th>Selec.</th>
                <th>ID Solicitud</th>
                <th>Descripcion solicitud</th>
                <th>Cantidad</th>
                <th>Fecha de solicitud</th>
                <th>Estado de la orden</th>
                <th>Fecha finalización</th>
                <th>Empleado solicitante</th>
                <th>Empleado ejecutor</th>
                <th>Tipo de Orden</th>
                <th>Diseño</th>                
            </tr>
        </thead>
        <tbody>
        <% 
        for (ProduccionVo ordProdObj : ordProd) {
        %>
        <tr>
            <td class="centrado">
            <!-- Casilla de verificación -->
            <input type="checkbox" name="seleccionar" value="<%= ordProdObj.getIdProduccion()%>">
            </td>
            <td><%= ordProdObj.getIdProduccion()%></td>
            <td><%= ordProdObj.getDescProd()%></td>
            <td><%= ordProdObj.getCantidad() %></td>
            <td><%= ordProdObj.getFechaCreacion() %></td>            
            <td><%= ordProdObj.getEstado() %></td>
            <td><%= ordProdObj.getFechaFinal() %></td>
            <td><%= ordProdObj.getEmpSolicitante() %></td>            
            <td><%= ordProdObj.getEmpEjecutor() %></td>
            <td><%= ordProdObj.getTipoOrden() %></td>
            <td>
                <!-- Enlace para descargar el archivo PDF -->
                <a href="ProduccionControlador?action=descargar&idDisenoPrd=<%= ordProdObj.getIdProduccion()%>">Descargar</a>                              
            </td>
            <td>
                <!-- Hipervínculo para finalizar tarea -->
                <a href="ProduccionControlador?action=finalizar&id=<%= ordProdObj.getIdProduccion()%>">Finalizar</a>
            </td>
        </tr>
        <% 
        } // Fin del bucle for
        %>       
        </tbody>
    </table>
<% } %>
<div class="botones">
    <input type="hidden" id="opcionProduccion" name="opcionProduccion" value="4"> <!-- Valor predeterminado -->    
    <button id="asignarBtn" onclick="guardarSeleccion()">Asignar Tarea</button>    
    <button id="finalizarBtn" onclick="tareaFinalizada(4)">Finalizar Tarea</button>
</div>
</form>
        <script>
    var opcionProduccion = 3; // Variable global
    
    function guardarSeleccion() {
        document.getElementById("opcionProduccion").value = opcionProduccion;
        var checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
        var selecciones = [];

        checkboxes.forEach(function(checkbox) {
            var id = checkbox.value;
            var isChecked = checkbox.checked;
            selecciones.push({id: id, isChecked: isChecked});
        });

        var seleccionJSON = JSON.stringify(selecciones);

        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                console.log(this.responseText);
            }
        };
        xhttp.open("POST", "GuardarSeleccionServlet", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("seleccionJSON=" + seleccionJSON);
    }

    function tareaFinalizada(opcion) {
        opcionProduccion = opcion;
        document.getElementById("opcionProduccion").value = opcion;

        // Obtener la fecha actual
        var fechaActual = new Date().toISOString().slice(0, 10); // Formato YYYY-MM-DD

        // Enviar la fecha actual al servidor usando AJAX
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // La respuesta del servidor (si hay alguna) puede ser manejada aquí
                console.log(this.responseText);
            }
        };
        xhttp.open("POST", "ProduccionControlador", true);
        xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xhttp.send("fechaActual=" + fechaActual);
    }
        </script>
    </body>
</html>
