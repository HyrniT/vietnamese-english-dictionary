import java.util.List;

public class DictionaryFavorite extends Dictionary {
    private static DictionaryFavorite instance = null;

    private DictionaryFavorite(String fileName) {
        try {
            setRecords(XMLReader.readXML(fileName).getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DictionaryFavorite getInstance(String fileName) {
        if (instance == null) {
            synchronized (DictionaryEN2VN.class) {
                if (instance == null) {
                    instance = new DictionaryFavorite(fileName);
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
    
    public void exportRecords(String fileName) {
        super.exportRecords(fileName, instance);
    }
}
