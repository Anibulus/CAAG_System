package modelo;
public class empleado {
    private int idEmpleado;
    private String nombre;
    private String app;
    private String apm;
    private String telefono;
    private String puesto;
    private double salario;
    private String calle;
    private int numExterior;
    private int numInterior;
    private String colonia;
    private int idUsuario; 
    private int idDireccion;
    private String usuario;
    private String contrasena;
    private String estatus;
    
    public empleado(){
        this.idEmpleado = 0;
        this.nombre = "";
        this.app = "";
        this.apm = "";
        this.telefono = "";
        this.puesto = "";
        this.salario = 0;
        this.calle = "";
        this.numExterior = 0;
        this.numInterior = 0;
        this.colonia = "";
        this.usuario = "";
        this.contrasena = "";
        this.idDireccion=0;
        this.idUsuario = 0; 
        this.estatus = "";
    }//Fin del constructor vacio

    public empleado(int idEmpleado, String nombre, String app, String apm, String telefono, String puesto, double salario,int idDireccion, String calle, int numExterior, int numInterior, String colonia, String usuario, String contrasena, String estatus) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.telefono = telefono;
        this.puesto = puesto;
        this.salario = salario;
        this.calle = calle;
        this.numExterior = numExterior;
        this.numInterior = numInterior;
        this.colonia = colonia;
        this.idDireccion = idDireccion;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.estatus = estatus;
    }//Fin del constructor

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getApm() {
        return apm;
    }

    public void setApm(String apm) {
        this.apm = apm;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumExterior() {
        return numExterior;
    }

    public void setNumExterior(int numExterior) {
        this.numExterior = numExterior;
    }

    public int getNumInterior() {
        return numInterior;
    }

    public void setNumInterior(int numInterior) {
        this.numInterior = numInterior;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
    
    
}//Fin de la clase
