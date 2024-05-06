/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ModeloDAO.FacturasDao;
import ModeloVO.FacturasVo;
import ModeloVO.UsuarioVo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author SHERMAN
 */
@MultipartConfig
@WebServlet(name = "FacturasControlador", urlPatterns = {"/FacturasControlador"})
public class FacturasControlador extends HttpServlet {

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
        
        String idFactura = request.getParameter("idFactura");
        System.out.println(idFactura);        
        
        // Obtener el objeto UsuarioVo de la sesión para obtener el ID del usuario loggeado
        String usuarioModificadorFacturas = "";
        UsuarioVo usuario = (UsuarioVo) session.getAttribute("DatosDeSesion");
        usuarioModificadorFacturas = usuario.getIdEmpleado();
        
        // Se obtienen los parametros del formulario para agregar                
        String TipoFactura = request.getParameter("tipofacturas");
        String descripcion = request.getParameter("textDescripcionFactura");
        String opcionFactura = request.getParameter("opcionFact");
        int opcionFact;
        if(opcionFactura != null){
            opcionFact = Integer.parseInt(opcionFactura);
        }
        else{            
            opcionFact = 2;
        }                        
        
        switch (opcionFact) {
            case 1:
                //OBTENER ARCHIVO: Obtener el Part del archivo del request
                Part archivoPart = request.getPart("archivofactura");
                // Leer el contenido del archivo en un InputStream
                InputStream inputStream = archivoPart.getInputStream();
                // Convertir el InputStream a un arreglo de bytes
                byte[] archivoFactura = inputStream.readAllBytes();
                //Objetos para el manejo de facturas
                FacturasVo FactVO = new FacturasVo("", usuarioModificadorFacturas, descripcion, "", TipoFactura, archivoFactura);
                FacturasDao FactDAO = new FacturasDao (FactVO);                                
                FactDAO.agregarRegistro();
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
            break;
            case 2:                
                System.out.println(idFactura);
                descargarArchivo(idFactura, response);               
            break;
        }                       
        
    }
        
        private void descargarArchivo(String idFactura, HttpServletResponse response) throws IOException {
        FacturasVo FactVO = new FacturasVo();
        FacturasDao FactDAO = new FacturasDao (FactVO);
        byte[] archivoPDF = FactDAO.descargarFactura(idFactura);

        response.setContentType("application/pdf"); // Establecer el tipo de contenido como PDF
        response.setContentLength(archivoPDF.length); // Establecer la longitud del contenido

        response.setHeader("Content-Disposition", "attachment; filename=factura.pdf");

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
