package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;

/**
 * Interfaz que define los métodos que debe implementar una clase que se encargue
 * de la interacción con el usuario.
 *
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public interface UI {
    
    /**
     * Método que recibe las ordenes del jugador para desplazarse.
     * @return Dirección hacia la que desea desplazarse el jugador
     */
    public Directions nextMove();
    
    
    /**
     * Método que muestra el estado completo del juego, mostrándo el laberinto,
     * los monstruos, los jugadores, mensaje con eventos importantes y si ha habido
     * un ganador.
     * 
     * @param gameState estado actual del juego
     */
    public void showGame(GameState gameState);
}
