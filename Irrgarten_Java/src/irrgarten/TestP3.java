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
    
    /**
     * Método que realiza las pruebas unia
     * tarias de la práctica 3
     * @param args Argumentos recibidos al ejecutar
     */
    public static void main ( String [ ] args ) {
        
        final int N_PLAYERS = 1;

        TextUI vista = new TextUI();
        Game juego = new Game(N_PLAYERS);
        Controller controlador = new Controller(juego, vista);

        controlador.play();
    }
}
