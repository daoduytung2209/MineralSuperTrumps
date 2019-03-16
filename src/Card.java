public abstract class Card {
    protected String name;
    protected boolean isTrump;

    public String getName(){
        return name;
    }

    public abstract String getFilename();

    public abstract String getDescription();

    public abstract String getHardness();

    public abstract String getGravity();

    public abstract String getCleavage();

    public abstract String getCrustalAbundance();

    public abstract String getEcoValue();

    public abstract String getCategoryInPlay(int categorySelectionNum);

    public abstract String getCategoryValueInPlay(int categorySelectionNum);
}
