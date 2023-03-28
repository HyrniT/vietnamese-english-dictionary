import java.util.List;

public class DictionaryVN2EN {
    private static Dictionary instance = null;

    private DictionaryVN2EN() {
        Dictionary dictionary = new Dictionary();
        try {
            List<Record> records = XMLReader.readXML("Assets/Viet_Anh.xml");
            dictionary.setRecords(records);
        } catch (Exception e) {
            e.printStackTrace();
        }
        instance = dictionary;
    }

    public static Dictionary getInstance() {
        if (instance == null) {
            synchronized (DictionaryVN2EN.class) {
                if (instance == null) {
                    instance = new Dictionary();
                }
            }
        }
        return instance;
    }
}