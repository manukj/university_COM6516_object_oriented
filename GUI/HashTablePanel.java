package GUI;
/*
 * Author : Manu Kenchappa Junjanna
 * Email : mkenchappajunjanna1@sheffield.ac.uk
 * Created on Mon Dec 11 2023
 */

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

public class HashTablePanel extends JPanel {
    private JTable jTable;

    public HashTablePanel(MyHashTable table) {
        setLayout(new BorderLayout());
        DefaultTableModel model = new DefaultTableModel(new String[] { "Word", "Count" }, 0);

        for (int i = 0; i < table.linkedList.length; i++) {
            MyLinkedObject current = table.linkedList[i];
            while (current != null) {
                String word = current.getWord();
                int count = current.getCount();
                // Add the word and count to the table model
                model.addRow(new Object[] { word, count });
                current = current.getNext();
            }
        }
        jTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(jTable);

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Total words: " + table.getTotalWordCount()));
        topPanel.add(new JLabel("Total Unique Words: " + table.getUniqueWordCount()));
        add(topPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void cleanUp() {
        jTable = null;
    }

}
