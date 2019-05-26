package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.curso;
import modelo.cursosDAO;
import vista.ventanaNuevoCurso;

public class controlNuevoCurso implements ActionListener {
    private ventanaNuevoCurso v;
    private cursosDAO cDAO;
    
    public controlNuevoCurso (ventanaNuevoCurso v, cursosDAO cDAO){
        this.cDAO=cDAO;
        this.v=v;
        this.v.btnCurso.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnCurso){
            String nombre=v.txtNombre.getText();
            String desc=v.txtDescripcion.getText();
            int duracion=(int)v.txtDuracion.getValue();
            double costo=0;
            boolean error=false;
            try{
            costo=Double.valueOf(v.txtCosto.getText());
            }catch(Exception e){
            error=true;
            }
            
            curso cur=null;
            try {
                cur=cDAO.consultaCurso(0, nombre);
                System.out.println(cur);
                
                if(cur==null){
                    if(!error){
                        if(!nombre.equals("")&&!desc.equals("")&&costo>0){
                            if(cDAO.nuevoCurso(nombre, desc, costo, duracion))
                                JOptionPane.showMessageDialog(null, "Se registro correctamente el curso "+nombre);
                            else
                                JOptionPane.showMessageDialog(null, "No se ha podido registrar");
                        }
                    }else
                        JOptionPane.showMessageDialog(null, "Verifique el valor del campo \"Costo\"");
                }else
                    JOptionPane.showMessageDialog(null, "El curso ya existe");  
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un problema con la conexion a la base de datos");
            }
        }
    }//Fin del AE  
}//FIN DE LA CLASE
