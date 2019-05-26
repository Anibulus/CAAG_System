package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.cursosDAO;
import vista.ventanaNuevaMateria;

public class controlNuevaMateria implements ActionListener {
    private ventanaNuevaMateria v;
    private cursosDAO cDAO;
    
    public controlNuevaMateria(ventanaNuevaMateria v, cursosDAO cDAO){
        this.v=v;
        this.cDAO=cDAO;
        this.v.btnCurso.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnCurso){
            String nombre=v.txtNombre.getText();
            int curso=v.txtCurso.getSelectedIndex();
            String desc=v.txtDescripcion.getText();
            if(!nombre.equals("")&&!desc.equals("")){
                try {
                    if(cDAO.nuevaMateria(nombre, desc, curso))
                        JOptionPane.showMessageDialog(null, "Se guardo correctamente");
                    else
                        JOptionPane.showMessageDialog(null, "No se ha podido guardar");
                        } catch (SQLException ex) {
                   JOptionPane.showMessageDialog(null, "Ocurrio un problema al conectar con la base de datos");;
                }
                
            }else JOptionPane.showMessageDialog(null, "Se necesitan llenar todos los campos");
        }
    }//Aqui termina el ae
}//Aqui termina la clase
