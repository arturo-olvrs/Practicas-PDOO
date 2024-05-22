package irrgarten;

import java.util.ArrayList;

/**
 * Clase que representa otro tipo de jugador que actúa de forma más aleatoria
 * que los jugadores normales, pues estos actúan de forma determinista.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class FuzzyPlayer extends Player{
    
    /**
     * Constructor de copia (y único) a partir de un objeto player
     * de la clase FuzzyPlayer
     * @param other Objeto Player a copiar
     */
    public FuzzyPlayer (Player other){
        super(other);
    }
    
    /**
     * Método que informa sobre la dirección en la que se va a mover el jugador.
     * Destacar que en este caso se elige de forma aleatoria entre la preferida
     * (obtenida del método move de Player) y las posibles <b>validMoves</b>.
     * @see Player#move
     * 
     * @param direction  Dirección en la que se quiere mover el fuzzyplayer
     * @param validMoves  Lista de movimientos válidos
     * @return  Devuelve la dirección en la que se moverá el jugador (si es válida)
     */
    @Override
    public Directions move(Directions direction, ArrayList<Directions> validMoves){
        Directions preference=super.move(direction, validMoves);
        return Dice.nextStep(preference, validMoves, this.getIntelligence());
    }
    
    /**
     * Calcula ataque total del fuzzyplayer, teniendo en cuenta su fuerza y el poder
     * de sus armas
     * @return Devuelve la suma de su fuerza (a partir del método de Dice) y
     * del poder de las armas
     */
    @Override
    public float attack(){
        return (Dice.intensity(this.getStrength())+this.sumWeapons());
    }
    
    /**
     * Calcula defensa total del fuzzyplayer, teniendo en cuenta su inteligencia y 
     * la protección de sus escudos
     * @return Devuelve la suma de su inteligencia (a partir del método de Dice)
     * y la protección de los escudos
     */
    @Override
    protected float defensiveEnergy(){
        return (Dice.intensity(this.getIntelligence())+this.sumShields());
    }
    
    /**
     * Método que devuelve una representación en forma de cadena de caracteres
     * del estado interno del fuzzyplayer.
     * @return Representación en forma de cadena de caracteres del estado interno 
     * del fuzzyplayer.
     */
    @Override
    public String toString(){
        String toReturn="(Fuzzy) ";
        toReturn+=super.toString();
        
        return toReturn;
    }
}
