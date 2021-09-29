import java.util.ArrayList;

/**
 * Implementation of the functions of a Blackjack game
 * @author Dan Yee
 */
public class Blackjack {
    private Deck cardDeck;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> playerHand;
    private int dealerHandValue;
    private int playerHandValue;

    public Blackjack() {
        this.cardDeck = new Deck();
        this.dealerHand = new ArrayList<Card>();
        this.playerHand = new ArrayList<Card>();
        this.dealerHandValue = 0;
        this.playerHandValue = 0;
    }

    /**
     * Gets the value of the dealers hand
     * @return the value of the dealers hand
     */
    public int getDealerHandValue() {
        return this.dealerHandValue;
    }

    /**
     * Changes the value of the dealers hand - Used for updating hand values after hitting
     * @param value the new hand value of the dealer
     */
    private void setDealerHandValue(int value) {
        this.dealerHandValue = value;
    }

    /**
     * Gets the value of the players hand
     * @return the value of the players hand
     */
    public int getPlayerHandValue() {
        return this.playerHandValue;
    }

    /**
     * Changes the value of the players hand - Used for updating values after hitting
     * @param value the new hand value of the player
     */
    private void setPlayerHandValue(int value) {
        this.playerHandValue = value;
    }

    /**
     * Gets the list of cards in the dealers hand - Only used when initializing a new game
     * @return
     */
    public ArrayList<Card> getDealerHand() {
        return this.dealerHand;
    }

    /**
     * Gets the list of cards in the players hand - Only used when initializing a new game
     * @return
     */
    public ArrayList<Card> getPlayerHand() {
        return this.playerHand;
    }

    /**
     * Draws a random card from the deck and adds it to the list of the respective playerID
     * @param playerID 1 for player, 0 for dealer
     * @return the card drawn from the deck
     */
    public Card hitFunction(int playerID) {
        Card drawnCard = this.cardDeck.drawCard();

        // playerID 0 is dealer, otherwise player
        if(playerID == 0)
            this.dealerHand.add(drawnCard);
        else
            this.playerHand.add(drawnCard);
        this.updateHandValues();

        return drawnCard;
    }

    /**
     * Initiates a new game of Blackjack
     */
    public void newGame() {
        this.cardDeck.buildDeck();

        // "deal" two cards each to the dealer and player
        for(int i = 0; i < 2; i++) {
            this.dealerHand.add(this.cardDeck.drawCard());
            this.playerHand.add(this.cardDeck.drawCard());
        }
        this.updateHandValues();
    }

    /**
     * Updates the values of the dealer and player hands
     */
    private void updateHandValues() {
        int handValue = 0;

        // updates hand value for dealer
        for(Card card : this.dealerHand)
            handValue += card.getCardValue();
        this.setDealerHandValue(handValue);
        handValue = 0;

        // updates hand value for player
        for(Card card : this.playerHand)
            handValue += card.getCardValue();
        this.setPlayerHandValue(handValue);
    }

    /**
     * Checks the players hand, switching Aces from 11 to 1 if needed
     * Checks if the player has busted
     * @return -1 if the player has busted
     * @return 0 if no game interruption is expected
     */
    public int checkPlayerStatus() {
        for(Card card : this.playerHand) {
            if(card.getFileName().equalsIgnoreCase("ace.png") && card.getCardValue() == 11) {
                if(this.getPlayerHandValue() > 21) {
                    card.setCardValue(1);
                    this.updateHandValues();
                }
            }
        }
        if(this.getPlayerHandValue() > 21)
            return -1;
        return 0;
    }

    /**
     * Checks the dealers hand, switching Aces from 11 to 1 if needed
     * Checks if the dealer has busted
     * @return -1 if the dealer has busted
     * @return 0 if no game interruption is expected
     */
    public int checkDealerStatus() {
        for(Card card : this.dealerHand) {
            if(card.getFileName().equalsIgnoreCase("ace.png") && card.getCardValue() == 11) {
                if(this.getDealerHandValue() > 17) {
                    card.setCardValue(1);
                    this.updateHandValues();
                }
            }
        }
        if(this.getDealerHandValue() > 21)
            return -1;
        return 0;
    }

    /**
     * Compares the hand values of the dealer and the player to see who won the game
     * @return 1 the player beat the dealer
     * @return -1 the dealer beat the player
     * @return 0 the dealer and the player tied
     */
    public int checkGameStatus() {
        if(this.dealerHandValue > 21)
            return -2;

        if(this.playerHandValue > this.dealerHandValue)
            return 1;
        else if(this.playerHandValue < this.dealerHandValue)
            return -1;
        return 0;
    }
}