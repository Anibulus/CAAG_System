package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class empleadoDAO {
    conexion c;
    public empleadoDAO(){
        c=new conexion();
    }
    
    public boolean ingresar(String usu, String contra) throws SQLException{
        boolean entrar=false;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            System.out.println("Hay conexion");
            ps=con.prepareCall("select * from usuario where usuario=?");
            /*
            No olvidar poner los comodines
            */
            ps.setString(1, usu);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println("Hago la consulta");
                /*Ver si agregar el idUsuario*/
                String usuBD=rs.getString("usuario");
                String contraBD=rs.getString("contrasenia");               
                String estatus=rs.getString("estatus");
                System.out.println(usuBD);
                System.out.println(contraBD);                
                /*Se acaba de comprobar el usuario en la base de datos, se procede a comparar la contraseña*/
                if(contra.equals(contraBD)){
                    /*el usuario y contraseña coinciden*/
                    if(estatus.equals("A")){
                        int id=rs.getInt("idUsuario");
                        c.setEmpleadoActivo(consultarIDUsuarioEmpleado(id));
                        System.out.println("entrar es verdadero");
                        entrar=true;
                        System.out.println("Entro al sistema");
                    }
                    else{
                       JOptionPane.showMessageDialog(null, "La persona no trabaja actualmente"); 
                    }//Si la persona es activa
                }else{
                    /*la contraseña no coincide*/
                    JOptionPane.showMessageDialog(null, "Contraseña incorrecta, prueba otra vez");
                }//Si usu i pass coinciden
            }else{
                JOptionPane.showMessageDialog(null, "El usuario no se encuentra");
            }//Si se encuentra el usuario  
            con.close();
        }//Si la conexion es nula
        return entrar;
    }//Fin de la funcion iniciar sesion
    
    public int registrarDireccion(String calle, int ext, int inte, String col) throws SQLException{
        int dir=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("insert into direccion (calle, numExterior, numInterior, colonia) values (?, ?, ?, ?)");
            ps.setString(1, calle);
            ps.setInt(2, ext);
            ps.setInt(3, inte);
            ps.setString(4, col);
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                /*Conseguir el ultimo registro*/
                ps=con.prepareCall("SELECT @@identity AS id");
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    dir=rs.getInt("id");
                    System.out.println(dir);
                }
                
            }
            con.close();
        }
        return dir;
    }
    
    public boolean modificarDireccion(int idDireccion, String calle, int ext, int inte, String col) throws SQLException{
        boolean modificar=false;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("update direccion set calle=?, numExterior=?, numInterior=?, colonia=? where idDireccion=?");
            ps.setString(1, calle);
            ps.setInt(2, ext);
            ps.setInt(3, inte);
            ps.setString(4, col);
            ps.setInt(5, idDireccion);
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                modificar=true;
            }
            con.close();
        }
        return modificar;
    }
    
    public empleado consultarDireccion(int idDireccion) throws SQLException{
        empleado emp=null;
        
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from direccion where idDireccion=?");
            ps.setInt(1, idDireccion);
            ResultSet rs=ps.executeQuery();
            System.out.println(rs);
            
            if(rs.next()){
                emp=new empleado();
                emp.setCalle(rs.getString("calle"));
                emp.setNumExterior(rs.getInt("numExterior"));
                emp.setNumInterior(rs.getInt("numInterior"));
                emp.setColonia(rs.getString("colonia"));
            }
            con.close();
        }
        return emp;
    }
    
    public boolean registrarEmpleado(String nombre, String app, String apm, int direccion, String telefono, String puesto, double salario, int idUsuario) throws SQLException{
     boolean registro=false;
     Connection conn=c.getConexion();
     if(conn!=null){
         PreparedStatement ps;
         ps=conn.prepareCall("insert into empleado (nombre, app, apm, idDireccion, telefono, puesto, salario, idUsuario) values (?, ?, ?, ?, ?, ?, ?, ?)");
         ps.setString(1, nombre);
         ps.setString(2, app);
         ps.setString(3, apm);
         ps.setInt(4, direccion);
         ps.setString(5, telefono);
         ps.setString(6,puesto);
         ps.setDouble(7, salario);
         ps.setInt(8, idUsuario);
         int numFilas=ps.executeUpdate();
         if(numFilas>0){
             registro=true;
         }
         conn.close();
     }
     return registro;
    }
    
    public boolean modificarEmpleado(int idEmpleado,String nombre, String app, String apm, String telefono, String puesto, double salario) throws SQLException{
     boolean registro=false;
     Connection conn=c.getConexion();
     if(conn!=null){
         PreparedStatement ps;
         ps=conn.prepareCall("update empleado set nombre=?, app=?, apm=?, telefono=?, puesto=?, salario=? where idEmpleado=?");
         ps.setString(1, nombre);
         ps.setString(2, app);
         ps.setString(3, apm);
         ps.setString(4, telefono);
         ps.setString(5,puesto);
         ps.setDouble(6, salario);
         ps.setInt(7, idEmpleado);
         int numFilas=ps.executeUpdate();
         if(numFilas>0){
             registro=true;
         }
         conn.close();
     }
     return registro;
    }
    
    public boolean eliminarEmpleado(int id, String nombre) throws SQLException{
        boolean eliminar=false;
        Connection con=c.getConexion();
        if(con!=null){
            empleado emp=consultarEmpleado(id, nombre);
            PreparedStatement ps;
            ps=con.prepareCall("update empleado set estatus='I' where idUsuario=?");
            ps.setInt(1,emp.getIdUsuario());
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                eliminar=true;
            }
            con.close();
        }
        return eliminar;
    }
    
    public empleado consultarEmpleado(int id, String nom) throws SQLException{
        empleado emp=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from empleado where idEmpleado=? or nombre=?");
            ps.setInt(1, id);
            ps.setString(2, nom);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                emp=new empleado();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApp(rs.getString("app"));
                emp.setApm(rs.getString("apm"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setSalario(rs.getDouble("salario"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setIdUsuario(rs.getInt("idUsuario"));
                emp.setIdDireccion(rs.getInt("idDireccion"));
            }
            con.close();
        }
        return emp;
    }
    
    public int registrarUsuario(String usu, String contra) throws SQLException{
        int registrar=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("insert into usuario (usuario, contrasenia, estatus) values (?, ?, 'A')");
            ps.setString(1, usu);
            ps.setString(2, contra);
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                /*Conseguir el ultimo registro*/
                ps=con.prepareCall("SELECT @@identity AS id");
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    registrar=rs.getInt("id");
                    System.out.println(registrar);
                }
            }
            con.close();
        }
        return registrar;
    }
    
    public boolean modificarUsuario(String usu, String contra, int idUsuario) throws SQLException{
        boolean modificar=false;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("update usuario set usuario=?, contrasenia=? where idUsuario=?");
            ps.setString(1, usu);
            ps.setString(2, contra);
            ps.setInt(3, idUsuario);
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                modificar=true;
            }
            con.close();
        }
        return modificar;
    }
    
    public empleado consultarUsuario(int idUsuario) throws SQLException{
        empleado emp=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from usuario where idUsuario=?");
            ps.setInt(1, idUsuario);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                emp=new empleado();
                //emp.setIdUsuario(idUsuario);
                emp.setUsuario(rs.getString("usuario"));
                emp.setContrasena(rs.getString("contrasenia"));
                emp.setEstatus(rs.getString("estatus"));
            }
            con.close();
        }
        return emp;
    }
    
    public int consultarIDUsuarioEmpleado(int id) throws SQLException{
        int idEmp=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from empleado where idUsuario=?");
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                idEmp=rs.getInt("idEmpleado");
            }
            con.close();
        }
        return idEmp;
    }
    
    public boolean validaPuesto (int id) throws SQLException{
        boolean puesto=false;
        Connection con=c.getConexion();
        empleado e=null;
        if(con!=null){   
            PreparedStatement ps=con.prepareStatement("select * from empleado where idEmpleado=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                e=new empleado();
                e.setPuesto(rs.getString("puesto"));
                if(e.getPuesto().equals("Administrador")){
                    puesto=true;
                }
            }           
            con.close();
        } 
        return puesto;
    }
    
    public ArrayList<empleado> consultarTodos() throws SQLException{
        ArrayList todos=new ArrayList();
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from empleado");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                empleado emp=new empleado();
                emp.setIdEmpleado(rs.getInt("idEmpleado"));
                emp.setNombre(rs.getString("nombre"));
                emp.setApp(rs.getString("app"));
                emp.setApm(rs.getString("apm"));
                emp.setPuesto(rs.getString("puesto"));
                emp.setSalario(rs.getDouble("salario"));
                emp.setTelefono(rs.getString("telefono"));
                emp.setIdUsuario(rs.getInt("idUsuario"));
                todos.add(emp);
            }
            con.close();
        }
        return todos;
    }
    
    /*
    "update empleado set direccion=?, telefono=? where idempleado=?
    "insert into usuarios (usuario, contrasenia, estatus) values (?, ?, 'A')"
    */
}//Fin de la clase
