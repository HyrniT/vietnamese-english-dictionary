import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class SearchHistoryPanel extends JFrame {
    private JLabel fromDateLabel, toDateLabel, searchLabel;
    private JTextField fromDateField, toDateField, searchField;
    private JButton searchButton;
    private JTable searchTable;
    private DefaultTableModel tableModel;
    private SearchHistory searchHistory;

    public SearchHistoryPanel() {
        super("Search History GUI");
        setLayout(new GridLayout(4, 2));

        fromDateLabel = new JLabel("From date (yyyy-MM-dd):");
        toDateLabel = new JLabel("To date (yyyy-MM-dd):");
        searchLabel = new JLabel("Search word:");
        fromDateField = new JTextField();
        toDateField = new JTextField();
        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Word", "Count"}, 0);
        searchTable.setModel(tableModel);

        add(fromDateLabel);
        add(fromDateField);
        add(toDateLabel);
        add(toDateField);
        add(searchLabel);
        add(searchField);
        add(searchButton);

        JScrollPane scrollPane = new JScrollPane(searchTable);
        add(scrollPane);

        setSize(500, 300);
        setVisible(true);

        searchHistory = new SearchHistory(LocalDate.now(), LocalDate.now());

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate fromDate = null, toDate = null;

                try {
                    fromDate = LocalDate.parse(fromDateField.getText());
                    toDate = LocalDate.parse(toDateField.getText());
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(SearchHistoryPanel.this, "Invalid date format! Please use yyyy-MM-dd.");
                    return;
                }

                if (toDate.isBefore(fromDate)) {
                    JOptionPane.showMessageDialog(SearchHistoryPanel.this, "To date must be after from date!");
                    return;
                }

                String search = searchField.getText().trim();

                if (search.isEmpty()) {
                    JOptionPane.showMessageDialog(SearchHistoryPanel.this, "Please enter a search word!");
                    return;
                }

                searchHistory.setFromDate(fromDate);
                searchHistory.setToDate(toDate);

                searchTable.clearSelection();
                tableModel.setRowCount(0);

                Map<String, Integer> searchMap = searchHistory.getSearchMap();

                if (searchMap.containsKey(search)) {
                    searchHistory.addSearch(search);
                    searchMap.put(search, searchMap.get(search) + 1);
                } else {
                    searchMap.put(search, 1);
                }

                for (Map.Entry<String, Integer> entry : searchMap.entrySet()) {
                    if (entry.getValue() > 0 && (entry.getKey().startsWith(search) || entry.getKey().endsWith(search))) {
                        Object[] rowData = new Object[]{entry.getKey(), entry.getValue()};
                        tableModel.addRow(rowData);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        SearchHistoryPanel gui = new SearchHistoryPanel();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
