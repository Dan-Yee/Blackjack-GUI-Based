/**
 * Implementation of a playing card object
 * @author Dan Yee
 */
public class Card {
    private String suitName;
    private String fileName;
    private int cardValue;

    public Card(String suitName, String fileName, int cardValue) {
        this.suitName = suitName;
        this.fileName = fileName;
        this.cardValue = cardValue;
    }

    /**
     * Gets the value of the card
     * @return the value of the card
     */
    public int getCardValue() {
        return this.cardValue;
    }

    /**
     * Changes the value of the card - for Aces Only
     * @param cardValue the new value of the card
     */
    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    /**
     * Gets the file name of the card to build a path for display of image
     * @return the file name of the card
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * Gets the suit of the card to build a path for display of image
     * @return the suit of the card
     */
    public String getSuitName() {
        return this.suitName;
    }
    
    /**
     * Get the file path of the image representing the card object
     * @return the file path of the image
     */
    public String getPath() {
        return "/cards/" + this.getSuitName() + "/" + this.getFileName();
    }
}