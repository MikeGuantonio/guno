
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicuno;

//~--- JDK imports ------------------------------------------------------------
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

//~--- classes ----------------------------------------------------------------
/**
 *
 * @author mike
 */
public class Robot extends Player {

    
    private static final Logger log = Logger.getLogger(Robot.class.getName());
    private Card.cardColor[] colorValues = Card.cardColor.values();
    private Card playingCard = null;
    private Stack<Card> wildSet = new Stack<>(); 

    /**
     *
     * @param name
     * @param pos
     */
    public Robot(String name, int pos)
    {
        super.name = name;
        super.playerPos = pos;
        log.setLevel(Level.WARNING);
    }

    
    /**
     *
     * @param dex
     * @return
     */
    @Override
    public Card Discard(int dex) //this function needs fixed as well...
    {
        log.entering("Discard", name);
        Card retC;

        if (dex == -1)
        {
            retC = null;
        }
        else
        {
            retC = hand.get(dex);
        }
        log.exiting("Discard", name);
        return retC;
    }
            
    /**
     *
     * @param c
     */
    public void Remove(Card c)
    {
        hand.remove(c);
    }

   
    /**
     *
     * @param topCard
     * @param d
     * @return
     */
    @Override
    public Card PlayAHand(Card topCard, Deck d)
    {
        log.entering("Play a hand", name);

        boolean done = false;
        boolean tried = false;
        int pos = 0; 
        Card toPlay = null; 

        log.fine("Trying to decide");

        int state = Decide(topCard);

        if (state == 1)
        {
            log.fine(String.format("Decided %s %s", state, playingCard.toString()));
        }
        else
        {
            log.fine(String.format("decided %s", state));
        }
        
        while (!done)
        {
           switch (state)
           {
                case 1: 
                        if (playingCard.getClass().equals(WildCard.class))
                        {
                            state = 4;
                        }
                        else
                        {
                            toPlay = hand.remove(FindCard(playingCard));
                            state = 5;
                        }
                        break;

                case 2: 
                        log.fine("Need to draw a card");
                        super.GetCard(d.DrawNext());
                        state = Decide(d.TopDiscard());
                        tried = true;
                        if (state == 2)
                        {
                            state = 3;
                        }
                        break;

                case 3: log.fine("Pass");
                        state = 5;
                        break;

                case 4: WildCard tmp = (WildCard)playingCard;
                        log.fine("Time for a wild card.");
                        int colorChoice = (int)(Math.random() * 3);
                        ByteArrayInputStream in = new ByteArrayInputStream(colorValues[colorChoice].toString().getBytes());
                        System.setIn(in);
                        tmp.Wild(new Scanner(System.in));
                        playingCard = (Card)tmp;
                        hand.remove(FindCard(playingCard));
                        state = 5;
                        break;

                case 5: log.fine("End turn");
                        done = true;
                        toPlay = playingCard;
                        break;

                default: log.severe("I don't know what to do");
                         break;
            }
        }

        log.exiting("Play a Hand", name);
        return toPlay;
    }

    
    private int Decide(Card topCard)
    {
        log.entering("Decide", name);

        
        int choice;
        playingCard = null;
        
        if(this.Match(topCard))
        {
            choice = 1; 
        }
        else
        {
            if(this.PlayAWild())
            {
                choice = 1; 
                
            }
            else
            {
                choice = 2;
                
            }
        }
        
        log.exiting("Decide", name);
        return choice;
    }
    
    
    private boolean PlayAWild()
    { 
        boolean isWild = false;
        for(Card c : hand)
        {
            if(c.getClass().equals(WildCard.class))
            {
                playingCard = c;
                isWild = true;
                break;
            }
        }
        return isWild; 
    }
    
    /**
     *
     * @param c
     * @return
     */
    public boolean Match(Card c) //topDiscard
    {
        boolean possible = false; 
        
        for(Card inPlay : this.hand)
        {
            if(!inPlay.getClass().equals(WildCard.class)) 
            {
                
                boolean match = c.match(inPlay);
                if(match)
                {
                    playingCard = inPlay;
                    possible = true;
                    break;   
                }
                
            }
            
                        
                            
        }
        
        return possible; 
    }
    
    
    
    
}
