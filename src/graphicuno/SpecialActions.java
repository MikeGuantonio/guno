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
public interface SpecialActions 
{
    
    enum Face {REVERSE,SKIP,DRTWO} //face
    int Skip(int currentPlayerIndex, int playerSize); 
    int Reverse(int currentPlayerIndex, int playerSize); 
    int DrawTwo(Deck d, ArrayList<Player> p , int pos); 
   
}
