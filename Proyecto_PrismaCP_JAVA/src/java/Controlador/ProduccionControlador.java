/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ModeloDAO.ProduccionDao;
import ModeloVO.ProduccionVo;
import ModeloVO.UsuarioVo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

/**
 *
 * @author SHERMAN
 */
@MultipartConfig
@WebServlet(name = "ProduccionControlador", urlPatterns = {"/ProduccionControlador"})
public class ProduccionControlador extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String idProd = request.getParameter("idDisenoPrd");
        // Obtener el objeto UsuarioVo de la sesión para obtener el ID del usuario loggeado
        String usuarioCreadorOrden = "";
        UsuarioVo usuario = (UsuarioVo) session.getAttribute("DatosDeSesion");
        usuarioCreadorOrden = usuario.getIdEmpleado();
        
        // Se obtienen los parametros del formulario para agregar
        String TipoOrden = request.getParameter("tipoOrden");
        String Cantidad = request.getParameter("cantidad");
        String DescripcionOrden = request.getParameter("descripcionOrden");        
        String opcionProd = request.getParameter("opcionProduccion");
        
        //Se declara estado vacio, para insertar es 1, si es actualizar toma el valor esperado
        String idEstadoProd;
        
        int opcionPrd;
        if(opcionProd != null){
            opcionPrd = Integer.parseInt(opcionProd);
        }
        else{            
            opcionPrd = 2;
        }        
        System.out.println("La opcion es "+opcionPrd);
        switch (opcionPrd) {
            case 1:
                
                idEstadoProd = "1";
                // Obtener la fecha actual
                Date fechaCreacion = new Date();
                
                // Frmatear la fecha en el formato esperado
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String fechaFormateadaCreacion = formatter.format(fechaCreacion);
                
                //OBTENER ARCHIVO: Obtener el Part del archivo del request
                Part archivoPart = request.getPart("archivodiseno");
                // Leer el contenido del archivo en un InputStream
                InputStream inputStream = archivoPart.getInputStream();
                // Convertir el InputStream a un arreglo de bytes
                byte[] archivoDiseno = inputStream.readAllBytes();
                
                ProduccionVo ProdVo = new ProduccionVo("",DescripcionOrden, Cantidad, fechaFormateadaCreacion, idEstadoProd, "", usuarioCreadorOrden, "", TipoOrden, archivoDiseno);
                ProduccionDao ProdDao = new ProduccionDao(ProdVo);
                if (ProdDao.agregarRegistro()) {
                    request.setAttribute( "mensajeExito", "El diseño se agrego correctamente!");                    
                }else {
                    request.setAttribute( "mensajeError", "El diseño NO se agrego correctamente!");
                }
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
            break;
            case 2:
                System.out.println("El id de diseño en prd controlador es: "+idProd);
                descargarArchivoProd(idProd, response);
            break;
            case 3:
                ProduccionVo ProdVoAct = new ProduccionVo();
                ProduccionDao ProdDaoAct = new ProduccionDao(ProdVoAct);                
                
                // Obtener el ID y el estado de la selección del checkbox desde la solicitud
                String id = request.getParameter("id");
                String isChecked = request.getParameter("isChecked");
        
                // Obtener los ID seleccionados
                String[] idsSeleccionados = request.getParameterValues("seleccionar");

                if (idsSeleccionados != null) {
                    for (String idSel : idsSeleccionados) {
                        // Procesar cada ID seleccionado como sea necesario
                        ProdDaoAct.actualizarRegistroEmpleadoEjecutor(idSel, usuarioCreadorOrden);
                        System.out.println("ID seleccionado: " + idSel);
                    }
                 
                }
                else if(idsSeleccionados == null){
                            
                }
                else {
                    // Si no se seleccionaron IDs, imprimir un mensaje o manejar la situación de acuerdo a tus necesidades
                    System.out.println("No se seleccionaron IDs");
                }
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
            break;
        } 
    }

        private void descargarArchivoProd (String idDiseno, HttpServletResponse response) throws IOException {
        ProduccionVo prdVO = new ProduccionVo();
        ProduccionDao prdDAO = new ProduccionDao (prdVO);
        byte[] archivoPDF = prdDAO.descargarDisenoOrdProd(idDiseno);

        response.setContentType("application/pdf"); // Establecer el tipo de contenido como PDF
        response.setContentLength(archivoPDF.length); // Establecer la longitud del contenido

        response.setHeader("Content-Disposition", "attachment; filename=diseñoPrd.pdf");

        response.getOutputStream().write(archivoPDF);
        response.getOutputStream().flush();
    }    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
