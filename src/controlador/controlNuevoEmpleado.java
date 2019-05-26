package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.empleado;
import modelo.empleadoDAO;
import vista.ventanaNuevoEmpleado;

public class controlNuevoEmpleado implements ActionListener {
    private empleadoDAO empDAO;
    private ventanaNuevoEmpleado ventana;
    
    public controlNuevoEmpleado(ventanaNuevoEmpleado ventana, empleadoDAO empDAO){
        this.ventana=ventana;
        this.empDAO=empDAO;
        this.ventana.btnContratar.addActionListener(this);
        this.ventana.btnSalir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==ventana.btnContratar){
            boolean error=false;
            String usu=ventana.txtUsuario.getText();
            String contra=ventana.txtContrasena.getText();
            String nombre=ventana.txtNombre.getText();
            String app=ventana.txtApp.getText();
            String apm=ventana.txtApm.getText();
            double sueldo=0;
            String telefono=ventana.txtTelefono.getText();
            String puesto=String.valueOf(ventana.txtPuesto.getSelectedItem());
            String calle=ventana.txtDomicilio.getText();
            String colonia=ventana.txtColonia.getText();
            int numExterior=0;
            int numInterior=0;
            try{
                sueldo=Double.valueOf(ventana.txtSueldo.getText());
                numExterior=Integer.parseInt(ventana.txtNumExterior.getText());   
                numInterior=Integer.parseInt(ventana.txtNumInterior.getText());  
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Comprueba la información Ingresada");
                error=true;
            }
            if(!nombre.equals("")&&!app.equals("")&&!calle.equals("")&&!usu.equals("")&&!contra.equals("")){
                empleado emp=null;
                try {
                    emp=empDAO.consultarEmpleado(0, nombre);
                    if(emp==null){
                        if(numExterior>0||numInterior>0){
                            if(!error){
                                if(empDAO.registrarEmpleado(nombre, app, apm, empDAO.registrarDireccion(calle, numExterior, numInterior, usu), telefono, puesto, sueldo, empDAO.registrarUsuario(usu, contra))){
                                  JOptionPane.showMessageDialog(null, "El empleado se registró correctamente"); 
                                  ventana.dispose();
                                }
                                else{
                                    JOptionPane.showMessageDialog(null, "Ocurrio un error al conectarse con la base de datos");
                                }
                            }//Si pusieromn letras en lugar de numeros
                        }//Si numero de casa vale 0
                    }//Si el empleado ya existe
                    else{
                        JOptionPane.showMessageDialog(null, "El empleado ya existe"); 
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Error en la consulta a la base de datos");
                }
            }//Si alguno de los camposviene vacio
            else{
                JOptionPane.showMessageDialog(null, "Llena los campos requeridos"); 
            }
        }//Fin del ae.getsource
        if(ae.getSource()==ventana.btnSalir){
            ventana.dispose();
        }
    } 
}
