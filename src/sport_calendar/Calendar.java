package sport_calendar;

import java.util.ArrayList;

public class Calendar {

    //ATRIBUTOS
    private ArrayList<Equipo> listaEquipos;
    private ArrayList<Jornada> calendario;
    private boolean descanso;
    private int nJornadas;
    private int nPartidos;
    private int nEquipos;

    //CONSTRUCTOR
    public Calendar() {
        listaEquipos = new ArrayList();
        calendario = new ArrayList();
    }

    //SETTER & GETTERÇ
    public ArrayList<Equipo> getListaEquipos() {
        return listaEquipos;
    }

    public void setListaEquipos(ArrayList<Equipo> listaEquipos) {
        this.listaEquipos = listaEquipos;
    }

    public ArrayList<Jornada> getCalendario() {
        return calendario;
    }

    public void setListaJornadas(ArrayList<Jornada> listaJornadas) {
        this.calendario = listaJornadas;
    }

    //FUNCIONES
    public void sortearJornadas() {

        //Se inicializan los valores iniciales de los atributos dependientes de las listas
        //nEQuipos, nJornadas, nPartidos y descanso
        nEquipos = listaEquipos.size();
        if (nEquipos % 2 == 0) {
            nJornadas = nEquipos - 1;
            descanso = false;
        } else {
            nJornadas = nEquipos;
            listaEquipos.add(new Equipo(null, "descansa"));
            descanso = true;
            nEquipos++;
        }
        nPartidos = listaEquipos.size() / 2;

        //Sorteo de la primera jornada mediante la utilización del método sorteoPrimeraJornada();
        calendario.add(sorteoPrimeraJornada());

        //Sintesis de las demás jornadas en función de la primera sorteada y de si es par o impar
        for (int i = 1; i < nJornadas; i++) {
            //Jornadas pares i=0 es jornada 1 (IMPAR) e i=1 es jornada 2 (PAR)
            if (i % 2 == 1) {
                calendario.add(sorteoJornadaPar(i));
            } else {
                calendario.add(sorteoJornadaImpar(i));
            }
        }

        //Se cambia local por visitante en las dos ultimas jornadas del partido donde esté involucrado el equipo pivote
        //Con esto se evita que algún equipo juegue mas de 2 partidos seguidos en casa
        cambioDosUltmiasPivote();

        //En caso de haber descanso se quitan los partidos en los que juega "descanso" y se agrega a jornada.descansa
        if (descanso) {
            quitarDescanso();
        }

        //Se sintetizan todas las jornadas de vueltas invirtiendo las idas
        int tamanoMitadCalendario = calendario.size();
        if (nEquipos <= 4) {
            for (int i = tamanoMitadCalendario - 1; i >= 0; i--) {
                calendario.add(jornadaDeVuelta(calendario.get(i)));
            }
        } else {
            for (int i = 0; i < tamanoMitadCalendario; i++) {
                calendario.add(jornadaDeVuelta(calendario.get(i)));
            }
        }
    }

    public void mostrarCalendario() {
        //Método que muestra toda la temporada y hace uso de mostrarJornada()
        int numeroJornada = 1;
        System.out.println("+---------------------------------------------+");
        System.out.println("|         CALENDARIO DE LA TEMPORADA          |");
        System.out.println("|  Realizado mediante sport_calendar Beta 0.1 |");
        System.out.println("+---------------------------------------------+\n");
        System.out.println("------------------");
        System.out.println("  PRIMERA VUELTA");
        System.out.println("------------------");
        for (Jornada jornada : calendario) {
            if (numeroJornada == nJornadas + 1) {
                System.out.println("------------------");
                System.out.println("  SEGUNDA VUELTA");
                System.out.println("------------------");
            }
            System.out.println("Jornada " + numeroJornada++);
            jornada.mostrarJornada();
            System.out.println();
        }
    }

    private Jornada jornadaDeVuelta(Jornada jornada) {
        Jornada nuevaJornada = new Jornada();
        nuevaJornada.setLocales(jornada.getVisitantes());
        nuevaJornada.setVisitantes(jornada.getLocales());
        nuevaJornada.setDescansa(jornada.getDescansa());
        return nuevaJornada;
    }

    private Jornada sorteoPrimeraJornada() {
        Jornada jornada = new Jornada();
        boolean[] usados = new boolean[nEquipos];
        for (int i = 0; i < nEquipos; i++) {
            int equipoAleatorio = (int) (Math.random() * nEquipos);
            boolean encontrado = false;
            while (!encontrado) {
                if (!usados[equipoAleatorio]) {
                    usados[equipoAleatorio] = true;
                    encontrado = true;
                } else {
                    equipoAleatorio++;
                    if (equipoAleatorio == nEquipos) {
                        equipoAleatorio = 0;
                    }
                }
            }
            if (i % 2 == 0) {
                jornada.getLocales().add(listaEquipos.get(equipoAleatorio));
            } else {
                jornada.getVisitantes().add(listaEquipos.get(equipoAleatorio));
            }
        }
        return jornada;
    }

    private void quitarDescanso() {
        for (Jornada jornada : calendario) {
            for (int i = 0; i < nPartidos; i++) {
                boolean descansaEsLocal = jornada.getLocales().get(i).getNombre().equals("descansa");
                boolean descansaEsVisitante = jornada.getVisitantes().get(i).getNombre().equals("descansa");
                if (descansaEsLocal) {
                    jornada.setDescansa(jornada.getVisitantes().get(i));
                } else if (descansaEsVisitante) {
                    jornada.setDescansa(jornada.getLocales().get(i));

                }
                if (descansaEsLocal || descansaEsVisitante) {
                    jornada.getLocales().remove(i);
                    jornada.getVisitantes().remove(i);
                    break;
                }
            }
        }
        nPartidos--;
    }

    private Jornada sorteoJornadaPar(int nJornadaActual) {
        Jornada jornada = new Jornada();
        ArrayList<Equipo> locales = (ArrayList<Equipo>) calendario.get(nJornadaActual - 1).getVisitantes().clone();
        ArrayList<Equipo> visitantes = (ArrayList<Equipo>) calendario.get(nJornadaActual - 1).getLocales().clone();
        jornada.setLocales(locales);
        jornada.setVisitantes(visitantes);
        Equipo anterior = visitantes.get(nPartidos - 1);
        for (int i = 0; i < nPartidos; i++) {
            anterior = visitantes.set(i, anterior);
        }
        return jornada;
    }

    private Jornada sorteoJornadaImpar(int nJornadaActual) {
        Jornada jornada = new Jornada();
        ArrayList<Equipo> locales = (ArrayList<Equipo>) calendario.get(nJornadaActual - 2).getLocales().clone();
        ArrayList<Equipo> visitantes = (ArrayList<Equipo>) calendario.get(nJornadaActual - 2).getVisitantes().clone();
        jornada.setLocales(locales);
        jornada.setVisitantes(visitantes);
        Equipo anterior = visitantes.get(0);
        for (int i = 0; i < nPartidos - 1; i++) {
            anterior = locales.set(i, anterior);
        }
        for (int i = nPartidos - 1; i >= 0; i--) {
            anterior = visitantes.set(i, anterior);
        }
        return jornada;
    }

    private void cambioDosUltmiasPivote() {
        Equipo pivote = calendario.get(0).getLocales().get(nPartidos - 1);
        for (int i = nJornadas - 2; i < nJornadas; i++) {
            Jornada jornada = calendario.get(i);
            for (int j = 0; j < nPartidos; j++) {
                if (jornada.getLocales().get(j).equals(pivote) || jornada.getVisitantes().get(j).equals(pivote)) {
                    Equipo auxiliar = jornada.getLocales().get(j);
                    jornada.getLocales().set(j, jornada.getVisitantes().get(j));
                    jornada.getVisitantes().set(j, auxiliar);
                    break;
                }
            }
        }
    }

}
