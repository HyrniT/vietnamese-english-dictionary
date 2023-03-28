import java.util.List;

public class DictionaryEN2VN extends Dictionary {
    private static DictionaryEN2VN instance = null;

    private DictionaryEN2VN() {
        try {
            setRecords(XMLReader.readXML("./Assets/Anh_Viet.xml").getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DictionaryEN2VN getInstance() {
        if (instance == null) {
            synchronized (DictionaryEN2VN.class) {
                if (instance == null) {
                    instance = new DictionaryEN2VN();
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
}