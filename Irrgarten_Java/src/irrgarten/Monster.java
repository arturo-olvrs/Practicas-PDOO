package irrgarten;

/**
 * Clase que almacena información de cada monstruo
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Monster extends LabyrinthCharacter {
    
    /**
     * Número de unidades de salud inicial de los monstruos
     */
    private static final int INITIAL_HEALTH=5;
       
   /**
     * Constructor de la clase
     * 
     * @param name Valor inicial para el nombre
     * @param intelligence Valor inicial para la inteligencia
     * @param strength Valor inicial para la fuerza
     */
    public Monster(String name, float intelligence, float strength){
        super(name, intelligence, strength, INITIAL_HEALTH);
    }
    
    /**
     * Indica la fuerza de ataque del monstruo
     * @return Devuelve el valor de fuerza del ataque
     */
    @Override
    public float attack(){
        return Dice.intensity(this.getStrength());
    }
    
    /**
     * Método que permite al monstruo defenderse de un ataque.
     * @param receivedAttack Intensidad del ataque recibido.
     * @return Devuelve true si el monstruo ha muerto y false en caso contrario.
     */
    @Override
    public boolean defend(float receivedAttack){

        boolean isDead = this.dead();
        
        if (!isDead){
            if (Dice.intensity(this.getIntelligence()) < receivedAttack){
                // Si está vivo y el ataque le vence, se contabiliza
                this.gotWounded();
                isDead = this.dead();
            }
        }

        return isDead;
    }
   
}
