package GUI;

import java.awt.FlowLayout;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

/**
 * BottomLayer
 */
public class BottomLayer extends JPanel {
    JPanel distributionPanel, textPanel;

    public BottomLayer(MyHashTable hashTable) {
        distributionPanel = new JPanel();
        distributionPanel.setLayout(new FlowLayout());

        textPanel = new JPanel();
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
        }
        renderDistributionBar(hashTable, distributionData);
        renderTextPanel(distributionData);
    }

    private void renderTextPanel(Map<Integer, Integer> distributionData) {
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

        distributionPanel.add(textPanel);
        add(distributionPanel);
    }

    private void renderDistributionBar(MyHashTable hashTable, Map<Integer, Integer> distributionData) {
        DistributionBarPanel chart = new DistributionBarPanel(distributionData);
        distributionPanel.add(chart);
    }

}