/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
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
import constants.Constants.HashFunctionType;
import hash_table.MyHashTable;
import hash_table.NGramAndProbabilityCalculation;
import util.FileReaderUtil;

public class Frame {
    private JFrame frame;
    private Container container;
    private WordAndCountTablePanel hashTablePanel;
    private InputReadFilePanel inputReadFilePanel;
    private JPanel initalButtonPanel;
    private NGramAndProbabilityCalculation nGram;

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
        pickFileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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

        JButton pickNewsButton = new JButton("Pick News");
        pickNewsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pickNewsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onPagePicked("./news.txt");
            }
        });

        container.setLayout(new BorderLayout());
        initalButtonPanel.add(pickFileButton);
        initalButtonPanel.add(pickNewsButton);
        container.add(initalButtonPanel, BorderLayout.CENTER);
    }

    public void toggleMyHashFunction() {
        // nGram.toggleMyHashFunction();
    }

    public void onPagePicked(String filePath) {
        StringBuilder wordsInStringBuilder = FileReaderUtil.readFile(filePath, Constants.MAX_CHAR_LIMIT);

        if (wordsInStringBuilder != null) {
            String[] data = wordsInStringBuilder.toString().split("\\s+|\\n");

            // initalise a instance of nGramProbabilityCalculation, which is later used for
            // probability calculation
            nGram = new NGramAndProbabilityCalculation(data, HashFunctionType.SIMPLE_HASH_FUNCTION);

            // render the UI for unigram
            container.removeAll();
            renderInputReadFile(wordsInStringBuilder, filePath);
            renderWordAndCountTable(nGram.getUniGramHashTable());
            renderStaticsPanel(nGram.getUniGramHashTable());
            renderBottomLayer(nGram.getUniGramHashTable());
            container.repaint();
            container.revalidate();
        }

    }

    public void renderInputReadFile(StringBuilder wordsInStringBuilder, String string) {
        inputReadFilePanel = new InputReadFilePanel(wordsInStringBuilder, string);
        container.add(inputReadFilePanel, BorderLayout.WEST);
    }

    public void renderWordAndCountTable(MyHashTable hashTable) {
        hashTablePanel = new WordAndCountTablePanel(hashTable);
        container.add(hashTablePanel, BorderLayout.CENTER);
    }

    private void renderStaticsPanel(MyHashTable uniGramHashTable) {
        BarGraphPanel barGraph = new BarGraphPanel(uniGramHashTable);
        container.add(barGraph, BorderLayout.EAST);
    }

    public void renderBottomLayer(MyHashTable hashTable) {
        ProbabilityCalculationPanel probabilityCalculationLayer = new ProbabilityCalculationPanel(hashTable,
                nGram);
        container.add(probabilityCalculationLayer, BorderLayout.SOUTH);
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
