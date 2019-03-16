public class MineralCard extends Card {
    private String filename;
    private String hardness;
    private String gravity;
    private String cleavage;
    private String crustalAbundance;
    private String ecoValue;

    public MineralCard(String name, String filename, String hardness, String gravity, String cleavage, String crustalAbundance,
                       String ecoValue){
        super.name = name;
        super.isTrump = false;
        this.filename = filename;
        this.hardness = hardness;
        this.cleavage = cleavage;
        this.gravity = gravity;
        this.crustalAbundance = crustalAbundance;
        this.ecoValue = ecoValue;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public String getHardness() {
        return hardness;
    }

    @Override
    public String getGravity() {
        return gravity;
    }

    @Override
    public String getCleavage() {
        return cleavage;
    }

    @Override
    public String getCrustalAbundance() {
        return crustalAbundance;
    }

    @Override
    public String getEcoValue() {
        return ecoValue;
    }

    @Override
    public String getCategoryInPlay(int categorySelectionNum) {
        String categoryInPlay = "";
        switch (categorySelectionNum) {
            case 1:
                categoryInPlay = "Hardness";
                break;
            case 2:
                categoryInPlay = "Specific Gravity";
                break;
            case 3:
                categoryInPlay = "Cleavage";
                break;
            case 4:
                categoryInPlay = "Crustal Abundance";
                break;
            case 5:
                categoryInPlay = "Economic Value";
                break;
        }
        return categoryInPlay;
    }

    @Override
    public String getCategoryValueInPlay(int categorySelectionNum) {
        String categoryValueInPlay = "";
        switch (categorySelectionNum) {
            case 1:
                categoryValueInPlay = getHardness();
                break;
            case 2:
                categoryValueInPlay = getGravity();
                break;
            case 3:
                categoryValueInPlay = getCleavage();
                break;
            case 4:
                categoryValueInPlay = getCrustalAbundance();
                break;
            case 5:
                categoryValueInPlay = getEcoValue();
                break;
        } return categoryValueInPlay;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
