
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;

/**
 * Esta clae se encarga de recibir las ordenes de los jugadores y transimitirlas
 * al juego para que haga las gestiones necesarias. También muestra en consola
 * el estado del juego, es decir, el laberinto y los jugadores, monstruos, etc.
 */
public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    /**
     * Obtiene el carácter o secuencia de carácteres leídos desde la entrada 
     * estándar
     * 
     * @return Devuelve el carácter recibido
     */
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }

    /**
     * Método que recibe las ordenes del jugador para desplazarse
     * @return dirección hacia la que desea desplazarse el jugador
     */
    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
     
    /**
     * Método que muestra el estado completo del juego, mostrándo el laberinto,
     * los monstruos, los jugadores, mensaje con eventos importantes y si ha habido
     * un ganador.
     * 
     * @param gameState estado actual del juego
     */
    public void showGame(GameState gameState) {
        System.out.print(gameState.getLabyrinth() + "\n");
        System.out.print(gameState.getPlayers() + "\n");
        System.out.print(gameState.getMonsters() + "\n");
        System.out.print("Log:\n" + gameState.getLog() + "\n");
                
        if (gameState.getWinner()) {
            System.out.print("We have a winner! Player " + gameState.getCurrentPlayer() + "\n");
        }else{
            System.out.print("Current player: " + gameState.getCurrentPlayer() + "\n");
        }
    }
    
}