/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicuno;

import java.util.Scanner;

/**
 *
 * @author mike
 */
public interface WildActions {
    
     /**
     *
     * @param scan
     * @return
     */
    Card.cardColor Wild(Scanner scan); 
     /**
     *
     * @param nextPlayer
     * @param copyDeck
     * @param scan
     * @return
     */
    Card.cardColor DrawFour(Player nextPlayer, Deck copyDeck, Scanner scan); 
    
}
