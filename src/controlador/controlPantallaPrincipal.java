package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import modelo.*;
import vista.*;

public class controlPantallaPrincipal implements ActionListener{
    private empleadoDAO empDAO;
    private ventanaPantallaPrincipal ventana;
    private DefaultTableModel tabla;
    private alumnoDAO aluDAO;
    private cursosDAO cDAO;
    private cursoActualDAO cADAO;
    
    public controlPantallaPrincipal(ventanaPantallaPrincipal ventana ,empleadoDAO empDAO){
        this.empDAO=empDAO;
        this.ventana=ventana;
        this.aluDAO=new alumnoDAO();
        this.cDAO=new cursosDAO();
        this.cADAO=new cursoActualDAO();
        this.ventana.btnAcercaDe.addActionListener(this);
        this.ventana.btnAspirante.addActionListener(this);
        this.ventana.btnCalificaciones.addActionListener(this);
        this.ventana.btnConcentrado.addActionListener(this);
        this.ventana.btnPagos.addActionListener(this);
        /*Esto se refiere a menu empleados*/
        this.ventana.itemConsultar.addActionListener(this);
        this.ventana.itemIngresar.addActionListener(this);
        this.ventana.itemModificar.addActionListener(this);
        this.ventana.itemEliminar.addActionListener(this);  
        /*Esto es menu alumno*/
        this.ventana.itemConsultarAlumno.addActionListener(this);
        this.ventana.itemModificarAlumno.addActionListener(this);
        this.ventana.itemRegistrarAlumno.addActionListener(this);
        //Esto es de los cursos
        this.ventana.itemNuevoCurso.addActionListener(this);
        this.ventana.itemModificarCurso.addActionListener(this);
        this.ventana.itemNuevaMateria.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==ventana.btnAspirante){
            ventanaAspirante v=new ventanaAspirante();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            controlAspirante ca=new controlAspirante(v,aluDAO);
        }
        else if(ae.getSource()==ventana.btnConcentrado){
            ventanaQueCurso v=new ventanaQueCurso();
            int maximo=0;
            v.txtCurso.removeAllItems();
            System.out.println("elimino items");
            try {
                maximo=cDAO.listaDeCursos().size();
                for (int i = 0; i < maximo; i++) {
                    String elemento=cDAO.listaDeCursos().get(i).getNombre();//-------------------------------------------------------Este codigo es ara ver el listado de materias
                    v.txtCurso.addItem(elemento);
                }   
            } catch (SQLException e) {
            }
            System.out.println("termino de consultar");
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            System.out.println("muestro ventana");
            controlQueCurso cq=new controlQueCurso(v, cDAO, aluDAO);
        }else if(ae.getSource()==ventana.btnPagos){
            ventanaPago vp=new ventanaPago();
            int maximo=0;
            vp.txtCurso.removeAllItems();
            System.out.println("elimino items");
            try {
                maximo=cDAO.listaDeCursos().size();
                for (int i = 0; i < maximo; i++) {
                    String elemento=cDAO.listaDeCursos().get(i).getNombre();//-------------------------------------------------------Este codigo es ara ver el listado de materias
                    vp.txtCurso.addItem(elemento);
                }   
            } catch (SQLException e) {
            }
            vp.setLocationRelativeTo(null);
            vp.setVisible(true);
            controlPago cp=new controlPago(vp, cADAO);
        }
        /*
        *--------------------------------------------------------------------------------------------
        *                                                                   Aqui terminan los Botones 
        *---------------------------------------------------------------------------------------------
        */
        if(ae.getSource()==ventana.itemIngresar){
            ventanaNuevoEmpleado vi=new ventanaNuevoEmpleado();
            vi.setLocationRelativeTo(null);
            vi.setVisible(true);
            controlNuevoEmpleado c=new controlNuevoEmpleado(vi,empDAO);
        }
        else if(ae.getSource()==ventana.itemConsultar){
            ventanaConsultaEmpleado v=new ventanaConsultaEmpleado();
            v.setLocationRelativeTo(null);
            v.setVisible(true); 
            tabla= (DefaultTableModel) v.tablaTodosEmpleados.getModel(); //---------------------------Entender esta linea         
           for(int a=0;a<tabla.getRowCount();a++){
               tabla.removeRow(tabla.getRowCount()-1);
           } 
           Object[] columna=new Object[7];
           int maximo=0;
            try {
                maximo=empDAO.consultarTodos().size();
            } catch (SQLException ex) {}
            //Esta funcion limpia la tabla antes de mostrarla
            while(tabla.getRowCount()!=0){
                tabla.removeRow(0);
            }
            for(int i=0; i<maximo; i++){
               try {
                   columna[0]=empDAO.consultarTodos().get(i).getIdEmpleado();
                   columna[1]=empDAO.consultarTodos().get(i).getNombre();
                   columna[2]=empDAO.consultarTodos().get(i).getApp();
                   columna[3]=empDAO.consultarTodos().get(i).getApm();
                   columna[4]=empDAO.consultarTodos().get(i).getPuesto();
                   columna[5]=empDAO.consultarTodos().get(i).getSalario();
                   columna[6]=empDAO.consultarTodos().get(i).getTelefono();
               } 
               catch (SQLException ex) {
                   i=maximo;
                   System.out.println("No se pudo");
               }     
                tabla.addRow(columna);
            }            
        }
        else if(ae.getSource()==ventana.itemModificar){
            ventanaModificarEmpleado v=new ventanaModificarEmpleado();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            controlBuscarEmpleadoUnitario c=new controlBuscarEmpleadoUnitario(v, empDAO);
        }
        
        /*
        *--------------------------------------------------------------------------------------------
        *Aqui termina el menu Empleados
        *---------------------------------------------------------------------------------------------
        */
        
        if(ae.getSource()==ventana.itemModificarAlumno){
            ventanaModificarAlumno v=new ventanaModificarAlumno();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            controlModificarAlumno c=new controlModificarAlumno(v,aluDAO);
        }
        else if(ae.getSource()==ventana.itemRegistrarAlumno){
            ventanaNuevoAlumno a=new ventanaNuevoAlumno();
            int maximo=0;
            a.txtCurso.removeAllItems();
            try {
                maximo=cDAO.listaDeCursos().size();
                for (int i = 0; i < maximo; i++) {
                    String elemento=cDAO.listaDeCursos().get(i).getNombre();//-------------------------------------------------------Este codigo es ara ver el listado de materias
                    a.txtCurso.addItem(elemento);
                }   
            } catch (SQLException e) {
            }
            a.setLocationRelativeTo(null);
            a.setVisible(true);
            controlNuevoAlumno c=new controlNuevoAlumno(a,aluDAO);
        }
        
        /*
        *--------------------------------------------------------------------------------------------
        *Aqui termina menu alumnos
        *---------------------------------------------------------------------------------------------
        */
        
        if(ae.getSource()==ventana.itemNuevoCurso){
            ventanaNuevoCurso v=new ventanaNuevoCurso();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            controlNuevoCurso c=new controlNuevoCurso(v,cDAO);
        }
        else if(ae.getSource()==ventana.itemModificarCurso){
           ventanaModificarCurso v=new ventanaModificarCurso();
           v.setLocationRelativeTo(null);
           v.setVisible(true);
           controlModificarCurso c=new controlModificarCurso(v, cDAO);
        }
        
        if(ae.getSource()==ventana.itemNuevaMateria){
            ventanaNuevaMateria v=new ventanaNuevaMateria();
            int maximo=0;
            v.txtCurso.removeAllItems();
            try {
                maximo=cDAO.listaDeCursos().size();
                for (int i = 0; i < maximo; i++) {
                    String elemento=cDAO.listaDeCursos().get(i).getNombre();//-------------------------------------------------------Este codigo es ara ver el listado de materias
                    v.txtCurso.addItem(elemento);
                }   
            } catch (SQLException e) {
            }
            
            v.setLocationRelativeTo(null);
            v.setVisible(true);
            controlNuevaMateria c=new controlNuevaMateria(v,cDAO);
            //Aqui se rellena la lista desplegable
            
        }
        
    }//Fin de actionListener
}//Fin de la clase
