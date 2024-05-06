/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.FacturasVo;
import Util.CRUD;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Util.ConexionBD;
import com.mysql.cj.jdbc.Blob;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SHERMAN
 */

public class FacturasDao extends ConexionBD implements CRUD {
    
    private String IdFacturas = "", IdEmpleado = "", descFacturas = "", idTipoFactura = "", TipoFactura = "";
    private byte [] ArchivoFactura ;
    
// Declarar vairables y/u objetos
    private Connection connection;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;
    private String sql;
    
// Inicializar atributos (metodo principal se usa para recibir los datos del VO)
    public FacturasDao(FacturasVo FacturasVO) {
        super();
        try {
            connection = this.obtenerConexion();
            System.out.println("Conexion exitosa");            
            IdFacturas = FacturasVO.getIdFacturas();
            IdEmpleado = FacturasVO.getIdEmpleado();
            TipoFactura = FacturasVO.getTipoFactura();
            ArchivoFactura = FacturasVO.getArchivoFactura();
            descFacturas = FacturasVO.getDescFacturas();
        }
        catch (Exception e) {            
            System.err.println("Error" + e.toString());
        }
        
    }
    
    //Agregar nueva factura, debe crear el registro en Facturas
    @Override
    public boolean agregarRegistro(){
    try {
        System.out.println("La desc factura es "+descFacturas);
        connection = this.obtenerConexion();
        sql = "INSERT INTO facturas (Empleado_Id_Empleado, Descripcion_Factura, ArchivoFactura, Tipo_Factura_Id_Tipo_Factura) "
                + "VALUES (?,?,?, (SELECT Id_Tipo_Factura FROM tipo_factura WHERE Descripcion_Tipo_Factura LIKE ?))";
        
        puente = connection.prepareStatement(sql);               
        puente.setString(1, IdEmpleado);
        puente.setString(2, descFacturas);
        InputStream inputStream = new ByteArrayInputStream(ArchivoFactura);
        puente.setBinaryStream(3, inputStream);
        puente.setString(4, "%"+TipoFactura+"%");
        
        puente.executeUpdate();        
                
    }catch (Exception e) {
        System.out.println("Error: " + e.toString());
    }
    finally {
        try {
            this.cerrarConexion();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
    }
        return operacion;
    }

    @Override
    public boolean actualizarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminarRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<FacturasVo> consultarInformacion() {
        
    List<FacturasVo> informacionFacturas = new ArrayList<>();
    try {
        connection = this.obtenerConexion();
        sql = "SELECT fac.Id_Facturas, emp.Nombre_Usuario, fac.Descripcion_Factura, fac.Tipo_Factura_Id_Tipo_Factura, tf.Descripcion_Tipo_Factura, fac.ArchivoFactura "
                + "FROM facturas fac "
                + "INNER JOIN tipo_factura tf ON fac.Tipo_Factura_Id_Tipo_Factura = tf.Id_Tipo_Factura "
                + "INNER JOIN empleado emp ON fac.Empleado_Id_Empleado = emp.Id_Empleado";
        
        puente = connection.prepareStatement(sql);
        mensajero = puente.executeQuery();        
        
        while (mensajero.next()) {            
            InputStream inputStream = mensajero.getBinaryStream("ArchivoFactura");
            byte[] archivoBytesFactura = inputStreamToBytes (inputStream);        
            // Crear un objeto FacturasVo con los datos de la consulta
            FacturasVo inforFacturasObj = new FacturasVo(
                mensajero.getString("Id_Facturas"),
                mensajero.getString("Nombre_Usuario"),
                mensajero.getString("Descripcion_Factura"),
                mensajero.getString("Tipo_Factura_Id_Tipo_Factura"),                
                mensajero.getString("Descripcion_Tipo_Factura"),
                archivoBytesFactura
                
            );
            // Agregar el objeto FacturasVo a la lista
            informacionFacturas.add(inforFacturasObj);
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
    return informacionFacturas;
}
    
    public List<FacturasVo> consultarTipoFacturas() {
        List<FacturasVo> TipoFacturas = new ArrayList<>();

        try {
            connection = this.obtenerConexion();
            sql = "SELECT * FROM tipo_factura";        
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next()) {
                byte[] byteVacio = new byte[0];
                FacturasVo tipFact = new FacturasVo(
                        "","","",
                        mensajero.getString("Id_Tipo_Factura"),
                        mensajero.getString("Descripcion_Tipo_Factura"),
                        byteVacio
                );                
                TipoFacturas.add(tipFact);
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
        return TipoFacturas;
    }
    
    public byte [] descargarFactura(String idFactura){
        byte [] archivoPDF = new byte[0];
        System.out.println(idFactura);
        try {
        connection = this.obtenerConexion();
        sql = "SELECT fac.ArchivoFactura "
                + "FROM facturas fac "
                + "WHERE Id_Facturas = ?";        
        puente = connection.prepareStatement(sql);
        puente.setString(1, idFactura);
        mensajero = puente.executeQuery();        
        
        if (mensajero.next()) {
            InputStream inputStream = mensajero.getBinaryStream("ArchivoFactura");
            archivoPDF = inputStreamToBytes(inputStream);  
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
    return archivoPDF;
    }
    
    //Metodo creado para convertir el archivo PDF de la BDD al arreglo requerido para la manipulación
    private byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        byte[] data = new byte[4096]; // Tamaño del buffer configurado para que soporte el archivo de BDD
        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }
}
