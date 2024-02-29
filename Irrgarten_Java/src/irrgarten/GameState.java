package irrgarten;

/**
 * Clase que almacena informacion sobre el estado del juego
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class GameState {
    private String labyrinth;
    private String players;
    private String monsters;
    private int currentPlayer;
    private boolean winner;
    private String log;

    // Constructor para inicializar todos los atributos
    public GameState(String labyrinth, String players, String monsters, int currentPlayer, boolean winner, String log) {
        this.labyrinth = labyrinth;
        this.players = players;
        this.monsters = monsters;
        this.currentPlayer = currentPlayer;
        this.winner = winner;
        this.log = log;
    }

    // Consultores para acceder a los atributos
    public String getLabyrinth() {
        return labyrinth;
    }

    public String getPlayers() {
        return players;
    }

    public String getMonsters() {
        return monsters;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean getWinner() {
        return winner;
    }

    public String getLog() {
        return log;
    }
}
