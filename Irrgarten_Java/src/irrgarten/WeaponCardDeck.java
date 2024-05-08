package irrgarten;

import java.util.Random; // TODO: ver si es necesario (creo q si)

/**
 * Clase que representa la baraja de cartas de tipo Arma.
 *
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */

// TODO: es correcta está forma de inicializar CardDeck?
public class WeaponCardDeck extends CardDeck<Weapon>{
    
    /**
     * Tamaño máximo para la baraja de cartas de tipo Arma
     */
    private static final int TAMANIO_MAX=25;
    
    /**
     * Método que añade todas las cartas de tipo Arma a la baraja
     * con un tamaño máximo de TAMANIO_MAX cartas
     */
    @Override 
    protected void addCards(){
        Random generator=new Random();
        
        int tamaño=generator.nextInt(TAMANIO_MAX);
        
        for(int i=0; i<tamaño; i++){
            this.addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}