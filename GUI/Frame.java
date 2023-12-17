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
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hash_table.MyFileReader;
import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

public class Frame {
    private JFrame frame;
    private Container container;
    private WordAndCountTable hashTablePanel;
    private InputReadFilePanel inputReadFilePanel;
    private JPanel initalButtonPanel;

    public Frame() {
        // write a code to create a Jframe with full screen
        frame = new JFrame();
        container = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        initaliseFilePicker();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                cleanUp();
            }
        });
    }

    private void initaliseFilePicker() {
        initalButtonPanel = new JPanel();
        initalButtonPanel.setLayout(new BoxLayout(initalButtonPanel, BoxLayout.Y_AXIS));

        JButton pickFileButton = new JButton("Pick File");
        JButton pickNewsButton = new JButton("Pick News");
        pickFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                File defaultDirectory = new File("./");
                fileChooser.setCurrentDirectory(defaultDirectory);
                int result = fileChooser.showOpenDialog(container);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    onPagePicked(selectedFile.getAbsolutePath());
                }
            }
        });
        pickNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPagePicked("./news.txt");
            }
        });
        initalButtonPanel.add(pickFileButton);
        initalButtonPanel.add(pickNewsButton);
        container.add(initalButtonPanel, BorderLayout.NORTH);
    }

    public void onPagePicked(String filePath) {
        StringBuilder wordsInStringBuilder = MyFileReader.readFile(filePath);
        if (wordsInStringBuilder != null) {
            String[] words = wordsInStringBuilder.toString().split("\\s+|\\n");
            MyHashTable hashTable = new MyHashTable(10);
            for (String word : words) {
                hashTable.add(word);
            }
            System.out.println("Total words: " + hashTable.getTotalWordCount());
            container.removeAll();
            renderInputReadFile(wordsInStringBuilder, filePath);
            renderWordAndCountTable(hashTable);
            renderDistibutionCurve(hashTable);
            container.repaint();
            container.revalidate();
        }

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

        JLabel titleLabel = new JLabel("Word Distribution in Linked List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        JLabel subTitleLabel = new JLabel(
                "The bars represent the number of Unique words in each index of the linked list");

        String[] columnNames = { "Index", "Total Words" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Map.Entry<Integer, Integer> entry : distributionData.entrySet()) {
            int index = entry.getKey();
            int frequency = entry.getValue();
            model.addRow(new Object[] { index, frequency });
        }
        JTable jTable = new JTable(model);
        jTable.setAutoCreateRowSorter(true);

        textPanel.add(titleLabel);
        textPanel.add(subTitleLabel);
        textPanel.add(jTable);

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

    public void cleanUp() {
        frame.dispose();
        if (hashTablePanel != null) {
            hashTablePanel.cleanUp();
        }
        if (inputReadFilePanel != null) {
            inputReadFilePanel.cleanUp();
        }
        // Release references to objects
        inputReadFilePanel = null;
        hashTablePanel = null;
    }
}
