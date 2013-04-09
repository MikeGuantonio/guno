/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicuno;

import java.util.ArrayList;

/**
 *
 * @author mike
 */
public interface SpecialActions {
    
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    int Skip(int currentPlayerIndex, int playerSize); //Skips the player. player index plus 1
    /**
     *
     * @param currentPlayerIndex
     * @param playerSize
     * @return
     */
    int Reverse(int currentPlayerIndex, int playerSize); //Decrement player index by 1
    /**
     *
     * @param d
     * @param p
     */
    int DrawTwo(Deck d, ArrayList<Player> p , int pos); 
   
}
