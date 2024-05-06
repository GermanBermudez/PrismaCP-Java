
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author SHERMAN
 */
public class ConexionBD {
    
    // Se declaran atributos u objetos
    
    private String driver, user, password, nameDb, port, urlDb;
    public Connection connection;
    // Asignar valores en el constructor

    public ConexionBD() {
        
        driver = "com.mysql.cj.jdbc.Driver";
        user = "root";
        password = "";
        nameDb = "bd_prismacp";
        port = "3306";
        urlDb = "jdbc:mysql://localhost:"+port+"/"+nameDb;  
        
        //Conectar
        
        try {
            Class.forName(driver).newInstance();
            connection = DriverManager.getConnection (urlDb,user,password);
            System.out.println("!Conectado con exito!");
            
        } catch (Exception e) {
            System.out.println("Error al conectarse "+e);
        }
    } 
    public Connection obtenerConexion () {
        return connection;
    }
    
    public Connection cerrarConexion () throws SQLException{
        connection.close();
        connection = null;
        return connection;
    }
    
}

