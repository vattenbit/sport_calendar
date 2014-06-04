![#sport_calendar] (https://raw.githubusercontent.com/CubeEight/sport_calendar/master/src/imagenes/logo_calendar.jpg "Logo sport_calendar")

La creación de calendarios es siempre un problema cuando te planteas realizar una liga o un torneo, no importa si es de fútbol, de chapas o de tu juego online favorito. sport_calendar es un programa Open Source que te invita a realizarlo mediante el algoritmo que se utiliza por ejemplo en la liga nacional de fútbol. La idea principal es que ningún equipo juega mas de dos veces seguidas en casa y solo una vez con cada equipo.

##1. Versiones

###1.1 Versión Beta 0.1  
Esta primera versión tan solo implementa el algoritmo en la clase Calendario y un archivo Demo que nos enseña una manera de utilizarlo.

###1.2 Versión Beta 1.0 (En desarrollo) 
Esta primera versión Beta será la primera que ofrezca un interface gráfico para poder realizar operaciones:
* Agregar Equipo
* Eliminar Equipo
* Renombrar Equipo
* Sortear Calendario
* Exportación de calendario en archivo .txt

###1.3 Versiones++ 
Se plantean las siguientes ampliaciones: 
* Exportación de calendario en archivo .ods
* Exportación de calendario en archivo .xls
* Visión gráfica de calendario
* Visión de calendario para cada equipo

##2. Algoritmo de sorteo
El algoritmo es relativamente fácil de implementar y se basa en 5 pasos simples, es importante resaltar antes que en caso de descanso el sorteo se hace con un equipo "descansa" y mas tarde se trata todo el calendario quitando el partido y guardandolo como equipo que descansa, además se utiliza para un número de equipos mayor o igual a 5, cuando estos son menos se utiliza un modo de sorteo similar a la Champions jugando el primer partido de la vuelta contra el ultimo que se jugó la ida.

** La primera jornada se confecciona mediante sorteo directo y se denomina "equipo comodin" al equipo local de la última jornada.

** Las jornadas pares de la primera vuelta se forman mediante la jornada impar anterior. En la lista de locales el ultimo local pasa a ser el local del primer partido y el resto se desplaza un lugar, además se intercambian los locales y visitantes de toda la jornada después.

** Las jornadas impares de la primera vuelta se forman a partir de la jornada impar anterior. El equipo comodín (que será el último de los locales siempre) se queda fijo y con el resto se realiza un movimiento de permutación circular en el sentido opuesto a las agujas del reloj.

** Por último para evitar que un equipo juegue tres veces como local se intercambia local y visitante de los partidos del equipo comodín de las últimas dos jornadas.

** Ya sorteada la primera vuelta la segunda vuelta será la primera invertida.
