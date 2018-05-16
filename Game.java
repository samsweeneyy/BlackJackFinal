/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Scanner;
public class Game
{
    // instance variables - replace the example below with your own
    Scanner scan = new Scanner(System.in);
    Deck deck = new Deck();
    Player player;
    Dealer dealer;
    
    /**
     * Constructor for objects of class Game
     */
    public Game()
    { 
        // initialise instance variables
        int money = 1000;
        System.out.println("What is your name?");
        String name = scan.next();
        player = new Player(name, money);
        dealer = new Dealer();
        deck.shuffle();
    }
    
    public void deal() {
        Card c = deck.deal();
        player.hit(c);
    }
    
    public void dealDealer() {
        Card c = deck.deal();
        dealer.hit(c);
    }

    public void playGame() {
        deck.shuffle();
        givePlayerFirstHand();
        giveDealerHand();
        sayHit();
        sayDealerHit();
        endGame();
    }
    
    public String winner() {
        if(player.getHandTotal() > 21 || player.getHandTotal() < dealer.getHandTotal() && dealer.getHandTotal() < 22) {
            return "Dealer wins";
        }
        else {
            return "Player wins";
        }
    }
    
    public void endGame() {
        System.out.println(winner());
        System.out.println("The game is over");
    }
    
    public void givePlayerFirstHand() {
        deal();
        deal();
        System.out.println(player);
    }
    
    public void sayHit() {
        System.out.println("Would you like to hit?");
        if (scan.next().equals("yes")) {
            deal();
            System.out.println(player);
            if(player.getHandTotal() < 21) {
                sayHit();
            }
            else {
                winner();
            }
        }
        else {
            player.stay();
            System.out.println("Your final hand total is " + player.getHandTotal());
        }
    }
    
    public void sayDealerHit() {
        if (dealer.getHandTotal() < 15) {
            dealDealer();
            sayDealerHit();
        }
        else {
            System.out.println("The dealers final hand total is " + dealer.getHandTotal());
        }
        
    }
    
    public void giveDealerHand() {
        dealDealer();
        dealDealer();
    }
}
