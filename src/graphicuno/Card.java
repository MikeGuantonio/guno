
package graphicuno;

import javax.swing.*;
/**
 *
 * @author mike
 */
abstract class Card <typeFace extends CardFace> 
{
    protected typeFace face; 
    
    public typeFace getFace()
    {
        return face; 
    }
    
    
    //<------------------ TERMINAL FIELDS --------------------->
    //< ------------------MOVE TO T_Card ---------------------->
    private static final String BLUECOLOR = "\033[1;34m";
    private static final String REDCOLOR = "\033[31m";
    private static final String YELLOWCOLOR = "\033[1;33m";
    private static final String GREENCOLOR = "\033[1;32m";
    
    //<---------------- GENERAL CARD ------------------------->
    protected cardColor color;
   
    abstract void Print();    
    abstract cardColor GetColor();
    abstract String getName(); 
    //abstract String getNumFace(); <-- Need to convert for a number...
    
    @Override
    public abstract String toString();
    public abstract boolean match(Card c); 
    
    
   
    public boolean colorMatch(Card c)
    {
        
   
        boolean possible = false; 
        if(c.GetColor() != null && this.color != null && this.color.equals(c.GetColor()))
        {
            possible = true; 
        }
        
        return possible;
    }
    
    public String ReturnColor(Card.cardColor color)
    {
        String colorVal = null; 
        
        switch(color)
        {
            case RED: colorVal = REDCOLOR; 
                      break;
            case BLUE: colorVal = BLUECOLOR; 
                       break;
            case GREEN: colorVal = GREENCOLOR;
                       break;
            case YELLOW: colorVal = YELLOWCOLOR; 
                       break;
        }
        return colorVal; 
    }
    
    enum cardColor {

        BLUE, RED, GREEN, YELLOW
    }
}
