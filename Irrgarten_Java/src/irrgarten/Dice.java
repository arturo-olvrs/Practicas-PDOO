package irrgarten;

import java.util.Random;
import java.util.ArrayList;


/**
 * Clase que tiene la responsabilidad de tomar todas las decisiones
 * que dependen del azar en el juego.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Dice {
    
    /**
     * Número máximo de usos de armas y escudos.
     */
    private static final int MAX_USES = 5;

    /**
     * Valor máximo para la inteligencia de jugadores y monstruos.
     */
    private static final float MAX_INTELLIGENCE = 10.0f;

    /**
     * Valor máximo para la fuerza de jugadores y monstruos.
     */
    private static final float MAX_STRENGTH = 10.0f;

    /**
     * Probabilidad de que un jugador sea resucitado en cada turno.
     */
    private static final float RESURRECT_PROB = 0.3f;

    /**
     * Número máximo de armas recibidas al ganar un combate.
     */
    private static final int WEAPONS_REWARD = 2;

    /**
     * Número máximo de escudos recibidos al ganar un combate.
     */
    private static final int SHIELDS_REWARD = 3;

    /**
     * Número máximo de unidades de salud recibidas al ganar un combate
     */
    private static final int HEALTH_REWARD = 5;

    /**
     * Máxima potencia de las armas.
     */
    private static final int MAX_ATTACK = 3;

    /**
     * Máxima potencia de los escudos.
     */
    private static final int MAX_SHIELD = 2;
    
    /**
     * Generador de números aleatorios.
     */
    private static final Random generator = new Random();
    
    /**
     * Genera una fila o columna aleatoria en el tablero
     * @param max Dimension maxima en el tablero (filas o columnas)
     * @return Una fila (o columna) aleatoria entre 0 y max
     */
    public static int randomPos(int max){
        return generator.nextInt(max);
    }
    
    /**
     * Determina qué jugador empieza la partida
     * @param nplayers Numero de jugadores
     * @return Numero que representa al jugador que empieza.
     *          Se numeran desde el 0.
     */
    public static int whoStarts(int nplayers){
        return generator.nextInt(nplayers);
    }
    
    /**
     * Método para generar un valor aleatorio de inteligencia en el intervalo
     * [0, MAX_INTELLIGENCE[.
     * @return Un valor aleatorio de inteligencia.
     */
    public static float randomIntelligence() {
        return generator.nextFloat() * MAX_INTELLIGENCE;
    }

    /**
     * Método para generar un valor aleatorio de fuerza en el intervalo
     * [0, MAX_STRENGTH[.
     * @return Un valor aleatorio de fuerza.
     */
    public static float randomStrength() {
        return generator.nextFloat() * MAX_STRENGTH;
    }
    
    /**
     * Método que determina si un jugador resucita o no
     * @return true en el caso de que resucite, false en caso contrario.
     */
    public static boolean resurrectPlayer(){
        
        return generator.nextFloat() <= RESURRECT_PROB;
    }
    
    
    /**
     * Método que indica la cantidad de armas que recibirá el jugador por ganar el combate.
     * @return La cantidad de armas recibidas.
     */
    public static int weaponsReward() {
        return generator.nextInt(WEAPONS_REWARD + 1); // +1 pq debe estar en el cerrado
    }

    /**
     * Método que indica la cantidad de escudos que recibirá el jugador por ganar el combate.
     * @return La cantidad de escudos recibidos.
     */
    public static int shieldsReward() {
        return generator.nextInt(SHIELDS_REWARD + 1); // +1 pq debe estar en el cerrado
    }

    /**
     * Método que indica la cantidad de unidades de salud que recibirá el jugador por ganar el combate.
     * @return La cantidad de unidades de salud recibidas.
     */
    public static int healthReward() {
        return generator.nextInt(HEALTH_REWARD + 1);  // +1 pq debe estar en el cerrado
    }

    /**
     * Método que devuelve un valor aleatorio en el intervalo [0, MAX_ATTACK[.
     * @return Un valor aleatorio de potencia de arma.
     */
    public static float weaponPower() {
        return generator.nextFloat() * MAX_ATTACK;
    }

    /**
     * Método que devuelve un valor aleatorio en el intervalo [0, MAX_SHIELD[.
     * @return Un valor aleatorio de potencia de escudo.
     */
    public static float shieldPower() {
        return generator.nextFloat() * MAX_SHIELD;
    }

    /**
     * Método que devuelve el número de usos que se asignará a un arma o escudo.
     * @return El número de usos asignados.
     */
    public static int usesLeft() {
        return generator.nextInt(MAX_USES + 1);  // +1 pq debe estar en el cerrado
    }

    /**
     * Método que devuelve la cantidad de competencia aplicada.
     * @param competence La competencia aplicada.
     * @return Un valor aleatorio del intervalo [0, competence[.
     */
    public static float intensity(float competence) {
        return generator.nextFloat() * competence;
    }

    /**
     * Método que descarta un elemento (arma o escudo) con una probabilidad
     * inversamente proporcional a lo cercano que esté el parámetro del número
     * máximo de usos que puede tener un arma o escudo.
     * @param usesLeft El número de usos restantes del elemento.
     * @return True si el elemento debe ser descartado, False en caso contrario.
     */
    public static boolean discardElement(int usesLeft) {
        
        float prob = (float)usesLeft / MAX_USES;
        
        return generator.nextFloat() >= prob;
        
        // En el caso de usesLeft=0, siempre devolverá true;
        // En el caso de usesLeft=MAX_USES, siempre devolverá false;
    }
    
    /**
     * En función de la inteligencia dada, devolverá ,con más probabilidad si <b>intelligence</b> es mayor,
     * <b>preference</b> y ,con menos, una dirección aleatoria de <b>validMoves</b> 
     * @param preference Dirección preferida hacia la que moverse
     * @param validMoves Direcciones posibles para moverse
     * @param intelligence Inteligencia del jugador
     * @return Dirección elegida hacia la que moverse
     */
    public static Directions nextStep(Directions preference, ArrayList<Directions> validMoves, float intelligence){
        Directions toReturn=preference;
        
        if(Dice.randomIntelligence()>intelligence){
            int indice=generator.nextInt(validMoves.size());
            toReturn=validMoves.get(indice);          
        }
        
        return toReturn;
             
    }
    
    
}
