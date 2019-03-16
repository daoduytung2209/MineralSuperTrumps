import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JChooseCategoryFrame extends JFrame implements ActionListener {
    private static LaunchControl launchControl;

    private JLabel placeHolder, message;
    private JButton hardnessButton, specificGravityButton, cleavageButton, crustalAbundanceButton, ecoValueButton;
    private Font bigFont = new Font("Arial", Font.BOLD, 20);

    private Game game;
    private JMineralSuperTrumps jmst;
    private int playerIndex;
    private int cardIndex;

    JChooseCategoryFrame(Game game, JMineralSuperTrumps jmst, int playerIndex, int cardIndex){
        super("Mineral Super Trumps");
        setLayout(new GridLayout(7,1));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        message = new JLabel("Choose the category you want to play:");
        message.setFont(bigFont);
        message.setHorizontalAlignment(SwingConstants.CENTER);
        placeHolder = new JLabel();
        hardnessButton = new JButton("Hardness");
        hardnessButton.setFont(bigFont);
        specificGravityButton = new JButton("Specific Gravity");
        specificGravityButton.setFont(bigFont);
        cleavageButton = new JButton("Cleavage");
        cleavageButton.setFont(bigFont);
        crustalAbundanceButton = new JButton("Crustal Abundance");
        crustalAbundanceButton.setFont(bigFont);
        ecoValueButton = new JButton("Economic Value");
        ecoValueButton.setFont(bigFont);
        add(message);
        add(hardnessButton);
        add(specificGravityButton);
        add(cleavageButton);
        add(crustalAbundanceButton);
        add(ecoValueButton);
        add(placeHolder);
        hardnessButton.addActionListener(this);
        specificGravityButton.addActionListener(this);
        cleavageButton.addActionListener(this);
        crustalAbundanceButton.addActionListener(this);
        ecoValueButton.addActionListener(this);

        this.game = game;
        this.jmst = jmst;
        this.playerIndex = playerIndex;
        this.cardIndex = cardIndex;
    }

    public static void setLaunchControl(LaunchControl lc) {
        launchControl = lc;
    }

    public void assignCategory(String category, int categorySelectionNum){
        if (game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName().equals("The Geologist")){
            game.startRound = false;
            game.playerChoiceCategory = category;
            game.playingCategory = game.playerChoiceCategory;
            game.setPlayingValue(0);
            game.playingValueAsString = "";
            jmst.playingCategoryLb.setText("  Playing category: " + game.playingCategory);
            jmst.playingValueLb.setText("  Playing value: " + game.getPlayingValueAsString());
            jmst.cardPileLb.setIcon(new ImageIcon(new ImageIcon("images\\" + game.players.get(playerIndex).getHand().getCards().get(cardIndex).getFilename())
                    .getImage().getScaledInstance(300, 380, Image.SCALE_DEFAULT)));
            jmst.prompt.setText("Player " + game.players.get(playerIndex).getName()
                    + " has changed the current playing category to: " + game.getPlayingCategory());
            game.players.get(playerIndex).getHand().getCards().remove(cardIndex);
            game.trumpReset();
            jmst.determineWinner();
            game.newRoundChecking();
            if (game.hasWinner) {
                if (playerIndex == game.players.size()) {
                    jmst.playerIndex = 0;
                }
            }
            jmst.clearCardsPanel();
            jmst.gameFlow();
        }
        else {
            game.playerChoiceCategory = category;
            game.playingCategory = game.playerChoiceCategory;
            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
            game.setPlayingValue(game.getPlayerChoiceValue());
            game.setPlayingValueAsString(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
            jmst.cardPileLb.setIcon(new ImageIcon(new ImageIcon("images\\" + game.players.get(playerIndex).getHand().getCards().get(cardIndex).getFilename())
                    .getImage().getScaledInstance(300, 380, Image.SCALE_DEFAULT)));
            jmst.playingCategoryLb.setText("  Playing category: " + game.playerChoiceCategory);
            jmst.playingValueLb.setText("  Playing value: " + game.playingValueAsString);
            jmst.prompt.setText("Player " + game.players.get(playerIndex).getName() + " played " +
                    game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName() + ", " + game.getPlayerChoiceCategory()
                    + ", " + game.getPlayingValueAsString());
            game.players.get(playerIndex).getHand().getCards().remove(cardIndex);
            game.startRound = false;
            jmst.determineWinner();
            game.newRoundChecking();
            if (game.hasWinner) {
                if (playerIndex == 0){
                    playerIndex = game.players.size() - 1;
                }
                else {
                    playerIndex = playerIndex - 1;
                }
            }
            jmst.playerIndex = (playerIndex + 1) % game.players.size();
            jmst.clearCardsPanel();
            jmst.gameFlow();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == hardnessButton){
            assignCategory("Hardness", 1);
        }
        else if (source == specificGravityButton){
            assignCategory("Specific Gravity", 2);
        }
        else if (source == cleavageButton){
            assignCategory("Cleavage", 3);
        }
        else if (source == crustalAbundanceButton){
            assignCategory("Crustal Abundance", 4);
        }
        else if (source == ecoValueButton){
            assignCategory("Economic Value", 5);
        }
        setVisible(false);
    }
}
