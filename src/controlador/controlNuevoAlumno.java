package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.alumno;
import modelo.alumnoDAO;
import modelo.curso;
import modelo.cursoActualDAO;
import modelo.cursosDAO;
import vista.ventanaNuevoAlumno;

public class controlNuevoAlumno implements ActionListener{
    private ventanaNuevoAlumno v;
    private alumnoDAO aluDAO;
    private cursoActualDAO caDAO;
    private cursosDAO cDAO;
   
    public controlNuevoAlumno(ventanaNuevoAlumno v, alumnoDAO aluDAO ){
        this.v=v;
        this.aluDAO=aluDAO;
        this.caDAO=new cursoActualDAO();
        this.cDAO=new cursosDAO();
        this.v.btnBuscar.addActionListener(this);
        this.v.btnGuardar.addActionListener(this);
        this.v.txtCurso.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnBuscar){
            int id=(int)v.txtCódigo.getValue();
            String nombre=v.txtNombre1.getText();
            alumno alu=null;
            try {
                alu=aluDAO.consultarAlumnoCompleta(id, nombre);
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar");
            }
            if(alu!=null){
                v.txtCódigo.setValue(alu.getIdAlumno());
                v.txtNombre1.setText(alu.getNombre());
                v.txtApp.setText(alu.getApp());
                v.txtApm.setText(alu.getApm());
                v.txtCorreo.setText(alu.getCorreo());
                v.txtTelefono.setText(alu.getTelefono());
                v.txtCalle.setText(alu.getCalle());
                v.txtColonia.setText(alu.getColonia());
                v.txtNumExterior.setText(String.valueOf(alu.getNumExterior()));
                v.txtNumInterior.setText(String.valueOf(alu.getNumInterior()));
                
                v.txtApp.setEnabled(false);
                v.txtApm.setEnabled(false);
                v.txtCorreo.setEnabled(false);
                v.txtTelefono.setEnabled(false);
                v.txtCalle.setEnabled(false);
                v.txtColonia.setEnabled(false);
                v.txtNumExterior.setEnabled(false);
                v.txtNumInterior.setEnabled(false);
            }
            else{
                JOptionPane.showMessageDialog(null, "No se encontro el aspirante/alumno");
            }
        }
        else if(ae.getSource()==v.btnGuardar){
            int id=(int)v.txtCódigo.getValue();
            String nombre=v.txtNombre1.getText();
            alumno alu=null;
            try {
                alu=aluDAO.consultarAlumnoCompleta(id, nombre);
            }catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error al consultar");
            }
            /*
            Continuart cuando se hallan puesto los cursos y materia<s

            */
            int curso=v.txtCurso.getSelectedIndex()+1;
            Date fecha=v.txtFecha.getDate();
            String duracion=v.txtDuración.getText();
            double colegiatura=0;
            String horario=v.txtHorario.getText();
            try{colegiatura=Double.valueOf(v.txtPAgo.getText());}catch(NumberFormatException e){}
            System.out.println(fecha);
            if(fecha!=null&&!duracion.equals("")&&colegiatura>0&&!horario.equals("")){
                if(alu==null){//Si no esta registrada en aspirantes pero igual quiere un curso
                    String app=v.txtApp.getText();
                    String apm=v.txtApm.getText();
                    String correo=v.txtCorreo.getText();
                    String tel=v.txtTelefono.getText();
                    String calle=v.txtCalle.getText();
                    String col=v.txtColonia.getText();
                    int numInterior=0;
                    int numExterior=0;
                    try {
                        numExterior=Integer.parseInt(v.txtNumExterior.getText());
                        numInterior=Integer.parseInt(v.txtNumInterior.getText());
                    } catch (Exception e) {
                    }
                    if(numExterior>0){
                        if(!nombre.equals("")&&!app.equals("")&&!tel.equals("")&&!correo.equals("")&&!calle.equals("")){
                            try {
                                id=(aluDAO.registrarAspirante(nombre, app, apm, correo, tel, aluDAO.registrarDireccion(calle, numExterior, numInterior, col), aluDAO.registrarPaperlesFugaz()));
                                    if(id>0)JOptionPane.showMessageDialog(null, "Llenar los campos de la direccion");
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Ocurrio un problema con la conexion a la base de datos");
                            }

                        }
                    }
                    else {JOptionPane.showMessageDialog(null, "Llenar los campos de la direccion");}
                    //poner num exterior e interior
                }//Fin de else registra nuevo alumno
                
                /*
                    para este punto el alumno estara ya en la base de datos si no estaba ingresado
                */
                try {  
                    if(caDAO.registrarAlumnoACurso(id, curso, fecha, duracion, horario, colegiatura))
                        JOptionPane.showMessageDialog(null, "Se registro correctamente a "+nombre+" en el curso de "+v.txtCurso.getItemAt(curso-1));
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al registrarlo al curso");
                }
                
            }else JOptionPane.showMessageDialog(null, "Se deben llenar los datos del curso");
        }//Fin de guardar
        else if(ae.getSource()==v.txtCurso){
            System.out.println("Hola");
            int id=v.txtCurso.getSelectedIndex()+1;
            System.out.println(id);
            curso c=null;
            try {
                c=cDAO.consultaCurso(id,"");
            } catch (SQLException ex) {
                Logger.getLogger(controlNuevoAlumno.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(c!=null){
                v.txtDuración.setText(String.valueOf(c.getDuracion()));
                v.txtPAgo.setText(String.valueOf(c.getCosto()));
                v.txtDuración.setEnabled(false);
                v.txtPAgo.setEnabled(false);
            }
        }
    }//Aqui termina el AE
}//Fin de la clase
