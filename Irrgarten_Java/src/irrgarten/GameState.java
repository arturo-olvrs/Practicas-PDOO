package irrgarten;

/**
 * Clase que almacena informacion sobre el estado del juego
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class GameState {

    /**
     * Estado del laberinto
     */
    private final String labyrinth;
    /**
     * Estado de los jugadores
     */
    private final String players;
    /**
     * Estado de los monstruos
     */
    private final String monsters;
    /**
     * Identificador del jugador actual
     */
    private final int currentPlayer;
    /**
     * Indicador de si hay un ganador
     */
    private final boolean winner;
    /**
     * Eventos interesantes a lo largo de la partida
     */
    private final String log;

    /**
     * Constructor de la clase GameState
     * @param labyrinth Estado del laberinto
     * @param players Estado de los jugadores
     * @param monsters Estado de los monstruos
     * @param currentPlayer Identificador del jugador actual
     * @param winner Indicador de si hay un ganador
     * @param log Eventos interesantes a lo largo de la partida
     */
    public GameState(String labyrinth, String players, String monsters, 
            int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }

    /**
     * Consultor del estado del laberinto
     * @return Estado del laberinto
     */
    public String getLabyrinth() {
        return labyrinth;
    }
    
    /**
     * Consultor del estado de los jugadores
     * @return Estado de los jugadores
     */
    public String getPlayers() {
        return players;
    }

    /**
     * Consultor del estado de los monstruos
     * @return Estado de los monstruos
     */
    public String getMonsters() {
        return monsters;
    }

    /**
     * Consultor para identificar el jugador actual
     * @return Índice del jugador actual
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Consultor de si ya hay un ganador
     * @return True si hay ganador, false en caso constrario
     */
    public boolean getWinner() {
        return winner;
    }

    /**
     * Consultor de eventos interesantes de la partida
     * @return Eventos interesantes de la partida
     */
    public String getLog() {
        return log;
    }
}
