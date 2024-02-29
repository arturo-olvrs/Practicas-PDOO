package irrgarten;

/**
 * Clase que representa los escudos que utiliza el jugador cuando se defiende
 * de un ataque de un monstruo.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Shield {
    
    private float protection;
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
    public String toString() {
        return "S[" + protection + ", " + uses + "]";
    }
    
}
