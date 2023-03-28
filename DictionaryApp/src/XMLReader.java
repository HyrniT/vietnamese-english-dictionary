import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    public static Dictionary readXML(String fileName) throws Exception {
        File file = new File(fileName);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(file);
        document.getDocumentElement().normalize();
        
        List<Record> records = new ArrayList<Record>();
        NodeList nodeList = document.getElementsByTagName("record");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String word = element.getElementsByTagName("word").item(0).getTextContent();
                String meaning = element.getElementsByTagName("meaning").item(0).getTextContent();
                Record record = new Record(word, meaning);
                records.add(record);
            }
        }
        Dictionary dictionary = new Dictionary();
        dictionary.setRecords(records);
        return dictionary;
    }
}