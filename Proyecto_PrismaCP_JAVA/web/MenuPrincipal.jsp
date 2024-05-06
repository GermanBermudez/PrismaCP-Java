<%-- 
    Document   : Menu
    Created on : 17/03/2024, 11:39:01 a. m.
    Author     : SHERMAN
--%>


<%@page import="java.util.List"%>
<%@page import="ModeloDAO.UsuarioDao"%>
<%@page import="ModeloVO.UsuarioVo"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
        
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <link rel="stylesheet" type="text/css" href="assets/css/style.css">

  <title>Prisma CP App - Index</title>
  <meta content="" name="description">
  <meta content="" name="keywords">

  <!-- Iconos para colocar en la barra superior -->
  <link href="assets/img/favicon.png" rel="icon">
  
  <!-- Fuentes de Google -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">
  
  <!-- Vendor CSS Files -->
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>
    
    

    <!-- ======= Navegacion movil con botones ======= -->
    <i class="bi bi-list mobile-nav-toggle d-xl-none"></i>

    <!-- ======= Header ======= -->
    <header id="header">
        <div class="d-flex flex-column">

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
            
            <div class="profile">
            <img src="assets/img/logoiniciosesion.png" alt="" class="img-fluid rounded-circle">
            <h1 class="text-light"><a href="#" onclick="mostrarContenido('PaginaInicio.jsp')"><%=usuario%></a> <i class="bx bx-home"></i> </h1>
            <form method="post" action="Sesiones" > <input type="submit" value="Cerrar Sesión" class="nav-link scrollto active"> <i class='bx bx-log-out-circle' ></i> </form>
            </div>

            <%
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
            response.setDateHeader("Expires", 0);
            %>                        
            
            <%
            String idCargo ="";
            if (BuscarSesion.getAttribute("DatosDeSesion")==null) {
                request.getRequestDispatcher("Index.jsp").forward(request, response);
    
                }else {
                UsuarioVo usuVO = (UsuarioVo)BuscarSesion.getAttribute("DatosDeSesion");
                idCargo = usuVO.getIdCargo();
                System.out.println(idCargo);
                }
            %>
            
            <nav id="navbar" class="nav-menu navbar">

              <%
                if(idCargo.equals("1")){
              %>

                <ul>
                  <li><a href="#" onclick="mostrarContenido('RegistrarUsuario.jsp')" class="nav-link scrollto"> <i class='bx bx-user-plus'></i> <span>Registrar Usuario</span></a></li>

                  <li><a href="#" onclick="mostrarContenido('Disenos.jsp')" class="nav-link scrollto"> <i class='bx bx-spray-can' ></i> <span>Diseños</span></a></li>

                  <li><a href="#" onclick="mostrarContenido('Inventario.jsp')" class="nav-link scrollto"> <i class='bx bx-layer'></i> <span>Inventario</span></a></li>
                  
                  <li><a href="#" onclick="mostrarContenido('OrdenProduccion.jsp')" class="nav-link scrollto"> <i class='bx bx-list-check'></i> <span>Ordenes a Producción</span></a></li>
                  
                  <li><a href="#" onclick="mostrarContenido('AreaProduccion.jsp')" class="nav-link scrollto"> <i class='bx bx-wrench'></i> <span>Area de Producción</span></a></li>
                  
                  <li><a href="#" onclick="mostrarContenido('Facturas.jsp')" class="nav-link scrollto"> <i class='bx bx-file'></i> <span>Facturas</span></a></li>
                  <br><br><br>
                  
                  

                </ul>
              
              <%        
                  }
                  else if(idCargo.equals("2")){
              %>
              <ul>
                <li><a href="#" onclick="mostrarContenido('Disenos.jsp')" class="nav-link scrollto active"> <i class='bx bx-spray-can' ></i> <span>Diseños</span></a></li>
                <li><a href="#" onclick="mostrarContenido('OrdenProduccion.jsp')" class="nav-link scrollto active"> <i class='bx bx-list-check'></i> <span>Ordenes a Producción</span></a></li>
                <li><a href="#" onclick="mostrarContenido('Facturas.jsp')" class="nav-link scrollto active"> <i class='bx bx-file'></i> <span>Facturas</span></a></li>
              </ul>

              <%  
                  }
                  else if(idCargo.equals("3")){
              %>
              <ul>
                <li><a href="#" onclick="mostrarContenido('Inventario.jsp')" class="nav-link scrollto active"> <i class='bx bx-layer'></i> <span>Inventario</span></a></li>
                <li><a href="#" onclick="mostrarContenido('AreaProduccion.jsp')" class="nav-link scrollto active"> <i class='bx bx-wrench'></i> <span>Area de Producción</span></a></li>
                <li><a href="#" onclick="mostrarContenido('Facturas.jsp')" class="nav-link scrollto active"> <i class='bx bx-file'></i> <span>Facturas</span></a></li>
              </ul>
              <%  
                  }
              %>  
            </nav><!-- .nav-menu -->
        </div>
    </header><!-- Fin del Header -->
    
  <main id="main">      
      <div id="contenidoPrincipal"></div>      
  </main>

  <!-- ======= Footer ======= -->
  <footer id="footer">
    <div class="container">
      <div class="copyright">
        &copy; Copyright <strong><span>Prisma CP</span></strong>
      </div>
      <div class="credits">        
        Diseñado por Grupo Softeam &copy;
      </div>
    </div>
  </footer><!-- Fin del Footer -->

  <!-- Archivos Vendor JS -->
  <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
  <script src="assets/vendor/aos/aos.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
  <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
  <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
  <script src="assets/vendor/typed.js/typed.umd.js"></script>
  <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
  <script src="assets/vendor/php-email-form/validate.js"></script>

  <!-- Template Main JS File -->
  <script src="assets/js/main.js"></script>

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

<script>
    function abrirVentana(idMaterial) {
        var opciones = "width=600,height=400,scrollbars=yes,resizable=yes";
        var url = "ModificarMaterialInventario.jsp?id=" + idMaterial;
        window.open(url, "_blank", opciones);
    }
</script>    
    
    
<script>
    // Función para cargar el contenido en la parte derecha cuando se hace clic en un enlace del side navigation menu
    function mostrarContenido(pagina) {
        // Realiza una petición HTTP GET para cargar la página seleccionada
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                // Actualiza el contenido de la parte derecha con la respuesta obtenida
                document.getElementById("contenidoPrincipal").innerHTML = this.responseText;
                // Carga automáticamente los archivos JavaScript asociados a la página
                var scripts = document.getElementById("contenidoPrincipal").getElementsByTagName("script");
                for (var i = 0; i < scripts.length; i++) {
                    eval(scripts[i].innerHTML);
                    console.log("Ingreso a "+pagina);
                }
            }
        };
        xhttp.open("GET", pagina, true);
        xhttp.send();
    }
</script>  

  <div class="error-message">    
  <% if (request.getAttribute("mensajeError")!=null) { %>
      <%= request.getAttribute("mensajeError") %>
  <% } %>
  </div>

  <div class="success-message">
  <% if (request.getAttribute("mensajeExito")!=null) { %>
      <%= request.getAttribute("mensajeExito") %>
  <% } %>
  </div>
</body>
</html>