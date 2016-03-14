package Capa_Conexion;
//Importamos los paquetes necesarios.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

// Esta es la clase conexión, como su nombre indica esto permite conectar 
// nuestra aplicación a la base de datos SQLite.

public class Conexion {
    //Creamos la variable Connection.
    static Connection cn = null;
    //Creamos la clase de conexion hija.
    public static Connection Enlace(Connection cn)throws SQLException{
        //Creamos la ruta de la base de datos.
        String ruta = "src/BDproducto.db";
        try{
        Class.forName("org.sqlite.JDBC");
        cn = DriverManager.getConnection("jdbc:sqlite:"+ruta);
        }catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null, e);
        }
        return cn;
    }
}

