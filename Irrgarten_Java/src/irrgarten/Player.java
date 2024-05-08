package irrgarten;

import java.util.ArrayList;

/**
 * Clase que almacena información de cada jugador
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Player extends LabyrinthCharacter {
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
     * Número identificador del jugador.
     */
    private char number;
    /**
     * Número de golpes consecutivos que ha recibido el jugador.
     */
    private int consecutiveHits;
    
    /**
     * ArrayList de armas que lleva el jugador.
     */
    private ArrayList<Weapon> weapons;        // Se pone final porque no se asigna a otra cosa.
    /**
     * ArrayList de escudos que lleva el jugador.
     */
    private ArrayList<Shield> shields;        // Se pone final porque no se asigna a otra cosa.
    
    /**
     * Baraja de cartas de tipo Arma
     */
    private WeaponCardDeck weaponCardDeck;
    
    /**
     * Baraja de cartas de tipo Escudo
     */
    private ShieldCardDeck shieldCardDeck;
    
    /**
     * Constructor de la clase Player
     * @param number número identificador del jugador
     * @param intelligence inteligencia del jugador
     * @param strength fuerza del jugador
     */
    public Player (char number, float intelligence, float strength){
        super("Player "+number, intelligence, strength, INTIAL_HEALTH);
        this.number=number;
        this.consecutiveHits=0;

        // Hay que inicializar los ArrayList
        this.weapons= new ArrayList<>();
        this.shields= new ArrayList<>();
        
        // Hay que inicializar las barajas
        this.weaponCardDeck= new WeaponCardDeck();
        this.shieldCardDeck= new ShieldCardDeck();
    }
    
    /**
     * Consctructor de copia
     * @param other Objeto que copiar // TODO: preguntar a Lastra
     */
    public Player (Player other){
        super(other); // Se usa el constructor de copia de LabyrinthCharacter
        this.number=other.number;
        this.consecutiveHits=other.consecutiveHits;
        
        // Hay que inicializar los ArrayList, pero vacíos
        weapons= new ArrayList<>();
        shields= new ArrayList<>();
    }
    
    /**
     * Resurreción de un jugador, indicando los valores que debe tener
     * al resucitar
     */
    public void resurrect(){
        this.setHealth(INTIAL_HEALTH);
        resetHits();
        // Inicializamos los vectores como vacío
        this.weapons.clear();
        this.shields.clear();
    }

    /**
     * Informa sobre el número del jugador
     * @return Número del jugador
     */
    public char getNumber(){
        return this.number;
    }
    
    /**
     * Método que informa sobre la dirección en la que se va a mover el jugador
     * 
     * @param direction  Dirección en la que se quiere mover el jugador
     * @param validMoves  Lista de movimientos válidos
     * @return  Devuelve la dirección en la que se moverá el jugador (si es válida)
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
        }
        // Si size==0, se devuelve la dirección deseada y ya se comprobará en putPlayer2D que no se puede mover

        return toReturn;
    }
    
    /**
     * Calcula ataque total del jugador, teniendo en cuenta su fuerza y el poder
     * de sus armas
     * @return Devuelve la suma de su fuerza y el poder de las armas
     */
    @Override
    public float attack(){
        return (this.getStrength()+this.sumWeapons());
    }
    
    /**
     * Método que permite al jugador defenderse de un ataque.
     * Relega en manageHit(float) la gestión de la defensa.
     * @param receivedAttack Intensidad del ataque recibido
     * @return  Devuelve true si el jugador ha muerto y false en caso contrario.
     * @see #manageHit(float)
     */
    @Override
    public boolean defend(float receivedAttack){
        return this.manageHit(receivedAttack);
    }
    
    /**
     * Método que actualiza las armas, escudos y salud del jugador tras recibir una recompensa.     * 
     */
    public void receiveReward(){
        // Weapons rewards
        int wReward = Dice.weaponsReward();
        for (int i=0; i<wReward; i++)
            this.receiveWeapon(this.newWeapon());
        
        // Shields rewards
        int sReward = Dice.shieldsReward();
        for (int i=0; i<sReward; i++)
            this.receiveShield(this.newShield());

        // Health reward
        this.setHealth(this.getHealth()+Dice.healthReward());
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del jugador.
     * @return Representación en forma de cadena de caracteres del estado interno del jugador.
     */
    @Override // TODO: comprobar que está igual en ruby
    public String toString(){
        String toReturn=super.toString();
        toReturn+=", ch:"+this.consecutiveHits+", ";
        
        // Bucles para mostrar con un formato determinado el array de
        // armas y escudos del jugador
        String toWeapons="[";
        int tamWeapons=this.weapons.size();
        for(int i=0; i<tamWeapons-1; i++){
            toWeapons+=this.weapons.get(i).toString()+", ";
        }
        if (tamWeapons>0)
            toWeapons+=this.weapons.get(tamWeapons-1);
        toWeapons+="]";
        
        String toShields="[";
        int tamShields=this.shields.size();
        for(int i=0; i<tamShields-1; i++){
            toShields+=this.shields.get(i).toString()+", ";
        }
        if (tamShields>0)
            toShields+=this.shields.get(tamShields-1);
        toShields+="]";
        
        // Definimos el formato final para el toString
        toReturn+="w:" + toWeapons+", sh:"+toShields+" ]";
        
        return toReturn;
    }

    /**
     * Método que actualiza las armas del jugador tras recibir un arma.
     * En primer lugar, se eliminan las armas que han de ser descartadas.
     * Después, se añade el arma al jugador si cabe.
     * @param w Arma nueva a añadir
     */
    private void receiveWeapon(Weapon w){
        // Iteramos sobre las armas del jugador, viendo si tienen que ser descartadas
        for (int i=0; i<weapons.size(); i++){
            if (weapons.get(i).discard()){
                weapons.remove(i);
                i--;
            }
        }

        // Añadimos el escudo al jugador si cabe
        if (weapons.size() < MAX_WEAPONS)
            weapons.add(w);
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
        for (int i=0; i<shields.size(); i++){
            if (shields.get(i).discard()){
                shields.remove(i);
                i--;
            }
        }

        // Añadimos el escudo al jugador si cabe
        if (shields.size() < MAX_SHIELDS)
            shields.add(s);
    }
    
    // TODO: mirar si lo que quiere son estos cambios en newWeapon y newShield
    /**
     * Genera una nueva instancia de la clase Weapon, con los parámetros
     * que indica el dado
     * @return Devuelve el arma creada de forma aleatoria con el dado
     */
    private Weapon newWeapon(){
        return this.weaponCardDeck.nextCard();
    }
    
    /**
     * Genera una nueva instancia de la clase Shield, con los parámetros
     * que indica el dado
     * @return Devuelve el escudo creado de forma aleatoria con el dado
     */
    private Shield newShield(){
        return this.shieldCardDeck.nextCard();
    }
    
    /**
     * Indica la suma total de los poderes de todas las armas que tiene
     * el jugador
     * @return Devuelve la suma de poderes de todas las armas
     */
    protected float sumWeapons(){
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
    protected float sumShields(){
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
    protected float defensiveEnergy(){
        return (this.getIntelligence()+this.sumShields());
    }
    
    /**
     * Método que gestiona el ataque recibido por el jugador.
     * 
     * @param receivedAttack Intensidad del ataque recibido
     * @return Devuelve true si el jugador ha muerto y false en caso contrario.
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
        boolean lose = (this.consecutiveHits==Player.HITS2LOSE) || this.dead();

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
     * Incrementa en una unidad el contador de impactos consecutivos
     */
    private void incConsecutiveHits(){
        this.consecutiveHits++;
    }
}
