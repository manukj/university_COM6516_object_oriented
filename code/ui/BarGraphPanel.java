package ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import hash_table.MyHashTable;

public class BarGraphPanel extends JPanel {

    JPanel distributionPanel, textPanel;

    public BarGraphPanel(MyHashTable hashTable) {
        Map<Integer, Integer> distributionData = hashTable.getWordsCountPresentInEachLinkedList();

        distributionPanel = new JPanel();
        distributionPanel.setLayout(new BoxLayout(distributionPanel, BoxLayout.Y_AXIS));

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        renderTextPanel(distributionData);
        distributionPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        renderDistributionBar(hashTable, distributionData);
        add(distributionPanel);

    }

    private void renderDistributionBar(MyHashTable hashTable, Map<Integer, Integer> distributionData) {
        DistributionBarPanel chart = new DistributionBarPanel(distributionData);
        distributionPanel.add(chart);
    }

    private void renderTextPanel(Map<Integer, Integer> distributionData) {
        JLabel titleLabel = new JLabel("Word Distribution in Hash Table");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subTitleLabel = new JLabel(
                "<html>The bars represent the number of Unique words in each index of the Hash Table</html>");
        subTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] columnNames = { "Index", "Total Words" };
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Map.Entry<Integer, Integer> entry : distributionData.entrySet()) {
            int index = entry.getKey();
            int frequency = entry.getValue();
            model.addRow(new Object[] { index, frequency });
        }

        JTable jTable = new JTable(model);
        jTable.setPreferredSize(new Dimension(240, 200));
        jTable.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(jTable);

        textPanel.add(titleLabel);
        textPanel.add(subTitleLabel);
        textPanel.add(scrollPane);
        textPanel.setPreferredSize(new Dimension(240, 300));

        distributionPanel.add(textPanel);
    }

}
