import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Dictionary dictionary = DictionaryEN2VN.getInstance();
        // XMLWriter.writeXML("./Assets/output.xml", dictionary.getRecords());

        // List<Record> records = XMLReader.readXML("./Assets/Anh_Viet.xml");
        // XMLWriter.writeXML("./Assets/output.xml", records);

        // Dictionary dictionary = XMLReader.readXML("./Assets/Anh_Viet.xml");
        Dictionary dictionary = DictionaryEN2VN.getInstance();
        XMLWriter.writeXML("./Assets/output.xml", dictionary);
    }
}
