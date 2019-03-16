import java.io.*;
import java.util.ArrayList;

public class Table {
    private ArrayList<Card> allCards;

    public Table() {
        allCards = new ArrayList<Card>();
        ArrayList<String[]> mineralCards = new ArrayList<String[]>();
        ArrayList<String[]> supertrumpCards = new ArrayList<String[]>();
        collectCards("Cards.txt", mineralCards);
        collectCards("Supertrump Cards.txt", supertrumpCards);
    }

    public void collectCards(String fileName, ArrayList<String[]> cards){
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String attrLine = br.readLine();
            String cardInfo;
            while ((cardInfo = br.readLine()) != null){
                String[] cardElements = cardInfo.split(",");
                cards.add(cardElements);
            }
            switch (fileName){
                case "Cards.txt":
                    for (String[] cardElements : cards) {
                        allCards.add(new MineralCard(cardElements[0], cardElements[1], cardElements[2], cardElements[3],
                                cardElements[4], cardElements[5], cardElements[6]));
                    }
                    break;
                case "Supertrump Cards.txt":
                    for (String[] cardElements : cards) {
                        allCards.add(new SupertrumpCard(cardElements[0], cardElements[1], cardElements[2]));
                    }
                    break;
            }
            br.close();
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        catch (IOException e){
            System.out.println();
        }
    }

    public Card draw() {
        if (!isEmpty()) {
            return allCards.remove(allCards.size() - 1);
        } else {
            return null;
        }
    }

    public boolean isEmpty() {
        return allCards.isEmpty();
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }
}
