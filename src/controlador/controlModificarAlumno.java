package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.alumno;
import modelo.alumnoDAO;
import vista.ventanaModificarAlumno;

public class controlModificarAlumno implements ActionListener{
    private ventanaModificarAlumno v;
    private alumnoDAO aluDAO;
    
    public controlModificarAlumno(ventanaModificarAlumno v, alumnoDAO aluDAO){
        this.v=v;
        this.aluDAO=aluDAO;
        this.v.btnBuscar.addActionListener(this);
        this.v.btnGuardar.addActionListener(this);
        //Agregar boton de salier-----------------------------------------------------------
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnBuscar){
            int id= (int) v.txtCódigo.getValue();
            String nombre=v.txtNombre.getText();
            if(id>0||!nombre.equals("")){
             alumno alu=null;
                try {
                    alu=aluDAO.consultarAlumnoCompleta(id, nombre);
                    if(alu!=null){
                        v.txtCódigo.setValue(alu.getIdAlumno());
                        v.txtNombre.setText(alu.getNombre());
                        v.txtApp.setText(alu.getApp());
                        v.txtApm.setText(alu.getApm());
                        v.txtTelefono.setText(alu.getTelefono());
                        v.txtCorreo.setText(alu.getCorreo());
                        v.txtCalle.setText(alu.getCalle());
                        v.txtColonia.setText(alu.getColonia());
                        v.txtNumExterior.setText(String.valueOf(alu.getNumExterior()));
                        v.txtNumInterior.setText(String.valueOf(alu.getNumInterior()));
                        v.checkActa.setSelected(alu.isActaNacimiento());
                        v.checkComprobante.setSelected(alu.isComprobanteDomicilio());
                        v.checkCurp.setSelected(alu.isCurp());
                        v.checkFotos.setSelected(alu.isFotografias());
                        v.checkIne.setSelected(alu.isIne());
                        
                    }//Si el objeto alumno esta vacio
                    else{
                        JOptionPane.showMessageDialog(null, "No se encontró la persona");
                    }
                } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Ocurrió un error en la conexion con la base de datos");
                }
            }
        }else if(ae.getSource()==v.btnGuardar){
            int id= (int) v.txtCódigo.getValue();
            String nombre=v.txtNombre.getText();
            alumno alu=null;
            try {
                alu=aluDAO.consultarAlumnoSimple(id, nombre);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error en la conexion con la base de datos");
            }
            
            if(alu!=null){
                String app=v.txtApp.getText();
                String apm=v.txtApm.getText();
                String tel=v.txtTelefono.getText();
                String correo=v.txtCorreo.getText();
                String calle=v.txtCalle.getText();
                String colonia=v.txtColonia.getText();
                boolean error=false;
                int numExterior=0;
                int numInterior=0;
                boolean acta=v.checkActa.isSelected();
                boolean comprobante=v.checkComprobante.isSelected();
                boolean curp=v.checkCurp.isSelected();
                boolean fotos=v.checkFotos.isSelected();
                boolean ine=v.checkIne.isSelected();
                String certificado="Poner certif";
                try{
                    numExterior=Integer.parseInt(v.txtNumExterior.getText());
                    numInterior=Integer.parseInt(v.txtNumInterior.getText());
                }catch(NumberFormatException e){
                    error=true;
                }
                if(!nombre.equals("")){
                    if(!error){
                        try {
                            /*
                            Hacer que permita numero exterior y verifique numero interior
                            */
                            if(aluDAO.modificarAlumno(id, nombre, app, apm, correo, tel))
                                if(aluDAO.modificarDireccion(alu.getIdDireccion(), calle, numExterior, numInterior, colonia))
                                    if(aluDAO.modificarPapeles(alu.getIdPapeles(), acta, certificado, comprobante, curp, fotos, ine))
                                        JOptionPane.showMessageDialog(null, "Se ha modificado correctamente");  
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Ocurrió un error con la conexion a base de datos");
                        }
                    }//Fin de si opusieron letras donde hay numeros
                    else{
                        JOptionPane.showMessageDialog(null, "Verifique los espacios Numero Interior o Exterior");
                    }
                }//Fin de si los campos estaban vacios
                else{
                    JOptionPane.showMessageDialog(null, "Verifique que los campos mínimos estén llenos");
                }
            }//Fin de Si se encontro a lapersona a modificar
            else{
                JOptionPane.showMessageDialog(null, "No se encontró la persona que desea modificar");
            }
        }
    }//Aqui termina el AE
}//Aqui termina la clase
