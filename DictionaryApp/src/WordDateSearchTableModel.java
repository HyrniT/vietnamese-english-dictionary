import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

public class WordDateSearchTableModel extends AbstractTableModel {
    Map<LocalDate, Map<String, Integer>> data;
    List<Object[]> rows;

    public WordDateSearchTableModel(Map<LocalDate, Map<String, Integer>> data) {
        this.data = data;
        this.rows = new ArrayList<>();
        for (LocalDate date : data.keySet()) {
            Map<String, Integer> wordMap = data.get(date);
            for (String word : wordMap.keySet()) {
                int frequency = wordMap.get(word);
                rows.add(new Object[] { date, word, frequency });
            }
        }
    }

    @Override
    public int getRowCount() {
        return rows.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] row = rows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return row[0];
            case 1:
                return row[1];
            case 2:
                return row[2];
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Date";
            case 1:
                return "Word";
            case 2:
                return "Frequency";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return LocalDate.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return null;
        }
    }
}
