package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.alumnoDAO;
import vista.ventanaAspirante;

public class controlAspirante implements ActionListener {
    private ventanaAspirante v;
    private alumnoDAO aluDAO;
    
    public controlAspirante(ventanaAspirante v, alumnoDAO aluDAO){
        this.v=v;
        this.aluDAO=aluDAO;
        this.v.btnSalir.addActionListener(this);
        this.v.btnRegistrar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnSalir){
            v.dispose();
        }else if(ae.getSource()==v.btnRegistrar){
            String nombre=v.txtNombre.getText();
            String app=v.txtApp.getText();
            String apm=v.txtApm.getText();
            String tel=v.txtTelefono.getText();
            String correo=v.txtCorreo.getText();
            String calle=v.txtCalle.getText();
            String col=v.txtColonia.getText();
            String curso=(String)v.listaCurso.getSelectedItem();
            String nivelAcad=(String)v.listaNivelAcad.getSelectedItem();
            String reconocimiento=v.txtReconocer.getText();
            int numE=0;
            int numI=0;
            boolean error=false;
            
            try {
                numE=Integer.parseInt(v.txtNumExterior.getText());
                numI=Integer.parseInt(v.txtNumInterior.getText());
            } catch (Exception e) {
                error=true;
            }
            
            if(!error){
                if(!nombre.equals("")&&!app.equals("")&&!tel.equals("")&&!calle.equals("")&&numE>0){
                    try {
                        /*consultar al alumno si ya existe*/
                        error=aluDAO.consultarAlumnoExacto(0, nombre, app, apm, tel);
                        /*
                        *Reutilizo la variable error para saber si el alumno existe
                        *ya que estando dentro des uy primer filtro no afecta
                        */
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error en la consulta de a base de datos");
                    }
                    if(!error){
                        if(!reconocimiento.equals("")){
                            System.out.println("Estoy por registrar");
                            try {
                                if(aluDAO.registrarInteres(aluDAO.registrarAspirante(nombre, app, apm, correo, tel, aluDAO.registrarDireccion(calle, numE, numI, col),aluDAO.registrarPaperlesFugaz()), curso, nivelAcad, reconocimiento)){
                                    JOptionPane.showMessageDialog(null, "El aspirante se registro correctamente "); 
                                }else{
                                    JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar al aspirante");                         
                                }
                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Ocurrio un error al registrar al aspirante"); 
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Llenar el campo de \"¿Cómo \n conociste la escuela?\" es OBLIGATORIO ");
                        }
                    }
                }//Cuando no estan llenos loc campos mínimos  
                else{
                     JOptionPane.showMessageDialog(null, "Revisa que los campos mínimos estén llenos");
                }
            }else{//Cuando se ponen letras donde van numeros
                JOptionPane.showMessageDialog(null, "Verifica los campos número Exterior y/o Interior");
            }
        }
    }//Fin de AE  
}//Fin de la clase
