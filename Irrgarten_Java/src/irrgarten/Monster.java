package irrgarten;

/**
 * Clase que almacena información de cada monstruo
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Monster {
    
    /**
     * Número de unidades de salud inicial de los monstruos
     */
    private static final int INITIAL_HEALTH=5;
       
    /**
     * Posición inválida para inicializar row y col en el constructor
     */
    private static final int INVALID_POS=-1;
    

    /**
     * Nombre del monstruo
     */
    private final String name;
    /**
     * Inteligencia del monstruo
     */
    private final float intelligence;
    /**
     * Fuerza del monstruo
     */
    private final float strength;
    /**
     * Salud del monstruo
     */
    private float health;
    // TODO: ¿Por qué health es float?
    /**
     * Fila en la que se encuentra el monstruo
     */
    private int row;
    /**
     * Columna en la que se encuentra el monstruo
     */
    private int col;
    
    /**
     * Constructor de la clase
     * 
     * @param name Valor inicial para el nombre
     * @param intelligence Valor inicial para la inteligencia
     * @param strength Valor inicial para la fuerza
     */
    public Monster(String name, float intelligence, float strength){
        this.name=name;
        this.intelligence=intelligence;
        this.health=INITIAL_HEALTH;
        this.strength=strength;
        this.row=INVALID_POS;
        this.col=INVALID_POS;
    }
    
    /**
     * Comprueba si está muerto el mostruo
     * @return Devuelve true si está muerto y false en caso contrario
     */
    public boolean dead(){
        return (this.health<=0);
    }
    
    /**
     * Indica la fuerza de ataque del monstruo
     * @return Devuelve el valor de fuerza del ataque
     */
    public float attack(){
        return Dice.intensity(this.strength);
    }
    
    /**
     * Método que permite al monstruo defenderse de un ataque.
     * @param receivedAttack Intensidad del ataque recibido.
     * @return Devuelve true si el monstruo ha muerto y false en caso contrario.
     */
    public boolean defend(float receivedAttack){

        boolean isDead = this.dead();
        
        if (!isDead){
            if (Dice.intensity(this.intelligence) < receivedAttack){
                // Si está vivo y el ataque le vence, se contabiliza
                this.gotWounded();
                isDead = this.dead();
            }
        }

        return isDead;
    }
    
    /**
     * Se actualiza la posición del monstruo
     * @param row valor para la fila
     * @param col valor para la columna
     */
    public void setPos(int row, int col){
        this.row=row;
        this.col=col;
    }
    
    /**
     * Muestra las características del monstruo
     * @return devuelve una cadena con la información del monstruo y su posición
     */
    @Override
    public String toString(){
        final String FORMAT = "%.6f";
        return "M["+this.name+", i:"+ String.format(FORMAT, intelligence) + ", s:"+String.format(FORMAT, strength)+", h:"
                +String.format(FORMAT, health)+", p:("+this.row+", "+this.col+")]";
    }
    
    /**
     * Decrementa en uno la vida del monstruo
     */
    private void gotWounded(){
        this.health--;
    }
}
