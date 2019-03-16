public class SupertrumpCard extends Card{
    private String filename;
    private String description;

    public SupertrumpCard(String name, String filename, String description){
        super.name = name;
        super.isTrump = true;
        this.filename = filename;
        this.description = description;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getHardness() {
        return null;
    }

    @Override
    public String getGravity() {
        return null;
    }

    @Override
    public String getCleavage() {
        return null;
    }

    @Override
    public String getCrustalAbundance() {
        return null;
    }

    @Override
    public String getEcoValue() {
        return null;
    }

    @Override
    public String getCategoryInPlay(int categorySelectionNum) {
        return null;
    }

    @Override
    public String getCategoryValueInPlay(int categorySelectionNum) {
        return null;
    }
}
