package irrgarten;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Clase que almacena información de cada jugador
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Player {
    /**
     * Número máximo de armas que puede llevar un jugador.
     */
    private static final int MAX_WEAPONS=2;
    
    /**
     * Número máximo de escudos que puede llevar un jugador.
     */
    private static final int MAX_SHIELDS=3;
    
    /**
     * Número de unidades de salud inicial de los jugadores.
     */
    private static final int INTIAL_HEALTH=10;
   
    /**
     * Número de golpes que puede recibir un jugador antes de morir.
     */
    private static final int HITS2LOSE=3;
    
    /**
     * Posición inválida para inicializar row y col en el constructor.
     */
    private static final int INVALID_POS=-1;
    


    /**
     * Nombre del jugador.
     */
    private String name;
    /**
     * Número identificador del jugador.
     */
    private char number;
    /**
     * Inteligencia del jugador.
     */
    private float intelligence;
    /**
     * Fuerza del jugador.
     */
    private float strength;
    /**
     * Salud del jugador.
     */
    private float health;
    /**
     * Fila en la que se encuentra el jugador.
     */
    private int row;
    /**
     * Columna en la que se encuentra el jugador.
     */
    private int col;
    /**
     * Número de golpes consecutivos que ha recibido el jugador.
     */
    private int consecutiveHits;
    
    /**
     * ArrayList de armas que lleva el jugador.
     */
    private ArrayList<Weapon> weapons;
    /**
     * ArrayList de escudos que lleva el jugador.
     */
    private ArrayList<Shield> shields;
    
    /**
     * Constructor de la clase Player
     * @param number número identificador del jugador
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
     * Método que informa sobre la dirección en la que se va a mover el jugador
     * 
     * @param direction  Dirección en la que se quiere mover el jugador
     * @param validMoves  Lista de movimientos válidos
     * @return  Devuelve la dirección en la que se mueve el jugador
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        int size = validMoves.size();
        boolean contained = validMoves.contains(direction);

        Directions toReturn;

        if ((size > 0) && !contained){ // El jugador se puede mover, pero no en la dirección deseada
            toReturn = validMoves.get(0);
        }
        else{
            toReturn = direction;
            // TODO: Si size==0 ?? No hay movimientos válidos, a dónde me muevo?
        }

        return toReturn;
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
     * Método que permite al jugador defenderse de un ataque.
     * Relega en #manageHit(float) la gestión de la defensa.
     * @param receivedAttack Intensidad del ataque recibido
     * @return  // TODO: Devuelve true o false si se ha defendido o no ????
     * @see #manageHit(float)
     */
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    /**
     * Método que actualiza las armas, escudos y salud del jugador tras recibir una recompensa.     * 
     */
    public void receiveReward(){
        // Weapons rewards
        int wRepard = Dice.weaponsReward();
        for (int i=0; i<wRepard; i++)
            this.weapons.add(this.newWeapon());
        
        // Shields rewards
        int sReward = Dice.shieldsReward();
        for (int i=0; i<sReward; i++)
            this.shields.add(this.newShield());

        // Health reward
        this.health+=Dice.healthReward();
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
     * Método que actualiza las armas del jugador tras recibir un arma.
     * En primer lugar, se eliminan las armas que han de ser descartadas.
     * Después, se añade el arma al jugador si cabe.
     */
    private void receiveWeapon(Weapon w){
        // Iteramos sobre las armas del jugador, viendo si tienen que ser descartadas
        Iterator<Weapon> iterator = weapons.iterator();
        while (iterator.hasNext()) {
            Weapon wi = iterator.next();
            if (wi.discard())
                iterator.remove();
        }

        // TODO: Preguntar si se itera así

        // Añadimos el escudo al jugador si cabe
        if (weapons.size() < MAX_WEAPONS)
            weapons.add(s);
    }
    
    /**
     * Método que actualiza los escudos del jugador tras recibir un escudo.
     * En primer lugar, se eliminan los escudos que han de ser descartados.
     * Después, se añade el escudo al jugador si cabe.
     * 
     * @param s  Escudo que recibe el jugador
     */
    private void receiveShield(Shield s){

        // Iteramos sobre los escudos del jugador, viendo si tienen que ser descartados
        Iterator<Shield> iterator = shields.iterator();
        while (iterator.hasNext()) {
            Shield si = iterator.next();
            if (si.discard())
                iterator.remove();
        }

        // TODO: Preguntar si se itera así

        // Añadimos el escudo al jugador si cabe
        if (shields.size() < MAX_SHIELDS)
            shields.add(s);
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
     * Método que gestiona el ataque recibido por el jugador.
     * 
     * @param receivedAttack Intensidad del ataque recibido
     * @return // TODO: Devuelve true o false si se ha defendido o no ????
     */
    private boolean manageHit(float receivedAttack){

        // Se contabiliza el ataque recibido
        if (this.defensiveEnergy() < receivedAttack){
            // El jugador no se ha defendido
            this.gotWounded();
            this.incConsecutiveHits();
        }
        else{
            // El jugador se ha defendido
            this.resetHits();
        }


        // Se comprueba si el jugador ha perdido
        boolean lose = (this.consecutiveHits==this.HITS2LOSE) || this.dead();

        if (lose)   // Si ha perdido se resetea el contador de golpes consecutivos
            resetHits();

        return lose;
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
