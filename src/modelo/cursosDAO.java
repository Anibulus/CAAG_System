package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class cursosDAO {
    conexion c=null;
    public cursosDAO(){
        c=new conexion();
    }
    
    /*Estos son las funciones de curso*/
    public boolean nuevoCurso(String nombre, String Descripcion, double costo, int duracion) throws SQLException{
        Connection con=c.getConexion();
        int numFilas=0;
        if(con!=null){
            PreparedStatement ps=con.prepareCall("insert into curso (nombre, descripcion, costo, duracion) values (?, ?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, Descripcion);
            ps.setDouble(3, costo);
            ps.setInt(4, duracion);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
    
    public curso consultaCurso(int id, String nombre) throws SQLException{
        curso cur=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from curso where idCurso=? or nombre=?");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                cur=new curso();
                cur.setIdCurso(rs.getInt("idCurso"));
                cur.setNombre(rs.getString("nombre"));
                cur.setDescripcion(rs.getString("descripcion"));
                cur.setCosto(rs.getDouble("costo"));
                cur.setDuracion(rs.getInt("duracion"));
            }
            con.close();
        }
        return cur;
    }
    
    public ArrayList<curso> listaDeCursos() throws SQLException{
        ArrayList<curso> cur=new ArrayList<curso>();
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from curso");
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                curso c=new curso(rs.getInt("idCurso"),rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("costo"), rs.getInt("duracion"));
                cur.add(c);
            }
            con.close();
        }
        return cur;
    }
    
    public boolean modificarCurso(int id, String nombre, String desc, double costo, int duracion) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("update curso set nombre=?, descripcion=?, costo=?, duracion=? where idCurso=?");
            ps.setString(1, nombre);
            ps.setString(2, desc);
            ps.setDouble(3, costo);
            ps.setInt(4, id);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
    /*Estos son las funciones de Materias*/
    public boolean nuevaMateria(String nombre, String desc, int curso) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("insert into materias (nombre, descripcion, idCurso) values (? , ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, desc);
            ps.setInt(3, curso);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
    
    public boolean modificarMateria(int id,String nombre, String desc, int curso) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("update materias set nombre=?, descripcion=?, idCurso=? where idMateria=?");
            ps.setString(1, nombre);
            ps.setString(2, desc);
            ps.setInt(3, curso);
            ps.setInt(4, id);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
    
    public materias consultarMaterias(int id, String nombre) throws SQLException{
        materias m=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from materias where idMateria=? or nombre=?");
            ps.setString(2, nombre);
            ps.setInt(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                m=new materias(rs.getInt("idMateria"), rs.getString("nombre"), rs.getInt("curso"), rs.getString("descripcion"));
            con.close();
        }
        return m;
    }
    
}//Fin de la clase
