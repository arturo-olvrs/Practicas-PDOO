
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author Joaquín Avilés de la Fuente
 * @author Arturo Olivares Martos
 */
public class Game {
    // Máximo de rondas de un combate
    private static final int MAX_ROUNDS=10;
    
// ---------------------- PERSONALIZACIÓN LABERINTO ---------------- //
    // Tamaño del laberinto
    private static final int ROWS=10;
    private static final int COLS=10;
    
    // Monstruos a incluirç
    private static final int NUM_MONSTERS=4;
    private static final int [][] INIT_MONSTERS= {{0,0}, {1,1}, {2,2}, {3,3}};
    
    // Bloques a incluir
    private static final int NUM_BLOCKS=4;
    // Object es la clase raíz de la jerarquía de Java, por lo que todas las clases
    // existentes se pueden definir como Object, haciendo después el
    // correspondiente casting para usar el tipo de dato que queramos
    // y sea el correcto con el que hay
    private static final Object [][] INIT_BLOCKS=
    { {Orientation.HORIZONTAL,0,0,3}, {Orientation.HORIZONTAL,3,3,5}, 
      {Orientation.VERTICAL,5,6,2}, {Orientation.VERTICAL,6,5,3} };

// ---------------------- PERSONALIZACIÓN LABERINTO ---------------- //
    
    private int currentPlayerIndex;
    private Player currentPlayer; // TODO: porque se añade currentPlayer? no lo pone que hay que añadirlo
    private String log;
    private ArrayList<Player> players;
    private ArrayList<Monster> monsters;
    private Labyrinth laberinto;
    
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
        
        // Inicializamos la instancia de laberinto
        this.laberinto= new Labyrinth(ROWS, COLS, exitRow, exitCol);
        // Se configura con bloques y mosntruos el laberinto
        this.configureLabyrinth();
        
        // Creamos los nplayers y los introducimos en el vector de players
        for(int i=0; i<nplayers; i++){
            // TODO: No se si debemos hacer setPos de cada jugador aquí, si es así cuidado con exitCasilla
            this.players.add(new Player((char)i, Dice.randomIntelligence(), Dice.randomStrength()));
        }
                
        // Definimos el jugador que empezará, es decir, el currentPlayer
        this.currentPlayerIndex=Dice.whoStarts(nplayers);
        this.currentPlayer=this.players.get(this.currentPlayerIndex);
        
        // Inicializamos log
        this.log="Game just started. \n";
    }
    
    /**
     * Indica si algún jugador ha ganado la partida, es decir, si ha finalizado
     * el juego
     * @return True si se finalizo el juego, false en caso contrario
     */
    public boolean finished(){
        return this.laberinto.haveWinner();
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
            infoPlayers=this.players.get(i).toString()+" ";
        }
        
        // Formato mosntruos
        for (int i=0; i<this.monsters.size(); i++){
            infoMonsters=this.monsters.get(i).toString()+" ";
        }
        
        // Estado general del juego
        GameState estadoGeneral= new GameState (this.laberinto.toString(), infoPlayers,
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
            this.laberinto.addMonster(INIT_MONSTERS[i][0], INIT_MONSTERS[i][1], monstruo);
        }
        
        // Añadimos los bloques al laberinto
        for (int i=0; i<NUM_BLOCKS; i++){
            this.laberinto.addBlock((Orientation)INIT_BLOCKS[i][0], (int)INIT_BLOCKS[i][1], 
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
        this.log+= "Player "+this.currentPlayerIndex+" won the fight \n";
    }
    
    /**
     * Se actualiza el log con la información de que el mosntruo
     * ha ganado el combate al jugador actual
     */
    private void logMonsterWon(){
        this.log+= "Monster won the fight \n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha ganado resucitado
     */
    private void logResurrected(){
        this.log+= "Player "+this.currentPlayerIndex+" was resurrected \n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha perdido su turno por estar muerto
     */
    private void logPlayerSkipTurn(){
        this.log+= "Player "+this.currentPlayerIndex+" lost his turn because he is dead \n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * ha intentado ejecutar alguna acción no permitida y no se ha producido ningún
     * cambio
     */
    private void logPlayerNoOrders(){
        this.log+= "Player "+this.currentPlayerIndex+" that's not possible \n";
    }
    
    /**
     * Se actualiza el log con la información de que el jugador actual
     * se ha desplazado a una casilla vacía o no ha sido posible el desplazamiento
     */
    private void logNoMonster(){
        this.log+= "Player "+this.currentPlayerIndex+" moved to an empty square or the move was not possible \n";
    }
    
    /**
     * Se actualiza el log con la información de que se han producido rounds
     * de max rounds en el combate actual
     */
    private void logRounds(int rounds, int max){
        this.log+= "Round "+rounds+" of maximmum "+max+" rounds \n";
        // TODO: para que se pasa max si tenemos la constante MAX_ROUNDS 
    }
}
