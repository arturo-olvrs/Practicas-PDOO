package irrgarten;

import java.util.ArrayList;

/**
 * Clase que almacena información de cada jugador
 * 
 * @author Arturo Olivares Martos
 * @author Joaquín Avilés de la Fuente
 */
public class Player {
    // Cantidad máxima de armas que puede llevar
    private static final int MAX_WEAPONS=2;
    
    // Cantidad máxima de escudos que puede llevar
    private static final int MAX_SHIELDS=3;
    
    // Número de unidades de salud inicial
    private static final int INTIAL_HEALTH=10;
   
    
    private static final int HITS2LOSE=3;
    
    // Posición inválida para inicializar row y col en el constructor
    private static final int INVALID_POS=-1;
    
    private String name;
    private char number;
    private float intelligence;
    private float strength;
    private int row;
    private int col;
    private int consecutiveHits;
    
    private ArrayList<Weapon> weapons; // ArrayList de armas
    private ArrayList<Shield> shields; // ArrayList de escudos
    
    public Player (char number, float intelligence, float strength){
        this.number=number;
        this.name= "Player #"+number;
        this.intelligence=intelligence;
        this.strength=strength;
        this.consecutiveHits=0;
    }
}
