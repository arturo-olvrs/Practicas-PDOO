
package irrgarten;

import java.util.ArrayList;

/**
 * Clase que representa el juego Irrgarten. Esta clase se encarga de gestionar el estado del juego,
 * las acciones de los jugadores y los monstruos, y de generar un estado del juego que pueda ser
 * consultado por la interfaz de usuario.
 * 
 * @author Joaquín Avilés de la Fuente
 * @author Arturo Olivares Martos
 */
public class Game {
    /**
     * Número máximo de rondas que puede durar un combate.
     */
    private static final int MAX_ROUNDS=10;
    
// ---------------------- PERSONALIZACIÓN LABERINTO ---------------- //
    // Tamaño del laberinto
    /**
     * Número de filas del laberinto.
     */
    private static final int ROWS=10;
    /**
     * Número de columnas del laberinto.
     */
    private static final int COLS=10;
    

    // Monstruos a incluir
    /**
     * Número de monstruos a incluir en el laberinto.
     */
    private static final int NUM_MONSTERS=4;
    /**
     * Posiciones iniciales de los monstruos en el laberinto.
     */
    private static final int [][] INIT_MONSTERS= {{0,0}, {1,1}, {2,2}, {3,3}};
    
    // Bloques a incluir
    /**
     * Número de bloques a incluir en el laberinto.
     */
    private static final int NUM_BLOCKS=4;
    // Object es la clase raíz de la jerarquía de Java, por lo que todas las clases
    // existentes se pueden definir como Object, haciendo después el
    // correspondiente casting para usar el tipo de dato que queramos
    // y sea el correcto con el que hay
    /**
     * Bloques iniciales a incluir en el laberinto.
     */
    private static final Object [][] INIT_BLOCKS=
    { {Orientation.HORIZONTAL,0,0,3}, {Orientation.HORIZONTAL,3,3,5}, 
      {Orientation.VERTICAL,  5,6,2}, {Orientation.VERTICAL,  6,5,3} };

// ---------------------- PERSONALIZACIÓN LABERINTO ---------------- //
    
    /**
     * Índice del jugador actual.
     */
    private int currentPlayerIndex;
    /**
     * Jugador actual.
     */
    private Player currentPlayer;

    /**
     * Log del juego. Informa de las acciones que se han producido en el juego.
     */
    private String log;

    /**
     * Vector de jugadores que participan en el juego.
     */
    private ArrayList<Player> players;

    /**
     * Vector de monstruos presentes en el juego.
     */
    private ArrayList<Monster> monsters;

    /**
     * Laberinto en el que se desarrolla el juego.
     */
    private Labyrinth labyrinth;
    
    /**
     * Constructor de la clase Game, que inicializará los jugadores a jugar 
     * (definiendo el jugador que inicia la partida) y el estado del laberinto, 
     * que incluye su propia construcción, es decir, definir los bloques,
     * los monstruos y sus posiciones.
     * 
     * @param nplayers Numero de jugadores
     */
    public Game (int nplayers){
        // Definimos casilla de salida
        int exitRow=Dice.randomPos(ROWS);
        int exitCol=Dice.randomPos(COLS);

        // Inicializamos los vectores de jugadores y monstruos
        this.players=new ArrayList<Player>();
        this.monsters=new ArrayList<Monster>();
        
        // Creamos los nplayers y los introducimos en el vector de players
        for(int i=0; i<nplayers; i++){
            // TODO: No se si debemos hacer setPos de cada jugador aquí, si es así cuidado con exitCasilla
            this.players.add(new Player(Character.forDigit(i, 10)), Dice.randomIntelligence(), Dice.randomStrength());
        }
                
        // Definimos el jugador que empezará, es decir, el currentPlayer
        this.currentPlayerIndex=Dice.whoStarts(nplayers);
        this.currentPlayer=this.players.get(this.currentPlayerIndex);
        
        // Inicializamos la instancia de laberinto
        this.labyrinth= new Labyrinth(ROWS, COLS, exitRow, exitCol);
        // Se configura con bloques y mosntruos el laberinto
        this.configureLabyrinth();
        this.spreadPlayers(nplayers);
        // TODO: Revisar orden de llamada
        
        // Inicializamos log
        this.log="Game just started.\n";
    }
    
    /**
     * Indica si algún jugador ha ganado la partida, es decir, si ha finalizado
     * el juego
     * @return True si se finalizo el juego, false en caso contrario
     */
    public boolean finished(){
        return this.labyrinth.haveWinner();
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param preferredDirection
     * @return 
     */
    public boolean nextStep (Directions preferredDirection){
        throw new UnsupportedOperationException();  
    }
    
    /**
     * Crea una intancia de la clase GameState a partir de los miembros de 
     * la clase Game
     * 
     * @return Una intancia de GameState con los datos del estado actual del juego
     */
    public GameState getGameState(){
        // Creemos las cadenas de caracteres del estado de los
        // jugadores y de los monstruos
        String infoPlayers="";
        String infoMonsters="";
        
        // Formato jugadores
        for (int i=0; i<this.players.size(); i++){
            infoPlayers+=this.players.get(i).toString()+",\t";
        }
        
        // Formato monstruos
        for (int i=0; i<this.monsters.size(); i++){
            infoMonsters+=this.monsters.get(i).toString()+",\t";
        }
        
        // Estado general del juego
        GameState estadoGeneral = new GameState (this.labyrinth.toString(), infoPlayers,
                                infoMonsters, this.currentPlayerIndex, this.finished(), this.log);
        
        return estadoGeneral;
    }
    
    /**
     * Se crea el laberinto a partir de los monstruos y bloques definidos
     */
    private void configureLabyrinth(){
        // Inicializamos el vector de monstruos y los añadimos al laberinto
        for (int i=0; i<NUM_MONSTERS; i++){
            Monster monstruo=new Monster ("Monster "+i, Dice.randomIntelligence(), Dice.randomStrength());
            this.monsters.add(monstruo);
            // Destacar que la primera variable indica fila y la segunda columna
            this.labyrinth.addMonster(INIT_MONSTERS[i][0], INIT_MONSTERS[i][1], monstruo);
        }
        
        // Añadimos los bloques al laberinto
        for (int i=0; i<NUM_BLOCKS; i++){
            this.labyrinth.addBlock((Orientation)INIT_BLOCKS[i][0], (int)INIT_BLOCKS[i][1], 
                    (int)INIT_BLOCKS[i][2], (int)INIT_BLOCKS[i][3]);
        }
    }
    
    /**
     * Se indica que el turno pasa al siguiente jugador
     */
    private void nextPlayer(){
        // Pasamos al siguiente jugador según el índice del anterior
        this.currentPlayerIndex=(this.currentPlayerIndex+1) % this.players.size();
        this.currentPlayer=this.players.get(this.currentPlayerIndex);
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param preferredDirection
     * @return 
     */
    private Directions actualDirection(Directions preferredDirection){
        throw new UnsupportedOperationException();
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param monster
     * @return 
     */
    private GameCharacter combat(Monster monster){
        throw new UnsupportedOperationException();
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param winner 
     */
    private void manageRewart (GameCharacter winner){
        throw new UnsupportedOperationException();
    }
    
    /**
     * SIGUIENTE PRACTICA
     */
    private void manageResurrection (){
        throw new UnsupportedOperationException();
    }     
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha ganado el combate
     */
    private void logPlayerWon(){
        this.log+= "Player "+this.currentPlayerIndex+" won the fight.\n";
    }
    
    /**
     * Se actualiza el log con la información de que el mosntruo
     * ha ganado el combate al jugador actual
     */
    private void logMonsterWon(){
        this.log+= "Monster won the fight.\n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha ganado resucitado
     */
    private void logResurrected(){
        this.log+= "Player "+this.currentPlayerIndex+" resurrected.\n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha perdido su turno por estar muerto
     */
    private void logPlayerSkipTurn(){
        this.log+= "Player "+this.currentPlayerIndex+" skipped turn (is dead).\n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha intentado ejecutar alguna acción no permitida y no se ha producido ningún
     * cambio
     */
    private void logPlayerNoOrders(){
        this.log+= "Player "+this.currentPlayerIndex+" didn't follow orders, it was not possible.\n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * se ha desplazado a una casilla vacía o no ha sido posible el desplazamiento
     */
    private void logNoMonster(){
        this.log+= "Player "+this.currentPlayerIndex+" moved to an empty square or it was not possible to move.\n";
    }
    
    /**
     * Se actualiza el log con la información de que se han producido rounds
     * de max rounds en el combate actual
     * 
     * @param rounds Número de rounds que se han producido
     * @param max Número máximo de rounds que puede durar un combate
     */
    private void logRounds(int rounds, int max){
        this.log+= "Rounds: "+rounds+"/"+max+".\n";
    }
}
