package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.curso;
import modelo.cursosDAO;
import vista.ventanaModificarCurso;

public class controlModificarCurso implements ActionListener {
    private ventanaModificarCurso v;
    private cursosDAO cDAO;
    
    public controlModificarCurso(ventanaModificarCurso v, cursosDAO cDAO){
        this.v=v;
        this.cDAO=cDAO;
        this.v.btnBuscar.addActionListener(this);
        this.v.btnCurso.addActionListener(this);
        this.v.btnEliminar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnCurso){
            int id=(int)v.txtCodigo.getValue();
            String nombre=v.txtNombre.getText();
            int duracion=(int)v.txtDuracion.getValue();
            curso cur=null;
            try {
                cur=cDAO.consultaCurso(id, nombre);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar");
            }
            if(cur!=null){
                String desc=v.txtDescripcion.getText();
                double costo=0;
                try {
                costo=Double.valueOf(v.txtCosto.getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar");
                }
                if(!nombre.equals("")&&!desc.equals("")&&costo>0){
                    try {
                        if(cDAO.modificarCurso(id, nombre, desc, costo,duracion))
                            JOptionPane.showMessageDialog(null, "Se modificó correctamente");
                        else JOptionPane.showMessageDialog(null, "Ocurrio un error");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos");
                    }
                }else JOptionPane.showMessageDialog(null, "Necesita llenar todos los campos para esta función");
            }else JOptionPane.showMessageDialog(null, "No se puede Modificar un elemento que no existe");
        }
        else if(ae.getSource()==v.btnBuscar){
            int id=(int)v.txtCodigo.getValue();
            String nombre=v.txtNombre.getText();
            curso cur=null;
            try {
                cur=cDAO.consultaCurso(id, nombre);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar");
            }
            if(cur!=null){
                v.txtCodigo.setValue(cur.getIdCurso());
                v.txtNombre.setText(cur.getNombre());
                v.txtDescripcion.setText(cur.getDescripcion());
                v.txtCosto.setText(String.valueOf(cur.getCosto()));
                v.txtDuracion.setValue(cur.getDuracion());
            }else JOptionPane.showMessageDialog(null, "El curso no se ha encontrado");
        }
    }//Fin del ae 
}//Fin de la clase
