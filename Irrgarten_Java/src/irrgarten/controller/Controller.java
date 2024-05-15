package irrgarten.controller;

import irrgarten.Directions;
import irrgarten.Game;
import irrgarten.UI.*;

/**
 * Esta clase se encarga mantener el control general del juego, es decir, su visualización
 * el estado del juego, su finalización, etc.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Controller {
    
    /**
     * Juego a jugar
     */
    private Game game;
    
    /**
     * Vista del juego
     */
    private UI view;
    
    /**
     * Constructor del controlador
     * 
     * @param game Juego
     * @param view Clase que se encarga de la interacción con el usuario
     */
    public Controller(Game game, UI view) {
        this.game = game;
        this.view = view;
    }
    // TODO: UI se puede manejar así?
    
    /**
     * Método que se encarga de jugar al juego
     */
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState());
            
            // TODO: Como parar y que sea cuando lo indica el usuario
            Directions direction = view.nextMove(); 
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
}