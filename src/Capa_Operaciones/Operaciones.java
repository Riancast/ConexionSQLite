package Capa_Operaciones;
//Importamos los paquetes necesarios.
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Importamos el paquete de conexion.
import Capa_Conexion.Conexion;

// Esta es la clase operaciones, la cual realiza las distintas operaciones de la base de datos.

public class Operaciones {
    //Creamos las variables para la conexion.
    static Connection cn;
    static Statement s;
    static ResultSet rs;
    DefaultTableModel modelo = new DefaultTableModel();
    //Creamos la operacion para mostrar datos en una jtable en el jform.
    public DefaultTableModel lista(){
       try{
       cn = Conexion.Enlace(cn);
       Statement s = cn.createStatement();
       //Consulta a mostrar.
       String query = "Select * from producto";
       rs = s.executeQuery(query);
       ResultSetMetaData rsmd=rs.getMetaData();
       //Obtenemos el número de columnas.
       int CanColumns = rsmd.getColumnCount();
       //Comprobamos.
       for(int i=1;i<=CanColumns;i++){
       //Cargamos columnas en modelo.
           modelo.addColumn(rsmd.getColumnLabel(i));
       }
       while (rs.next()){
       //Creamos Array.
       Object[] fila = new Object[CanColumns];
       //Cargamos datos a modelo.
       for(int i=0;i<CanColumns;i++){
           fila[i] = rs.getObject(i+1);
       }
       modelo.addRow(fila);
       }
       }catch(Exception e){} 
        //Retornamos modelo para jtable.
       return modelo;
    }
    
    //Creamos método para insertar datos.
    public void agregarConsulta(String Nombre, String Precio){
    //Dentro de try catch por si los errores.
        try{
        Statement s = cn.createStatement();
        String query = "INSERT INTO producto(nombre,precio)values ('"+Nombre+"',"+Precio+")";
        s.executeUpdate(query);
        s.close();
        cn.close();
        JOptionPane.showMessageDialog(null, "Dato agregado.");
        }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    }
        //Creamos método para eliminar datos.
    public void eliminarConsulta(String ID){
         try{
         Statement s = cn.createStatement();
         String query = "DELETE FROM producto WHERE id="+ID+"";    
         s.executeUpdate(query);
         s.close();
         cn.close();
         JOptionPane.showMessageDialog(null, "Dato eliminado.");    
         }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    }
        //Creamos método para modificar datos.
        public void modificarConsulta(String Nombre, String Precio, String ID){
         try{
         Statement s = cn.createStatement();
         String query = "UPDATE producto SET nombre='"+Nombre+"',precio="+Precio+" WHERE id="+ID+"";    
         s.executeUpdate(query);
         s.close();
         cn.close();
         JOptionPane.showMessageDialog(null, "Dato modificado.");    
         }catch(Exception e){JOptionPane.showMessageDialog(null, e);}
    }
}
