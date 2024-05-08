package irrgarten;

/**
 * Clase que representa los escudos que utiliza el jugador cuando se defiende
 * de un ataque de un monstruo.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Shield extends CombatElement{
    
    /**
     * Constructor de la clase Shield.
     * @param protection La protección del escudo.
     * @param uses El número de usos disponibles del escudo.
     */
    public Shield(float protection, int uses){
        super(protection, uses);
    }
      
    /**
     * Método para realizar una protección con el escudo.
     * @return La intensidad de la protección del escudo.
     */
    public float protect(){
        return this.produceEffect();
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del objeto.
     * @return Representación en forma de cadena de caracteres del estado interno del objeto.
     */
    @Override
    public String toString() {
        String toReturn="S";
        toReturn+=super.toString();
        return toReturn;
    }
    
}
