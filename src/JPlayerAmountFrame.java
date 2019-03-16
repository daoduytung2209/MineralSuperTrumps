import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//this class is the initialization of the game, run this class to start the game
public class JPlayerAmountFrame extends JFrame implements ActionListener {
    private static LaunchControl launchControl;
    private static Game game;
    private static Table table;

    private JPanel mp, buttonPanel, imagePanel, userPanel;
    private JLabel message, greeting, image;
    private JButton button3, button4, button5;
    private Icon img;
    private Font bigFont = new Font("Arial", Font.BOLD, 20);

    JPlayerAmountFrame(){
        super("Mineral Super Trumps");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp = new JPanel(new GridLayout(1,2));
        setContentPane(mp);
        userPanel = new JPanel(new GridLayout(3,1));
        imagePanel = new JPanel(new GridLayout(1,1));
        buttonPanel = new JPanel(new GridLayout(1,3));
        mp.add(imagePanel);
        mp.add(userPanel);

        greeting = new JLabel("Welcome to Mineral Super Trumps!");
        greeting.setFont(bigFont);
        img = new ImageIcon(new ImageIcon("images\\Slide65.jpg").getImage().getScaledInstance(300, 380,
                Image.SCALE_DEFAULT));
        image = new JLabel(img);
        message = new JLabel("Please select the amount of players:");
        message.setFont(bigFont);
        button3 = new JButton("3 players");
        button3.setFont(bigFont);
        button4 = new JButton("4 players");
        button4.setFont(bigFont);
        button5 = new JButton("5 players");
        button5.setFont(bigFont);
        imagePanel.add(image);
        userPanel.add(greeting);
        userPanel.add(message);
        userPanel.add(buttonPanel);
        buttonPanel.add(button3);
        buttonPanel.add(button4);
        buttonPanel.add(button5);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        launchControl = new LaunchControl();
        game = new Game();
        table = new Table();
    }

    public static void main(String[] args) {
        JPlayerAmountFrame jpaf = new JPlayerAmountFrame();
        launchControl.setGame(game);
        launchControl.setTable(table);
        JPlayerSetupFrame.setLaunchControl(launchControl);
        JMineralSuperTrumps.setLaunchControl(launchControl);
        JChooseCategoryFrame.setLaunchControl(launchControl);
        JSpecialWinningFrame.setLaunchControl(launchControl);
        JWinnerPromptFrame.setLaunchControl(launchControl);
        jpaf.setSize(800, 350);
        jpaf.setLocationRelativeTo(null);
        jpaf.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == button3){
            game.playerAmount = 3;
        }
        else if (source == button4){
            game.playerAmount = 4;
        }
        else if (source == button5){
            game.playerAmount = 5;
        }
        setVisible(false);
        launchControl.launchPlayerSetupFrame(1);
    }
}
