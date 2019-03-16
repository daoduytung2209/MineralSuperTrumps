import java.util.ArrayList;

//this class represents player's hand and has the function that is storing player's cards
public class Hand {
    private ArrayList<Card> cards;

    public Hand(){
        cards = new ArrayList<Card>();
    }

    public void add(Card c){
        cards.add(c);
    }

    public Card getMagnetite(){
        for (Card c : cards){
            if (c.getName().equals("Magnetite")){
                return c;
            }
        }
        return null;
    }

    public Card getTheGeophysicist(){
        for (Card c : cards){
            if (c.getName().equals("The Geophysicist")){
                return c;
            }
        }
        return null;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }
}
