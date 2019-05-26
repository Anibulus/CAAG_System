package controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.conexion;
import modelo.empleadoDAO;
import vista.ventanaInicioSesion;
import vista.ventanaPantallaPrincipal;

public class controlInicioSesion implements ActionListener {
    private ventanaInicioSesion ventana;
    private empleadoDAO empDAO;
    
    public controlInicioSesion(ventanaInicioSesion ventana, empleadoDAO empDAO){
        this.ventana=ventana;
        this.empDAO=empDAO;
        this.ventana.btnEntrar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==ventana.btnEntrar){
            String usu=ventana.txtUsuario.getText();
            String contra=String.valueOf(ventana.txtContrasena.getPassword());
            System.out.println(usu+"\t"+contra);
            try {
                System.out.println("Entro en ingresar");
                if(empDAO.ingresar(usu, contra)){
                    /*
                    Añadir controladores y ventanas
                    */
                    ventanaPantallaPrincipal v=new ventanaPantallaPrincipal();
                    controlPantallaPrincipal ca=new controlPantallaPrincipal(v,empDAO);
                    v.setLocationRelativeTo(null);
                    v.setVisible(true);
                    ventana.dispose();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,"Ocurrio un error al consultar");
            }
        }
    }//Fin de la funcion actionListener   
}//Fin de la clase
