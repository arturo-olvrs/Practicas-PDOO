package irrgarten;

/**
 * Clase que representa los distintos personajes del juego que se encuentran
 * en el laberinto.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */

abstract class LabyrinthCharacter {
    /**
     * Nombre del personaje del laberinto.
     */
    private String name;
    /**
     * Inteligencia del personaje del laberinto.
     */
    private float intelligence;
    /**
     * Fuerza del personaje del laberinto.
     */
    private float strength;
    /**
     * Salud del personaje del laberinto.
     */
    private float health;
    /**
     * Fila en la que se encuentra el personaje del laberinto.
     */
    private int row;
    /**
     * Columna en la que se encuentra el personaje del laberinto.
     */
    private int col;
    
    /** 
     * Posición inválida para inicializar row y col en el constructor.
     */
    private static final int INVALID_POS=-1;
    
    /**
     * Constructor de la clase abstracta LabytinthCharacter
     * @param name Valor inicial para el nombre
     * @param intelligence Valor inicial para la inteligencia
     * @param strength Valor inicial para la fuerza
     * @param health Valor inicial para la salud
     */
    public LabyrinthCharacter (String name, float intelligence, float strength, float health){
        this.name=name;
        this.intelligence=intelligence;
        this.strength=strength;
        this.health=health;
        
        this.row=INVALID_POS;
        this.col=INVALID_POS;
    }
    
    /**
     * Consctructor de copia
     * @param other Objeto que copiar
     */
    public LabyrinthCharacter (LabyrinthCharacter other){
        this.name=other.name;
        this.intelligence=other.intelligence;
        this.strength=other.strength;
        this.health=other.health;
        
        this.row=other.row;
        this.col=other.col;
    }
    
    /**
     * Comprueba si está muerto el personaje
     * @return Devuelve true si está muerto y false en caso contrario
     */
    public boolean dead(){
        return (this.health<=0);
    }
    
    /**
     * Informa sobre la fila del personaje en el tablero
     * @return Fila de la pos del personaje en el tablero
     */
    public int getRow(){
        return this.row;
    }
    
    /**
     * Informa sobre la columna del personaje en el tablero
     * @return Columna de la pos del personaje en el tablero
     */
    public int getCol(){
        return this.col;
    }
    
    /**
     * Método para consultar el valor de la inteligencia del personaje
     * @return Valor de la inteligencia
     */
    protected float getIntelligence(){
        return this.intelligence;
    }
    
    /**
     * Método para consultar el valor de la fuerza del personaje
     * @return Valor de la fuerza
     */
    protected float getStrength(){
        return this.strength;
    }
    
    /**
     * Método para consultar el valor de la salud del personaje
     * @return Valor de la salud
     */
    protected float getHealth(){
        return this.health;
    }
    
    /**
     * Método para modificar el valor de la salud del personaje
     * @param health Salud nueva a actualizar
     */
    protected void setHealth(float health){
        this.health=health;
    }
    
    /**
     * Definimos la posición del personaje en el tablero
     * @param row Fila en el tablero
     * @param col Columna en el tablero
     */
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    /**
     * Muestra las características del personaje
     * @return devuelve una cadena con la información del personaje y su posición
     */
    @Override
    public String toString(){
        final String FORMAT = "%.6f";
        String toReturn=this.name+"["+"i:"+ String.format(FORMAT, this.intelligence) + ", s:"+String.format(FORMAT, this.strength);
        toReturn+=", h:"+String.format(FORMAT, this.health)+", p:("+this.row+", "+this.col+")]";
        
        return toReturn;
    }
    
    /**
     * Decrementa en uno la vida del monstruo
     */
    protected void gotWounded(){
        this.health--;
    }
    
    /**
     * Método que indica la fuerza del ataque del personaje
     * @return Devuelve el poder del ataque
     */
    public abstract float attack();
    
    /**
     * Método que se encarga de gestionar la defensa del personaje frente
     * a un ataque.
     * @param attack Intensidad del ataque recibido.
     * @return Devuelve true si el personaje ha muerto y false en caso contrario.
     */
    public abstract boolean defend (float attack);
}
