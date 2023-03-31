import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

public class StatisticsFrame extends JFrame {
    JTextField startDateText, endDateText;
    JLabel startDateLabel, endDateLable;
    JButton okButton;
    JTable statisticsTable;
    WordDateSearchTableModel model;

    StatisticsFrame() {

        setTitle("Statistics");

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        startDateLabel = new JLabel("Start Date (yyyy-mm-dd):");
        startDateLabel.setFont(new Font("Times", Font.BOLD, 13));
        startDateLabel.setForeground(MainFrame.myThemeColor);
        inputPanel.add(startDateLabel);

        startDateText = new JTextField(10);
        AbstractDocument document = (AbstractDocument) startDateText.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    char ch = string.charAt(i);
                    if (Character.isDigit(ch) || ch == '-') {
                        sb.append(ch);
                    }
                }
                super.insertString(fb, offset, sb.toString(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    char ch = string.charAt(i);
                    if (Character.isDigit(ch) || ch == '-') {
                        sb.append(ch);
                    }
                }
                super.replace(fb, offset, length, sb.toString(), attr);
            }
        });
        inputPanel.add(startDateText);

        endDateLable = new JLabel("End Date (yyyy-mm-dd):");
        endDateLable.setFont(new Font("Times", Font.BOLD, 13));
        endDateLable.setForeground(MainFrame.myThemeColor);
        inputPanel.add(endDateLable);

        endDateText = new JTextField(10);
        AbstractDocument document1 = (AbstractDocument) endDateText.getDocument();
        document1.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    char ch = string.charAt(i);
                    if (Character.isDigit(ch) || ch == '-') {
                        sb.append(ch);
                    }
                }
                super.insertString(fb, offset, sb.toString(), attr);
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
                    throws BadLocationException {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    char ch = string.charAt(i);
                    if (Character.isDigit(ch) || ch == '-') {
                        sb.append(ch);
                    }
                }
                super.replace(fb, offset, length, sb.toString(), attr);
            }
        });
        inputPanel.add(endDateText);

        okButton = new JButton("OK");
        inputPanel.add(okButton);

        topPanel.add(inputPanel, BorderLayout.NORTH);

        model = new WordDateSearchTableModel(WordDateSearch.getInstance().getWordFrequencyMap());
        statisticsTable = new JTable(model);
        Border border = BorderFactory.createLineBorder(Color.BLACK);
        statisticsTable.setBorder(border);
        JScrollPane scrollPane = new JScrollPane(statisticsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        topPanel.add(scrollPane, BorderLayout.CENTER);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date1 = startDateText.getText().trim();
                String date2 = endDateText.getText().trim();
                if (isValidDate(date1, date2)) {
                    Map<LocalDate, Map<String, Integer>> wordFrequencyMap = WordDateSearch.getInstance()
                            .getWordFrequencyMap();
                    Map<LocalDate, Map<String, Integer>> filteredWordFrequencyMap = new HashMap<>(wordFrequencyMap);
                    for (LocalDate date : wordFrequencyMap.keySet()) {
                        if (date.isBefore(LocalDate.parse(date1)) || date.isAfter(LocalDate.parse(date2))) {
                            filteredWordFrequencyMap.remove(date);
                        }
                    }
                    WordDateSearch filterWordDateSearch = new WordDateSearch();
                    filterWordDateSearch.setWordDateSearch(filteredWordFrequencyMap);
                    WordDateSearchTableModel filteredModel = new WordDateSearchTableModel(
                            filterWordDateSearch.getWordFrequencyMap());
                    statisticsTable.setModel(filteredModel);
                } else {
                    JOptionPane.showMessageDialog(statisticsTable, "Invalid date entered! Please try again...", "Alert",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        add(topPanel);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    WordDateSearch.getInstance().saveToFile("Assets/History.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }

    public boolean isValidDate(String date1, String date2) {
        try {
            LocalDate.parse(date1);
            LocalDate.parse(date2);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
