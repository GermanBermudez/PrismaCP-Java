/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ModeloDAO.DisenosDao;
import ModeloVO.DisenosVo;
import ModeloVO.UsuarioVo;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
@WebServlet(name = "DisenosControlador", urlPatterns = {"/DisenosControlador"})
public class DisenosControlador extends HttpServlet {

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
        
        String idDiseno = request.getParameter("idDiseno");
        System.out.println(idDiseno);  
        // Obtener el objeto UsuarioVo de la sesión para obtener el ID del usuario loggeado
        String usuarioModificadorDisenos = "";
        UsuarioVo usuario = (UsuarioVo) session.getAttribute("DatosDeSesion");
        usuarioModificadorDisenos = usuario.getIdEmpleado();
        
        System.out.println("El usuario es "+usuarioModificadorDisenos);
        // Se obtienen los parametros del formulario para agregar                
        String TipoDiseño = request.getParameter("tipodisenos");
        System.out.println(TipoDiseño);
        String descripcionDis = request.getParameter("textDescripcionDiseno");
        String nombreDis = request.getParameter("textNombreDiseno");
        String tamanoDis = request.getParameter("textTamanoDiseno");
        String opcionDiseno = request.getParameter("opcionDiseno");
        int opcionDis;
        if(opcionDiseno != null){
            opcionDis = Integer.parseInt(opcionDiseno);
        }
        else{            
            opcionDis = 2;
        }
        
        switch (opcionDis) {
            case 1:
                //OBTENER ARCHIVO: Obtener el Part del archivo del request
                Part archivoPart = request.getPart("archivodiseno");
                // Leer el contenido del archivo en un InputStream
                InputStream inputStream = archivoPart.getInputStream();
                // Convertir el InputStream a un arreglo de bytes
                byte[] archivoDiseno = inputStream.readAllBytes();
                //Objetos para el manejo de Diseños
                DisenosVo DisVo = new DisenosVo("", usuarioModificadorDisenos, nombreDis, descripcionDis, TipoDiseño, archivoDiseno);
                DisenosDao DisDao = new DisenosDao(DisVo);
                
                if (DisDao.agregarRegistro()) {
                    request.setAttribute( "mensajeExito", "El diseño se agrego correctamente!");
                    
                }else {
                    request.setAttribute( "mensajeError", "El diseño NO se agrego correctamente!");
                }
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);                                                
            break;
            case 2:
                System.out.println(idDiseno);
                descargarArchivo(idDiseno, response);
            break;
        }        
        
    }
    
        private void descargarArchivo(String idDiseno, HttpServletResponse response) throws IOException {
        DisenosVo DisVO = new DisenosVo();
        DisenosDao DisDAO = new DisenosDao (DisVO);
        byte[] archivoPDF = DisDAO.descargarDiseno(idDiseno);

        response.setContentType("application/pdf"); // Establecer el tipo de contenido como PDF
        response.setContentLength(archivoPDF.length); // Establecer la longitud del contenido

        response.setHeader("Content-Disposition", "attachment; filename=diseño.pdf");

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
