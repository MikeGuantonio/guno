package graphicuno;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

//~--- classes ----------------------------------------------------------------

/**
 * Class description
 *
 *
 * @version        Enter version here..., 13/04/09
 * @author         Enter your name here...    
 */
public class UnoCanvas extends JApplet
{
    /**
     * Method description
     *
     */
    public void init() {
        this.setBackground(Color.ORANGE);
        this.setSize(1000, 300);

        UnoMain	uc = new UnoMain();

        this.getContentPane().add(uc, BorderLayout.CENTER);
    }

    /**
     * Method description
     *
     *
     * @return
     */
    public Insets getInsets() {
        return new Insets(3, 3, 3, 3);
    }

    /**
     * Class description
     *
     *
     * @version        Enter version here..., 13/04/09
     * @author         Enter your name here...    
     */
    class UnoMain extends JPanel implements ActionListener
    {
        /** Field description */
        Card	c = new NumberCard(5, Card.cardColor.BLUE);

        /** Field description */
        Deck	deck;

        /** Field description */
        graphicuno.Robot	r;

        /** Field description */
        String	message;

        /** Field description */
        Font	bigFont;

        /** Field description */
        Font	smallFont;

        // <------------------------ END DELETEABLE CODE ------------------->

        /**
         * Constructs ...
         *
         */
        public UnoMain() {
            this.setBackground(Color.white);
            smallFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
            bigFont   = new Font(Font.SANS_SERIF, Font.BOLD, 16);
            doNewGame();
        }

        // <--------------------- DELETABLE CODE ------------------------->

        /**
         * Method description
         *
         *
         * @return
         */
        public ArrayList<Card> makeHand() {
            ArrayList<Card>	hand = new ArrayList<>();

            hand.add(c);
            hand.add(new NumberCard(4, Card.cardColor.GREEN));
            hand.add(new NumberCard(2, Card.cardColor.RED));
            hand.add(new NumberCard(4, Card.cardColor.YELLOW));
            hand.add(new SpecialCard(SpecialCard.cardValues.DRTWO, Card.cardColor.BLUE));
            hand.add(new SpecialCard(SpecialCard.cardValues.REVERSE, Card.cardColor.GREEN));
            hand.add(new SpecialCard(SpecialCard.cardValues.SKIP, Card.cardColor.YELLOW));

            return hand;
        }

        /**
         * Method description
         *
         *
         * @param evt
         */
        @Override
        public void actionPerformed(ActionEvent evt) {
            System.out.println("This should do something at some time");
        }

        /**
         * Method description
         *
         */
        void doNewGame()
        {
            deck = new Deck();
            r = new Robot("Bob", 0);

            deck.Shuffle();
            
            for (int i = 0; i < 7; i++) 
            {
                r.GetCard(deck.DrawNext());
            }
            deck.SetUpDiscard(null);
            repaint();
        }

        //This is the magic componet that paints all to the screen
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            g.setFont(bigFont);
            g.setColor(Color.cyan);
            
            g.drawString("UNO", WIDTH, HEIGHT);
            g.drawString("Discard -> ", 10, 23);
            g.drawString("Robot -> ", 10, 153);
            
            g.setFont(smallFont);
            
            drawCard(g, 10, 30, null);
            
            drawCard(g, 100, 30, deck.TopDiscard());

            boolean play = false;
            do
            {
                try {
                    PrintHand(r, g);
                    Card c = r.PlayAHand(deck.TopDiscard(), deck);
                    play = deck.AddDiscard(c);
                    r.Remove(c);
                    Thread.sleep(1_000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(UnoCanvas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            while(!play);
            
            
            
        }

        public void PrintHand(Robot r, Graphics g)
        {
          int k = 0; 
          for (int i = 0; i < 700; i = i + 100)
          {
                drawCard(g, 10 + i, 160, r.Discard(k));
                k++;
          }
          
        }
        
        public Color chooseColor(Card.cardColor c) {
            Color	ac = Color.WHITE;

            switch (c) {
            case BLUE :
                ac = Color.BLUE;

                break;

            case RED :
                ac = Color.RED;

                break;

            case GREEN :
                ac = Color.GREEN;

                break;

            case YELLOW :
                ac = Color.YELLOW;

                break;
            }

            return ac;
        }

        public void drawCard(Graphics g, int x, int y, Card c)
        {
            if(c == null)
                g.setColor(Color.white);
            else
                g.setColor(chooseColor(c.color));
            
            g.fillRect(x, y, 80, 100);
            g.setColor(Color.gray);
            g.drawRect(x, y, 79, 99);
            g.drawRect(x + 1, y + 1, 77, 97);
            if(c != null)
                g.drawString(c.getName(), x + 10, y + 30);
        }
    }
}



