import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JSpecialWinningFrame extends JFrame implements ActionListener {
    private static LaunchControl launchControl;

    private JLabel message;
    private JButton yesButton, noButton;
    private Font bigFont = new Font("Arial", Font.BOLD, 20);

    private Game game;
    private JMineralSuperTrumps jmst;
    private int playerIndex;
    private int cardIndex;

    JSpecialWinningFrame(Game game, JMineralSuperTrumps jmst, int playerIndex, int cardIndex){
        super("Mineral Super Trump");
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        message = new JLabel("You have Magnetite card in your hand. Do you want to place both cards to win the round?");
        message.setFont(bigFont);
        yesButton = new JButton("Yes");
        yesButton.setFont(bigFont);
        noButton = new JButton("No");
        noButton.setFont(bigFont);
        add(message);
        add(yesButton);
        add(noButton);
        yesButton.addActionListener(this);
        noButton.addActionListener(this);

        this.game = game;
        this.jmst = jmst;
        this.playerIndex = playerIndex;
        this.cardIndex = cardIndex;
    }

    public static void setLaunchControl(LaunchControl lc) {
        launchControl = lc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == yesButton){
            game.specialWinningRound(playerIndex);
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
        else if (source == noButton){
            game.startRound = false;
            game.playerChoiceCategory = "Specific Gravity";
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
        setVisible(false);
    }
}
