/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Sun Dec 10 2023
 */

package GUI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hash_table.MyHashTable;

public class Frame implements ReadFileUICallback {
    private JFrame frame;
    private JPanel loading, error;
    private Container container;
    private HashTablePanel hashTablePanel;
    InputReadFilePanel inputReadFilePanel;

    public Frame() {
        // write a code to create a Jframe with full screen
        frame = new JFrame();
        loading = new JPanel();
        error = new JPanel();
        container = frame.getContentPane();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }

    @Override
    public void onFileReading(String filePath) {
        loading.setSize(100, 100);
        loading.setBackground(java.awt.Color.RED);
        loading.add(new JLabel("Reading file: " + filePath));
        container.add(loading, BorderLayout.CENTER);
    }

    @Override
    public void onFileReadingComplete(StringBuilder wordsBuilder) {
        container.remove(loading);
        container.remove(error);
        inputReadFilePanel = new InputReadFilePanel(wordsBuilder);
        container.add(inputReadFilePanel, BorderLayout.PAGE_START);
    }

    @Override
    public void onFileReadingError(String errorMessage) {
        container.remove(loading);
        error.setSize(100, 100);
        error.setBackground(java.awt.Color.RED);
        error.add(new JLabel("Reading file failed " + errorMessage));
        container.add(error, BorderLayout.CENTER);
    }

    public void renderHashTable(MyHashTable hashTable) {
        container.remove(loading);
        container.remove(error);
        container.add(new HashTablePanel(hashTable), BorderLayout.PAGE_START);
    }

}
