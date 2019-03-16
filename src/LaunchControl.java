public class LaunchControl {
    private Game game;
    private Table table;
    private JMineralSuperTrumps jmst;

    public void launchPlayerSetupFrame(int countPlayer){
        JPlayerSetupFrame jpsf = new JPlayerSetupFrame(game, table, countPlayer);
        jpsf.setSize(500, 250);
        jpsf.setLocationRelativeTo(null);
        jpsf.setVisible(true);
    }

    public void launchMineralSuperTrumps(){
        jmst = new JMineralSuperTrumps(game, table);
        jmst.setSize(1150, 1000);
        jmst.setLocationRelativeTo(null);
        jmst.setVisible(true);
        jmst.play();
    }

    public void launchChooseCategoryFrame(int playerIndex, int cardIndex){
        JChooseCategoryFrame jccf = new JChooseCategoryFrame(game, jmst, playerIndex, cardIndex);
        jccf.setSize(600,600);
        jccf.setLocationRelativeTo(null);
        jccf.setVisible(true);
    }

    public void launchSpecialWinningFrame(int playerIndex, int cardIndex){
        JSpecialWinningFrame jswf = new JSpecialWinningFrame(game, jmst, playerIndex, cardIndex);
        jswf.setSize(950, 160);
        jswf.setLocationRelativeTo(null);
        jswf.setVisible(true);
    }

    public void launchWinnerPromptFrame(){
        JWinnerPromptFrame jwpf = new JWinnerPromptFrame(game);
        jwpf.setSize(300, 300);
        jwpf.setLocationRelativeTo(null);
        jwpf.setVisible(true);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setTable(Table table){
        this.table = table;
    }
}
