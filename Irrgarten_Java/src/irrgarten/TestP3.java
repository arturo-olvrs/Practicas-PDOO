package irrgarten;

import irrgarten.controller.Controller;
import irrgarten.UI.TextUI;

/**
 * Programa de prueba para la practica 3
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class TestP3 {
    public static void main ( String [ ] args ) {
        
        final int N_PLAYERS = 4;

        TextUI vista = new TextUI();
        Game juego = new Game(N_PLAYERS);
        Controller controlador = new Controller(juego, vista);

        controlador.play();
    }
}
