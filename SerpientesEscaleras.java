/*juego de Serpientes y Escaleras
        2 a 5 jugadores (leer nombres)
        Turnos alternados
        Leer nombres de los jugadores
        Usar 2 dados digitales
        En cada turno (imprimir jugador):
        Lanzar los 2 dados (usar Random)
        Avanzar las casillas
        Revisar casilla (Serpiente o Escalera)
        Revisar si llego a la posición 100
        Si se pasa de la posición 100, regresar
        Imprimir cada jugada
        Terminar cuando gane un jugador
        */
import java.util.Scanner;
import java.util.Random;

public class SerpientesEscaleras{
    /*El primer arreglo es donde estan ubicadas las serpientes y escaleras y el segundo es a la posición a donde se enviara al jugador si
    cae en una serpiente o escalera*/
        private static int[][] mapa = {{5,14,38,51,53,64,76,92,97},{58,49,20,10,72,83,54,73,54,73,61}};

        public static void main(String[] args) {
            int numJugadores=0;

            /*Se solicita al jugador que ingrese el número de jugadores y se valida que sean entre 2 y 5*/
            do{
                System.out.print("Numero de jugadores: ");
                numJugadores = Keyboard.readInt();
                if (numJugadores>5||numJugadores<2){
                    System.out.println("Solo se permiten de 2 a 5 jugadores");
                }
            }while (numJugadores>5||numJugadores<2);

            /*Se crea una matriz de tipo jugador para guardar los jugadores que ingrese el usuario*/
            Jugador [] jugadores = new Jugador[numJugadores];
            String nombre;
            for(int i=0; i < numJugadores; i++) {
                System.out.println("Nombre de jugador " + (i + 1) + ": ");
                nombre = Keyboard.readString();
                /*Se crean los objetos de tipo jugador*/
                jugadores[i] = new Jugador(nombre);
            }
            /*Se ejecuta el juego*/
            juego(jugadores);
        }
        private static void  juego(Jugador[] jugadores){

            Scanner scanerEnter =  new Scanner( System.in );
            boolean terminar = false;
            /*Ciclo del juego*/
           do {
               for (int i = 0; i < jugadores.length; i++) {
                   System.out.print("Jugador " + jugadores[i].obtenerNombre() + " es tu turno presiona enter para tirar los dados. ");
                   scanerEnter.nextLine();
                   int dados = tirarDados();
                   int nuevaPosicion = jugadores[i].obtenerPosicion() + dados;
                   nuevaPosicion = serpienteOescalera(nuevaPosicion);
                   if (nuevaPosicion == 100) {
                       terminar = true;
                       System.out.println("El valor de los dados es: "+dados + ". Has llegado a la posición 100. Jugador " + jugadores[i].obtenerNombre() + "¡eres el ganador!");
                       break;//Se obtiene un ganador y se detiene el ciclo de los jugadores, porque ya se obtiene un ganador

                   }else {

                       if (nuevaPosicion > 100) {
                           nuevaPosicion = 100 - (nuevaPosicion - 100);/*Aqui se vuelve a actualizar la posición, por eso se vuelve a llamar al método serpienteOescalera.*/
                           nuevaPosicion = serpienteOescalera(nuevaPosicion);
                       }
                       jugadores[i].asignarPosicion(nuevaPosicion);
                       System.out.println("El valor de los dados es: "+dados + ". La nueva posicion del jugador " + jugadores[i].obtenerNombre() + " es: " + jugadores[i].obtenerPosicion());
                   }
               }
               System.out.println("--------------------------------------------------------------------------------------------------------------");
           } while (!terminar);
            }
        private static int tirarDados(){
            Random dadosAleatorio = new Random();
            return dadosAleatorio.nextInt(11)+2;
        }
        private static int serpienteOescalera(int nuevaPosicion){

            /*Evalua si en la posción en que estoy despues de tirar los dados hay una serpiente o una escalera, si hay, actualiza la variable, si no, se queda igual.*/
            for(int j=0;j<mapa[0].length; j++){
                if(nuevaPosicion==mapa[0][j]){
                    if(mapa[0][j]<mapa[1][j]){
                        System.out.println( "La nueva posicion es: "+ nuevaPosicion + "¡pisaste una escalera avanzas!");
                    }else{
                        System.out.println("La nueva posicion es: "+ nuevaPosicion+ " ¡Pisaste una serpiente retrocedes!");
                    }

                    nuevaPosicion=mapa[1][j];
                    break; //No tiene caso seguir iterando cuando ya encontraste la nueva posición.
                }
            }
            return nuevaPosicion;
        }
    }





