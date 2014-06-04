package sport_calendar;

public class Equipo<E> {
    
    //ATRIBUTOS
    private E elemento;
    private String nombre;
    
    //CONSTRUCTOR
    public Equipo(E elemento, String nombre) {
        this.elemento = elemento;
        this.nombre = nombre;
    }
    
    public Equipo(String nombre) {
        this.nombre = nombre;
    }
    
    //GETTER & SETTER
    public E getElemento() {
        return elemento;
    }

    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
