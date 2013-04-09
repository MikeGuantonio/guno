package graphicuno; 

import java.util.*;

/**
 *
 * @author mike
 */
abstract class Player 
{
    protected ArrayList<Card> hand = new ArrayList<>(); 
    protected String name; 
    protected int playerPos; 
    
    abstract Card PlayAHand(Card topCard, Deck d); 
    abstract Card Discard(int dex);
    
   
    public int FindCard(Card c)
    {
        return hand.indexOf(c);
    }
    
   
    public void ShowHand()
    {
        
	for(Card c : hand)
	{
	    c.Print();
	}
    }

    public boolean Uno()
    {
        if(hand.size() == 1) {
            return true;
        } 
        else {
            return false;
        } 
    }
   
    public void GetCard(Card c)
    {
	hand.add(c);
    }

    
    public int TotalCards()
    {
	return hand.size(); 
    }
    
    public int GetPlayerPos()
    {
        return playerPos; 
    }
     
    public String GetName()
    {
        return name; 
    }
}

