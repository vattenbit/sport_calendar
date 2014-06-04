package sport_calendar;

import java.util.ArrayList;

public class Jornada {

    //ATRIBUTOS
    private ArrayList<Equipo> locales;
    private ArrayList<Equipo> visitantes;
    private Equipo descansa;

    //CONSTRUCTOR
    public Jornada() {
        locales = new ArrayList();
        visitantes = new ArrayList();
    }

    //SETTER & GETTER
    public Equipo getDescansa() {
        return descansa;
    }

    public void setDescansa(Equipo descansa) {
        this.descansa = descansa;
    }

    public ArrayList<Equipo> getLocales() {
        return locales;
    }

    public void setLocales(ArrayList<Equipo> locales) {
        this.locales = locales;
    }

    public ArrayList<Equipo> getVisitantes() {
        return visitantes;
    }

    public void setVisitantes(ArrayList<Equipo> visitantes) {
        this.visitantes = visitantes;
    }

    //FUNCIONES
    public void mostrarJornada() {
        for (int i = 0; i < locales.size(); i++) {
            System.out.println(locales.get(i).getNombre() + " - " + visitantes.get(i).getNombre());
        }
        if (descansa != null) {
            System.out.println("Descansa: " + descansa.getNombre());
        }
    }

}
