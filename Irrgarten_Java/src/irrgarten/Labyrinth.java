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
    /**
     * Carácter que indica que hay un obstáculo en la casilla.
     */
    private static final char BLOCK_CHAR='X';
    /**
     * Carácter que indica que hay un jugador en la casilla.
     */
    private static final char EMPTY_CHAR='-';
    /**
     * Carácter que indica que hay un monstruo en la casilla.
     */
    private static final char MONSTER_CHAR='M';
    /**
     * Carácter que indica que hay un combate en la casilla.
     */
    private static final char COMBAT_CHAR='C';
    /**
     * Carácter que indica que hay un jugador en la casilla.
     */
    private static final char EXIT_CHAR='E';
    
    // Variables que representa el orden de la fila y la columna en un array con 
    // dos componentes
    /**
     * Índice de la fila en un array de dos componentes.
     */
    private static final int ROW=0;
    /**
     * Índice de la columna en un array de dos componentes.
     */
    private static final int COL=1;

    /**
     * Índice que indica una posición inválida.
     */
    private static final int INVALID_POS = -1;
    
    // Variables que indican el tamaño del laberinto
    /**
     * Número de filas del laberinto.
     */
    private int nRows;
    /**
     * Número de columnas del laberinto.
     */
    private int nCols;
    
    // Variables que indican la casilla de salida
    /**
     * Fila de la casilla de salida.
     */
    private int exitRow;
    /**
     * Columna de la casilla de salida.
     */
    private int exitCol;
    
    // Matrices que almacenan información de los monstruos, jugadores
    // e información general del laberinto
    /**
     * Matriz que almacena los monstruos en el laberinto.
     */
    private Monster [][] monsters;
    /**
     * Matriz que almacena los jugadores en el laberinto.
     */
    private Player [][] players;
    /**
     * Matriz que almacena el estado de cada casilla del laberinto.
     * @see EMPTY_CHAR
     * @see BLOCK_CHAR
     * @see MONSTER_CHAR
     * @see COMBAT_CHAR
     * @see EXIT_CHAR
     */
    private char [][] labyrinth;
    
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
        this.labyrinth  = new char   [nRows][nCols];
        this.players    = new Player [nRows][nCols];
        this.monsters   = new Monster[nRows][nCols];
        
        // Inicializaremos la matrice laberinto con valor por defecto
        // EMPTY_CHAR
        for (int i=0; i<this.nRows; i++){
            for(int j=0; j<this.nCols; j++){
                this.labyrinth[i][j]=EMPTY_CHAR;
            }
        }
        
        // Definimos casilla salida
        this.labyrinth[exitRow][exitCol]=EXIT_CHAR;
    }
    
    /**
     * Distribuye una lista de jugadores por el laberinto.
     * @param players ArrayList con los jugadores a repartir por el Laberinto
     */
    public void spreadPlayers (ArrayList<Player> players){

        // Itera sobre todos los jugadores. Busca una casilla libre y luego pone el jugador
        for (int i=0; i<players.size(); i++){
            
            int [] pos = randomEmptyPos();
            putPlayer2D(INVALID_POS, INVALID_POS, pos[ROW], pos[COL], players.get(i));
        }
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
    @Override
    public String toString(){

        // Cálculo del número de caracteres que debe ocupar cada parte
        int filSize = Integer.toString(this.nRows-1).length();
        int colSize = Integer.toString(this.nCols-1).length();
        int nPlayersSize = Integer.toString(this.players.length-1).length();
        
        // Cálculo del tamaño máximo
        int maxSize = Math.max(Math.max(filSize, colSize), nPlayersSize);
        final String FORMAT = "%"+maxSize+"s";

        // Cadena a devolver
        String toReturn="";

        // Índices en cada columna
        toReturn+=" " + String.format(FORMAT, " ");
        for (int i=0; i<this.nCols; i++){
            toReturn+=String.format(FORMAT, i)+" ";
        }
        toReturn+="\n";


        for(int r=0; r<this.nRows; r++){
            toReturn+=String.format(FORMAT, r)+" "; // Índices en cada fila
            for(int c=0; c<this.nCols; c++){
                toReturn+=String.format(FORMAT, this.labyrinth[r][c])+" ";
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
        if (posOK(row, col) && (emptyPos(row, col))){
            monster.setPos(row,col);
            this.monsters[row][col]=monster;
            this.labyrinth[row][col]=MONSTER_CHAR;
        }
    }
    
    /**
     * Método que mueve un jugador en el laberinto en una dirección.
     * Informa sobre si se encuentra con un monstruo.
     * 
     * @param direction Dirección en la que ha de moverse el jugador. Si no es válida, no se mueve.
     * @param player Jugador a desplazar
     * @return El monstruo con el que se ha encontrado. Devuelve *null* si no hay monstro
     */
    public Monster putPlayer(Directions direction, Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();

        int[] newPos = dir2Pos(oldRow, oldCol, direction);

        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], player);

        return monster;
    }
    
    /**
     * Método que añade un bloque al laberinto.
     *
     * @param orientation Orientación del bloque, vertical u horizontal
     * @param startRow Fila de inicio del bloque
     * @param startCol Columna de inicio del bloque
     * @param length Longitud del bloque
     */
    public void addBlock (Orientation orientation, int startRow, int startCol, int length){
        
        int incRow=0, incCol=0; // Incremento de la fila y columna
        if (orientation == Orientation.VERTICAL){
            // Si la orientación es vertical, incrementamos la fila
            incRow++;
        }
        else{
            // Si la orientación es horizontal, incrementamos la columna
            incCol++;
        }

        // (row, col) posición actual
        int row=startRow;
        int col=startCol;

        // Mientras la posición sea correcta, la casilla esté vacía y la longitud sea mayor que 0
        // Se actualiza la casilla en cuestión
        while (posOK(row, col) && emptyPos(row, col) && length>0){
            this.labyrinth[row][col]=BLOCK_CHAR;
            row+=incRow;
            col+=incCol;
            length--;
        }
    }
    
    /**
     * Calcula las direcciones hacia las que se puede mover un jugador desde una
     * posición dada.
     * @param row Fila desde la que se quiere ver hacia donde se puede mover
     * @param col Columna desde la que se quiere ver hacia donde se puede mover
     * @return Direcciones en las que se puede mover desde (row, col)
     */
    public ArrayList<Directions> validMoves(int row, int col){
        
        ArrayList<Directions> output = new ArrayList<>();

        if (canStepOn(row+1, col))
            output.add(Directions.DOWN);
        if (canStepOn(row-1, col))
            output.add(Directions.UP);
        if (canStepOn(row, col+1))
            output.add(Directions.RIGHT);
        if (canStepOn(row, col-1))
            output.add(Directions.LEFT);

        return output;
    }
    
    /**
     * Método para actualizar un jugador del laberinto por un fuzzyplayer en su 
     * posición.
     * @param este Objeto de FuzzyPlayer que sustituirá al objeto Player que haya
     * en su posición
     */
    public void updatePos(FuzzyPlayer este){
        int row=este.getRow();
        int col=este.getCol();
        this.players[row][col]=este;
    }
    
    /**
     * Comprueba si una posición es válida dentro del laberinto
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si es correcta la posición, false en caso contrario
     */
    private boolean posOK(int row, int col){
        return (0<=row && row<this.nRows && 0<=col && col<this.nCols);
    }
    
    /**
     * Comprueba si una casilla está vacía dentro del laberinto
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si está vacía la casilla, false en caso contrario
     */
    private boolean emptyPos(int row, int col){
        return (this.labyrinth[row][col]==EMPTY_CHAR);
    }
    
    /**
     * Comprueba si una casilla tiene un monstruo
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si hay un monstruo en la casilla, false en caso contrario
     */
    private boolean monsterPos(int row, int col){
        return (this.labyrinth[row][col]==MONSTER_CHAR);        
    }
    
    /**
     * Comprueba si una casilla es la de salida
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si la casilla es la de salida, false en caso contrario
     */
    private boolean exitPos(int row, int col){
        return (this.labyrinth[row][col]==EXIT_CHAR);
    }
    
    /**
     * Comprueba si una casilla está en estado de combate
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si hay un combate en la casilla, false en caso contrario
     */
    private boolean combatPos(int row, int col){
        return (this.labyrinth[row][col]==COMBAT_CHAR);
    }
    
    /**
     * Comprueba si una casilla es válida en el laberinto y hay un monstruo, está vacía
     * o es casilla de salida
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     * @return True si cumple las características pedida, false en caso contrario
     */
    private boolean canStepOn(int row, int col){
        boolean comprobacion=this.posOK(row, col);
        comprobacion = comprobacion && (this.monsterPos(row, col) || this.exitPos(row, col) ||
                this.emptyPos(row, col));

        return comprobacion;
    }
    
    /**
     * Comprueba si una posición es válida, en caso afirmativo se establece la casilla
     * con un monstruo si estaba en estado de combate o en caso contrario se define
     * la casilla como vacía
     * @param row Fila a comprobar
     * @param col Columna a comprobar
     */
    private void updateOldPos(int row, int col){
        if (this.posOK(row, col)){
            if(combatPos(row, col)){
                this.labyrinth[row][col]=MONSTER_CHAR;
            }
            else{
                this.labyrinth[row][col]=EMPTY_CHAR;
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
    private int[] dir2Pos(int row, int col, Directions direction){
        int[] toReturn= new int[2];
        toReturn[ROW]=row;
        toReturn[COL]=col;
        
        switch(direction){
            case LEFT:
                toReturn[COL]--;
                break;
            case RIGHT:
                toReturn[COL]++;
                break;
            case UP:
                toReturn[ROW]--;
                break;
            case DOWN:
                toReturn[ROW]++;
                break;
        }
        
        return toReturn;
    }
    
    /**
     * Define una posición random en el laberinto, la cual debe estar vacía
     * @return Posición random dentro del laberinto
     */
    private int[] randomEmptyPos(){
        int row, col;
        do{
            row=Dice.randomPos(this.nRows);
            col=Dice.randomPos(this.nCols);
        }while (!this.emptyPos(row, col));
        
        int[] toReturn= new int[2]; // = {row, col};
        toReturn[ROW]=row;
        toReturn[COL]=col;
        
        return toReturn;
    }
    
    /**
     * Método que mueve un jugador en el laberinto en una dirección.
     * Va desde (oldRow, oldCol) a (row, col).
     *  
     * @param oldRow  Posición antigua del jugador (fila)
     * @param oldCol  Posición antigua del jugador (columna)
     * @param row  Nueva posición del jugador (fila)
     * @param col  Nueva posición del jugador (columna)
     * @param player Jugador a mover
     * @return  Monstruo que hay en la casilla a la que se llega.
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, Player player){

        Monster output = null;  // Monstruo que hay en la casilla a la que se llega

        if (canStepOn(row, col)){

            if (posOK(oldRow, oldCol)){

                // Si el jugador estaba en la casilla, se actualiza la casilla (liberándola en players)
                // y se actualiza la posición antigua.
                if (players[oldRow][oldCol]==player){
                    
                    updateOldPos(oldRow, oldCol);
                    this.players[oldRow][oldCol]=null;                    
                }
            }


            // Si llego a una casilla con monstruo, se devuelve el monstruo y se actualiza a COMBAT_CHAR
            if (monsterPos(row, col)){
                this.labyrinth[row][col]=COMBAT_CHAR;
                output = this.monsters[row][col];
            }
            else{
                // No hay monstruo. Simplemente reflejo el jugador en la casilla
                this.labyrinth[row][col]=player.getNumber();
            }

            // Actualizo la posición del jugador
            this.players[row][col]=player;
            player.setPos(row, col);
            
        } // canStepOn(row, col)

        return output;
    }
}
