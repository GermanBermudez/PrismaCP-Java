/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import ModeloDAO.UsuarioDao;
import ModeloVO.UsuarioVo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
@WebServlet(name = "EmpleadoControlador", urlPatterns = {"/EmpleadoControlador"})
public class UsuarioControlador extends HttpServlet {

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
        
        //1. Recibir datos de las vistas
        String Id_Empleado = request.getParameter("textId");
        String Nombre_Usuario = request.getParameter("textNombre");
        String Contrasena_Usuario = request.getParameter("textContrasena");
        String NombreEmpleado = request.getParameter("textNombreEmp");
        String CedulaEmpleado = request.getParameter("textCedula");
        String CorreoEmpleado = request.getParameter("textCorreo");
        String TelefonoEmpleado = request.getParameter("textTelefono");
        String IdCargo = request.getParameter("textCargo");
        String cargoEmpleado = request.getParameter("cargos");
        
        int opcion = Integer.parseInt(request.getParameter("opcion"));
        
            
        //2. Insertar usuario Vo Crear objeto del VO
        UsuarioVo usuVO = new UsuarioVo(Id_Empleado, Nombre_Usuario, Contrasena_Usuario, NombreEmpleado, CedulaEmpleado, CorreoEmpleado, TelefonoEmpleado, IdCargo, cargoEmpleado);
        
        //3.Insertar el usuario DAO Instanciar DAO
        UsuarioDao usuDao = new UsuarioDao(usuVO);                        
                
        //4. Administrar operaciones
        
        switch (opcion) {
            
            case 1://Agregar usuario
                
                if (usuDao.agregarRegistro()) {
                    request.setAttribute( "mensajeExito", "El usuario se registro correctamente!");
                    
                }else {
                    request.setAttribute( "mensajeError", "El usuario NO se registro correctamente!");
                }
                request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
                break;
                
            case 2:// Actualizar usuario
                
                if (usuDao.actualizarRegistro()) {
                    request.setAttribute("mensajeExito", "El usuario se actualizo correctamente!");
                }else {
                    request.setAttribute( "mensajeError", "El usuario NO se actualizo correctamente!");
                }
                request.getRequestDispatcher("ActualizarUsuario.jsp").forward(request, response);
                break;
                
            case 3:// Eliminar usuario
                
                if (usuDao.eliminarRegistro()) {
                    request.setAttribute("mensajeExito", "El usuario se elimino correctamente!");
                }else {
                    request.setAttribute("mensajeError", "El usuario NO se elimino correctamente!");
                }
                request.getRequestDispatcher("EliminarUsuario.jsp").forward(request, response);
                break;
            
            case 4://                
                Map<String, String> resultado = usuDao.iniciarSesion(Nombre_Usuario, Contrasena_Usuario);
                Id_Empleado = resultado.get("idEmp");
                IdCargo = resultado.get("idCar");

                if (Id_Empleado != null && !Id_Empleado.isEmpty()) {                    
                    HttpSession miSesion = request.getSession(true);
                    usuVO = new UsuarioVo(Id_Empleado, Nombre_Usuario, Contrasena_Usuario, NombreEmpleado, CedulaEmpleado, CorreoEmpleado, TelefonoEmpleado, IdCargo, cargoEmpleado);
                    miSesion.setAttribute("DatosDeSesion", usuVO);
                    System.out.println("Cargo es " + IdCargo);
                    request.getRequestDispatcher("MenuPrincipal.jsp").forward(request, response);
                } else {
                    request.setAttribute("mensajeError", "!Usuario y/o Contraseña incorrecta!. Verifique los datos");
                    request.getRequestDispatcher("Index.jsp").forward(request, response);
                }
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
