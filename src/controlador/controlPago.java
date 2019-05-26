package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import modelo.alumnoDAO;
import modelo.cursoActual;
import modelo.cursoActualDAO;
import modelo.cursosDAO;
import vista.ventanaPago;

public class controlPago implements ActionListener {
    private ventanaPago v;
    private cursoActualDAO cADAO;
    private DefaultListModel modeloLista;
    public controlPago(ventanaPago v, cursoActualDAO cADAO){
        this.modeloLista=new DefaultListModel();
        this.cADAO=cADAO;
        this.v=v;
        this.v.btnBuscar.addActionListener(this);
        this.v.btnGuardar.addActionListener(this);
        this.v.btnCobrar.addActionListener(this);
        this.v.btnAgregar.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==v.btnBuscar){
            int id=(int)v.txtMatricula.getValue();
            String nombre=v.txtNombre.getText();
            cursoActual ca=null;
            
            if(id>0||!nombre.equals("")){
                try {
                    ca=cADAO.consultarEstudiante(id, nombre, v.txtCurso.getSelectedIndex()+1);
                    if(ca!=null){
                        v.txtMatricula.setValue(ca.getIdAlumno());
                        v.txtCan.setText(String.valueOf((int)ca.getColegiatura()));
                        v.txtNombre.setText(ca.getNombre()+" "+ca.getApp()+" "+ca.getApm());
                        v.txtNumSemana.setText(String.valueOf(ca.getNumSemana()));
                        v.txtUltimaSemana.setText(String.valueOf(ca.getUltimaSemanaPagada()));
                    }else  JOptionPane.showMessageDialog(null, "No se encontro al estudiante \n o Pagos que haya realizado");
                } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Ocurrio un error al consultar");
                }
            }else JOptionPane.showMessageDialog(null, "Necesita llenar por lo menos un campo \n (Matrícula o Nombre)");
        }else if(ae.getSource()==v.btnGuardar){
            int idM=(int)v.txtMatricula.getValue();
            int can=Integer.parseInt(v.txtCan.getText());
            String motivo=v.txtMotivo.getText();
            try {
                if(cADAO.pagoConceptiosVarios(idM, can, motivo))
                    JOptionPane.showMessageDialog(null, "Se registro el pago correctamente");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un error al ingresar");
            }
        }else if(ae.getSource()==v.btnCobrar){
            //Se generan las variables para almacenar la informacion
            int idM=(int)v.txtMatricula.getValue();
            int can=Integer.parseInt(v.txtCan.getText());
            int[] semana=new int[10];
            int contador=0;
            //Se llena el arreglo
            v.txtListaSem.setModel(modeloLista);
            for (int i = 0;  i< modeloLista.getSize(); i++) {
                try{
                    String coso=String.valueOf(modeloLista.getElementAt(i));
                    semana[i]=Integer.parseInt(coso);
                    System.out.println(semana[i]);
                
                }catch(Exception e){
                }
            }
            System.out.println("hasta qui");
            //Se va a revisar el numero de semanas que se va a apagar
            for (int i = 0; i < 10; i++) {
                if(semana[i]>0){
                    contador++;
                }else i=10;
            }
            System.out.println("hasta qui");
            //Se busca al alumno(con curso activo) para verifixcar el precio que paga
            cursoActual curA=null;
            try {
                //Se revisa cuanto paga el alumno
                curA=cADAO.consultarCostoEstudiante(idM);
                System.out.println(curA);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrio un problema al consultar");
            }
            System.out.println("hasta qui");
            //Si no coincide no se hace el pago (solo multiplos de su pago)
            //Despues añadir abonos-----------          
            if(curA!=null){
                double precio=curA.getColegiatura();
                //Se multiplica numero de semanas por el precio base y se verifica que sea lo que el alumno esta pagando
                double total=(precio*contador);
                //Se terminan de generar la variables y se verifica que la informacion este correcta
                if(can==total){
                    System.out.println("Dentro");
                    try {
                        System.out.println("haciendo el coso");
                        if(cADAO.pagoSemanas(idM, can, semana))
                            JOptionPane.showMessageDialog(null, "Se ha guardado el pago con exito");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Ocurrio un problema al conectar");
                    }
                }else JOptionPane.showMessageDialog(null, "El monto de pago y el numero de semanas no coinciden");
            }else JOptionPane.showMessageDialog(null, "Verifique que se encuentre el nombre completo");
            
        }else if(ae.getSource()==v.btnAgregar){
            int sem=(int)v.txtSemanas.getValue();
            v.txtListaSem.setModel(modeloLista);
            modeloLista.addElement(sem);           
        }
    }//fin de la ae
}//Fin de la clase
