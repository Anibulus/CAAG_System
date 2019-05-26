package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.empleado;
import modelo.empleadoDAO;
import vista.ventanaModificarEmpleado;

public class controlBuscarEmpleadoUnitario implements ActionListener {
    private ventanaModificarEmpleado ventana;
    private empleadoDAO empDAO;
    
    public controlBuscarEmpleadoUnitario(ventanaModificarEmpleado ventana, empleadoDAO empDAO){
        this.ventana=ventana;
        this.empDAO=empDAO;
        this.ventana.btnBModificar.addActionListener(this);
        this.ventana.btnBuscar.addActionListener(this);
        this.ventana.btnSalir1.addActionListener(this);
    }
      @Override
    public void actionPerformed(ActionEvent ae) {       
        if(ae.getSource()==ventana.btnBuscar){
            int id=(int) ventana.txtCodigo.getValue();
            String nombre=ventana.txtNombre1.getText();
            if(id!=0||!nombre.equals("")){
                empleado emp=null;
                empleado emp2=null;
                empleado emp3=null;
                try {
                    emp=empDAO.consultarEmpleado(id, nombre);
                    emp2=empDAO.consultarDireccion(emp.getIdDireccion());
                    emp3=empDAO.consultarUsuario(emp.getIdUsuario());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Ocurrio un error en la consulta a la base de datos");
                }
                if(emp!=null&&emp2!=null&&emp3!=null){
                    ventana.txtNombre1.setText(emp.getNombre());
                    ventana.txtApp1.setText(emp.getApp());
                    ventana.txtApm1.setText(emp.getApm());
                    ventana.txtPuesto1.setSelectedItem(emp.getPuesto());
                    ventana.txtSueldo1.setText(String.valueOf(emp.getSalario()));
                    ventana.txtTelefono1.setText(emp.getTelefono());   
                    ventana.txtDomicilio1.setText(emp2.getCalle());
                    ventana.txtColonia1.setText(emp2.getColonia());
                    ventana.txtNumExterior1.setText(String.valueOf(emp2.getNumExterior()));
                    ventana.txtNumInterior1.setText(String.valueOf(emp2.getNumInterior()));
                    ventana.txtUsuario1.setText(emp3.getUsuario());
                    ventana.txtContrasena1.setText(emp3.getContrasena());
                }else{
                    JOptionPane.showMessageDialog(null,"La persona no se ha encontrado");
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Rellenar como minimo el campo Codigo o Nombre");
            }
        }//Aqui termina buscar
        if(ae.getSource()==ventana.btnBModificar){
            //Ver si la persona existe + pedir saber si desea ingresar
            boolean error=false;
            int id=(int) ventana.txtCodigo.getValue();
            String nombre=ventana.txtNombre1.getText();
            String app=ventana.txtApp1.getText();
            String apm=ventana.txtApm1.getText();
            String tel=ventana.txtTelefono1.getText();
            String puesto=(String)ventana.txtPuesto1.getSelectedItem();
            String calle=ventana.txtDomicilio1.getText();
            String colonia=ventana.txtColonia1.getText();
            String usuario=ventana.txtUsuario1.getText();
            String contrasena=ventana.txtContrasena1.getText();
            double sueldo=0;
            int numInterior=0;
            int numExterior=0;
            try{
            sueldo=Double.valueOf(ventana.txtSueldo1.getText());
            numInterior=Integer.parseInt(ventana.txtNumInterior1.getText());
            numExterior=Integer.parseInt(ventana.txtNumExterior1.getText());
            }catch(Exception e){
                error=true;
            }
            empleado emp=null;
            try {
                emp=empDAO.consultarEmpleado(id, nombre);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"1Ocurrio un error en la consulta a la base de datos");
            }
            
            if(emp!=null){
                if(!nombre.equals("")&&!app.equals("")&&!tel.equals("")&&!usuario.equals("")&&!contrasena.equals("")&&!calle.equals("")){
                    if(!error){
                        try {
                            if(empDAO.modificarEmpleado(id, nombre, app, apm, tel, puesto, sueldo)){
                                System.out.println("acabe el empleado");
                                if(empDAO.modificarDireccion(emp.getIdDireccion(), calle, numExterior, numInterior, colonia)){
                                    System.out.println("acabe la direccion");
                                    if(empDAO.modificarUsuario(usuario, contrasena,emp.getIdUsuario())){
                                        
                                        JOptionPane.showMessageDialog(null,"Se ha modificado correctamente");
                                    }
                                }
                                
                            }
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null,"5Ocurrio un error en la modificación del registro");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"4Evita poner letras donde van solo números");
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"3No dejar campos importantes vacíos");
                }
            }else{
                JOptionPane.showMessageDialog(null,"2La persona que desea modificar no existe");
                /*Saber si desea crear al empleado en modificar*/
            }
        }
        if(ae.getSource()==ventana.btnSalir1){
            ventana.dispose();
        }
    }          
}

    
