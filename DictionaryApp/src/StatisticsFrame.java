// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class StatisticsFrame extends JFrame {
//     JTextField startDateText, endDateText;
//     JLabel startDateLabel, endDateLable;
//     JButton okButton;
//     JTable statisticsTable;
//     WordDateSearchTableModel model;

//     StatisticsFrame() {
//         JPanel stackPanel = new JPanel(new GridBagLayout());
//         GridBagConstraints c = new GridBagConstraints();  

//         startDateLabel = new JLabel("Start Date (yyyy-mm-dd):");
//         startDateLabel.setFont(new Font("Times", Font.BOLD, 14));
//         startDateLabel.setForeground(MainFrame.myThemeColor);
//         c.gridx = 0;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 10.0;
//         c.weighty = 1.0;
//         stackPanel.add(startDateLabel, c);

//         startDateText = new JTextField();
//         startDateText.setPreferredSize(new Dimension(100,20));
//         c.gridx = 1;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 5.0;
//         c.weighty = 1.0;
//         stackPanel.add(startDateText, c);

//         JLabel nonLabel = new JLabel();
//         nonLabel.setPreferredSize(new Dimension(20,20));
//         c.gridx = 2;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 1.0;
//         c.weighty = 1.0;
//         stackPanel.add(nonLabel, c);

//         endDateLable = new JLabel("End Date (yyyy-mm-dd):");
//         endDateLable.setFont(new Font("Times", Font.BOLD, 14));
//         endDateLable.setForeground(MainFrame.myThemeColor);
//         c.gridx = 3;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 10.0;
//         c.weighty = 1.0;
//         stackPanel.add(endDateLable);

//         endDateText = new JTextField();
//         endDateText.setPreferredSize(new Dimension(100,20));
//         c.gridx = 4;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 5.0;
//         c.weighty = 1.0;
//         stackPanel.add(endDateText, c);

//         okButton = new JButton("OK");
//         c.gridx = 5;
//         c.gridy = 0;
//         c.gridheight = 1;
//         c.gridwidth = 1;
//         c.weightx = 1.0;
//         c.weighty = 1.0;
//         stackPanel.add(okButton, c);

//         stackPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 10, 50));
//         add(stackPanel, BorderLayout.NORTH);

//         model = new WordDateSearchTableModel(WordDateSearch.getInstance().getWordFrequencyMap());

//         statisticsTable = new JTable(model);
//         JScrollPane scrollPane = new JScrollPane(statisticsTable);
//         scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
//         add(scrollPane, BorderLayout.CENTER);

//         okButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 // Code here ....
//             }
//         });

//         pack();
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         setSize(800, 500);
//         setVisible(true);
//     }

//     public static void main(String args[]) {
//         new StatisticsFrame();
//     }
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class StatisticsFrame extends JFrame  {
    JTextField startDateText, endDateText;
    JLabel startDateLabel, endDateLable;
    JButton okButton;
    JTable statisticsTable;
    WordDateSearchTableModel model;

    StatisticsFrame() {
        
    
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        startDateLabel = new JLabel("Start Date (yyyy-mm-dd):");
        startDateLabel.setFont(new Font("Times", Font.BOLD, 13));
        startDateLabel.setForeground(MainFrame.myThemeColor);
        inputPanel.add(startDateLabel);

        startDateText = new JTextField(10);
        inputPanel.add(startDateText);

        endDateLable = new JLabel("End Date (yyyy-mm-dd):");
        endDateLable.setFont(new Font("Times", Font.BOLD, 13));
        endDateLable.setForeground(MainFrame.myThemeColor);
        inputPanel.add(endDateLable);

        endDateText = new JTextField(10);
        inputPanel.add(endDateText);

        okButton = new JButton("OK");
        inputPanel.add(okButton);

        topPanel.add(inputPanel, BorderLayout.NORTH);

        model = new WordDateSearchTableModel(WordDateSearch.getInstance().getWordFrequencyMap());
        statisticsTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(statisticsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        topPanel.add(scrollPane, BorderLayout.CENTER);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code here ....
            }
        });

        add(topPanel);

        
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    WordDateSearch.getInstance().loadFromFile("Assets/History.txt");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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
            public void windowClosed(WindowEvent e) { }

            @Override
            public void windowIconified(WindowEvent e) { }

            @Override
            public void windowDeiconified(WindowEvent e) { }

            @Override
            public void windowActivated(WindowEvent e) { }

            @Override
            public void windowDeactivated(WindowEvent e) { }
        });

        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setVisible(true);
    }

    // public static void main(String args[]) {
    //     new StatisticsFrame();
    // }
}
