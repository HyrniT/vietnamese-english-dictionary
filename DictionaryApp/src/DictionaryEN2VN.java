import java.util.List;

public class DictionaryEN2VN {
    private static DictionaryEN2VN instance = null;
    private Dictionary dictionary;

    private DictionaryEN2VN() {
        dictionary = new Dictionary();
        try {
            dictionary = XMLReader.readXML("./Assets/Anh_Viet.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // instance = dictionary;
    }

    public static Dictionary getInstance() {
        if (instance == null) {
            synchronized (DictionaryEN2VN.class) {
                if (instance == null) {
                    instance = new DictionaryEN2VN();
                }
            }
        }
        return ;
    }
}