/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;
import ModeloVO.UsuarioVo;
import ModeloDAO.InventarioDao;
import ModeloVO.InventarioVo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
         

/**
 *
 * @author SHERMAN
 */
@WebServlet(name = "InventarioControlador", urlPatterns = {"/InventarioControlador"})
public class InventarioControlador extends HttpServlet {

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
        
        // Se obtiene el usuario modificador de la sesion
        HttpSession session = request.getSession();
        
        // Obtener el objeto UsuarioVo de la sesión para obtener el ID del usuario loggeado
        String usuarioModificador = "";
        UsuarioVo usuario = (UsuarioVo) session.getAttribute("DatosDeSesion");
        usuarioModificador = usuario.getIdEmpleado();
        
        // Se obtienen los parametros del formulario para agregar
        String nombreMaterial = request.getParameter("nombreMaterial");
        String tamano = request.getParameter("tamano");
        String descripcion = request.getParameter("descripcion");
        String cantidad = request.getParameter("cantidad");
        String disponibilidad = request.getParameter("disponibilidad");
        String idMateriales = request.getParameter("idMateriales");        
        String tipoModificacion = request.getParameter("tipoModificacion");
        String idMaterial = request.getParameter("idMaterial");
        
        //Crear objetos para realizar nuevas inserciones
        int opcionInv = Integer.parseInt(request.getParameter("opcionInv"));      
        InventarioVo InvVO = new InventarioVo (idMaterial, cantidad, disponibilidad, idMateriales, nombreMaterial, descripcion, tamano, usuarioModificador, tipoModificacion);        
        InventarioDao InvDAO = new InventarioDao (InvVO);
        
        // Se obtienen los parametros del formulario para actualizar
        String nombreMaterialAct = request.getParameter("textNombreMatAct");
        String tamanoAct = request.getParameter("textTamanoAct");
        String descripcionAct = request.getParameter("textDescripcionAct");
        String cantidadAct = request.getParameter("textCantidadAct");
        String disponibilidadAct = request.getParameter("disponibilidad");
        String idMaterialesActu = request.getParameter("idMaterialAct");        
        String tipoModificacionAct = request.getParameter("2");
        String idMaterialAct = request.getParameter("idMaterialAct");        
        
        //Crear objetos para realizar actualizaciones
        InventarioVo InvVOActualizar = new InventarioVo (idMaterialAct, cantidadAct, "", idMaterialesActu, nombreMaterialAct, descripcionAct, tamanoAct, usuarioModificador, "");
        InventarioDao InvDAOActualizar = new InventarioDao (InvVOActualizar);
        
        switch (opcionInv) {
            case 1:                
                InvDAO.agregarRegistro();                
                response.sendRedirect("AgregarMaterialInventario.jsp?accion=guardadoExitoso");
            break;        
            case 2:
                System.out.println(nombreMaterialAct+" "+descripcionAct);
                InvDAOActualizar.actualizarRegistro();
                System.out.println("La opcion es "+opcionInv);
                System.out.println("El id del material para actualizar es: "+idMaterialAct);
            break;
        }
        
        
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
