package modelo;
public class pago {
    private int folio;
    private alumno cliente;
    private empleado cobra;
    private double cantidad;
    private String fecha;
    private String[] motivo;

    public pago() {
    }

    public pago(int folio, alumno cliente, empleado cobra, double cantidad, String fecha, String[] motivo) {
        this.folio = folio;
        this.cliente = cliente;
        this.cobra = cobra;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.motivo = motivo;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public alumno getCliente() {
        return cliente;
    }

    public void setCliente(alumno cliente) {
        this.cliente = cliente;
    }

    public empleado getCobra() {
        return cobra;
    }

    public void setCobra(empleado cobra) {
        this.cobra = cobra;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String[] getMotivo() {
        return motivo;
    }

    public void setMotivo(String[] motivo) {
        this.motivo = motivo;
    }
    
    
}
