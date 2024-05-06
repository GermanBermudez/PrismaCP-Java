package ModeloDAO;


import ModeloVO.InventarioVo;
import ModeloVO.UsuarioVo;
import Util.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Util.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 *
 * @author SHERMAN
 */
public class UsuarioDao extends ConexionBD implements CRUD{
    
    // Declarar vairables y/u objetos
    private Connection connection;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;
    private String operId="", idCarg="";
    private String sql;
    UsuarioVo usuaVO = new UsuarioVo();
    private String idEmpleado ="", nombreUsuario ="", contrasenaUsuario ="", NombreEmpleado ="", CedulaEmpleado ="", CorreoEmpleado ="", TelefonoEmpleado="", IdCargo ="", cargoEmpleado="";
    
    // Inicializar atributos (metodo principal se usa para recibir los datos del VO)

    public UsuarioDao(UsuarioVo usuVO) {
        super();
        try {
            connection = this.obtenerConexion();
            System.out.println("Conexion exitosa");
            idEmpleado = usuVO.getIdEmpleado();
            nombreUsuario = usuVO.getNombreUsuario();
            contrasenaUsuario = usuVO.getContrasenaUsuario();
            NombreEmpleado = usuVO.getNombreEmpleado();
            CedulaEmpleado = usuVO.getCedulaEmpleado();
            CorreoEmpleado = usuVO.getCorreoEmpleado();
            TelefonoEmpleado = usuVO.getTelefonoEmpleado();
            IdCargo = usuVO.getIdCargo();
            cargoEmpleado = usuVO.getCargoEmpleado();
        } catch (Exception e) {
            System.err.println("Error" + e.toString());
        }
        
    }

    @Override
    public boolean agregarRegistro() {
        try {            
            sql = "INSERT INTO empleado (Nombre_Usuario, Contrasena_Usuario, Nombre_Empleado, Cedula_Empleado, Correo_Empleado, Telefono_Empleado, Cargo_Id_Cargo) "
                + "VALUES (?,?,?,?,?,?, (SELECT Id_Cargo FROM cargo WHERE Nombre_Cargo LIKE ?));";
            puente = connection.prepareStatement(sql);
            puente.setString(1, nombreUsuario);
            puente.setString(2, contrasenaUsuario);
            puente.setString(3, NombreEmpleado);
            puente.setString(4, CedulaEmpleado);
            puente.setString(5, CorreoEmpleado);
            puente.setString(6, TelefonoEmpleado);
            puente.setString(7, "%" +cargoEmpleado+ "%"); // Agrega el comodín "%" aquí, no dentro de la consulta
            puente.executeUpdate();
            operacion = true;
            
        }
        catch (SQLIntegrityConstraintViolationException e) {
        System.out.println("Error: El nombre de usuario ya está en uso. Por favor, elija otro nombre de usuario.");
        operacion = false; // Indicar que la operación no fue exitosa debido al error
        }         
        catch (SQLException e) {
            System.out.println("Error" +e.toString ());            
        }
        finally {
            try {
                this.cerrarConexion();
            }
            catch (Exception e) {
            }
        }
        return operacion;
    }
    
    @Override
    public boolean actualizarRegistro() {
        try {
            sql ="update usuario set Nombre_Usuario =? , Contrasena_Usuario =? where Id_Empleado =?";
            puente = connection.prepareStatement(sql);
            puente.setString(1, nombreUsuario);
            puente.setString(2, contrasenaUsuario);
            puente.setString(3, idEmpleado);
            puente.executeUpdate();
            operacion = true;
            
        } catch (SQLException e) {
            System.out.println("Error" +e.toString ());
            
        }finally {
            try {
                this.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return operacion;
    }
    
    @Override
    public boolean eliminarRegistro() {
        try {
            sql ="delete usuario set Nombre_Usuario =? , Contrasena_Usuario =? where Id_Empleado =?";
            puente = connection.prepareStatement(sql);
            puente.setString(1, nombreUsuario);
            puente.setString(2, contrasenaUsuario);
            puente.setString(3, idEmpleado);
            puente.executeUpdate();
            operacion = true;
            
        } catch (SQLException e) {
            System.out.println("Error" +e.toString ());
            
        }finally {
            try {
                this.cerrarConexion();
            } catch (Exception e) {
            }
        }
        return operacion;
    }
    
    
    public Map<String, String> iniciarSesion(String nombreUsuario, String contrasenaUsuario) {
    Map<String, String> resultado = new HashMap<>();
    
    try {
        connection = this.obtenerConexion();
        sql = "SELECT * FROM empleado WHERE Nombre_Usuario = ? AND Contrasena_Usuario = ?";
        puente = connection.prepareStatement(sql);
        puente.setString(1, nombreUsuario);
        puente.setString(2, contrasenaUsuario);                        
        mensajero = puente.executeQuery();
        
        if (mensajero.next()) {            
            // Agregar valores al mapa
            resultado.put("idEmp", mensajero.getString("Id_Empleado"));
            resultado.put("idCar", mensajero.getString("Cargo_Id_Cargo"));            
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.toString());
    } finally {
        try {
            this.cerrarConexion();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }        
    }    
    return resultado;    
}
    
    
    public List<UsuarioVo> consultarCargos() {
        List<UsuarioVo> cargos = new ArrayList<>();
        try {
            connection = this.obtenerConexion();
            sql = "SELECT * FROM cargo";        
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next()) {
                UsuarioVo usuCargos = new UsuarioVo(
                "","","","","","","",
                mensajero.getString("Id_Cargo"),
                mensajero.getString("Nombre_Cargo")               
                );                                                               
                cargos.add(usuCargos);
            }                
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
        try {
            this.cerrarConexion();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }        
        return cargos;
    }

}
