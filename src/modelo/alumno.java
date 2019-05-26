package modelo;
public class alumno {
    private int idAlumno;
    private String nombre;
    private String app;
    private String apm;
    private String telefono;
    private String correo;
    private String calle;  
    private int numExterior;
    private int numInterior;
    private String colonia;
    private String estatus;
    private boolean actaNacimiento;
    private String certificado;
    private boolean comprobanteDomicilio;
    private boolean curp;
    private boolean fotografias;
    private boolean ine;
    private int idPapeles;
    private int idDireccion;

    public alumno() {
    }

    public alumno(int idAlumno, String nombre, String app, String apm, String telefono, String correo, String calle, int numExterior, int numInterior, String colonia, String estatus, boolean actaNacimiento, String certificado, boolean comprobanteDomicilio, boolean curp, boolean fotografias, boolean ine, int idDir, int idPapel) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.correo=correo;
        this.telefono = telefono;
        this.calle = calle;
        this.numExterior = numExterior;
        this.numInterior = numInterior;
        this.colonia = colonia;
        this.estatus = estatus;
        this.actaNacimiento = actaNacimiento;
        this.certificado = certificado;
        this.comprobanteDomicilio = comprobanteDomicilio;
        this.curp = curp;
        this.fotografias = fotografias;
        this.ine = ine;
        this.idDireccion=idDir;
        this.idPapeles=idPapel;
    }

    public void setIdPapeles(int idPapeles) {
        this.idPapeles = idPapeles;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public int getIdPapeles() {
        return idPapeles;
    }

    public int getIdDireccion() {
        return idDireccion;
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

    public boolean isActaNacimiento() {
        return actaNacimiento;
    }

    public void setActaNacimiento(boolean actaNacimiento) {
        this.actaNacimiento = actaNacimiento;
    }

    public String getCertificado() {
        return certificado;
    }

    public void setCertificado(String certificado) {
        this.certificado = certificado;
    }

    public boolean isComprobanteDomicilio() {
        return comprobanteDomicilio;
    }

    public void setComprobanteDomicilio(boolean comprobanteDomicilio) {
        this.comprobanteDomicilio = comprobanteDomicilio;
    }

    public boolean isCurp() {
        return curp;
    }

    public void setCurp(boolean curp) {
        this.curp = curp;
    }

    public boolean isFotografias() {
        return fotografias;
    }

    public void setFotografias(boolean fotografias) {
        this.fotografias = fotografias;
    }

    public boolean isIne() {
        return ine;
    }

    public void setIne(boolean ine) {
        this.ine = ine;
    }
    
    
}
