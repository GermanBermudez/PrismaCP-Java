/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.ProduccionVo;
import Util.CRUD;
import Util.ConexionBD;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyleConstants;

/**
 *
 * @author SHERMAN
 */
public class ProduccionDao  extends ConexionBD implements CRUD {
    
    private String IdProduccion, DescProd, Cantidad, FechaCreacion, Estado, FechaFinal, EmpSolicitante, EmpEjecutor, TipoOrden="";
    private byte [] ArchivoDisenoProd;
    
    //Declarar variables y/u objetos
    private Connection conection;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;
    private String sql;
    
    //Inicializar atributos (metodo principeal se usa para recibir los datos del VO)
    public ProduccionDao (ProduccionVo ProduccionVo) {
        super ();
        try {
            connection = this.obtenerConexion();
            System.out.println("Conexion exitosa");
            IdProduccion = ProduccionVo.getIdProduccion();
            DescProd = ProduccionVo.getDescProd();
            Cantidad = ProduccionVo.getCantidad();
            FechaCreacion = ProduccionVo.getFechaCreacion();
            Estado = ProduccionVo.getEstado();            
            FechaFinal = ProduccionVo.getFechaFinal();
            TipoOrden = ProduccionVo.getTipoOrden();
            ArchivoDisenoProd = ProduccionVo.getArchivoDisenoProd();
            EmpSolicitante = ProduccionVo.getEmpSolicitante();
            EmpEjecutor = ProduccionVo.getEmpEjecutor();
        }
        catch (Exception e) {
            System.err.println("Error" + e.toString());
        }
    }            
   
    @Override
    public boolean agregarRegistro() { 
        System.out.println("Tipo Orden DAO "+TipoOrden);
        try {            
            connection = this.obtenerConexion();
            sql = "INSERT INTO produccion (desc_produccion, cantidad, fecha_creacion, id_estado_prod, empleado_solicitante, diseno_prod, tipo_ord_prod) "
                    + "VALUES (?,?,?,?,?,?, (SELECT id_tipo_orden FROM tipo_orden WHERE desc_tipo_orden LIKE ?))";

            puente = connection.prepareStatement(sql);
            puente.setString(1, DescProd);
            puente.setString(2, Cantidad);
            puente.setString(3, FechaCreacion);
            puente.setString(4, Estado);            
            puente.setString(5, EmpSolicitante);
            InputStream inputStream = new ByteArrayInputStream(ArchivoDisenoProd);
            puente.setBinaryStream(6, inputStream);
            puente.setString(7, "%"+TipoOrden+"%");
            
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

    public boolean actualizarRegistroEmpleadoEjecutor (String idAct, String empleadoEjecutor) {        
        System.out.println("Ingreso a DAO Prod "+empleadoEjecutor);
        try {                  
            sql ="UPDATE produccion "
                    + "SET empleado_ejecutor = ? "
                    + "WHERE id_produccion = ?";
            
            puente = connection.prepareStatement(sql);
            puente.setString(1, empleadoEjecutor);
            puente.setString(2, idAct);
            
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<ProduccionVo> consultarInformacion () {
        List<ProduccionVo> informacionProduccion = new ArrayList<>();
        try {
            connection = this.obtenerConexion();
            sql = "SELECT prod.id_produccion, prod.desc_produccion, prod.cantidad, prod.fecha_creacion, prod.fecha_finalizacion, "
                    + "tipord.desc_tipo_orden, estord.desc_estado_orden, "
                    + "emp.Nombre_Empleado AS Nombre_Emp_Solicitante, "
                    + "empEjec.Nombre_Empleado AS Nombre_Emp_Ejecutor, "
                    + "prod.diseno_prod "
                    + "FROM produccion prod "
                    + "INNER JOIN tipo_orden tipord ON prod.tipo_ord_prod = tipord.id_tipo_orden "
                    + "INNER JOIN estado_orden estord ON prod.id_estado_prod = estord.id_estado_orden "
                    + "INNER JOIN empleado emp ON prod.empleado_solicitante = emp.Id_Empleado "
                    + "LEFT JOIN empleado empEjec ON prod.empleado_ejecutor = empEjec.Id_Empleado";
            
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next() ) {
                InputStream inputStream = mensajero.getBinaryStream("diseno_prod");
                byte[] archivoBytesDisenoProd = inputStreamToBytes (inputStream);
                //Crear un objeto DisenosVo con los datos de la consulta
                ProduccionVo inforProdObj = new ProduccionVo(
                    mensajero.getString("id_produccion"),
                    mensajero.getString("desc_produccion"),
                    mensajero.getString("cantidad"),
                    mensajero.getString("fecha_creacion"),
                    mensajero.getString("desc_estado_orden"),                        
                    mensajero.getString("fecha_finalizacion"),                                        
                    mensajero.getString("Nombre_Emp_Solicitante"),
                    mensajero.getString("Nombre_Emp_Ejecutor"),
                    mensajero.getString("desc_tipo_orden"),
                    archivoBytesDisenoProd
                        
                );               
                //Agregar el objeto DisenosVo a la lista
                informacionProduccion.add(inforProdObj);
            }
        }
            catch (Exception e) {
        System.out.println("Error: " + e.toString());
        } finally {
        try {
            this.cerrarConexion();
        } catch (Exception e) {
            System.out.println("Error al cerrar la conexión: " + e.toString());
        }
        }
        return informacionProduccion;
            
        }
    
    public List <ProduccionVo> consultarTipoProd () {
        List<ProduccionVo> TipoDisenos = new ArrayList<>();
        
        try {
            connection = this.obtenerConexion();
            sql = "SELECT * FROM tipo_orden";
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next() ) {
                byte[] byteVacio = new byte [0];
                ProduccionVo tipDis = new ProduccionVo(
                        "","","","","","","","",
                        mensajero.getString("desc_tipo_orden"),
                        byteVacio
                );
                TipoDisenos.add(tipDis);
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
        return TipoDisenos;
    }
    
    public byte [] descargarDisenoOrdProd(String idDisenoProd) {
        byte [] archivoPDF = new byte [0];
        System.out.println(idDisenoProd);
        try {
        connection = this.obtenerConexion();
        sql = "SELECT diseno_prod FROM produccion "
                + "WHERE id_produccion  = ?";
                
        puente = connection.prepareStatement(sql);
        puente.setString(1, idDisenoProd);
        mensajero = puente.executeQuery();        
        
        if (mensajero.next()) {
            InputStream inputStream = mensajero.getBinaryStream("diseno_prod");
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
