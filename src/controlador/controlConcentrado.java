package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.alumnoDAO;
import modelo.cursoActualDAO;
import vista.ventanaConcentrado;
import vista.ventanaPago;
public class controlConcentrado implements ActionListener {
    private ventanaConcentrado v;
    private cursoActualDAO cADAO;
    private int curso;
    private String nomCurso;
    
    public void setCurso(String nom){
        this.nomCurso=nom;
    }
    public controlConcentrado(ventanaConcentrado v,cursoActualDAO cADAO, int curso){
        this.v=v;
        this.cADAO=cADAO;
        this.curso=curso;
        v.btnSel.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==v.btnSel){
            int seleccion=v.tablaAlumnos.getSelectedRow();System.out.println(v.tablaAlumnos.getSelectedRow());
            if(seleccion>=0){
            int matricula=(int)v.tablaAlumnos.getValueAt(seleccion, 0);
            String nombre=String.valueOf(v.tablaAlumnos.getValueAt(seleccion, 1));
            String curs=String.valueOf(v.tablaAlumnos.getValueAt(seleccion, 2));
            //double pago=Double.valueOf(v.tablaAlumnos.getValueAt(seleccion, 3));
            
            ventanaPago vp=new ventanaPago();
            vp.txtMatricula.setValue(matricula);
            vp.txtNombre.setText(nombre);
            vp.txtCurso.removeAllItems();
            vp.txtCurso.addItem(curs);
            vp.txtCurso.setEnabled(false);
            /*
            *Añadir funcion para llenar más datos de la wea esa
             */
            
            vp.setLocationRelativeTo(null);
            vp.setVisible(true);
            controlPago cp=new controlPago(vp, cADAO);
            }else JOptionPane.showMessageDialog(null, "Se necesita seleccionar un elemento");
        }
    }//fin de ae
}//fin de la clase
