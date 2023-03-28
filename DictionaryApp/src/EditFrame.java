import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EditFrame extends JFrame implements ActionListener {
    static Color myTheme = Color.BLUE;
    JButton addButton, removeButton, updateButton;
    Record tempRecord = new Record();

    EditFrame() {
        // Container contentPane = getContentPane();
        // contentPane.setLayout(new BorderLayout());

        JPanel stackPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 10, 5, 10);

        JLabel editWordLabel = new JLabel("Word:");
        editWordLabel.setFont(new Font("Times", Font.BOLD, 14));
        editWordLabel.setForeground(myTheme);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editWordLabel, c);

        JTextField editWordField = new JTextField();
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
        editMeaningLabel.setForeground(myTheme);
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        stackPanel.add(editMeaningLabel, c);

        JTextField editMeaningField = new JTextField();
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

        addButton.addActionListener(this);

        String[] columnNames = { "ID", "Name", "Age" };
        Object[][] data = { { "1", "John", "30" }, { "2", "Mary", "25" }, { "3", "Tom", "35" } };
        JTable editWordTable = new JTable(data, columnNames);
        // contentPane.add(editWordTable, BorderLayout.CENTER);
        add(editWordTable, BorderLayout.CENTER);
        // contentPane.add(stackPanel);
        add(stackPanel, BorderLayout.EAST);

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String newWord = JOptionPane.showInputDialog("Input your new word");
            if (newWord != null) {
                String newMeaning = JOptionPane.showInputDialog("Input meaning of your new word");
                if (newMeaning != null) {
                    tempRecord.setWord(newWord);
                    tempRecord.setMeaning(newMeaning);
                }
            }
        }
    }
}