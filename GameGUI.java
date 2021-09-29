import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Implementaion of graphical user interface for a Blackjack game
 * @author Dan Yee
 */
public class GameGUI implements ActionListener {
    private JFrame gameFrame;
    private JButton hitButton, standButton, quitButton, newGame;
    private JLabel headerText, dealerText, playerText, statusText, dealerFirstCard;
    private Blackjack blackjack;
    private int dealerDisplayOffsetX, playerDisplayOffsetX;

    public GameGUI() {
        this.gameFrame = new JFrame("Blackjack");

        this.hitButton = new JButton("Hit");
        this.standButton = new JButton("Stand");
        this.quitButton = new JButton("Quit");
        this.newGame = new JButton("New Game");

        this.headerText = new JLabel("Welcome to Blackjack!");
        this.dealerText = new JLabel("Dealer Hand:");
        this.playerText = new JLabel("Player Hand:");
        this.statusText = new JLabel("Status: Hit or Stand?");
        this.dealerFirstCard = new JLabel();

        this.blackjack = new Blackjack();

        this.dealerDisplayOffsetX = 20;
        this.playerDisplayOffsetX = 20;

        this.setupGUI();
        this.newGame();
    }

    /**
     * Setups the JFrame and adds all JButtons and JLabels needed
     */
    private void setupGUI() {
        // Setup for JFrame
        this.gameFrame.setSize(750, 500);
        this.gameFrame.setLayout(null);
        this.gameFrame.setVisible(true);
        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // edit JLabels
        this.headerText.setBounds(300, 20, 300, 40);
        this.dealerText.setBounds(20, 50, 300, 40);
        this.playerText.setBounds(20, 200, 300, 40);
        this.statusText.setBounds(20, 375, 750, 40);

        // edit JButtons
        this.hitButton.setBounds(285, 410, 75, 30);
        this.standButton.setBounds(370, 410, 75, 30);
        this.quitButton.setBounds(620, 10, 100, 20);
        this.newGame.setBounds(620, 35, 100, 20);

        // add JLabels to frame
        this.gameFrame.add(this.headerText);
        this.gameFrame.add(this.dealerText);
        this.gameFrame.add(this.playerText);
        this.gameFrame.add(this.statusText);

        // add JButtons to frame
        this.gameFrame.add(this.hitButton);
        this.gameFrame.add(this.standButton);
        this.gameFrame.add(this.quitButton);
        this.gameFrame.add(this.newGame);

        // add Action Listeners to JButtons
        this.hitButton.addActionListener(this);
        this.standButton.addActionListener(this);
        this.quitButton.addActionListener(this);
        this.newGame.addActionListener(this);
    }
    
    @Override
    /**
     * Action Listener for buttons on the JFrame
     * @param event the event being triggered
     */
    public void actionPerformed(ActionEvent event) {
        Card hitCard;
        JLabel newCard;

        if(event.getSource() == this.hitButton) {
            hitCard = this.blackjack.hitFunction(1);
            newCard = new JLabel(new ImageIcon(GameGUI.class.getResource(hitCard.getPath())));

            newCard.setBounds(this.playerDisplayOffsetX, 250, 72, 96);
            this.gameFrame.add(newCard);
            this.playerDisplayOffsetX += 77;

            // implement hit cap to prevent cards going out of range of frame dimensions
            if(this.playerDisplayOffsetX > 700) {
                this.statusText.setText("Status: You have reached the \"Hit\" cap, please \"Stand\".");
                this.hitButton.removeActionListener(this);
            }

            // check player status to see if bust and update Ace values if needed
            if(this.blackjack.checkPlayerStatus() == -1) {
                // remove action listeners if player bust to prevent further changes to current game
                this.hitButton.removeActionListener(this);
                this.standButton.removeActionListener(this);
                this.statusText.setText("Status: You busted, Dealer wins.");
            }
            this.playerText.setText("Player Hand: [Value: " + this.blackjack.getPlayerHandValue() + "]");
        } else if(event.getSource() == this.standButton) {
            // remove action listeners to prevent further player action on current game
            this.hitButton.removeActionListener(this);
            this.standButton.removeActionListener(this);

            // Reveal dealers hidden card and display dealer hand value
            this.dealerFirstCard.setIcon(new ImageIcon(GameGUI.class.getResource((this.blackjack.getDealerHand().get(0).getPath()))));
            this.statusText.setText("Status: You have chosen to \"Stand\". Dealer is now in play.");

            // Dealer must hit until their hand value is greater than or equal to 17
            while(this.blackjack.getDealerHandValue() < 17) {
                hitCard = this.blackjack.hitFunction(0);
                newCard = new JLabel(new ImageIcon(GameGUI.class.getResource(hitCard.getPath())));

                newCard.setBounds(this.dealerDisplayOffsetX, 100, 72, 96);
                this.gameFrame.add(newCard);
                this.dealerDisplayOffsetX += 77;

                // prevent extra cards from displaying outside JFrame dimensions
                if(this.dealerDisplayOffsetX > 700)
                    break;
                // if the dealer busted
                if(this.blackjack.checkDealerStatus() == -1)
                    break;
                this.dealerText.setText("Dealer Hand: [Value: " + this.blackjack.getDealerHandValue() + "]");
            }
            this.dealerText.setText("Dealer Hand: [Value: " + this.blackjack.getDealerHandValue() + "]");

            // Checks final game status to see who won
            switch(this.blackjack.checkGameStatus()) {
                case 1:
                    this.statusText.setText("Status: You beat the dealer!     Press \"New Game\" to play again.");
                    break;
                case -1:
                    this.statusText.setText("Status: You lost to the dealer. Better luck next time!     Press \"New Game\" to play again.");
                    break;
                case 0:
                    this.statusText.setText(("Status: You tied with the dealer!     Press \"New Game\" to play again."));
                    break;
                case -2:
                    this.statusText.setText("Status: Dealer busted, Player wins.     Press \"New Game\" to play again.");
                    break;
            }
        } else if(event.getSource() == quitButton) {                                                            // quit the game by disposing of the Frame and all components
            this.gameFrame.dispose();
        } else if(event.getSource() == newGame) {                                                               // launch new instance of game by disposing old Frame and components and creating new GUI instance
            this.gameFrame.dispose();
            new GameGUI();
        }

        // refresh JFrame to update display
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }

    /**
     * Initiates a new game of Blackjack
     */
    private void newGame() {
        ArrayList<Card> dealerHand;
        ArrayList<Card> playerHand;
        JLabel tempCard;

        this.blackjack.newGame();

        // get the initial hands of the dealer and player
        dealerHand = blackjack.getDealerHand();
        playerHand = blackjack.getPlayerHand();

        // display the players initial two cards
        for(int i = 0; i < 2; i++) {
            tempCard = new JLabel(new ImageIcon(GameGUI.class.getResource(playerHand.get(i).getPath())));
            tempCard.setBounds(this.playerDisplayOffsetX, 250, 72, 96);
            this.gameFrame.add(tempCard);
            this.playerDisplayOffsetX += 77;
        }

        // Check to see if player Ace needs to be updated
        // SPECIAL CASE: Player starts with 2 Aces, hand value == 12
        this.blackjack.checkPlayerStatus();
        this.playerText.setText("Player Hand: [Value: " + this.blackjack.getPlayerHandValue() + "]");

        // hide the dealers first card
        this.dealerFirstCard = new JLabel((new ImageIcon(GameGUI.class.getResource("/cards/flipped_card.png"))));
        this.dealerFirstCard.setBounds(this.dealerDisplayOffsetX, 100, 72, 96);
        this.gameFrame.add(this.dealerFirstCard);
        this.dealerDisplayOffsetX += 77;

        // display the dealers second card
        tempCard = new JLabel(new ImageIcon(GameGUI.class.getResource(dealerHand.get(1).getPath())));
        tempCard.setBounds(this.dealerDisplayOffsetX, 100, 72, 96);
        this.gameFrame.add(tempCard);
        this.dealerDisplayOffsetX += 77;

        // refresh JFrame to update display
        this.gameFrame.revalidate();
        this.gameFrame.repaint();
    }
}