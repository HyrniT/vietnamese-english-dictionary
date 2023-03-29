import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditFrame extends JFrame {
    static Color myThemeColor = Color.BLUE;
    JButton addButton, removeButton, updateButton;
    Record newRecord;
    JTable editWordTable;
    DictionaryTableModel model;
    JTextArea editWordField, editMeaningField;
    JTextField findWordField;
    int indexFind = 0;

    EditFrame() {
        setTitle("Edit " + ((MainFrame.myEditDictionaryType == 1) ? "English-Vietnamese" : "Vietnamese-English")
                + " Dictionary");

        JPanel stackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 0, 10);

        JLabel findWordLabel = new JLabel("Find word:");
        findWordLabel.setFont(new Font("Times", Font.BOLD, 14));
        findWordLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(findWordLabel, c);

        findWordField = new JTextField();
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(findWordField, c);

        JLabel editWordLabel = new JLabel("Word:");
        editWordLabel.setFont(new Font("Times", Font.BOLD, 14));
        editWordLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editWordLabel, c);

        editWordField = new JTextArea(2, 1);
        editWordField.setLineWrap(true);
        JScrollPane scrollPane1 = new JScrollPane(editWordField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(scrollPane1, c);

        JLabel editMeaningLabel = new JLabel("Meaning:");
        editMeaningLabel.setFont(new Font("Times", Font.BOLD, 14));
        editMeaningLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editMeaningLabel, c);

        editMeaningField = new JTextArea(10, 1);
        editMeaningField.setLineWrap(true);
        JScrollPane scrollPane2 = new JScrollPane(editMeaningField, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(scrollPane2, c);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(200, 25));
        c.gridx = 0;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(addButton, c);

        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(200, 25));
        c.gridx = 0;
        c.gridy = 7;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(removeButton, c);

        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(200, 25));
        c.gridx = 0;
        c.gridy = 8;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(updateButton, c);

        JLabel nonLabel = new JLabel();
        c.gridx = 0;
        c.gridy = 9;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 40.0;
        c.fill = GridBagConstraints.BOTH;
        stackPanel.add(nonLabel, c);

        // DictionaryTableModel model = null;
        if (MainFrame.myEditDictionaryType == 1) {
            model = new DictionaryTableModel(DictionaryEN2VN.getInstance().getRecords());
        }
        if (MainFrame.myEditDictionaryType == 2) {
            model = new DictionaryTableModel(DictionaryVN2EN.getInstance().getRecords());
        }

        JPanel panel = new JPanel(new BorderLayout());
        editWordTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(editWordTable);
        panel.add(scrollPane);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

        editWordTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int index = editWordTable.getSelectedRow();
                editWordField.setText(editWordTable.getValueAt(index, 0) + "");
                editMeaningField.setText(editWordTable.getValueAt(index, 1) + "");
            }
        });

        add(panel, BorderLayout.CENTER);
        add(stackPanel, BorderLayout.EAST);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addButton) {
                    String newWord = JOptionPane.showInputDialog(scrollPane, "Enter new word");
                    if (!newWord.isEmpty()) {
                        String newMeaning = JOptionPane.showInputDialog(scrollPane, "Enter meaning of new word");
                        if (!newMeaning.isEmpty()) {
                            newRecord = new Record(newWord, newMeaning);
                            JOptionPane.showMessageDialog(scrollPane, "Add successfully!");
                            model.addRecord(newRecord);
                            model.fireTableDataChanged();
                            clearFields();
                        }
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == removeButton) {
                    int index = editWordTable.getSelectedRow();
                    if (index >= 0) {
                        int ans = JOptionPane.showConfirmDialog(scrollPane, "Are you sure to delete "
                                + "\"" + editWordTable.getValueAt(index, 0) + "\"", "Delete confirmation",
                                JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(scrollPane, "Delete successfully!");
                            model.removeRecord(index);
                            model.fireTableDataChanged();
                        }
                    } else {
                        JOptionPane.showMessageDialog(scrollPane, "Please select a row to remove.");
                    }
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateButton) {
                    int index = editWordTable.getSelectedRow();
                    if (index >= 0) {
                        String word = editWordField.getText().trim();
                        String meaning = editMeaningField.getText().trim();
                        Record updatedRecord = new Record(word, meaning);
                        model.updateRecord(index, updatedRecord);
                        JOptionPane.showMessageDialog(scrollPane, "Update successfully!");
                        model.fireTableDataChanged();
                    }
                } else {
                    JOptionPane.showMessageDialog(scrollPane, "Please select a row to update.");
                }
            }
        });

        findWordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (findWordField.getText() != "") {
                        String keyWord = findWordField.getText().toLowerCase();
                        int minDistance = Integer.MAX_VALUE;
                        String closestWord = null;
                        String closestMeaning = null;
                        Dictionary dictionary = null;
                        int index = 0;

                        if (MainFrame.myEditDictionaryType == 1) {
                            dictionary = DictionaryEN2VN.getInstance();
                        }

                        if (MainFrame.myEditDictionaryType == 2) {
                            dictionary = DictionaryVN2EN.getInstance();
                        }

                        for (Record record : dictionary.getRecords()) {
                            int distance = Helper.LevenshteinDistance(keyWord, record.getWord().toLowerCase());
                            if (distance < minDistance) {
                                minDistance = distance;
                                closestWord = record.getWord();
                                closestMeaning = record.getMeaning();
                                indexFind = index;
                            }
                            index++;
                        }
                        editWordField.setText(closestWord);
                        editMeaningField.setText(closestMeaning);
                        editWordTable.setRowSelectionInterval(indexFind, indexFind);

                        Rectangle rect = editWordTable.getCellRect(indexFind, 0, true); // get the cell rectangle
                        editWordTable.scrollRectToVisible(rect); // scroll the row into view
                    }
                }
            }
        });

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }

    void clearFields() {
        editWordField.setText("");
        editMeaningField.setText("");
        // addButton.setEnabled(true);
        // updateButton.setEnabled(false);
        // removeButton.setEnabled(false);
    }
}