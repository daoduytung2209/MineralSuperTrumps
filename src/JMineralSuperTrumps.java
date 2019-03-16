import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class JMineralSuperTrumps extends JFrame implements ActionListener {
    private static LaunchControl launchControl;

    private JPanel mp, cardsPanel, infoPanel, playerPanel, statusPanel, buttonPanel;
    public JLabel playerNameLb, cardAmountLb, cardPileLb, prompt, playingCategoryLb, playingValueLb, remainingCardsLb;
    private JButton passButton;
    private Icon cardPileDefault, cardImg;
    private JScrollPane scroll;
    private Font bigFont = new Font("Arial", Font.BOLD, 24);

    private Game game;
    private Table table;
    private ArrayList<JButton> cardButtons;
    public int playerIndex;

    JMineralSuperTrumps(Game game, Table table){
        super("Mineral Super Trumps");
        mp = new JPanel(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mp);
        infoPanel = new JPanel(new GridLayout(1,3));
        cardsPanel = new JPanel();
        scroll = new JScrollPane(cardsPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        cardButtons = new ArrayList<JButton>();
        mp.add(infoPanel, BorderLayout.CENTER);
        mp.add(scroll, BorderLayout.SOUTH);
        playerPanel = new JPanel(new GridLayout(3,1));
        statusPanel = new JPanel(new GridLayout(3,1));
        buttonPanel = new JPanel(new GridLayout(1,1));
        playerNameLb = new JLabel("Player Tung's Turn");
        playerNameLb.setFont(bigFont);
        cardAmountLb = new JLabel("Number of cards: ");
        cardAmountLb.setFont(bigFont);
        cardPileDefault = new ImageIcon(new ImageIcon("images\\Slide66.jpg").getImage().getScaledInstance(300, 380,
                Image.SCALE_DEFAULT));
        cardPileLb = new JLabel(cardPileDefault);
        cardPileLb.setOpaque(true);
        cardPileLb.setBackground(Color.LIGHT_GRAY);
        prompt = new JLabel();
        prompt.setFont(bigFont);
        prompt.setPreferredSize(new Dimension(300, 80));
        prompt.setHorizontalAlignment(SwingConstants.CENTER);
        prompt.setBorder(BorderFactory.createEtchedBorder());
        mp.add(prompt, BorderLayout.NORTH);
        playingCategoryLb = new JLabel("  Playing category: ");
        playingCategoryLb.setFont(bigFont);
        playingValueLb = new JLabel("  Playing value: ");
        playingValueLb.setFont(bigFont);
        playingValueLb.setBorder(BorderFactory.createEtchedBorder());
        remainingCardsLb = new JLabel("  Remaining cards on table: ");
        remainingCardsLb.setFont(bigFont);
        passButton = new JButton("Pass");
        passButton.setFont(bigFont);

        buttonPanel.add(passButton);
        infoPanel.add(playerPanel);
        infoPanel.add(cardPileLb);
        infoPanel.add(statusPanel);
        playerPanel.add(playerNameLb);
        playerPanel.add(buttonPanel);
        playerPanel.add(cardAmountLb);
        statusPanel.add(playingCategoryLb);
        statusPanel.add(playingValueLb);
        statusPanel.add(remainingCardsLb);
        passButton.addActionListener(this);

        this.game = game;
        this.table = table;
    }

    public static void setLaunchControl(LaunchControl lc) {
        launchControl = lc;
    }

    public void play(){
        game.setDealer();
        for (int i = 0; i < game.playerAmount; i++){
            game.players.get(i).drawCards(8);
        }
        int dealerIndex = game.getDealerIndex();
        playerIndex = (dealerIndex + 1) % game.players.size();
        prompt.setText("Player " + game.players.get(dealerIndex).getName() + " is a dealer for this round. " +
                "Player " + game.players.get(playerIndex).getName() + " will go first.");
        gameFlow();
    }

    public void gameFlow(){
        if (game.playerAmount == 1){
            launchControl.launchWinnerPromptFrame();
            setVisible(false);
        }
        else {
            if (game.newRound) {
                playerIndex = game.getLeadPlayerIndex();
                for (Player p : game.players) {
                    p.inRound = true;
                }
                prompt.setText("The current round has ended. Start new round!");
                playingCategoryLb.setText("  Playing category: ");
                playingValueLb.setText("  Playing value: ");
                cardPileLb.setIcon(cardPileDefault);
                game.newRound = false;
            }
            if (game.players.get(playerIndex).inRound) {
                playerNameLb.setText("Player " + game.players.get(playerIndex).getName() + "'s turn: ");
                cardAmountLb.setText("Number of cards: " + game.players.get(playerIndex).getHand().getCards().size());
                remainingCardsLb.setText("  Remaining cards on table: " + table.getAllCards().size());
                for (int i = 0; i < game.players.get(playerIndex).getHand().getCards().size(); i++) {
                    cardImg = new ImageIcon(new ImageIcon("images\\" + game.players.get(playerIndex).getHand().getCards().get(i).getFilename())
                            .getImage().getScaledInstance(300, 380, Image.SCALE_DEFAULT));
                    cardButtons.add(new JButton(cardImg));
                    cardButtons.get(i).addActionListener(this);
                    cardsPanel.add(cardButtons.get(i));
                }
            } else {
                playerIndex = (playerIndex + 1) % game.players.size();
                gameFlow();
            }
        }
    }

    public void clearCardsPanel(){
        cardButtons.clear();
        cardsPanel.removeAll();
        cardsPanel.revalidate();
        cardsPanel.repaint();
    }

    public void changeCategoryByTrump(String cardName){
        switch (cardName){
            case "The Miner":
                game.playerChoiceCategory = "Economic Value";
                break;
            case "The Petrologist":
                game.playerChoiceCategory = "Crustal Abundance";
                break;
            case "The Gemmologist":
                game.playerChoiceCategory = "Hardness";
                break;
            case "The Mineralogist":
                game.playerChoiceCategory= "Cleavage";
                break;
            case "The Geophysicist":
                game.playerChoiceCategory = "Specific Gravity";
                break;
        }
        game.playingCategory = game.playerChoiceCategory;
        game.setPlayingValue(0);
        game.playingValueAsString = "";
        playingCategoryLb.setText("  Playing category: " + game.playingCategory);
        playingValueLb.setText("  Playing value: " + game.getPlayingValueAsString());
    }

    public void determineWinner(){
        if (game.hasWinner){
            game.playerAmount--;
            game.hasWinner = false;
        }
        if (game.players.get(playerIndex).getHand().getCards().size() == 0) {
            game.hasWinner = true;
            if (game.playerAmount == 2){
                game.playerAmount--;
            }
            game.setWinnerIndex(playerIndex);
            game.players.get(playerIndex).isWinner = true;
            game.countWinner++;
            if (game.countWinner == 1){
                prompt.setText("Congratulations " + game.players.get(playerIndex).getName() + "! You are the winner!\n");
            }
            else {
                prompt.setText("Congratulations " + game.players.get(playerIndex).getName() +"! Your hand is empty!\n");
            }
            game.winners.add(game.players.get(playerIndex));
            game.players.remove(playerIndex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (!((JButton) source).getText().contains("Pass")){
            int cardIndex = cardButtons.indexOf(source);
            if (game.players.get(playerIndex).getHand().getCards().get(cardIndex).isTrump) {
                if (game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName().equals("The Geologist")) {
                    launchControl.launchChooseCategoryFrame(playerIndex, cardIndex);
                }
                else if (game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName().equals("The Geophysicist")
                        && game.players.get(playerIndex).hasMagnetite() && game.players.get(playerIndex).hasTheGeophysicist()){
                    launchControl.launchSpecialWinningFrame(playerIndex, cardIndex);
                }
                else {
                    game.startRound = false;
                    changeCategoryByTrump(game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName());
                    cardPileLb.setIcon(new ImageIcon(new ImageIcon("images\\" + game.players.get(playerIndex).getHand().getCards().get(cardIndex).getFilename())
                            .getImage().getScaledInstance(300, 380, Image.SCALE_DEFAULT)));
                    prompt.setText("Player " + game.players.get(playerIndex).getName()
                            + " has changed the current playing category to: " + game.getPlayingCategory());
                    game.players.get(playerIndex).getHand().getCards().remove(cardIndex);
                    game.trumpReset();
                    determineWinner();
                    game.newRoundChecking();
                    if (game.hasWinner) { //when the game has winner, the players array size is minus one so that
                                          //the player turn sequence are affected, this "if" statement helps to resolve
                                          //the sequence problem
                        if (playerIndex == game.players.size()) {
                            playerIndex = 0;
                        }
                    }
                    clearCardsPanel();
                    gameFlow();
                }
            } else {
                if (game.startRound) {
                    launchControl.launchChooseCategoryFrame(playerIndex, cardIndex);
                } else {
                    int categorySelectionNum = -1;
                    switch (game.playingCategory) {
                        case "Hardness":
                            categorySelectionNum = 1;
                            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                            break;
                        case "Specific Gravity":
                            categorySelectionNum = 2;
                            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                            break;
                        case "Cleavage":
                            categorySelectionNum = 3;
                            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                            break;
                        case "Crustal Abundance":
                            categorySelectionNum = 4;
                            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                            break;
                        case "Economic Value":
                            categorySelectionNum = 5;
                            game.setPlayerChoiceValue(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                            break;
                    }
                    if (game.getPlayerChoiceValue() > game.getPlayingValue()) {
                        game.setPlayingValueAsString(game.players.get(playerIndex).getHand().getCards(), cardIndex, categorySelectionNum);
                        game.setPlayingValue(game.getPlayerChoiceValue());
                        cardPileLb.setIcon(new ImageIcon(new ImageIcon("images\\" + game.players.get(playerIndex).getHand().getCards().get(cardIndex).getFilename())
                                .getImage().getScaledInstance(300, 380, Image.SCALE_DEFAULT)));
                        prompt.setText("Player " + game.players.get(playerIndex).getName() + " plays " +
                                game.players.get(playerIndex).getHand().getCards().get(cardIndex).getName() + ", " + game.getPlayerChoiceCategory()
                                + ", " + game.getPlayingValueAsString());
                        playingValueLb.setText("  Playing value: " + game.getPlayingValueAsString());
                        game.players.get(playerIndex).getHand().getCards().remove(cardIndex);
                        determineWinner();
                        game.newRoundChecking();
                        if (game.hasWinner) { //when the game has winner, the players array size is minus one so that
                                              //the player turn sequence are affected, this "if" statement helps to resolve
                                              //the sequence problem
                            if (playerIndex == 0){
                                playerIndex = game.players.size() - 1;
                            }
                            else {
                                playerIndex = playerIndex - 1;
                            }
                        }
                        playerIndex = (playerIndex + 1) % game.players.size();
                        clearCardsPanel();
                        gameFlow();
                    } else {
                        prompt.setText("Your playing card must have the same category as round's playing category and " +
                                "higher value than the current playing value.");
                    }
                }
            }
        }
        else if (source == passButton){
            clearCardsPanel();
            prompt.setText("Player " + game.players.get(playerIndex).getName() + " has passed his/her turn.");
            game.players.get(playerIndex).drawCards(1);
            game.players.get(playerIndex).inRound = false;
            game.countOutPlayer++;
            game.newRoundChecking();
            playerIndex = (playerIndex + 1) % game.players.size();
            gameFlow();
        }
    }
}
