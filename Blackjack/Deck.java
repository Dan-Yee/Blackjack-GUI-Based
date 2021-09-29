import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of a deck of playing cards
 * @author Dan Yee
 */
public class Deck {
    private ArrayList<Card> cardDeck;
    private Random randomNumber;

    public Deck() {
        this.cardDeck = new ArrayList<Card>(52);
        this.randomNumber = new Random();
    }

    /**
     * Builds the deck of cards by 52 card objects, 13 for each respective suit
     */
    public void buildDeck() {
        this.cardDeck.clear();

        this.cardDeck.add(new Card("diamonds", "ace.png", 11));
        this.cardDeck.add(new Card("diamonds", "two.png", 2));
        this.cardDeck.add(new Card("diamonds", "three.png", 3));
        this.cardDeck.add(new Card("diamonds", "four.png", 4));
        this.cardDeck.add(new Card("diamonds", "five.png", 5));
        this.cardDeck.add(new Card("diamonds", "six.png", 6));
        this.cardDeck.add(new Card("diamonds", "seven.png", 7));
        this.cardDeck.add(new Card("diamonds", "eight.png", 8));
        this.cardDeck.add(new Card("diamonds", "nine.png", 9));
        this.cardDeck.add(new Card("diamonds", "ten.png", 10));
        this.cardDeck.add(new Card("diamonds", "jack.png", 10));
        this.cardDeck.add(new Card("diamonds", "queen.png", 10));
        this.cardDeck.add(new Card("diamonds", "king.png", 10));

        this.cardDeck.add(new Card("clubs", "ace.png", 11));
        this.cardDeck.add(new Card("clubs", "two.png", 2));
        this.cardDeck.add(new Card("clubs", "three.png", 3));
        this.cardDeck.add(new Card("clubs", "four.png", 4));
        this.cardDeck.add(new Card("clubs", "five.png", 5));
        this.cardDeck.add(new Card("clubs", "six.png", 6));
        this.cardDeck.add(new Card("clubs", "seven.png", 7));
        this.cardDeck.add(new Card("clubs", "eight.png", 8));
        this.cardDeck.add(new Card("clubs", "nine.png", 9));
        this.cardDeck.add(new Card("clubs", "ten.png", 10));
        this.cardDeck.add(new Card("clubs", "jack.png", 10));
        this.cardDeck.add(new Card("clubs", "queen.png", 10));
        this.cardDeck.add(new Card("clubs", "king.png", 10));

        this.cardDeck.add(new Card("hearts", "ace.png", 11));
        this.cardDeck.add(new Card("hearts", "two.png", 2));
        this.cardDeck.add(new Card("hearts", "three.png", 3));
        this.cardDeck.add(new Card("hearts", "four.png", 4));
        this.cardDeck.add(new Card("hearts", "five.png", 5));
        this.cardDeck.add(new Card("hearts", "six.png", 6));
        this.cardDeck.add(new Card("hearts", "seven.png", 7));
        this.cardDeck.add(new Card("hearts", "eight.png", 8));
        this.cardDeck.add(new Card("hearts", "nine.png", 9));
        this.cardDeck.add(new Card("hearts", "ten.png", 10));
        this.cardDeck.add(new Card("hearts", "jack.png", 10));
        this.cardDeck.add(new Card("hearts", "queen.png", 10));
        this.cardDeck.add(new Card("hearts", "king.png", 10));

        this.cardDeck.add(new Card("spades", "ace.png", 11));
        this.cardDeck.add(new Card("spades", "two.png", 2));
        this.cardDeck.add(new Card("spades", "three.png", 3));
        this.cardDeck.add(new Card("spades", "four.png", 4));
        this.cardDeck.add(new Card("spades", "five.png", 5));
        this.cardDeck.add(new Card("spades", "six.png", 6));
        this.cardDeck.add(new Card("spades", "seven.png", 7));
        this.cardDeck.add(new Card("spades", "eight.png", 8));
        this.cardDeck.add(new Card("spades", "nine.png", 9));
        this.cardDeck.add(new Card("spades", "ten.png", 10));
        this.cardDeck.add(new Card("spades", "jack.png", 10));
        this.cardDeck.add(new Card("spades", "queen.png", 10));
        this.cardDeck.add(new Card("spades", "king.png", 10));
    }

    /**
     * Picks a random suit and then a random card from that suit
     * @return a random card drawn from the Deck
     */
    public Card drawCard() {
        int randomCardIndex = randomNumber.nextInt(this.cardDeck.size());
        Card randomCard = this.cardDeck.remove(randomCardIndex);

        return randomCard;
    }
}