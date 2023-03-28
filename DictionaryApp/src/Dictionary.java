import java.util.ArrayList;
import java.util.List;

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

    // public Dictionary importRecords(String fileName) {
    //     Dictionary dictionary = new Dictionary();
    //     try {
    //         dictionary = XMLReader.readXML(fileName);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return dictionary;
    // }
}