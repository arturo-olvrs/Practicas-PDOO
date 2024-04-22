package irrgarten;

/**
 * Esta clase representa las armas que utiliza el jugador en los ataques
 * durante los combates.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Weapon {
    /**
     * Potencia del arma.
     */
    private final float power;
    /**
     * Número de usos disponibles del arma.
     */
    private int uses;

    /**
     * Constructor de la clase Weapon.
     * @param power La potencia del arma.
     * @param uses El número de usos disponibles del arma.
     */
    public Weapon(float power, int uses) {
        this.power = power;
        this.uses = uses;
    }

    /**
     * Método para realizar un ataque con el arma.
     * @return La intensidad del ataque del jugador.
     */
    public float attack() {
        if (uses > 0) {
            uses--;
            return power;
        } else {
            return 0;
        }
    }

    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del objeto.
     * @return Representación en forma de cadena de caracteres del estado interno del objeto.
     */
    @Override
    public String toString() {
        return "W[" + power + ", " + uses + "]";
    }
    
    /**
     * Método que indica si se descartará el arma en función de sus usos
     * @return devuelve true o false si se descarta o no
     */
    public boolean discard(){
        
        return Dice.discardElement(uses);
    }
}