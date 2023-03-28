import java.util.List;

public class DictionaryEN2VN extends Dictionary {
    private static DictionaryEN2VN instance = null;

    private DictionaryEN2VN() {
        try {
            // importRecords("./Assets/Anh_Viet.xml");
            setRecords(XMLReader.readXML("/Assets/Anh_Viet.xml").getRecords());
            // setRecords(importRecords("./Assets/Anh_Viet.xml").getRecords());
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

    // public void importRecords(String fileName) {
    //     super.importRecords(fileName);
    // }

    public Dictionary importRecords(String fileName) {
        return super.importRecords(fileName);
    }
}
