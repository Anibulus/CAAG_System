package modelo;
public class aspirante {
    private int idAlumno;
    private String nombre;
    private String app;
    private String apm;
    private String telefono;
    private String correo;
    private String cursoInteres;
    private String reconocimiento;
    private String nivelAcademico;
    private String fechaInforme;
    private String calle;
    private int numExterior;
    private int numInterior;
    private String colonia;
    private String estatus;

    public aspirante() {
    }
    
    public aspirante(int idAlumno, String nombre, String app, String apm, String telefono, String correo, String cursoInteres, String reconocimiento, String nivelAcademico, String fechaInforme, String calle, int numExterior, int numInterior, String colonia, String estatus) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.telefono = telefono;
        this.correo = correo;
        this.cursoInteres = cursoInteres;
        this.reconocimiento = reconocimiento;
        this.nivelAcademico = nivelAcademico;
        this.fechaInforme = fechaInforme;
        this.calle = calle;
        this.numExterior = numExterior;
        this.numInterior = numInterior;
        this.colonia = colonia;
        this.estatus = estatus;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCursoInteres() {
        return cursoInteres;
    }

    public void setCursoInteres(String cursoInteres) {
        this.cursoInteres = cursoInteres;
    }

    public String getReconocimiento() {
        return reconocimiento;
    }

    public void setReconocimiento(String reconocimiento) {
        this.reconocimiento = reconocimiento;
    }

    public String getNivelAcademico() {
        return nivelAcademico;
    }

    public void setNivelAcademico(String nivelAcademico) {
        this.nivelAcademico = nivelAcademico;
    }

    public String getFechaInforme() {
        return fechaInforme;
    }

    public void setFechaInforme(String fechaInforme) {
        this.fechaInforme = fechaInforme;
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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
}
