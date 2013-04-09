
/*TODO:
 Find a way to return the face and what not in a way that makes sense so
 * that it can be removed from the deck test and still retain functionality.
 */
package graphicuno;

/**
 *
 * @author mike
 */
abstract class Card {
    private static final String BLUECOLOR = "\033[1;34m";
    private static final String REDCOLOR = "\033[31m";
    private static final String YELLOWCOLOR = "\033[1;33m";
    private static final String GREENCOLOR = "\033[1;32m";

    protected cardColor color;
   
    abstract void Print();    
    abstract cardColor GetColor();
    
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
