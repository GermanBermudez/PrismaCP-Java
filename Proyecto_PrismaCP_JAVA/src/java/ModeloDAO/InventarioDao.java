/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.InventarioVo;

import Util.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Util.ConexionBD;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SHERMAN
 */
public class InventarioDao extends ConexionBD implements CRUD{
 
    private String IdMaterial ="", cantidadInventrio ="", disponibilidadInventario ="", idMaterialesInventario ="", NombreMaterial ="", DescripcionMaterial ="", 
            TamanoMaterial ="", UsuarioModInventario ="", TipoModificacion ="";
    
// Declarar vairables y/u objetos
    private Connection connection;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;
    private String sql;
        
// Inicializar atributos (metodo principal se usa para recibir los datos del VO)

    public InventarioDao(InventarioVo InventarioVO) {
        super();
        try {
            connection = this.obtenerConexion();
            System.out.println("Conexion exitosa");
            
            IdMaterial = InventarioVO.getIdMaterial();
            cantidadInventrio = InventarioVO.getCantidadInventrio();
            disponibilidadInventario = InventarioVO.getDisponibilidadInventario();
            idMaterialesInventario = InventarioVO.getIdMaterialesInventario();
            NombreMaterial = InventarioVO.getNombreMaterial();
            DescripcionMaterial = InventarioVO.getDescripcionMaterial();
            TamanoMaterial = InventarioVO.getTamanoMaterial();
            UsuarioModInventario = InventarioVO.getUsuarioModInventario();
            TipoModificacion = InventarioVO.getTipoModificacion();
            

        }
        catch (Exception e) {            
            System.err.println("Error" + e.toString());
        }
        
    }
    
    //Agregar nuevo material, debe crear el registro en inventario
    @Override
    public boolean agregarRegistro(){
    try {
        connection = this.obtenerConexion();
        sql = "INSERT INTO MATERIALES (Nombre_Material, Tamaño_Material, descripcion_Material) VALUES (?,?,?)";              
        puente = connection.prepareStatement(sql);
        puente.setString(1, NombreMaterial);
        puente.setString(2, TamanoMaterial);
        puente.setString(3, DescripcionMaterial);        
        puente.executeUpdate();
        
        String max;
        sql = "SELECT Id_Materiales FROM MATERIALES ORDER BY Id_Materiales DESC LIMIT 1";
        puente = connection.prepareStatement(sql);
        mensajero = puente.executeQuery();

        // Tomar el primer valor el cual es el mayor
        if (mensajero.next()) {
            max = mensajero.getString("Id_Materiales");
            System.out.println(max);
            sql = "INSERT INTO INVENTARIO (Cantidad, Disponibilidad, Materiales_Id_Materiales ) VALUES (?,?,?)";
            PreparedStatement puenteInventario = connection.prepareStatement(sql);
            puenteInventario.setString(1, cantidadInventrio);
            puenteInventario.setString(2, disponibilidadInventario);
            puenteInventario.setString(3, max);        
            puenteInventario.executeUpdate();
            
            //Extraer el ultimo dato que se almaceno en la tabla inventario.
            String maxInv;
            sql = "SELECT Id_Inventario FROM inventario ORDER BY Id_Inventario DESC LIMIT 1";
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            if(mensajero.next()){
                maxInv = mensajero.getString("Id_Inventario");
                System.out.println("El ultimo inventario es "+maxInv);                
                sql = "INSERT INTO modificaciones_inventario (Empleado_Id_Empleado, Inventario_Id_Inventario, Tipo_Modificacion_Id_Tipo_Modificacion) VALUES (?,?,?)";
                PreparedStatement puenteModInventario = connection.prepareStatement(sql);
                puenteModInventario.setString(1, UsuarioModInventario);
                puenteModInventario.setString(2, maxInv);
                puenteModInventario.setString(3, "1");        
                puenteModInventario.executeUpdate();
                
            }
            else{
                System.out.println("No se encontraron registros en la tabla MATERIALES");
            }
            
        } else {
            System.out.println("No se encontraron registros en la tabla MATERIALES");
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
        return operacion;
    }
    
    //Actuliza estado e inventario a no disponible y 0 respectivamente
    @Override
    public boolean eliminarRegistro() {
        return operacion;
    }
    
    @Override
    public boolean actualizarRegistro() {
        try {
            connection = this.obtenerConexion();            
            sql ="UPDATE materiales set Nombre_Material =?, Tamaño_Material =?, descripcion_Material=? where Id_Materiales =?";
            puente = connection.prepareStatement(sql);            
            puente.setString(1, NombreMaterial);
            puente.setString(2, TamanoMaterial);            
            puente.setString(3, DescripcionMaterial);  
            puente.setString(4, IdMaterial); 
            puente.executeUpdate();
            operacion = true;
            System.out.println("Actualizo :D");
            
            sql ="INSERT INTO inventario (Cantidad, Disponibilidad, Materiales_Id_Materiales ) VALUES (?,?,?)";
            PreparedStatement puenteModInventario = connection.prepareStatement(sql);
            puenteModInventario.setString(1, cantidadInventrio);
            puenteModInventario.setString(2, "1");
            puenteModInventario.setString(3, IdMaterial);        
            puenteModInventario.executeUpdate();
            System.out.println("Agrego nuevo inventario");
            
            //Extraer el ultimo dato que se almaceno en la tabla inventario.
            String maxInv;
            sql = "SELECT Id_Inventario FROM inventario ORDER BY Id_Inventario DESC LIMIT 1";
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            if(mensajero.next()){                
                maxInv = mensajero.getString("Id_Inventario");
                System.out.println(maxInv+" "+UsuarioModInventario);
                sql ="INSERT INTO modificaciones_inventario (Empleado_Id_Empleado, Inventario_Id_Inventario, Tipo_Modificacion_Id_Tipo_Modificacion) VALUES (?,?,?)";
                PreparedStatement puenteActModInventario = connection.prepareStatement(sql);
                puenteActModInventario.setString(1, UsuarioModInventario);
                puenteActModInventario.setString(2, maxInv);
                puenteActModInventario.setString(3, "2");        
                puenteActModInventario.executeUpdate();
                System.out.println("Agrego nueva modificación");
            }
            else{
                System.out.println("No se encontraron registros en la tabla MATERIALES");
            }
            
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

    //  Lista la información completa requerida para mostrar en la vista
    public List<InventarioVo> consultarInformacion() {
        
    List<InventarioVo> inventarios = new ArrayList<>();
    try {
        connection = this.obtenerConexion();
        sql = "SELECT "
                + "mat.Id_Materiales, "
                + "inv.Cantidad, "
                + "di.descripcion_Disponibilidad, "
                + "inv.Materiales_Id_Materiales, "
                + "mat.Nombre_Material, "
                + "mat.descripcion_Material, "
                + "mat.Tamaño_Material, "
                + "emp.Nombre_Usuario, "
                + "tm.Descripción_Modificación "
                + "FROM inventario inv "
                + "INNER JOIN materiales mat ON inv.Materiales_Id_Materiales = mat.Id_Materiales "
                + "INNER JOIN modificaciones_inventario mod_inv ON inv.Id_Inventario = mod_inv.Inventario_Id_Inventario "
                + "INNER JOIN tipo_modificación tm ON mod_inv.Tipo_Modificacion_Id_Tipo_Modificacion = tm.Id_Tipo_Modificación "
                + "INNER JOIN disponibilidad_invenetario di ON inv.Id_Disponibilidad = di.id_Disponibilidad_Inventario "
                + "INNER JOIN empleado emp ON mod_inv.Empleado_Id_Empleado = emp.Id_Empleado;";
        
        puente = connection.prepareStatement(sql);
        mensajero = puente.executeQuery();        
        
        while (mensajero.next()) {
            // Crear un objeto InventarioVo con los datos de la consulta
            InventarioVo inventario = new InventarioVo(
                mensajero.getString("Id_Materiales"),
                mensajero.getString("Cantidad"),
                mensajero.getString("descripcion_Disponibilidad"),
                mensajero.getString("Materiales_Id_Materiales"),
                mensajero.getString("Nombre_Material"),
                mensajero.getString("descripcion_Material"),
                mensajero.getString("Tamaño_Material"),
                mensajero.getString("Nombre_Usuario"),
                mensajero.getString("Descripción_Modificación")
            );
            // Agregar el objeto InventarioVo a la lista
            inventarios.add(inventario);
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
    return inventarios;
}
   
    
    //Usar para actualizar
    public List<InventarioVo> consultarInformacionMaterial(String nombreBusqueda) {

    System.out.println("El material a buscar es: "+nombreBusqueda);
    List<InventarioVo> inventarios = new ArrayList<>();
    try {
        connection = this.obtenerConexion();
        sql = "SELECT "
                + "mat.Id_Materiales, "
                + "inv.Cantidad, "
                + "inv.Disponibilidad, "
                + "inv.Materiales_Id_Materiales, "
                + "mat.Nombre_Material, "
                + "mat.descripcion_Material, "
                + "mat.Tamaño_Material, "
                + "emp.Nombre_Usuario, "                              
                + "mod_inv.Tipo_Modificacion_Id_Tipo_Modificacion "             
                + "FROM inventario inv "
                + "INNER JOIN materiales mat ON inv.Materiales_Id_Materiales = mat.Id_Materiales "
                + "INNER JOIN modificaciones_inventario mod_inv ON inv.Id_Inventario = mod_inv.Inventario_Id_Inventario "
                + "INNER JOIN tipo_modificación tm ON mod_inv.Tipo_Modificacion_Id_Tipo_Modificacion = tm.Id_Tipo_Modificación "
                + "INNER JOIN empleado emp ON mod_inv.Empleado_Id_Empleado = emp.Id_Empleado "
                + "WHERE mat.Nombre_Material like '%?%'";
        
        puente = connection.prepareStatement(sql);
        mensajero = puente.executeQuery();        
        
        while (mensajero.next()) {
            // Crear un objeto InventarioVo con los datos de la consulta
            InventarioVo inventario = new InventarioVo(
                mensajero.getString("Id_Materiales"),
                mensajero.getString("Cantidad"),
                mensajero.getString("Disponibilidad"),
                mensajero.getString("Materiales_Id_Materiales"),
                mensajero.getString("Nombre_Material"),
                mensajero.getString("descripcion_Material"),
                mensajero.getString("Tamaño_Material"),
                mensajero.getString("Nombre_Usuario"),
                mensajero.getString("Tipo_Modificacion_Id_Tipo_Modificacion")
            );
            // Agregar el objeto InventarioVo a la lista
            inventarios.add(inventario);
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
    return inventarios;
}
   
}
