import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Dictionary {
    private List<Record> records;

    public Dictionary() {
        this.records = new ArrayList<Record>();
    }

    public void addRecord(Record record) {
        for (Record r : records) {
            if (Helper.UnicodeToASCII(r.getWord()).equals(Helper.UnicodeToASCII(record.getWord()))) {
                return;
            }
        }
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

    public void sortRecords() {
        Collections.sort(records, new Comparator<Record>() {
            @Override
            public int compare(Record r1, Record r2) {
                return r1.getWord().compareToIgnoreCase(r2.getWord());
            }
        });
    }

    // public Dictionary importRecords(String fileName) {
    //     Dictionary dictionary = new Dictionary();
    //     try {
    //         dictionary = XMLReader.readXML(fileName);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return dictionary;
    // }

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