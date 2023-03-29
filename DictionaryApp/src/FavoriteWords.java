import java.awt.List;

public class FavoriteWords extends Dictionary {
    private static FavoriteWords instance = null;

    private FavoriteWords() {
        try {
            setRecords(XMLReader.readXML("Assets/Anh_Viet.xml").getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static FavoriteWords getInstance() {
        if (instance == null) {
            synchronized (FavoriteWords.class) {
                if (instance == null) {
                    instance = new FavoriteWords();
                }
            }
        }
        return instance;
    }

    public void setRecords(List<Record> records) {
        super.setRecords(records);
    }

    public List<Record> getRecords() {
        return super.getRecords();
    }

    // public void importRecords(String fileName) {
    //     super.importRecords(fileName);
    // }

    public Dictionary importRecords(String fileName) {
        return super.importRecords(fileName);
    }

    public void exportRecords(String fileName) {
        super.exportRecords(fileName, instance);
    }
}
