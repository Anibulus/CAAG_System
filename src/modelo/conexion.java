package modelo;

import java.sql.*;
import javax.swing.*;

public class conexion {
    public Connection conn;
    
    public Connection getConexion() throws SQLException{
        String db="caag";
        String un="sa";
        String pass="12345";
        
        try{
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl="jdbc:sqlserver://CAAG-SL-LAB8-PC:1433;databaseName="+db+";user="+un+";password="+pass+";";
            //-------------------------NOMBRE DE PC, Nombre de sqlserver y puesto 
            conn=DriverManager.getConnection(connectionUrl);
        }
        catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null,"Error"+e.getMessage());  
        }
        catch(SQLException e){
          JOptionPane.showMessageDialog(null,"Error"+e.getMessage());  
        }
        catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error"+e.getMessage());  
        }
        return conn;
    }
    
    //Como aun no creo empleado, provoca un error
    static empleado Activo=null;
    public empleado getEmpleadoActivo(){
        return Activo;
    }
    public int getEmpleadoActivoN(){
        return Activo.getIdEmpleado();
    }
    public void setEmpleadoActivo(int id){
        Activo=new empleado();
        Activo.setIdEmpleado(id);
    }
}
