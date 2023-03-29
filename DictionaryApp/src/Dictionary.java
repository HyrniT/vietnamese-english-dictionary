import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Dictionary {
    private List<Record> records;

    public Dictionary() {
        this.records = new ArrayList<Record>();
    }

    public void addRecord(Record record) {
        records.add(record);
    }

    public void removeRecord(int index) {
        records.remove(index);
    }

    public void updateRecord(int index, Record record) {
        records.set(index, record);
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public Dictionary importRecords(String fileName) {
        Dictionary dictionary = new Dictionary();
        try {
            dictionary = XMLReader.readXML(fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dictionary;
    }

    public void exportRecords(String fileName, Dictionary dictionary) {
        try {
            XMLWriter.writeXML(fileName, dictionary);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}