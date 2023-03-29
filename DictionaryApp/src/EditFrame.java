import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EditFrame extends JFrame {
    static Color myThemeColor = Color.BLUE;
    JButton addButton, removeButton, updateButton;
    Record newRecord;
    JTable editWordTable;
    DictionaryTableModel model;
    JTextField editWordField, editMeaningField;

    EditFrame() {
        setTitle("Edit " + ((MainFrame.myEditDictionaryType == 1) ? "English-Vietnamese" : "Vietnamese-English")
                + " Dictionary");

        JPanel stackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);

        JLabel editWordLabel = new JLabel("Word:");
        editWordLabel.setFont(new Font("Times", Font.BOLD, 14));
        editWordLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editWordLabel, c);

        editWordField = new JTextField();
        editWordField.setPreferredSize(new Dimension(200, 25));
        stackPanel.add(editWordField);
        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editWordField, c);

        JLabel editMeaningLabel = new JLabel("Meaning:");
        editMeaningLabel.setFont(new Font("Times", Font.BOLD, 14));
        editMeaningLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editMeaningLabel, c);

        editMeaningField = new JTextField();
        editMeaningField.setPreferredSize(new Dimension(200, 25));
        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editMeaningField, c);

        addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(addButton, c);

        removeButton = new JButton("Remove");
        removeButton.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(removeButton, c);

        updateButton = new JButton("Update");
        updateButton.setPreferredSize(new Dimension(200, 30));
        c.gridx = 0;
        c.gridy = 6;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(updateButton, c);

        JLabel nonLabel = new JLabel();
        c.gridx = 0;
        c.gridy = 7;
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
                    // String newWord = editWordField.getText().trim();
                    // String newMeaning = editMeaningField.getText().trim();
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