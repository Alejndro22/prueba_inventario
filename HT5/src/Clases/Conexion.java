package Clases;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;
    private final String driver = "com.mysql.jdbc.Driver";
    private final String base = "prueba_inventario";//<- Esto cambiarlo sin duda
    private final String host = "localhost";//<- Cambiar esto
    private final String puerto = "3306";//<- Posiblemente eso
    private final String url = "jdbc:mysql://" + host+ ":"+ puerto + "/" + base;
    private final String user = "root";
    private final String password = "datos2020";
    
    
   public Connection conectarMySQL(){
       conexion = null;
       try {
           Class.forName(driver);
           conexion = (Connection) DriverManager.getConnection(url,user,password);
           System.out.println(url);
           System.out.println("conectado");
       } catch (ClassNotFoundException | SQLException e) {
           System.out.println("error");
           System.out.println(url);
       }
       return conexion;
   }
}