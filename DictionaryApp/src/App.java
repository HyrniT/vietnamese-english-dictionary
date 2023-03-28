
public class App {
    public static void main(String[] args) throws Exception {
        
        Dictionary dictionaryEN2VN = DictionaryEN2VN.getInstance();
        XMLWriter.writeXML("./Assets/output1.xml", dictionaryEN2VN);

        Dictionary dictionaryVN2EN = DictionaryVN2EN.getInstance();
        XMLWriter.writeXML("./Assets/output2.xml", dictionaryVN2EN);
    }
}
