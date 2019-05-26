package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.alumnoDAO;
import modelo.cursoActualDAO;
import modelo.cursosDAO;
import vista.ventanaConcentrado;
import vista.ventanaPantallaPrincipal;
import vista.ventanaQueCurso;

public class controlQueCurso implements ActionListener {
    private ventanaQueCurso v;
    private cursosDAO cDAO;
    private alumnoDAO aluDAO;    
    private DefaultTableModel tabla;
    public controlQueCurso (ventanaQueCurso v, cursosDAO cDAO, alumnoDAO aluDAO){
        this.v=v;
        this.cDAO=cDAO;
        this.aluDAO=aluDAO;
        this.v.txtCurso.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.txtCurso){
            System.out.println("Empiezo la accion");
            int curso=v.txtCurso.getSelectedIndex()+1;
            System.out.println(curso);
            //Poner cursosDAO desde el menu principal
            cursoActualDAO cADAO=new cursoActualDAO();
            try {
                cDAO.consultaCurso(curso,"");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Ocurrio un problema al consultar");
            }
            ventanaConcentrado ve=new ventanaConcentrado();
            ve.setLocationRelativeTo(null);
            ve.setVisible(true);
            //llenado de tabla
            tabla= (DefaultTableModel) ve.tablaAlumnos.getModel(); //---------------------------Entender esta linea         
           for(int a=0;a<tabla.getRowCount();a++){
               tabla.removeRow(tabla.getRowCount()-1);
           } 
           Object[] columna=new Object[6];
           int maximo=0;
            try {
                maximo=cADAO.consultarAlumnosPorCurso(curso).size();
            } catch (SQLException ex) {}
            //Esta funcion limpia la tabla antes de mostrarla
            while(tabla.getRowCount()!=0){
                tabla.removeRow(0);
            }
            for(int i=0; i<maximo; i++){
               try {
                   columna[0]=cADAO.consultarAlumnosPorCurso(curso).get(i).getIdIngreso();
                   columna[1]=cADAO.consultarAlumnosPorCurso(curso).get(i).getNombre()+" "+cADAO.consultarAlumnosPorCurso(curso).get(i).getApp()+" "+cADAO.consultarAlumnosPorCurso(curso).get(i).getApm();
                   columna[2]=(String) v.txtCurso.getSelectedItem();
                   columna[3]=cADAO.consultarAlumnosPorCurso(curso).get(i).getColegiatura();
                   columna[4]=cADAO.consultarAlumnosPorCurso(curso).get(i).getInicio();
                   //columna[5]=
                   //columna[6]=cADAO.consultarAlumnosPorCurso(curso).get(i).getTelefono();
               } 
               catch (SQLException ex) {
                   i=maximo;
                   System.out.println("No se pudo");
               }     
                tabla.addRow(columna);
            }
            
            
            
            controlConcentrado cc=new controlConcentrado(ve,cADAO,curso);
            cc.setCurso((String) v.txtCurso.getSelectedItem());
            v.dispose();
        }
    }//Fin de el ae  
}//Fin de la clase
