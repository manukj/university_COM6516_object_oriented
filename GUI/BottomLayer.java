package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import hash_table.MyHashTable;
import hash_table.MyLinkedObject;
import n_gram.NGramProbabilityCalculation;

/**
 * BottomLayer
 */
public class BottomLayer extends JPanel {
    JPanel distributionPanel, textPanel;
    NGramProbabilityCalculation nGramProbabilityCalculation;

    public BottomLayer(MyHashTable hashTable, NGramProbabilityCalculation nGramProbabilityCalculation) {
        this.nGramProbabilityCalculation = nGramProbabilityCalculation;
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

        renderProbabilityPanel();

        add(distributionPanel);
    }

    private void renderProbabilityPanel() {
        JPanel probabilityPanel = new JPanel();
        probabilityPanel.setLayout(new BoxLayout(probabilityPanel, BoxLayout.Y_AXIS));
        probabilityPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Set alignment to left
        JLabel titleLabel = new JLabel("Probability Calculation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        probabilityPanel.add(titleLabel);

        // biagram calculation
        JLabel biagramTitleLabel = new JLabel("Biagram Probability Calculation");
        probabilityPanel.add(biagramTitleLabel);
        JPanel biagramPanel = new JPanel();
        biagramPanel.setLayout(new FlowLayout());

        JLabel biagramLabel = new JLabel("Input 2 words ");
        JTextField biagramInput = new JTextField(10);
        JButton calculateButton = new JButton("Calculate Probability");
        JTextArea resultArea = new JTextArea(1, 15);
        resultArea.setEditable(false);
        resultArea.setBorder(BorderFactory.createLineBorder(Color.RED));
        resultArea.setBackground(new Color(255, 220, 220));

        biagramPanel.add(biagramLabel);
        biagramPanel.add(biagramInput);
        biagramPanel.add(calculateButton);

        probabilityPanel.add(biagramPanel);
        probabilityPanel.add(resultArea);

        distributionPanel.add(probabilityPanel);
        probabilityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = biagramInput.getText();
                String[] words = input.split(" ");
                if (words.length != 2) {
                    resultArea.setText("Please input 2 words");
                    return;
                }
                String word1 = words[0];
                String word2 = words[1];
                resultArea.setText(nGramProbabilityCalculation.getBiGramProbability(word1, word2));
            }
        });

        probabilityPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // trigram calculation
        JLabel trigramTitleLabel = new JLabel("Trigram Probability Calculation");
        probabilityPanel.add(trigramTitleLabel);
        JPanel trigramPanel = new JPanel();
        trigramPanel.setLayout(new FlowLayout());

        JLabel trigramLabel = new JLabel("Input 3 words ");
        JTextField trigramInput = new JTextField(10);
        JButton calculateButton2 = new JButton("Calculate Probability");
        JTextArea resultArea2 = new JTextArea(1, 15);
        resultArea2.setEditable(false);
        resultArea2.setBorder(BorderFactory.createLineBorder(Color.RED));
        resultArea2.setBackground(new Color(255, 220, 220));
        trigramPanel.add(trigramLabel);
        trigramPanel.add(trigramInput);
        trigramPanel.add(calculateButton2);

        probabilityPanel.add(trigramPanel);
        probabilityPanel.add(resultArea2);

        distributionPanel.add(probabilityPanel);

        calculateButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = trigramInput.getText();
                String[] words = input.split(" ");
                if (words.length != 3) {
                    resultArea2.setText("Please input 3 words");
                    return;
                }
                String word1 = words[0];
                String word2 = words[1];
                String word3 = words[2];
                resultArea2.setText(nGramProbabilityCalculation.getTriGramProbability(word1, word2, word3));
            }
        });
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
        jTable.setPreferredScrollableViewportSize(new Dimension(250, 300));
        jTable.setAutoCreateRowSorter(true);

        textPanel.add(titleLabel);
        textPanel.add(subTitleLabel);
        textPanel.add(jTable);

        distributionPanel.add(textPanel);
    }

    private void renderDistributionBar(MyHashTable hashTable, Map<Integer, Integer> distributionData) {
        DistributionBarPanel chart = new DistributionBarPanel(distributionData);
        distributionPanel.add(chart);
    }

}