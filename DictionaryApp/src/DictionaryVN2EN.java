import java.util.List;

public class DictionaryVN2EN extends Dictionary {
    private static DictionaryVN2EN instance = null;

    private DictionaryVN2EN() {
        try {
            setRecords(XMLReader.readXML("Assets/Viet_Anh.xml").getRecords());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DictionaryVN2EN getInstance() {
        if (instance == null) {
            synchronized (DictionaryVN2EN.class) {
                if (instance == null) {
                    instance = new DictionaryVN2EN();
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

    public Dictionary importRecords(String fileName) {
        return super.importRecords(fileName);
    }

    public void exportRecords(String fileName) {
        super.exportRecords(fileName, instance);
    }
}
