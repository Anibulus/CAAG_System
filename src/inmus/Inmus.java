package inmus;

import controlador.controlInicioSesion;
import controlador.controlPantallaPrincipal;
import javax.swing.JOptionPane;
import modelo.empleadoDAO;
import vista.ventanaInicioSesion;
import vista.ventanaPantallaPrincipal;

public class Inmus {
    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null,"Recuerda habilitar la ventana de inicio de sesion");
        empleadoDAO empDAO=new empleadoDAO();
        
        ventanaInicioSesion ventana=new ventanaInicioSesion();
        
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true); 
        controlInicioSesion control=new controlInicioSesion(ventana, empDAO);
        
        /*
        Nota, no olvides mostrar la ventana
        */
//        ventanaPantallaPrincipal v=new ventanaPantallaPrincipal();
//        v.setLocationRelativeTo(null);
//        v.setVisible(true);
//        controlPantallaPrincipal c=new controlPantallaPrincipal(v,empDAO);
    }  
}
