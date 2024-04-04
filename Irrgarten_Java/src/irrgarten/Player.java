package irrgarten;

import java.util.ArrayList;

/**
 * Clase que almacena información de cada jugador
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Player {
    // Cantidad máxima de armas que puede llevar
    private static final int MAX_WEAPONS=2;
    
    // Cantidad máxima de escudos que puede llevar
    private static final int MAX_SHIELDS=3;
    
    // Número de unidades de salud inicial
    private static final int INTIAL_HEALTH=10;
   
    // Número de golpes que puede recibir un jugador antes de morir
    private static final int HITS2LOSE=3;
    
    // Posición inválida para inicializar row y col en el constructor
    private static final int INVALID_POS=-1;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private float health;
    private int row;
    private int col;
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons; // ArrayList de armas
    private ArrayList<Shield> shields; // ArrayList de escudos
    
    /**
     * Constructor de la clase Player
     * @param number número identificador
     * @param intelligence inteligencia del jugador
     * @param strength fuerza del jugador
     */
    public Player (char number, float intelligence, float strength){
        this.number=number;
        this.name= "Player "+number;
        this.intelligence=intelligence;
        this.strength=strength;
        this.consecutiveHits=0;
        this.health=INTIAL_HEALTH;

        // Hay que inicializar los ArrayList
        weapons= new ArrayList<Weapon>();
        shields= new ArrayList<Shield>();

        this.row=INVALID_POS;
        this.col=INVALID_POS;
    }
    
    /**
     * Resurreción de un jugador, indicando los valores que debe tener
     * al resucitar
     */
    public void resurrect(){
        this.health=INTIAL_HEALTH;
        resetHits();
        // Inicializamos los vectores como vacío
        this.weapons.clear();
        this.shields.clear();
    }
    
    /**
     * Informa sobre la fila del jugador en el tablero
     * @return Fila de la pos del jugador en el tablero
     */
    public int getRow(){
        return this.row;
    }
    
    /**
     * Informa sobre la columna del jugador en el tablero
     * @return Columna de la pos del jugador en el tablero
     */
    public int getCol(){
        return this.col;
    }

    /**
     * Informa sobre el número del jugador
     * @return Número del jugador
     */
    public char getNumber(){
        return this.number;
    }
    
    /**
     * Definimos la posición del jugador en el tablero
     * @param row Fila en el tablero
     * @param col Columna en el tablero
     */
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    /**
     * Comprueba si el jugador está muerto
     * @return Devuelve true si está vivo, en caso contrario devuelve false
     */
    public boolean dead(){
        return (this.health<=0);
    }
    
    /**
     * No sabemos que hace todavía
     * @param direction
     * @param validMoves
     * @return 
     */
    public Directions move(Directions direction, Directions [] validMoves){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Calcula ataque total del jugador, teniendo en cuenta su fuerza y el poder
     * de sus armas
     * @return Devuelve la suma de su fuerza y el poder de las armas
     */
    public float attack(){
        return (this.strength+this.sumWeapons());
    }
    
    /**
     * No sabmos que hace todavía
     * @param receivedAttack
     * @return 
     */
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    /**
     * No sabmos que hace todavía
     */
    public void receiveReward(){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del jugador.
     * @return Representación en forma de cadena de caracteres del estado interno del jugador.
     */
    public String toString(){
        String toReturn= this.name+"[i:"+this.intelligence+", s:"+this.strength;
        toReturn+=", h:"+this.health+", ";
        
        // Bucles para mostrar con un formato determinado el array de
        // armas y escudos del jugador
        String toWeapons="[";
        int tamWeapons=this.weapons.size();
        for(int i=0; i<tamWeapons-1; i++){
            toWeapons+=this.weapons.get(i).toString()+", ";
        }
        toWeapons+=this.weapons.get(tamWeapons-1)+"]";
        
        String toShields="[";
        int tamShields=this.shields.size();
        for(int i=0; i<tamShields-1; i++){
            toWeapons+=this.shields.get(i).toString()+", ";
        }
        toWeapons+=this.shields.get(tamShields-1)+"]";
        
        // Definimos el formato final para el toString
        toReturn+="w: " + toWeapons+", sh:"+toShields+", p:("+this.row+", "+this.col+")";
        toReturn+=", ch:"+this.consecutiveHits+"]";
        
        return toReturn;
    }

    /**
     * No sabmos que hace todavía
     * @param w 
     */
    private void receiveWeapon(Weapon w){
        throw new UnsupportedOperationException();
    }
    
    /**
     * No sabmos que hace todavía
     * @param s 
     */
    private void receiveShield(Shield s){
        throw new UnsupportedOperationException();
    }
    
    /**
     * Genera una nueva instancia de la clase Weapon, con los parámetros
     * que indica el dado
     * @return Devuelve el arma creada de forma aleatoria con el dado
     */
    private Weapon newWeapon(){
        return new Weapon(Dice.weaponPower(), Dice.usesLeft());
    }
    
    /**
     * Genera una nueva instancia de la clase Shield, con los parámetros
     * que indica el dado
     * @return Devuelve el escudo creado de forma aleatoria con el dado
     */
    private Shield newShield(){
        return new Shield(Dice.shieldPower(), Dice.usesLeft());
    }
    
    /**
     * Indica la suma total de los poderes de todas las armas que tiene
     * el jugador
     * @return Devuelve la suma de poderes de todas las armas
     */
    private float sumWeapons(){
       int attack=0;
       for (int i=0; i<weapons.size(); i++){
           attack+=weapons.get(i).attack();
       } 
       return attack;
    }
    
    /**
     * Indica la suma total de protección de todos los escudos que tiene
     * el jugador
     * @return Devuelve la suma de protección de todas los escudos
     */
    private float sumShields(){
       int protect=0;
       for (int i=0; i<shields.size(); i++){
           protect+=shields.get(i).protect();
       } 
       return protect;
    }
    
    /**
     * Calcula defensa total del jugador, teniendo en cuenta su inteligencia y 
     * la protección de sus escudos
     * @return Devuelve la suma de su inteligencia y la protección de los escudos
     */
    private float defensiveEnergy(){
        return (this.intelligence+this.sumShields());
    }
    
    /**
     * No sabmos que hace todavía
     * @param receivedAttack
     * @return 
     */
    private boolean manageHit(float receivedAttack){
        throw new UnsupportedOperationException();
    }

    /**
     * Reinicia el contador de impactos consecutivos a 0
     */
    private void resetHits(){
        this.consecutiveHits=0;
    }
    
    /**
     * Decrementa en una unidad la salud del jugador
     */
    private void gotWounded(){
        this.health--;
    }
    
    /**
     * Incrementa en una unidad el contador de impactos consecutivos
     */
    private void incConsecutiveHits(){
        this.consecutiveHits++;
    }
}
