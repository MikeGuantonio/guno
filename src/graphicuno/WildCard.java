
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package graphicuno;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

//~--- classes ----------------------------------------------------------------

/**
 *
 * @author mike
 */
public class WildCard extends Card implements WildActions
{
    /**
     * Field description
     */
    private static final Logger	log = Logger.getLogger(WildCard.class.getName());

    /**
     * Field description
     */
    cardWild	wild;

    /**
     *
     * @param w
     */
    public WildCard(cardWild w)
    {
        wild  = w;
        color = null;
        log.setLevel(Level.SEVERE);
        
    }

    /**
     *
     */
    @Override
    public void Print()
    {
    }

    /**
     *
     * @return
     */
    @Override
    public Card.cardColor GetColor() {
        return color;
    }

    /**
     *
     * @return
     */
    public cardWild GetWild() {
        return wild;
    }

    
    @Override
    public Card.cardColor Wild(Scanner scan)
    {
        log.info("Call for wild called");

        Card.cardColor	wildColor = null;
        String		input     = scan.nextLine();

        try {
            wildColor = Card.cardColor.valueOf(input.toUpperCase());
        } catch (Exception ex) {
            log.severe("The color value is null!");
        }

        color = wildColor;
        return wildColor;
    }

    public Card.cardColor PlayWild(Scanner scan, ArrayList<Player> p, Deck d, int pos)
    {
        switch(this.wild)
        {
            case WILD : this.Wild(scan);
                        break;
            
            case WILDDRFOUR : this.DrawFour(p.get(pos), d, scan);
                               break; 
        }
        return this.color;
    }
    
    @Override //need to check for edge case 1 -> 0
    public Card.cardColor DrawFour(Player newPlayer, Deck copyDeck, Scanner scan)
    {
        Card.cardColor	newColor;

        newColor = Wild(scan);

        for (int i = 0; i < 4; i++) {
            newPlayer.GetCard(copyDeck.DrawNext());
        }

        return newColor;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Override
    public String toString()
    {
        String	faceValue = wild.toString();

        if (color != null)
        {
            String termColor = super.ReturnColor(color);
            faceValue = String.format("%s%s\033[0m", termColor, faceValue );
        }

        return faceValue;
    }
    
    @Override
    public boolean match(Card c)
    {
        if(this.colorMatch(c)) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Enum description
     *
     */
    enum cardWild { WILD, WILDDRFOUR }
}


