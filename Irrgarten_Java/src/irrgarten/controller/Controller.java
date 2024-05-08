package irrgarten.controller;

import irrgarten.Directions;
import irrgarten.Game;
import irrgarten.UI.TextUI;

/**
 * Esta clase se encarga mantener el control general del juego, es decir, su visualización
 * el estado del juego, su finalización, etc.
 */
public class Controller {
    
    /**
     * Juego a jugar
     */
    private Game game;
    
    /**
     * Vista del juego
     */
    private TextUI view;
    
    /**
     * Constructor del controlador
     * 
     * @param game juego
     * @param view visualización
     */
    public Controller(Game game, TextUI view) {
        this.game = game;
        this.view = view;
    }
    
    /**
     * Juego
     */
    public void play() {
        boolean endOfGame = false;
        while (!endOfGame) {
            view.showGame(game.getGameState()); 
            Directions direction = view.nextMove(); 
            endOfGame = game.nextStep(direction);
        }
      view.showGame(game.getGameState());        
    }
    
}