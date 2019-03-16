import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JWinnerPromptFrame extends JFrame implements ActionListener {
    private static LaunchControl launchControl;

    private JLabel winnerPlace, message;
    private JButton button;
    private Font bigFont = new Font("Arial", Font.BOLD, 20);

    JWinnerPromptFrame(Game game){
        super("Mineral Super Trumps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(game.winners.size() + 2, 1));
        message = new JLabel("The game is over!");
        message.setFont(bigFont);
        add(message);
        for (int i = 0; i < game.winners.size(); i++){
            winnerPlace = new JLabel("Winner Place " + (i+1) + ": " + game.winners.get(i).getName());
            winnerPlace.setFont(bigFont);
            add(winnerPlace);
        }
        button = new JButton("OK");
        button.setFont(bigFont);
        add(button);
        button.addActionListener(this);
    }

    public static void setLaunchControl(LaunchControl lc) {
        launchControl = lc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
