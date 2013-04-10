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
public interface WildActions 
{
    enum Face { WILD, WILDDRFOUR }
    Card.cardColor Wild(Scanner scan); 
    Card.cardColor DrawFour(Player nextPlayer, Deck copyDeck, Scanner scan); 
    
}
