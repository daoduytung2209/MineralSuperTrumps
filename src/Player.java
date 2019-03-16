public class Player {
    private String name;
    private Table table;
    private Hand hand;
    public boolean inRound;
    public boolean isWinner;
    public boolean isDealer;

    public Player(Table t, String name){
        this.name = name;
        hand = new Hand();
        table = t;
        isWinner = false;
        inRound = true;
        isDealer = false;
    }

    public void drawCards(int n){
        for (int i = 0; i < n; i++){
            if (!table.isEmpty()){
                Card c = table.draw();
                hand.add(c);
            }
        }
    }

    public boolean hasMagnetite(){
        if (hand.getCards().contains(hand.getMagnetite())){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasTheGeophysicist(){
        if (hand.getCards().contains(hand.getTheGeophysicist())){
            return true;
        }
        else {
            return false;
        }
    }

    public Hand getHand() {
        return hand;
    }

    public String getName() {
        return name;
    }
}
