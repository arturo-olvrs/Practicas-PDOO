
package irrgarten.UI;

import irrgarten.Directions;
import irrgarten.GameState;
import java.util.Scanner;


public class TextUI {
    
    private static Scanner in = new Scanner(System.in);
    
    private char readChar() {
        String s = in.nextLine();     
        return s.charAt(0);
    }
    

    public Directions nextMove() {
        System.out.print("Where? ");
        
        Directions direction = Directions.DOWN;
        boolean gotInput = false;
        
        while (!gotInput) {
            char c = readChar();
            switch(c) {
                case 'w':
                    System.out.print(" UP\n\n");
                    direction = Directions.UP;
                    gotInput = true;
                    break;
                case 's':
                    System.out.print(" DOWN\n\n");
                    direction = Directions.DOWN;
                    gotInput = true;
                    break;
                case 'd':
                    System.out.print("RIGHT\n\n");
                    direction = Directions.RIGHT;
                    gotInput = true;
                    break;
                case 'a':
                    System.out.print(" LEFT\n\n");
                    direction = Directions.LEFT;
                    gotInput = true;    
                    break;
            }
        }    
        return direction;
    }
    
    public void showGame(GameState gameState) {
        System.out.print(gameState.getLabyrinth() + "\n");
        System.out.print(gameState.getPlayers() + "\n");
        System.out.print(gameState.getMonsters() + "\n");
        if (gameState.getWinner()) {
            System.out.print("We have a winner! Player " + gameState.getCurrentPlayer() + "\n");
        }else{
            System.out.print("Current player: " + gameState.getCurrentPlayer() + "\n");
        }
        System.out.print("Log:\n" + gameState.getLog() + "\n");
    }
    
}