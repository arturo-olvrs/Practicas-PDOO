package irrgarten;

import irrgarten.controller.Controller;
import irrgarten.UI.GraphicUI;

/**
 * Programa de prueba para la practica 5
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class TestP5 {
    
    /**
     * Método que realiza las pruebas unia
     * tarias de la práctica 5
     * @param args Argumentos recibidos al ejecutar
     */
    public static void main ( String [ ] args ) {
        
        final int N_PLAYERS = 3;

        GraphicUI vista = new GraphicUI();
        Game juego = new Game(N_PLAYERS);
        Controller controlador = new Controller(juego, vista);

        controlador.play();
    }
}
