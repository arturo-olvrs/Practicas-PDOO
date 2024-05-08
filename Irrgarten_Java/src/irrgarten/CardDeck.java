package irrgarten;

import java.util.Collections;
import java.util.ArrayList;

/**
 * Clase parametrizable y abstracta de la baraja de cartas del juego.
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */

abstract class CardDeck <T extends CombatElement> {
    
    /**
     * Baraja de cartas
     */
    private ArrayList <T> cardDeck;
    
    /**
     * Constructor de la clase parametrizable CardDeck
     */
    public CardDeck(){
        cardDeck=new ArrayList<>();
    }
    
    /**
     * Método que añade todas las cartas del tipo corresopndiente a la baraja
     * delegando parate de su funcionalidad en el método addCards()
     * @see #addCards
     */
    protected abstract void addCards();    
    
    /**
     * Método que añade una carte del tipo correspondiente al contenedor
     * @param card Carta a añadir al contenedor
     */
    protected void addCard(T card){
        this.cardDeck.add(card);
    }
    
    /**
     * Método que devuelve la primera carta del tipo correspondiente de la 
     * baraja de cartas. Si la baraja está vacía se llama a addCards y se baraja.
     * Destacar el uso de <b>Collections.shuffle(array)</b> para barajar el array
     * pasado como argumento.
     * @see #addCards
     * 
     * @return Primera carta de la baraja.
     */
    public T nextCard(){
        if(this.cardDeck.size()==0) {
            this.addCards();
            Collections.shuffle(this.cardDeck); // Baraja el array pasado
        }
        T seleccionada=this.cardDeck.get(0);
        this.cardDeck.remove(0);
        
        return seleccionada;
        
    }
    
}
