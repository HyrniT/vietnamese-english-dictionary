import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
    static Color myThemeColor = Color.BLUE;
    static int myEditDictionaryType = 0;
    static int mySelectedLanguage = 0;

    JTextArea outputTextArea, inputTextArea;

    MainFrame() {
        setTitle("HyrniT's Dictionary");
        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);
        JMenu statistics = new JMenu("Statistics");
        menuBar.add(statistics);

        JMenu fileImport = new JMenu("Import");
        JMenuItem fileImportENtoVN = new JMenuItem("English-Vietnamese Dictionary");
        fileImportENtoVN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open XML File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showOpenDialog(fileImportENtoVN);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String fileName = file.getPath();
                    DictionaryEN2VN.getInstance().importRecords(fileName);
                }
            }
        });
        JMenuItem fileImportVNtoEN = new JMenuItem("Vietnamese-English Dictionary");
        fileImportVNtoEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Open XML File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showOpenDialog(fileImportVNtoEN);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String fileName = file.getPath();
                    DictionaryVN2EN.getInstance().importRecords(fileName);
                }
            }
        });
        JMenuItem fileImportFavorite = new JMenuItem("Favorite word list");
        fileImport.add(fileImportENtoVN);
        fileImport.add(fileImportVNtoEN);
        fileImport.add(fileImportFavorite);
        file.add(fileImport);

        JMenu fileExport = new JMenu("Export");
        JMenuItem fileExportENtoVN = new JMenuItem("English-Vietnamese Dictionary");
        fileExportENtoVN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save XML File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showSaveDialog(fileExportENtoVN);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    DictionaryEN2VN.getInstance().exportRecords(fileToSave.getAbsolutePath());
                }
            }
        });
        JMenuItem fileExportVNtoEN = new JMenuItem("Vietnamese-English Dictionary");
        fileExportVNtoEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save XML File");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML files", "xml");
                fileChooser.setFileFilter(filter);
                int userSelection = fileChooser.showSaveDialog(fileExportVNtoEN);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    DictionaryVN2EN.getInstance().exportRecords(fileToSave.getAbsolutePath());
                }
            }
        });
        JMenuItem fileExportFavorite = new JMenuItem("Favorite word list");
        fileExport.add(fileExportENtoVN);
        fileExport.add(fileExportVNtoEN);
        fileExport.add(fileExportFavorite);
        file.add(fileExport);

        JMenuItem editTheme = new JMenuItem("Theme");
        edit.add(editTheme);

        JMenu editDictionary = new JMenu("Dictionary");
        JMenuItem editDictionaryENtoVN = new JMenuItem("English-Vietnamese");
        editDictionaryENtoVN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myEditDictionaryType = 1;
                new EditFrame();
            }
        });
        JMenuItem editDictionaryVNtoEN = new JMenuItem("Vietnamese-English");
        editDictionaryVNtoEN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myEditDictionaryType = 2;
                new EditFrame();
            }
        });
        editDictionary.add(editDictionaryENtoVN);
        editDictionary.add(editDictionaryVNtoEN);
        edit.add(editDictionary);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Header
        JPanel headerContainer = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel headerLabel = new JLabel("☆     ★     ☆     ★     ☆     ★     ☆     ★     ☆     ★        " +
                "HyrniT's Dictionary        ★     ☆     ★     ☆     ★     ☆     ★     ☆     ★     ☆");
        headerLabel.setFont(new Font("Times", Font.BOLD, 20));
        headerLabel.setForeground(myThemeColor);
        headerContainer.add(headerLabel);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 10, 0);
        mainPanel.add(headerContainer, c);

        // Option
        JPanel optionContainer = new JPanel(new GridBagLayout());
        String languages[] = { "(Select your language)", "English", "Vietnamese" };

        JLabel inputOptionLabel = new JLabel("▶ Input language:");
        inputOptionLabel.setFont(new Font("Times", Font.BOLD, 16));
        inputOptionLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 15, 0, 0);
        optionContainer.add(inputOptionLabel, c);

        final JComboBox inputOptionComboBox = new JComboBox(languages);
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 15);
        optionContainer.add(inputOptionComboBox, c);

        JLabel outputOptionLabel = new JLabel("▶ Output language:");
        outputOptionLabel.setFont(new Font("Times", Font.BOLD, 16));
        outputOptionLabel.setForeground(myThemeColor);
        c.gridx = 3;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 20, 0, 0);
        optionContainer.add(outputOptionLabel, c);

        final JComboBox outputOptionComboBox = new JComboBox(languages);
        c.gridx = 4;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(0, 0, 0, 15);
        optionContainer.add(outputOptionComboBox, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(optionContainer, c);

        inputOptionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = inputOptionComboBox.getSelectedIndex();
                if (selectedIndex == 1) {
                    mySelectedLanguage = 1;
                    outputOptionComboBox.setSelectedIndex(2);
                } else if (selectedIndex == 2) {
                    mySelectedLanguage = 2;
                    outputOptionComboBox.setSelectedIndex(1);
                } else if (selectedIndex == 0) {
                    mySelectedLanguage = 0;
                    outputOptionComboBox.setSelectedIndex(0);
                }
            }
        });

        outputOptionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = outputOptionComboBox.getSelectedIndex();
                if (selectedIndex == 1) {
                    mySelectedLanguage = 2;
                    inputOptionComboBox.setSelectedIndex(2);
                } else if (selectedIndex == 2) {
                    mySelectedLanguage = 1;
                    inputOptionComboBox.setSelectedIndex(1);
                } else if (selectedIndex == 0) {
                    mySelectedLanguage = 0;
                    inputOptionComboBox.setSelectedIndex(0);
                }
            }
        });

        // Text area
        JPanel textAreaContainer = new JPanel(new GridLayout(1, 2, 60, 0));

        inputTextArea = new JTextArea();
        inputTextArea.setLineWrap(true);
        inputTextArea.setWrapStyleWord(true);
        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane1 = new JScrollPane(inputTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaContainer.add(scrollPane1, c);

        inputTextArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (mySelectedLanguage == 0) {
                        JOptionPane.showMessageDialog(inputTextArea, "Please choose your languge!");
                    } else {
                        String keyWord = inputTextArea.getText().replace("\n", "").toLowerCase();
                        if (!search(keyWord)) {
                            JOptionPane.showMessageDialog(inputTextArea, "Not found!");
                        }
                    }
                }
            }
        });
        outputTextArea = new JTextArea();
        outputTextArea.setText("<meaning of word>");
        outputTextArea.setEditable(false);
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
        c.gridx = 1;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane2 = new JScrollPane(outputTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textAreaContainer.add(scrollPane2, c);

        // Popup menu
        JPopupMenu popupMenu1 = new JPopupMenu();
        JMenuItem cut1 = new JMenuItem("Cut");
        JMenuItem copy1 = new JMenuItem("Copy");
        JMenuItem paste1 = new JMenuItem("Paste");

        copy1.addActionListener(e -> inputTextArea.copy());
        paste1.addActionListener(e -> inputTextArea.paste());
        cut1.addActionListener(e -> inputTextArea.cut());

        JPopupMenu popupMenu2 = new JPopupMenu();
        JMenuItem copy2 = new JMenuItem("Copy");

        copy2.addActionListener(e -> outputTextArea.copy());

        popupMenu1.add(cut1);
        popupMenu1.add(copy1);
        popupMenu1.add(paste1);
        inputTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu1.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu1.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        popupMenu2.add(copy2);

        outputTextArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu2.show(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu2.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        c.gridx = 0;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 10.0;
        c.fill = GridBagConstraints.BOTH;
        c.insets = new Insets(5, 30, 5, 30);
        mainPanel.add(textAreaContainer, c);

        // Favorite
        JPanel extendContainer = new JPanel(new GridLayout(1, 2, 60, 0));
        JPanel favoriteContainer = new JPanel(new BorderLayout());
        JPanel stackContainer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JLabel favoriteLabel = new JLabel("❤ Your favorite words");
        favoriteLabel.setFont(new Font("Times", Font.BOLD, 16));
        favoriteLabel.setForeground(myThemeColor);
        favoriteContainer.add(favoriteLabel, BorderLayout.WEST);

        JButton likeButton = new JButton("Like");
        stackContainer.add(likeButton);

        JButton dislikeButton = new JButton("Dislike");
        stackContainer.add(dislikeButton);

        JButton reloadButton = new JButton("Reload");
        stackContainer.add(reloadButton);

        favoriteContainer.add(stackContainer, BorderLayout.EAST);

        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        extendContainer.add(favoriteContainer, c);

        // Recent
        JPanel recentContainer = new JPanel(new BorderLayout());

        JLabel recentLabel = new JLabel("⌛ Recently searched words");
        recentLabel.setFont(new Font("Times", Font.BOLD, 16));
        recentLabel.setForeground(myThemeColor);
        recentContainer.add(recentLabel, BorderLayout.WEST);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        extendContainer.add(recentContainer, c);

        c.gridx = 0;
        c.gridy = 3;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(extendContainer, c);

        // Table
        JPanel tableContainer = new JPanel(new GridLayout(1, 2, 60, 0));

        // Favorite
        // Start Mock
        Object data1[][] = { { "apple", "trái táo" },
                { "banana", "chuối", },
                { "cherry", "che-ri" } };
        Object features1[] = { "Word", "Meaning" };
        // End Mock
        JTable favoriteTable = new JTable(data1, features1);

        ListSelectionModel favoriteTableModel = favoriteTable.getSelectionModel();
        favoriteTableModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        favoriteTableModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Code here
            }
        });
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane3 = new JScrollPane(favoriteTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableContainer.add(scrollPane3, c);

        // Recent
        // Start Mock
        Object data2[][] = { { "apple", "trái táo" },
                { "banana", "chuối", },
                { "cherry", "che-ri" } };
        Object features2[] = { "Word", "Meaning" };
        // End Mock
        JTable recentTable = new JTable(data2, features2);

        recentTable.setEnabled(false);

        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane4 = new JScrollPane(recentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableContainer.add(scrollPane4, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 20.0;
        c.fill = GridBagConstraints.BOTH;
        mainPanel.add(tableContainer, c);

        // Footer
        JLabel footerLabel = new JLabel("Copyright © by HyrniT");
        footerLabel.setFont(new Font("Times", Font.PLAIN, 12));
        footerLabel.setForeground(myThemeColor);
        c.gridx = 0;
        c.gridy = 5;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.weightx = 1.0;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(footerLabel, c);

        editTheme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myThemeColor = JColorChooser.showDialog(edit, "Edit theme", Color.BLUE);
                headerLabel.setForeground(myThemeColor);
                inputOptionLabel.setForeground(myThemeColor);
                outputOptionLabel.setForeground(myThemeColor);
                favoriteLabel.setForeground(myThemeColor);
                recentLabel.setForeground(myThemeColor);
                EditFrame.myThemeColor = myThemeColor;
            }
        });

        add(mainPanel);
        setSize(1000, 600);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    boolean search(String keyWord) {
        int minDistance = Integer.MAX_VALUE;
        String closestWord = null;
        Dictionary dictionary = null;

        if (mySelectedLanguage == 1) {
            dictionary = DictionaryEN2VN.getInstance();
        }

        if (mySelectedLanguage == 2) {
            dictionary = DictionaryVN2EN.getInstance();
        }

        for (Record record : dictionary.getRecords()) {
            int distance = Helper.LevenshteinDistance(keyWord, record.getWord().toLowerCase());
            if (distance == 0) {
                outputTextArea.setText(record.getMeaning());
                return true;
            } else if (distance < minDistance) {
                minDistance = distance;
                closestWord = record.getWord();
            }
        }

        int ans = JOptionPane.showConfirmDialog(inputTextArea, "Sorry!\n\"" + keyWord + "\"" + " cannot found\n" +
                "Another word closest to this is: " + "\"" + closestWord + "\". Do you want to change?");

        if (ans == JOptionPane.YES_OPTION) {
            return search(closestWord);
        }

        return false;
    }
}