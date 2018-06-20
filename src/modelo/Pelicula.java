package modelo;
/**
 *
 * @author LN710Q
 */
public class Pelicula {
    private int id;
    private String nombre;
    private String annio;
    private String director;
    private String paisp;
    private String clasificacion;
    private int stock;
    private boolean existenciap;
    
    public Pelicula(){
        
    }
    
    public Pelicula(int id, String nombre, String annio,String director,String paisp,String clasificacion, int stock, boolean existenciap){
        this.id = id;
        this.annio = annio;
        this.director = director;
        this.paisp = paisp;
        this.clasificacion = clasificacion;
        this.stock = stock;
        this.existenciap = existenciap;
        this.nombre=nombre;
    }
    public Pelicula(String codigo, String annio, int stock,String clasificacion, boolean existenciap){
        this.nombre=nombre;
        this.annio=annio;
        this.clasificacion=clasificacion;
        this.stock=stock;
        this.existenciap=existenciap;
    }
    public Pelicula(String annio, int stock, boolean existenciap){
        this.annio = annio;
        this.stock = stock;
        this.existenciap = existenciap;
    }

    public int getId() {
        return id;
    }

    public String getPaisp() {
        return paisp;
    }

    public void setPaisp(String paisp) {
        this.paisp = paisp;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAnnio() {
        return annio;
    }

    public void setAnnio(String annio) {
        this.annio = annio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isExistenciap() {
        return existenciap;
    }

    public void setExistenciap(boolean existenciap) {
        this.existenciap = existenciap;
    }
    
    public boolean getExistenciap(){
        return existenciap;
    }
    
    
}
