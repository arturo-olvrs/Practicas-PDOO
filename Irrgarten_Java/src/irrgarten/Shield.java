package irrgarten;

/**
 * Clase que representa los escudos que utiliza el jugador cuando se defiende
 * de un ataque de un monstruo.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Shield {
    
    /**
     * Protección que proporciona el escudo.
     */
    private float protection;
    /**
     * Número de usos disponibles del escudo.
     */
    private int uses;
    
    /**
     * Constructor de la clase Shield.
     * @param protection La proteccion que proporciona el escudo.
     * @param uses El número de usos disponibles del escudo.
     */
    public Shield(float protection, int uses) {
        this.protection = protection;
        this.uses = uses;
    }


    /**
     * Método que determina si se descarta el escudo.
     * @return true si se descarta el escudo, false en caso contrario.
     * @see Dice#discardElement(int)
     */
    public boolean discard() {
        return Dice.discardElement(this.uses);
    }
    
    

    /**
     * Método para protegerse con el escudo.
     * @return La intensidad de la defensa del escudo
     */
    public float protect() {
        if (uses > 0) {
            uses--;
            return protection;
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
        return "S[" + protection + ", " + uses + "]";
    }
    
}
