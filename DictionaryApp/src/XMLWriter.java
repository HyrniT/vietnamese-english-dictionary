import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLWriter {
    public static void writeXML(String fileName, Dictionary dictionary) throws ParserConfigurationException, TransformerException {
        List<Record> records = dictionary.getRecords();
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        document.setXmlStandalone(true);
        Element rootElement = document.createElement("dictionary");
        for (Record record : records) {
            Element recordElement = document.createElement("record");
            Element wordElement = document.createElement("word");
            wordElement.appendChild(document.createTextNode(record.getWord()));
            recordElement.appendChild(wordElement);
            Element meaningElement = document.createElement("meaning");
            meaningElement.appendChild(document.createTextNode(record.getMeaning()));
            recordElement.appendChild(meaningElement);
            rootElement.appendChild(recordElement);
        }
        document.appendChild(rootElement);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        
        Writer out;
        try {
            out = new OutputStreamWriter(new FileOutputStream(new File(fileName)), "UTF-8");
            transformer.transform(new DOMSource(document), new StreamResult(out));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}