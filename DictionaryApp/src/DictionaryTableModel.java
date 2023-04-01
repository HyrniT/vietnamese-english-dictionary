import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DictionaryTableModel extends AbstractTableModel {
    private List<Record> records;
    private final String[] columnNames = {"Word", "Meaning"};

    public DictionaryTableModel(List<Record> records) {
        this.records = records;
    }

    @Override
    public int getRowCount() {
        return records.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Record record = records.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return record.getWord();
            case 1:
                return record.getMeaning();
            default:
                return null;
        }
    }
}