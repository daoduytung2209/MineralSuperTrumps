import java.util.ArrayList;
import java.util.Random;

//this class contains methods which help the game runs
public class Game {
    public String playerChoiceCategory;
    private double playerChoiceValue;
    public ArrayList<Player> winners; //the list of players who lose all of their cards
    public int countWinner; //count number of players who lose all of their cards
    private int winnerIndex; //index of player who lose all of his/her cards
    private double playingValue; //the double playing value is used to compare with other players's value

    public String playingValueAsString; //the string playing value is used to display to the console
    public String playingCategory;
    public boolean startRound;
    public boolean newRound;
    public boolean hasWinner; //determines if the game recently had the player who lose all of his/her cards
    public int countOutPlayer;
    public int playerAmount;
    public ArrayList<Player> players;

    public Game(){
        playingCategory = "";
        playingValue = 0;
        playingValueAsString = "";
        startRound = true;
        newRound = false;
        hasWinner = false;
        winners = new ArrayList<Player>();
        countOutPlayer = 0;
        countWinner = 0;
        playerAmount = 0;
        players = new ArrayList<Player>();
    }

    public void setDealer(){
        Random rand = new Random();
        int dealerIndex = rand.nextInt(players.size());
        players.get(dealerIndex).isDealer = true;
    }

    public int getDealerIndex(){
        int dealerIndex = 0;
        for (Player p : players){
            if (p.isDealer){
                dealerIndex = players.indexOf(p);
            }
        }
        return dealerIndex;
    }

    public void setWinnerIndex(int winnerIndex) {
        this.winnerIndex = winnerIndex;
    }

    public String getPlayerChoiceCategory() {
        return playerChoiceCategory;
    }

    public double getPlayerChoiceValue() {
        return playerChoiceValue;
    }

    public void setPlayerChoiceValue(ArrayList<Card> c, int cardIndex, int categorySelectionNum) {
        String categoryValueInPlay = c.get(cardIndex).getCategoryValueInPlay(categorySelectionNum);
        if (playerChoiceCategory.equals("Hardness") || playerChoiceCategory.equals("Specific Gravity")){
            playerChoiceValue = Double.parseDouble(categoryValueInPlay);
        }
        else if (playerChoiceCategory.equals("Cleavage")){
            playerChoiceValue = getCleavageIntVal(categoryValueInPlay);
        }
        else if (playerChoiceCategory.equals("Crustal Abundance")){
            playerChoiceValue = getCrustalAbundanceIntVal(categoryValueInPlay);
        }
        else if (playerChoiceCategory.equals("Economic Value")){
            playerChoiceValue = getEconomicValueIntVal(categoryValueInPlay);
        }
    }

    public double getPlayingValue() {
        return playingValue;
    }

    public void setPlayingValue(double value){
        playingValue = value;
    }

    public String getPlayingValueAsString() {
        return playingValueAsString;
    }

    public void setPlayingValueAsString(ArrayList<Card> c, int cardIndex, int categorySelectionNum){
        playingValueAsString = c.get(cardIndex).getCategoryValueInPlay(categorySelectionNum);
    }

    public String getPlayingCategory() {
        return playingCategory;
    }

    public void newRoundChecking() {
        if (countOutPlayer == (playerAmount - 1)) {
            if (playerAmount != 1) {
                startRound = true;
                newRound = true;
                playingCategory = "";
                countOutPlayer = 0;
                playingValue = 0;
                playingValueAsString = "";
            }
        }
    }

    public void trumpReset(){
        for (Player p : players){
            p.inRound = true;
        }
        countOutPlayer = 0;
    }

    public int getLeadPlayerIndex(){
        int leadPlayerIndex = 0;
        if (hasWinner) { //if the game recently had the player who lose all of his/her cards, and the other players cannot
            //play against the last value of that player, then when the new round starts, the lead player of
            //that round will be the player on the left of the player who just lost all of his/her cars
            leadPlayerIndex = winnerIndex % players.size();
        }
        for (int i = 0; i < players.size(); i++){
            if (players.get(i).inRound){
                leadPlayerIndex = i;
            }
        }
        return leadPlayerIndex;
    }

    public void specialWinningRound(int playerIndex){
        int magnetiteIndex = players.get(playerIndex).getHand().getCards().indexOf(players.get(playerIndex).getHand().getMagnetite());
        players.get(playerIndex).getHand().getCards().remove(magnetiteIndex);
        int theGeophysicistIndex = players.get(playerIndex).getHand().getCards().indexOf(players.get(playerIndex).getHand().getTheGeophysicist());
        players.get(playerIndex).getHand().getCards().remove(theGeophysicistIndex);
        //the following has the function is making the player who just play both special cards become the lead player of
        //the next round
        for (Player p : players){
            if (p.inRound) {
                p.inRound = false;
                countOutPlayer++;
            }
        }
        players.get(playerIndex).inRound = true;
        countOutPlayer--;
    }

    public int getCleavageIntVal(String cleavage) {
        int cleavageIntValue = 0;
        switch (cleavage) {
            case "none":
                cleavageIntValue = 1;
                break;
            case "poor/none":
                cleavageIntValue = 2;
                break;
            case "1 poor":
                cleavageIntValue = 3;
                break;
            case "2 poor":
                cleavageIntValue = 4;
                break;
            case "1 good":
                cleavageIntValue = 5;
                break;
            case "1 good/1 poor":
                cleavageIntValue = 6;
                break;
            case "2 good":
                cleavageIntValue = 7;
                break;
            case "3 good":
                cleavageIntValue = 8;
                break;
            case "1 perfect":
                cleavageIntValue = 9;
                break;
            case "1 perfect/1 good":
                cleavageIntValue = 10;
                break;
            case "1 perfect/2 good":
                cleavageIntValue = 11;
                break;
            case "2 perfect/1 good":
                cleavageIntValue = 12;
                break;
            case "3 perfect":
                cleavageIntValue = 13;
                break;
            case "4 perfect":
                cleavageIntValue = 14;
                break;
            case "6 perfect":
                cleavageIntValue = 15;
        }
        return cleavageIntValue;
    }

    public int getCrustalAbundanceIntVal(String crustalAbundance) {
        int crustalAbundanceIntVal = 0;
        switch (crustalAbundance) {
            case "ultratrace":
                crustalAbundanceIntVal = 1;
                break;
            case "trace":
                crustalAbundanceIntVal = 2;
                break;
            case "low":
                crustalAbundanceIntVal = 3;
                break;
            case "moderate":
                crustalAbundanceIntVal = 4;
                break;
            case "high":
                crustalAbundanceIntVal = 5;
                break;
            case "very high":
                crustalAbundanceIntVal = 6;
                break;
        }
        return crustalAbundanceIntVal;
    }

    public int getEconomicValueIntVal(String ecoValue) {
        int ecoValueIntVal = 0;
        switch (ecoValue) {
            case "trivial":
                ecoValueIntVal = 1;
                break;
            case "low":
                ecoValueIntVal = 2;
                break;
            case "moderate":
                ecoValueIntVal = 3;
                break;
            case "high":
                ecoValueIntVal = 4;
                break;
            case "very high":
                ecoValueIntVal = 5;
                break;
            case "I'm rich!":
                ecoValueIntVal = 6;
                break;
        }
        return ecoValueIntVal;
    }
}
