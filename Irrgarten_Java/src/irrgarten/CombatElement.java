package irrgarten;

/**
 * Clase que representa los elementos de combate que utiliza el jugador durante 
 * los combates a lo largo del juego.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */

// TODO: poner visibilidad delante?
abstract class CombatElement {
    /**
     * Efecto del elemento de combate.
     */
    private float effect;
    
    /**
     * Número de usos disponibles del elemento de combate.
     */
    private int uses;
    
    /**
     * Constructor de la clase abstracta CombatElement
     * @param effect Efecto del elemento de combate
     * @param uses El número de usos disponibles del elemento de combate
     */
    public CombatElement (float effect, int uses){
        this.effect=effect;
        this.uses=uses;
    }
    
    /**
     * Método para realizar el efecto correspondiente al elemento de combate,
     * se reduce en uno su uso.
     * @return La intensidad del efecto de dicho elemento de combate.
     */
    protected float produceEffect(){
        if(uses>0){
            uses--;
            return effect;
        }
        else
            return 0;
    }
    
    /**
     * Método que indica si se descartará el elemento de combate en función de
     * sus usos
     * @return devuelve true o false si se descarta o no
     */
    public boolean discard(){
        return Dice.discardElement(this.uses);
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del elemento de combate.
     * @return Representación en forma de cadena de caracteres del estado interno del elemento de combate.
     */
    @Override
    public String toString(){
        return "[" + effect + ", " + uses + "]";
    }
}
