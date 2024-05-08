package irrgarten;

/**
 * Esta clase representa las armas que utiliza el jugador en los ataques
 * durante los combates.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Weapon extends CombatElement {
   
    /**
     * Constructor de la clase Weapon.
     * @param power El poder del arma.
     * @param uses El número de usos disponibles del arma.
     */
    public Weapon(float power, int uses){
        super(power, uses);
    }
      
    /**
     * Método para realizar una ataque con el arma.
     * @return La intensidad del ataque del arma.
     */
    public float attack(){
        return this.produceEffect();
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del objeto.
     * @return Representación en forma de cadena de caracteres del estado interno del objeto.
     */
    @Override
    public String toString() {
        String toReturn="W";
        toReturn+=super.toString();
        return toReturn;
    }
    
}
