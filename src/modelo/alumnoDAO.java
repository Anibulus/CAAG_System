package modelo;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class alumnoDAO {
    private final conexion c;
    public alumnoDAO(){
        c=new conexion();
    }//Fin del constructor
    
    public boolean registrarInteres(int idAlumno, String curso, String nivelAca, String reconocimiento) throws SQLException{
        boolean interes=false;
        System.out.println("Estoy aqui");
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select convert(char(10), getdate(), 103) as hoy");
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                String fecha=rs.getString("hoy");
                System.out.println(fecha);
                ps=con.prepareCall("insert into interes (idAlumno, fechaInforme, cursoDeInteres, nivelAcademico, reconocimiento) values (?, ?, ?, ?, ?)");
                ps.setInt(1, idAlumno);
                ps.setString(2,fecha);
                ps.setString(3, curso);
                ps.setString(4, nivelAca);
                ps.setString(5, reconocimiento);
                int numFilas=ps.executeUpdate();
                if(numFilas>0){
                    interes=true;
                }
            }
            con.close();
        }
        return interes;
    }
    
    public int registrarPaperlesFugaz() throws SQLException{
        int papel=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("insert into papeles (actaNacimiento, certificado, comprobanteDomicilio, curp, fotografias, ine) values (0, 'NO', 0, 0, 0, 0)");
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                /*Conseguir el ultimo registro*/
                    ps=con.prepareCall("SELECT @@identity AS id");
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        papel=rs.getInt("id");
                        System.out.println(papel);
                    }
            }
            con.close();
        }
        return papel;
    }
    
    public boolean modificarPapeles(int idPapel, boolean acta, String certificado, boolean comprobante, boolean curp, boolean fotos, boolean ine)throws SQLException{
        Connection con=c.getConexion();
        int modificar=0;
        if(con!=null){
            System.out.println(acta);
            System.out.println(comprobante);
            System.out.println(curp);
            System.out.println(fotos);
            System.out.println(ine);
            PreparedStatement ps=con.prepareCall("update papeles set actaNacimiento=?, certificado=?, comprobanteDomicilio=?, curp=?, fotografias=?, ine=? where idPapeles=?");
            ps.setBoolean(1, acta);
            ps.setString(2, certificado);
            ps.setBoolean(3, comprobante);
            ps.setBoolean(4, curp);
            ps.setBoolean(5, fotos);
            ps.setBoolean(6, ine);
            ps.setInt(7,idPapel);
            modificar=ps.executeUpdate();
            con.close();
        }//si la conexion es nula
        return modificar>0;
    }//Fin de la funcion modificar papeles
    
    public int registrarAspirante(String nombre, String app, String apm, String correo, String telefono, int idDireccion, int papel) throws SQLException{
        int aspirante=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("insert into alumno (nombre, app, apm, correo, telefono, idDireccion, idPapeles, estatus) values (?, ?, ?, ?, ?, ?, ?, 'C')");
            ps.setString(1, nombre);
            ps.setString(2, app);
            ps.setString(3, apm);
            ps.setString(4, correo);
            ps.setString(5, telefono);
            ps.setInt(6, idDireccion);
            ps.setInt(7, papel);
            int numFilas=ps.executeUpdate();
            if(numFilas>0){
                /*Conseguir el ultimo registro*/
                    ps=con.prepareCall("SELECT @@identity AS id");
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        aspirante=rs.getInt("id");
                        System.out.println(aspirante);
                    }
            }
            con.close();
        }
        return aspirante;
    }
    
    public boolean registrarAlumno(String nombre, String app, String apm, String correo, String telefono, int idDireccion, int papel) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("insert into alumno (nombre, app, apm, correo, telefono, idDireccion, idPapeles, estatus) values (?, ?, ?, ?, ?, ?, ?, 'A')");
            ps.setString(1, nombre);
            ps.setString(2, app);
            ps.setString(3, apm);
            ps.setString(4, correo);
            ps.setString(5, telefono);
            ps.setInt(6, idDireccion);
            ps.setInt(7, papel);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
    
    public boolean modificarAlumno(int id, String nombre, String app, String apm, String correo, String telefono) throws SQLException{
        Connection con=c.getConexion();
        int numFilas=0;
        if(con!=null){
            PreparedStatement ps=con.prepareCall("update alumno set nombre=?, app=?, apm=?, correo=?, telefono=? where idAlumno=?");
            ps.setString(1, nombre);
            ps.setString(2, app);
            ps.setString(3, apm);
            ps.setString(4,correo);
            ps.setString(5,telefono);
            ps.setInt(6, id);
            numFilas=ps.executeUpdate();
        }
        return numFilas>0;
    }
    
    public boolean consultarAlumnoExacto(int id, String nombre, String app, String apm, String tel) throws SQLException{
        boolean alu=false;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from alumno where idAlumno=? or nombre=? and app=? and apm=? and telefono=?");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ps.setString(3, app);
            ps.setString(4, apm);
            ps.setString(5, tel);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                alu=true;
            }
            con.close();
        }     
        return alu;
    }
    
    public alumno consultarAlumnoSimple(int id, String nombre) throws SQLException{
        alumno alu=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from alumno where idAlumno=? or nombre=?");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                alu=new alumno();
                alu.setIdAlumno(rs.getInt("idAlumno"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApp(rs.getString("app"));
                alu.setApm(rs.getString("apm"));
                alu.setCorreo(rs.getString("correo"));
                alu.setTelefono(rs.getString("telefono"));
                alu.setEstatus(rs.getString("estatus"));
                alu.setIdDireccion(rs.getInt("idDireccion"));
                alu.setIdPapeles(rs.getInt("idPapeles"));
            }
            con.close();
        }     
        return alu;
    }
    
    public alumno consultarAlumnoCompleta(int id, String nombre) throws SQLException{
        alumno alu=null;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps;
            ps=con.prepareCall("select * from alumno where idAlumno=? or nombre=?");
            ps.setInt(1, id);
            ps.setString(2, nombre);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                alu=new alumno();
                alu.setIdAlumno(rs.getInt("idAlumno"));
                alu.setNombre(rs.getString("nombre"));
                alu.setApp(rs.getString("app"));
                alu.setApm(rs.getString("apm"));
                alu.setCorreo(rs.getString("correo"));
                alu.setTelefono(rs.getString("telefono"));
                alu.setEstatus(rs.getString("estatus"));
                alu.setIdDireccion(rs.getInt("idDireccion"));
                alu.setIdPapeles(rs.getInt("idPapeles"));
                //Esta varianle se usa mas abajo en la misma funcion
                boolean registro=false;
                //Comienza la busqueda de direccion
                System.out.println("Encontre la persona");
                
                ps=con.prepareCall("select * from direccion where idDireccion=?");
                ps.setInt(1, alu.getIdDireccion());
                rs=ps.executeQuery();
                if(rs.next()){
                    alu.setCalle(rs.getString("calle"));
                    alu.setNumExterior(rs.getInt("numExterior"));
                    alu.setNumInterior(rs.getInt("numInterior"));
                    alu.setColonia(rs.getString("colonia"));
                    
                    System.out.println("Encontre la direccion");
                    //Aqui comienza la busqueda de los papeles
                    ps=con.prepareCall("select * from papeles where idPapeles=?");
                    ps.setInt(1, alu.getIdPapeles());
                    rs=ps.executeQuery();
                    if(rs.next()){
                        System.out.println("Encontre los papeles");
                        alu.setCertificado(rs.getString("certificado"));
                        alu.setActaNacimiento(rs.getBoolean("actaNacimiento"));
                        alu.setComprobanteDomicilio(rs.getBoolean("comprobanteDomicilio"));
                        alu.setCurp(rs.getBoolean("curp"));
                        alu.setFotografias(rs.getBoolean("fotografias"));
                        alu.setIne(rs.getBoolean("ine"));
                    }//Si encuentra los papeles
                }//Si encuentra la direccion
            }//Si encuentra al alumno
            con.close();
        }//si la conexion es nula     
        return alu;
    
    }//fin de la funcion
    
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
    
}//Fin de la clase
