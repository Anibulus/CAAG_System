package modelo;
public class cursoActual {
    private int idAlumno;
    private int idIngreso;
    private String nombre;
    private String app;
    private String apm;
    private String telefono;
    private double colegiatura;
    private String inicio;
    private String fin;
    private String duracion;
    private String horario;
    private String tramiteCertificacion;
    private int ultimaSemanaPagada;
    private int numSemana;

    public cursoActual() {
    }

    public cursoActual(int idAlumno, String nombre, String app, String apm, String telefono, double colegiatura, String inicio, String fin, String duracion, String horario, String tramiteCertificacion, int ultimaSemanaPagada) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.app = app;
        this.apm = apm;
        this.telefono = telefono;
        this.colegiatura = colegiatura;
        this.inicio = inicio;
        this.fin = fin;
        this.duracion = duracion;
        this.horario = horario;
        this.tramiteCertificacion = tramiteCertificacion;
        this.ultimaSemanaPagada = ultimaSemanaPagada;
    }

    public int getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(int idIngreso) {
        this.idIngreso = idIngreso;
    }
    
    

    public int getNumSemana() {
        return numSemana;
    }

    public void setNumSemana(int numSemana) {
        this.numSemana = numSemana;
    }
    

    public int getUltimaSemanaPagada() {
        return ultimaSemanaPagada;
    }

    public void setUltimaSemanaPagada(int ultimaSemanaPagada) {
        this.ultimaSemanaPagada = ultimaSemanaPagada;
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

    public double getColegiatura() {
        return colegiatura;
    }

    public void setColegiatura(double colegiatura) {
        this.colegiatura = colegiatura;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTramiteCertificacion() {
        return tramiteCertificacion;
    }

    public void setTramiteCertificacion(String tramiteCertificacion) {
        this.tramiteCertificacion = tramiteCertificacion;
    }
    
    
}
