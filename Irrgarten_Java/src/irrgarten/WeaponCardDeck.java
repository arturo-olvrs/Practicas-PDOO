package irrgarten;

/**
 * Clase que representa la baraja de cartas de tipo Arma.
 *
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */

public class WeaponCardDeck extends CardDeck<Weapon>{
    
    /**
     * Método que añade todas las cartas de tipo Arma a la baraja
     * con un tamaño máximo de TAMANIO_MAX cartas
     */
    @Override 
    protected void addCards(){
        for(int i=0; i<WeaponCardDeck.TAMANIO_MAX; i++){
            this.addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
        }
    }
}