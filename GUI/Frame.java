/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hash_table.MyFileReader;
import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

public class Frame implements ReadFileUICallback {
    private JFrame frame;
    private JPanel loading, error;
    private Container container;
    private WordAndCountTable hashTablePanel;
    private InputReadFilePanel inputReadFilePanel;
    private JButton pickFileButton;

    public Frame() {
        // write a code to create a Jframe with full screen
        frame = new JFrame();
        loading = new JPanel();
        error = new JPanel();
        container = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        initaliseFilePicker();
        initalisePanels();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cleanUp();
            }
        });
    }

    private void initalisePanels() {
        container.add(pickFileButton, BorderLayout.NORTH);
    }

    private void initaliseFilePicker() {
        pickFileButton = new JButton("Pick File");
        pickFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                File defaultDirectory = new File("./");
                fileChooser.setCurrentDirectory(defaultDirectory);
                int result = fileChooser.showOpenDialog(container);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    StringBuilder wordsInStringBuilder = MyFileReader.readFile(selectedFile.getAbsolutePath(),
                            (ReadFileUICallback) Frame.this);
                    if (wordsInStringBuilder != null) {
                        String[] words = wordsInStringBuilder.toString().split("\\s+|\\n");
                        MyHashTable hashTable = new MyHashTable(10);
                        for (String word : words) {
                            hashTable.add(word);
                        }
                        System.out.println("Total words: " + hashTable.getTotalWordCount());
                        container.removeAll();
                        renderInputReadFile(wordsInStringBuilder, selectedFile.getAbsolutePath());
                        renderWordAndCountTable(hashTable);
                        renderDistibutionCurve(hashTable);
                        container.repaint();
                        container.revalidate();
                    }

                }
            }
        });
    }

    @Override
    public void onFileReading(String filePath) {
        loading.setSize(100, 100);
        loading.setBackground(java.awt.Color.RED);
        loading.add(new JLabel("Reading file: " + filePath));
        container.add(loading, BorderLayout.CENTER);
        container.repaint();
        container.revalidate();
    }

    @Override
    public void onFileReadingComplete(StringBuilder wordsBuilder, String filePath) {
        // container.remove(loading);
        // container.remove(error);
        // inputReadFilePanel = new InputReadFilePanel(wordsBuilder,filePath);
        // container.add(inputReadFilePanel, BorderLayout.WEST);
    }

    @Override
    public void onFileReadingError(String errorMessage) {
        // container.remove(loading);
        // error.setSize(100, 100);
        // error.setBackground(java.awt.Color.RED);
        // error.add(new JLabel("Reading file failed " + errorMessage));
        // container.add(error, BorderLayout.CENTER);
    }

    public void cleanUp() {
        frame.dispose();

        // Remove components from the container
        // container.remove(loading);
        // container.remove(error);
        // container.remove(inputReadFilePanel);

        if (hashTablePanel != null) {
            hashTablePanel.cleanUp();
        }
        if (inputReadFilePanel != null) {
            inputReadFilePanel.cleanUp();
        }
        // Release references to objects
        loading = null;
        error = null;
        inputReadFilePanel = null;
        hashTablePanel = null;
    }

    public void renderDistibutionCurve(MyHashTable hashTable) {
        JPanel distributionPanel = new JPanel();
        distributionPanel.setLayout(new FlowLayout());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        Map<Integer, Integer> distributionData = new LinkedHashMap<>();
        for (int i = 0; i < hashTable.linkedList.length; i++) {
            int count = 0;
            MyLinkedObject current = hashTable.linkedList[i];
            while (current != null) {
                count++;
                current = current.getNext();
            }
            distributionData.put(i, count);
            System.out.println(distributionData);
        }

        DistributionBarPanel chart = new DistributionBarPanel(distributionData);

        JLabel titleLabel = new JLabel("Word Frequency Distribution");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel subTitleLabel = new JLabel("Number of words in each linked list");
        textPanel.add(titleLabel);
        textPanel.add(subTitleLabel);

        distributionPanel.add(chart);
        distributionPanel.add(textPanel);

        container.add(distributionPanel, BorderLayout.SOUTH);
    }

    public void renderWordAndCountTable(MyHashTable hashTable) {
        hashTablePanel = new WordAndCountTable(hashTable);
        container.add(hashTablePanel, BorderLayout.EAST);
    }

    public void renderInputReadFile(StringBuilder wordsInStringBuilder, String string) {
        inputReadFilePanel = new InputReadFilePanel(wordsInStringBuilder, string);
        container.add(inputReadFilePanel, BorderLayout.CENTER);
    }
}
