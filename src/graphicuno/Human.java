/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graphicuno;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mike
 */
public class Human extends Player
{
    private static final Logger log = Logger.getLogger(Human.class.getName());
    public Scanner input = new Scanner(System.in);
    /**
     *
     * @param name
     * @param pos
     */
    public Human(String name, int pos)
    {
        super(); 
        super.name= name;
        super.playerPos = pos;
        log.setLevel(Level.ALL);
        
    }
    
    @Override
    public void ShowHand()
    {
        for (int i = 0; i < hand.size(); i++) 
        {
            System.out.println(String.format("[%s]: %s", i, hand.get(i).toString()));
        }
    }
    
    //Change discard to take a card and just find the card. 
    @Override
    public Card Discard(int dex)
    {
        Card c; 
        if(dex >= hand.size())
        {
            c = null; 
        }
        else
        {
            hand.get(dex).Print();
            c = hand.remove(dex);
        }
	return c;
    }
      
    @Override  
    public Card PlayAHand(Card topCard, Deck d)
    {
        int state = 0; 
        int pos = 0; 
        boolean done = false;
        boolean drawn = false; 
        String color;
        Card c = null;
        
        while(!done)
        {
            switch(state)
            {
                case 0: Menu(d.TopDiscard());
                        state = GetPlayerChoice();
                        break;  
                    
                case 1: PlayMenu();
                        int card = GetPlayerChoice();
                        if(card == -1)
                        {
                            if(drawn)
                            {
                                state = 5;
                                System.out.println("Already drew this turn");
                            }
                            else
                                state = 2;
                        }
                        else
                        {
                            c = this.Discard(card);
                            if(this.Match(c, topCard))
                            {
                                if(c.getClass().equals(WildCard.class))
                                {
                                    state = 4; 
                                }
                                else
                                {
                                    this.hand.remove(c);
                                    state = 5;
                                }
                            }
                            else
                            {
                                ReplayMenu();
                                state = 1; 
                                if(c != null)
                                    this.GetCard(c);
                            }
                            
                        }
                        break;
                    
                case 2: c = d.DrawNext();
                        this.GetCard(c);
                        System.out.println("You got " + c.toString());
                        DrawMenu();
                        state = this.GetPlayerChoice();
                        drawn = true; 
                        break;
                    
                case 3: System.out.println(this.GetName() + " passes");
                        state = 5; 
                        break;
                    
                case 4: WildMenu();
                        WildCard w = (WildCard)c;
                        w.Wild(input);
                        state = 5; 
                        break;
                    
                case 5: done = true;
                        break;
                    
                default: break;
            }
        }
        return c; 
    }
    
    private boolean Match(Card c, Card discard)
    {
       boolean possible = false;
       if(c != null)
           possible = c.match(discard); 
       return possible;
    }
    
    private void ReplayMenu()
    {
        System.out.println("That is not a card"); 
        System.out.println("Please choose a real card");
        System.out.println("Or pass.");
    }
    
    private String GetPlayerChoiceStr()
    {
        String resp; 
        resp = input.nextLine().toUpperCase();
        return resp; 
    }
    
    private int GetPlayerChoice()
    {
        int choice = 0; 
        choice = input.nextInt(); 
        return choice;
    }
    
    private void WildMenu()
    {
        System.out.println("Please choose a color");
        System.out.println("Red");
        System.out.println("Green");
        System.out.println("Blue");
        System.out.println("Yellow");
    }        
    
    private void DrawMenu()
    {
        System.out.println("What would you like to do?");
        System.out.println("1. Play a Card");
        System.out.println("3. Pass ");
    }
    
    private void PlayMenu()
    {
        System.out.println("Please choose a card");
        this.ShowHand();
        System.out.println("Which card would you like to play?");
        System.out.println("Please use a -1 to draw a card");
    }
    
    private void Menu(Card c)
    {
        System.out.println("");
        System.out.println("What would you like to do? ");
        System.out.println("1. Play a card");
        System.out.println("2. Draw a card");
    }
}
