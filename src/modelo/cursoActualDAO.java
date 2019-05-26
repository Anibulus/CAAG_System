package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class cursoActualDAO {
    conexion c=null;
    public cursoActualDAO(){
        c=new conexion();      
    }
    public ArrayList<cursoActual> consultarTodos() throws SQLException{
        ArrayList<cursoActual> lista=new ArrayList<cursoActual>();
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from");
            
            con.close();
        }
        return lista;
    }
    
    //Aqui se regiustra un alumno
     public boolean registrarAlumnoACurso(int idAlu, int idCurso, Date inicio,  String duracion, String horario, double colegiatura) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
           String inicio1=String.valueOf(inicio);
            inicio1 =fechas(inicio1);
            PreparedStatement ps=con.prepareCall("insert into ingreso (idAlumno, idCurso, inicio, duracion, horario, colegiatura, tramiteCertificacion) values (?,?,?,?,?,?,'NO')");
            ps.setInt(1, idAlu);
            ps.setInt(2, idCurso);
            ps.setString(3, inicio1);
            ps.setString(4, duracion);
            ps.setString(5, horario);
            ps.setDouble(6, colegiatura);
            numFilas=ps.executeUpdate();
            con.close();
        }
        return numFilas>0;
    }
     
    public String fechas(String fecha){
        String f=fecha;
        String mes=f.substring(4, 7);
        String dia=f.substring(8, 10);
        String año=f.substring(24,28);
        if(mes.equalsIgnoreCase("Jan"))mes="01";
        if(mes.equalsIgnoreCase("Feb"))mes="02";
        if(mes.equalsIgnoreCase("Mar"))mes="03";
        if(mes.equalsIgnoreCase("Apr"))mes="04";
        if(mes.equalsIgnoreCase("May"))mes="05";
        if(mes.equalsIgnoreCase("Jun"))mes="06";
        if(mes.equalsIgnoreCase("Jul"))mes="07";
        if(mes.equalsIgnoreCase("Aug"))mes="08";
        if(mes.equalsIgnoreCase("Sep"))mes="09";
        if(mes.equalsIgnoreCase("Oct"))mes="10";
        if(mes.equalsIgnoreCase("Nov"))mes="11";
        if(mes.equalsIgnoreCase("Dec"))mes="12";
        f=año+"/"+mes+"/"+dia;
        System.out.println(f);
        return f;
    }
    
    public boolean pagoSemanas(int idM, double cantidad, int[] semana) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from ingreso where idIngreso=?");
            ps.setInt(1, idM);
            ResultSet rs=ps.executeQuery();
            System.out.println("tengo al alumno");
            if(rs.next()){
                int idAlu=rs.getInt("idAlumno");
                ps=con.prepareCall("select convert(char(10), getdate(), 103) as hoy");
                rs=ps.executeQuery();
                if(rs.next()){
                    String fecha=rs.getString("hoy");
                    ps=con.prepareCall("insert into pagos (idAlumno, idEmpleado, cantidad, fecha) values (?, ?, ?, ?)");
                    ps.setInt(1, idAlu);
                    ps.setInt(2, c.getEmpleadoActivoN());
                    ps.setDouble(3, cantidad);
                    ps.setString(4, fecha);
                    numFilas=ps.executeUpdate();
                    System.out.println("Tengo al alumno again");
                    if(numFilas>0){
                        ps=con.prepareCall("SELECT @@identity AS id");
                        rs=ps.executeQuery();
                        System.out.println("Tengo el coso del pago");
                        if(rs.next()){
                            int pago=rs.getInt("id");
                            System.out.println(pago);
                            for (int i = 0; i < 10; i++) {
                                if(semana[i]>0){
                                    ps=con.prepareCall("insert into periodo (idIngreso, idPago, semana) values (?, ?, ?)");
                                    ps.setInt(1, idM);
                                    ps.setInt(2, pago);
                                    ps.setInt(3, semana[i]);
                                    System.out.println("lleno weas");
                                    numFilas+=ps.executeUpdate();
                                }else i=10;
                            }//Fin e for de rellenado
                            System.out.println(numFilas);
   
                        }//Fin de consulta por idPago
                    }//Fin de insercion a pagos
                }//Fin de consulta fecha
            }//Fin de consulta a ingreso
            con.close();
        }//Fin de la conexion
        return numFilas>1;
    }//Fin del metodo
    
    public boolean pagoConceptiosVarios(int idM, double cantidad, String motivo) throws SQLException{
        int numFilas=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from ingreso where idIngreso=?");
            ps.setInt(1, idM);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int idAlu=rs.getInt("idAlumno");
                ps=con.prepareCall("select convert(char(10), getdate(), 103) as hoy");
                rs=ps.executeQuery();
                if(rs.next()){
                    String fecha=rs.getString("hoy");
                    ps=con.prepareCall("insert into pagos (idAlumno, idEmpleado, cantidad, fecha) values (?, ?, ?, ?)");//-----------------Revisar que concuerde con la basede datos
                    ps.setInt(1, idAlu);
                    ps.setInt(2, c.getEmpleadoActivoN());
                    ps.setDouble(3, cantidad);
                    ps.setString(4, fecha);
                    numFilas=ps.executeUpdate();
                    if(numFilas>0){
                        ps=con.prepareCall("SELECT @@identity AS id");
                        rs=ps.executeQuery();
                        if(rs.next()){
                            int pago=rs.getInt("id");
                            System.out.println(pago);
                                ps=con.prepareCall("insert into detalle (idAlumno, idPago, motivo) values (?, ?, ?)");
                                ps.setInt(1, idAlu);
                                ps.setInt(2, pago);
                                ps.setString(3, motivo);
                                numFilas+=ps.executeUpdate();
                            System.out.println(numFilas);
   
                        }
                    }
                
                }
            }
            con.close();
        }
        return numFilas>1;
    }
    
    //Crear una funcion con todos los datos, solo con nombre o solo con id y Curso
    public cursoActual consultarEstudiante(int idM, String nombre, int curso) throws SQLException{
        cursoActual ca=null;
        int contador=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from alumno where nombre=?");
            ps.setString(1, nombre);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                int idAlu=rs.getInt("idAlumno");
                String nom=rs.getString("nombre");
                String app=rs.getString("app");
                String apm=rs.getString("apm");
                ca=new cursoActual();
                ca.setIdAlumno(idM);
                ca.setNombre(nom);
                ca.setApp(app);
                ca.setApm(apm);
                ps=con.prepareCall("select * from ingreso where idIngreso=? or idCurso=?");
                ps.setInt(1, idM);
                ps.setInt(2, curso);
                rs=ps.executeQuery();
                if(rs.next()){
                    double pago=rs.getDouble("colegiatura");
                    ca.setColegiatura(pago);
                    ps=con.prepareCall("select * from periodo where idIngreso=?");
                    ps.setInt(1, idM);
                    rs=ps.executeQuery();
                    while(rs.next()){
                        contador++;
                        ca.setNumSemana(rs.getInt("semana"));
                        ca.setUltimaSemanaPagada(contador);
                    }
                    ca.setUltimaSemanaPagada(contador);
                }
            }
            con.close();
        }
        return ca;
    }
    
    public cursoActual consultarCostoEstudiante(int idM) throws SQLException{
        cursoActual ca=null;
        int contador=0;
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from ingreso where idIngreso=?");
                ps.setInt(1, idM);
                ResultSet rs=ps.executeQuery();
                if(rs.next()){
                    ca=new cursoActual();
                    ca.setColegiatura(rs.getDouble("colegiatura"));
                }
            con.close();
        }//Fin de la conexino
        return ca;
    }//Fin de la clase
    
    public ArrayList<cursoActual> consultarAlumnosPorCurso(int curso) throws SQLException{
        ArrayList<cursoActual> lista=new ArrayList<cursoActual>();
        Connection con=c.getConexion();
        if(con!=null){
            PreparedStatement ps=con.prepareCall("select * from ingreso where idCurso=?");//----------------MejorarLa consulta
            ps.setInt(1 ,curso);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                cursoActual ca=new cursoActual();
                ca.setColegiatura(rs.getDouble("colegiatura"));
                ca.setInicio(rs.getString("inicio"));
                ca.setIdIngreso(rs.getInt("idIngreso"));
                ca.setIdAlumno(rs.getInt("idAlumno"));
                ps=con.prepareCall("select * from alumno where idAlumno=?");
                ps.setInt(1, ca.getIdAlumno());
                rs=ps.executeQuery();
                if(rs.next()){
                    ca.setNombre(rs.getString("nombre"));
                    ca.setApp(rs.getString("app"));
                    ca.setApm(rs.getString("apm"));
                }
                lista.add(ca);
            }
            con.close();
        }
        return lista;
    }
}//Fin de la clase
