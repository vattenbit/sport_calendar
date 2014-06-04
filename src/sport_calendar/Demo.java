package sport_calendar;

public class Demo {
    
    private static final Calendar calendario = new Calendar();
    
    public static void main(String args[]) {
        
        //Creamos X equipos que nos deberá de dar n jornadas
        calendario.getListaEquipos().add(new Equipo("Atlético de Madrid"));
        calendario.getListaEquipos().add(new Equipo("Real Madrid"));
        calendario.getListaEquipos().add(new Equipo("Barcelona"));
        calendario.getListaEquipos().add(new Equipo("Valencia"));
        calendario.getListaEquipos().add(new Equipo("Sevilla"));
        calendario.getListaEquipos().add(new Equipo("Athletic de Bilbao"));
        calendario.getListaEquipos().add(new Equipo("Deportivo de la Coruña"));
        calendario.getListaEquipos().add(new Equipo("CD Toledo"));
        calendario.getListaEquipos().add(new Equipo("Getafe"));
//        calendario.getListaEquipos().add(new Equipo("Numancia"));
//        calendario.getListaEquipos().add(new Equipo("Betis"));
//        calendario.getListaEquipos().add(new Equipo("Real Sociedad"));
//        calendario.getListaEquipos().add(new Equipo("Malaga"));
//        calendario.getListaEquipos().add(new Equipo("Albacete Balompié"));
//        calendario.getListaEquipos().add(new Equipo("Rayo Vallecano"));
        
        calendario.sortearJornadas();

        calendario.mostrarCalendario();
    }
    
}
