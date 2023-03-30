import java.util.List;

public class DictionaryEN2VN extends Dictionary {
    private static DictionaryEN2VN instance = null;

    private DictionaryEN2VN(String fileName) {
        try {
            setRecords(XMLReader.readXML(fileName).getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DictionaryEN2VN() {
        try {
            setRecords(XMLReader.readXML("Assets/Anh_Viet.xml").getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DictionaryEN2VN getInstance(String fileName) {
        if (instance == null) {
            synchronized (DictionaryEN2VN.class) {
                if (instance == null) {
                    instance = new DictionaryEN2VN(fileName);
                }
            }
        }
        return instance;
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

    // public Dictionary importRecords(String fileName) {
    //     return super.importRecords(fileName);
    // }

    public void exportRecords(String fileName) {
        super.exportRecords(fileName, instance);
    }
}
