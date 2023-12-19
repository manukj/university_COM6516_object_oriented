/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import constants.Constants;
import hash_table.MyFileReader;
import hash_table.MyHashTable;
import n_gram.NGramProbabilityCalculation;

public class Frame {
    private JFrame frame;
    private Container container;
    private WordAndCountTable hashTablePanel;
    private InputReadFilePanel inputReadFilePanel;
    private JPanel initalButtonPanel;
    private NGramProbabilityCalculation nGramProbabilityCalculation;

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
        StringBuilder wordsInStringBuilder = MyFileReader.readFile(filePath, Constants.MAX_CHAR_LIMIT);

        if (wordsInStringBuilder != null) {
            String[] data = wordsInStringBuilder.toString().split("\\s+|\\n");

            // create Unigram Hash Table
            MyHashTable uniGramHashTable = new MyHashTable(Constants.HASH_TABLE_SIZE);
            for (String word : data) {
                uniGramHashTable.add(word);
            }

            // initalise a instance of nGramProbabilityCalculation, which is later used for
            // probability calculation
            nGramProbabilityCalculation = new NGramProbabilityCalculation(data,
                    uniGramHashTable);

            // render the UI for unigram
            container.removeAll();
            renderInputReadFile(wordsInStringBuilder, filePath);
            renderWordAndCountTable(uniGramHashTable);
            renderBottomLayer(uniGramHashTable);
            container.repaint();
            container.revalidate();
        }

    }

    public void renderBottomLayer(MyHashTable hashTable) {
        BottomLayer bottomLayer = new BottomLayer(hashTable, nGramProbabilityCalculation);
        container.add(bottomLayer, BorderLayout.SOUTH);
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
