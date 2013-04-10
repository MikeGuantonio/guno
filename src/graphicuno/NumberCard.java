/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicuno;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class NumberCard extends Card implements NumberActions
{
    private static final Logger log = Logger.getLogger(NumberCard.class.getName());
    private int face;
    
    
   
    
    /**
     *
     * @param newNum
     * @param color
     */
    public NumberCard(int newNum, Card.cardColor color)
    {
        //shouldn't be able to set a card from -1 to 10...
        face = newNum; 
        super.color = color; 
        log.setLevel(Level.SEVERE);
    }
     
    public String getName()
    {
        String foo; 
        foo = String.format("%s", face);
        return foo; 
    }
    
    public int getNumFace()
    {
        return face; 
    }
    
    
    /**
     *
     * @return
     */
    @Override
    public Card.cardColor GetColor()
    {
        return color; 
    }
    
    
    /**
     *
     */
    @Override
    public void Print()
    { 
        System.out.println(this.toString());
    }
    
    @Override
    public String toString()
    {
        String termColor = super.ReturnColor(color);
        String faceValue = String.format("%s%s \033[0m", termColor, face); 
        return faceValue;
    }
    
    /**
     *
     * @param c
     * @return
     */
    @Override
    public boolean match(Card c)
    {
        boolean possible = false; 
        
        if(this.colorMatch(c))
        {
            possible = true;
        }
        else if((c.getClass().equals(NumberCard.class)))
        {
           
            NumberCard toMatch = (NumberCard)c;
            if(this.getNumFace() == toMatch.getNumFace())
            {
            possible = true;
            }
        }
        return possible;
    }
}
