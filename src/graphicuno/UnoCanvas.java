package graphicuno;

//~--- JDK imports ------------------------------------------------------------

import java.awt.*;
import java.awt.event.*;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class UnoCanvas extends JApplet
{
    
    public void init() {
        this.setBackground(Color.BLACK);
        this.setSize(1000, 300);

        UnoMain	uc = new UnoMain();

        this.getContentPane().add(uc, BorderLayout.CENTER);
    }

    
    public Insets getInsets()
    {
        return new Insets(3, 3, 3, 3);
    }

    
    class UnoMain extends JPanel implements ActionListener
    {
        Card	c = new NumberCard(5, Card.cardColor.BLUE);
        Deck	deck;
        Robot	r;
        String	message;

        Font	bigFont;
        Font	smallFont;

       public UnoMain() 
       {
            this.setBackground(Color.white);
            smallFont = new Font(Font.SANS_SERIF, Font.BOLD, 12);
            bigFont   = new Font(Font.SANS_SERIF, Font.BOLD, 16);
            doNewGame();
        }

        
        public void actionPerformed(ActionEvent evt)
        {
           
        }

        
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
            
            int k = 0; 
            for (int i = 0; i < 700; i = i + 100)
            {
                drawCard(g, 10 + i, 160, r.Discard(k));
                k++;
            }
                       
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
        
        public Color chooseColor(Card.cardColor c)
        {
            Color	ac = Color.WHITE;

            switch (c)
            {
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
                g.setColor(Color.MAGENTA);
            else if(c.color == null )
                g.setColor(Color.MAGENTA);
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



