/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import ModeloVO.DisenosVo;
import Util.CRUD;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Util.ConexionBD;
import com.mysql.cj.Messages;
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
public class DisenosDao  extends ConexionBD implements CRUD {
    
    private String IdDiseno ="", Empleado ="", NombreDiseno ="", descDiseno ="", TipoDiseno ="";
    private byte [] ArchivoDiseno;
    
    //Declarar variables y/u Objetos
    private Connection conection;
    private PreparedStatement puente;
    private ResultSet mensajero;
    private boolean operacion = false;
    private String sql;
           
    //Inicializar atributos (metodo principeal se usa para recibir los datos del VO)
    public DisenosDao (DisenosVo DisenosVo) {
        super ();
        try {
            connection = this.obtenerConexion();
            System.out.println("Conexion exitosa");
            IdDiseno = DisenosVo.getIdDiseno();
            Empleado = DisenosVo.getEmpleado();
            NombreDiseno = DisenosVo.getNombreDiseno();
            descDiseno = DisenosVo.getDescDiseno();            
            TipoDiseno = DisenosVo.getTipoDiseno();
            ArchivoDiseno = DisenosVo.getArchivoDiseno();            
        }
        catch (Exception e) {            
            System.err.println("Error" + e.toString());
        }
    }
    
    //Agregar nuevo Diseño, debe crear el registro de Diseños
    @Override
    public boolean agregarRegistro () {
        try {           
            connection = this.obtenerConexion();
            sql = "INSERT INTO disenos (Nombre_Disenos, idEmpleado, DescripcionDiseno, ArchivoDiseno, Tipo_Diseno_Id_Tipo_Diseno) "
                    + "VALUES (?,?,?,?, (SELECT Id_Tipo_Diseno FROM tipo_diseno WHERE Nombre_Tipo_Diseno LIKE ?))";
                        
            puente = connection.prepareStatement(sql);
            puente.setString(1, NombreDiseno);
            puente.setString(2, Empleado);
            puente.setString(3, descDiseno);
            InputStream inputStream = new ByteArrayInputStream(ArchivoDiseno);
            puente.setBinaryStream(4, inputStream);
            puente.setString(5, "%"+TipoDiseno+"%");
            
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
    
    public List<DisenosVo> consultarInformacion () {
        List<DisenosVo> informacionDisenos = new ArrayList<>();
        try {
            connection = this.obtenerConexion();
            sql = "SELECT dis.Id_Disenos, dis.Nombre_Disenos, dis.ArchivoDiseno, dis.DescripcionDiseno, td.Nombre_Tipo_Diseno, emp.Nombre_Empleado "
                    + "FROM disenos dis "
                    + "INNER JOIN tipo_diseno td ON dis.Tipo_Diseno_Id_Tipo_Diseno = td.Id_Tipo_Diseno "
                    + "INNER JOIN empleado emp ON dis.idEmpleado = emp.Id_Empleado";
            
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next() ) {
                InputStream inputStream = mensajero.getBinaryStream("ArchivoDiseno");
                byte[] archivoBytesDiseno = inputStreamToBytes (inputStream);
                //Crear un objeto DisenosVo con los datos de la consulta
                DisenosVo inforDisenosObj = new DisenosVo(
                    mensajero.getString("Id_Disenos"),
                    mensajero.getString("Nombre_Empleado"),
                    mensajero.getString("Nombre_Disenos"),
                    mensajero.getString("DescripcionDiseno"),
                    mensajero.getString("Nombre_Tipo_Diseno"),                    
                    archivoBytesDiseno
                        
                );               
                //Agregar el objeto DisenosVo a la lista
                informacionDisenos.add(inforDisenosObj);
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
        return informacionDisenos;
            
        }
    
    public List <DisenosVo> consultarTipoDisenos () {
        List<DisenosVo> TipoDisenos = new ArrayList<>();
        
        try {
            connection = this.obtenerConexion();
            sql = "SELECT * FROM tipo_diseno";
            puente = connection.prepareStatement(sql);
            mensajero = puente.executeQuery();
            
            while (mensajero.next() ) {
                byte[] byteVacio = new byte [0];
                DisenosVo tipDis = new DisenosVo(
                        "","","",
                        mensajero.getString("Id_Tipo_Diseno"),
                        mensajero.getString("Nombre_Tipo_Diseno"),
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
    
    public byte [] descargarDiseno(String idDiseno) {
        byte [] archivoPDF = new byte [0];
        System.out.println(idDiseno);
        try {
        connection = this.obtenerConexion();
        sql = "SELECT ArchivoDiseno FROM disenos "
                + "WHERE Id_Disenos = ?";
                
        puente = connection.prepareStatement(sql);
        puente.setString(1, idDiseno);
        mensajero = puente.executeQuery();        
        
        if (mensajero.next()) {
            InputStream inputStream = mensajero.getBinaryStream("ArchivoDiseno");
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
    
