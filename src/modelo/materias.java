package modelo;
public class materias {
    private int idMateria;
    private String nombre;
    private int curso;
    private String descripcion;
    /*Agregar plan de estudios en características*/
    
    public materias(){
        
    }
    
    public materias(int id, String nombre, int curso, String descripcion){
        this.idMateria=id;
        this.nombre=nombre;
        this.curso=curso;
        this.descripcion=descripcion;
    }
    
    public void setId(int id){
        this.idMateria=id;
    }
    
    public int getId(){
        return idMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
}
