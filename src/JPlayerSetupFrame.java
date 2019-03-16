import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;

public class JPlayerSetupFrame extends JFrame implements ActionListener {
    private static LaunchControl launchControl;
    private static int countPlayer = 1;

    private JPanel mp, buttonPanel;
    private JLabel message;
    private JTextField nameTextField;
    private JButton but;
    private Font bigFont = new Font("Arial", Font.BOLD, 20);

    private Game game;
    private Table table;

    JPlayerSetupFrame(Game game, Table table, int countPlayer){
        super("Mineral Super Trumps");
        mp = new JPanel(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mp);
        buttonPanel = new JPanel();
        message = new JLabel("Enter a name for player " + countPlayer + ":");
        message.setFont(bigFont);
        nameTextField = new JTextField(20);
        nameTextField.setFont(bigFont);
        but = new JButton("OK");
        but.setFont(bigFont);
        add(message);
        add(nameTextField);
        add(buttonPanel);
        buttonPanel.add(but);
        but.addActionListener(this);

        this.game = game;
        this.table = table;
    }

    public static void setLaunchControl(LaunchControl lc) {
        launchControl = lc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String playerName = nameTextField.getText();
        game.players.add(new Player(table, playerName));
        countPlayer++;
        setVisible(false);
        if (countPlayer > game.playerAmount) {
            Collections.shuffle(table.getAllCards());
            launchControl.launchMineralSuperTrumps();
        }
        else {
            launchControl.launchPlayerSetupFrame(countPlayer);
        }
    }
}
