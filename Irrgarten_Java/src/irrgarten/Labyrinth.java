
package irrgarten;

import java.util.ArrayList;

/**
 * Esta clase representará el tablero indicando los elementos que hay
 * en cada posición de él, es decir, los monstruos, muros y jugadores, así como
 * la casilla de salida.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente 
 */
public class Labyrinth {
    // Constantes que indicarán el estado de la casilla
    private static final char BLOCK_CHAR='X';
    private static final char EMPTY_CHAR='-';
    private static final char MONSTER_CHAR='M';
    private static final char COMBAT_CHAR='C';
    private static final char EXIT_CHAR='E';
    
    // Variables que representa el orden de la fila y la columna en un array con 
    // dos componentes
    private static final int ROW=0;
    private static final int COL=1;
    
    // Variables que indican el tamaño del laberinto
    private int nRows;
    private int nCols;
    
    // Variables que indican la casilla de salida
    private int exitRow;
    private int exitCol;
    
    // Matrices que almacenan información de los monstruos, jugadores
    // e información general del laberinto
    private Monster [][] monsters;
    private Player [][] players;
    private char [][] laberinto;
    
    /**
     * Constructor de la clase
     * @param nRows Número de filas del laberinto
     * @param nCols Número de columnas del laberinto
     * @param exitRow Fila de la casilla de salida
     * @param exitCol Columna de la casilla de salida
     */
    public Labyrinth (int nRows, int nCols, int exitRow, int exitCol){
        this.nRows=nRows;
        this.nCols=nCols;
        this.exitCol=exitCol;
        this.exitRow=exitRow;
        
        // Definimos tamaño del laberinto, players y monsters
        this.laberinto= new char [nRows][nCols];
        this.players=new Player [nRows][nCols];
        this.monsters=new Monster [nRows][nCols];
        
        // Inicializaremos la matrice laberinto con valor por defecto
        // EMPTY_CHAR
        for (int i=0; i<this.nRows; i++){
            for(int j=0; j<this.nCols; j++){
                this.laberinto[i][j]=EMPTY_CHAR;
            }
        }
        
        // Definimos casilla salida
        this.laberinto[exitRow][exitCol]=EXIT_CHAR;
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param players 
     */
    public void spreadPlayer (ArrayList<Player> players){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Indica si algún jugador llego a la casilla de salida, y por tanto
     * gano el juego
     * 
     * @return Devuelve true si alguien gano, false en caso contrario
     */
    public boolean haveWinner(){
        return (this.players[this.exitRow][this.exitCol]!=null);
    }
     
    /**
      * Muestra el estado de cada casilla del laberinto, como
      * por ejemplo si hay monstruo, combate, etc
      * 
      * @return Cadena de carácteres que indican el estado de cada casilla del laberinto
      */
    public String toString(){
        String toReturn="";
        for(int r=0; r<this.nRows; r++){
            for(int c=0; c<this.nCols; c++){
                toReturn+=this.laberinto[r][c]+" ";
            }
            toReturn+="\n";
        }
        return toReturn;
    }
    
    /**
     * Añade un monstruo recibido al laberinto, si la posición dada está vacía
     * y es correcta, es decir, es una posición válida en el laberinto
     * 
     * @param row Fila en la que se implementara el monstruo
     * @param col Columna en la que se implementara el monstruo
     * @param monster Monstruo que se implementara en el laberinto
     */
    public void addMonster (int row, int col, Monster monster){
        if (posOk(row, col) && (emptyPos(row, col))){
            Monster host= monster;
            host.setPos(row,col);
            this.monsters[row][col]=monster;
            this.laberinto[row][col]=MONSTER_CHAR;
        }
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param direction
     * @param player
     * @return 
     */
    public Monster putPlayer(Directions direction, Player player){
        throw new UnsupportedOperationException();
    }
    
    /**
     * SIGUIENT PRACTICA
     * @param orientation
     * @param startRow
     * @param startCol
     * @param length 
     */
    public void addBlock (Orientation orientation, int startRow, int startCol, int length){
        throw new UnsupportedOperationException();
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param row
     * @param col
     * @return 
     */
    public ArrayList<Directions> validMoves(int row, int col){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Comprueba si una posición es válida dentro del laberinto
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si es correcta la posición, false en caso contrario
     */
    private boolean posOk(int row, int col){
        return (0<=row && row<this.nRows && 0<=col && col<this.nCols);
    }
    
    /**
     * Comprueba si una casilla está vacía dentro del laberinto
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si está vacía la casilla, false en caso contrario
     */
    private boolean emptyPos(int row, int col){
        return (this.laberinto[row][col]==EMPTY_CHAR);
    }
    
    /**
     * Comprueba si una casilla tiene un monstruo
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si hay un monstruo en la casilla, false en caso contrario
     */
    private boolean monsterPos(int row, int col){
        return (this.laberinto[row][col]==MONSTER_CHAR);        
    }
    
    /**
     * Comprueba si una casilla es la de salida
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si la casilla es la de salida, false en caso contrario
     */
    private boolean exitPos(int row, int col){
        return (this.laberinto[row][col]==EXIT_CHAR);
    }
    
    /**
     * Comprueba si una casilla está en estado de combate
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si hay un combate en la casilla, false en caso contrario
     */
    private boolean combatPos(int row, int col){
        return (this.laberinto[row][col]==COMBAT_CHAR);
    }
    
    /**
     * Comprueba si una casilla es válida en el laberinto y hay un monstruo, está vacía
     * o es casilla de salida
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si cumple las características pedida, false en caso contrario
     */
    private boolean canStepOn(int row, int col){
        boolean comprobacion=this.monsterPos(row, col) || this.exitPos(row, col) ||
                this.emptyPos(row, col);
        return (comprobacion && this.posOk(row, col));
    }
    
    /**
     * Comprueba si una posición es válida, en caso afirmativo se establece la casilla
     * con un monstruo si estaba en estado de combate o en caso contrario se define
     * la casilla como vacía
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     */
    private void updateOldPos(int row, int col){
        if (this.posOk(row, col)){
            if(this.laberinto[row][col]==COMBAT_CHAR){
                this.laberinto[row][col]=MONSTER_CHAR;
            }
            else{
                this.laberinto[row][col]=EMPTY_CHAR;
            }
        }
    }
    
    /**
     * Calcula la nueva posición tras hacer el movimiento en una unidad hacia la
     * dirección dada
     * @param row Fila inicial
     * @param col Columna inicial
     * @param direction Dirección en la que nos desplazamos
     * @return Nueva posición tras el desplazamiento en una unidad
     */
    private ArrayList<Integer> dir2Pos(int row, int col, Directions direction){
        switch(direction){
            case LEFT:
                row--;
                break;
            case RIGHT:
                row++;
                break;
            case UP:
                col++;
                break;
            case DOWN:
                col--;
                break;
        }
        // Importante añadir los paréntesis al final de ArrayList<>
        ArrayList<Integer> toReturn= new ArrayList<>();
        toReturn.add(row);
        toReturn.add(col);
        
        return toReturn;
    }
    
    /**
     * Define una posición random en el laberinto, la cual debe estar vacía
     * @return Posición random dentro del laberinto
     */
    private ArrayList<Integer> randomEmptyPos(){
        int row=Dice.randomPos(this.nRows);
        int col=Dice.randomPos(this.nCols);
        while (!this.emptyPos(row, col)){
            row=Dice.randomPos(this.nRows);
            col=Dice.randomPos(this.nCols);
        }
        
        ArrayList<Integer> toReturn= new ArrayList<>();
        toReturn.add(row);
        toReturn.add(col);
        
        return toReturn;
    }
    
    /**
     * SIGUIENTE PRACTICA
     * @param oldRow
     * @param oldCol
     * @param row
     * @param col
     * @param player
     * @return 
     */
    public Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){
        throw new UnsupportedOperationException();
    }
}
