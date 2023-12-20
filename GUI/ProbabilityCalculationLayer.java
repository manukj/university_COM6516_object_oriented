package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import hash_table.MyHashTable;
import n_gram.NGramProbabilityCalculation;

/**
 * BottomLayer
 */
public class ProbabilityCalculationLayer extends JPanel {
    NGramProbabilityCalculation nGramProbabilityCalculation;

    public ProbabilityCalculationLayer(MyHashTable hashTable, NGramProbabilityCalculation nGramProbabilityCalculation) {
        this.nGramProbabilityCalculation = nGramProbabilityCalculation;

        JPanel probabilityPanel = new JPanel();
        probabilityPanel.setLayout(new BoxLayout(probabilityPanel, BoxLayout.Y_AXIS));
        probabilityPanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Set alignment to left
        JLabel titleLabel = new JLabel("Probability Calculation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        probabilityPanel.add(titleLabel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel biagramLabel = new JLabel("Input Inital words ");
        JTextField biagramInput = new JTextField(10);
        JButton calculateButton = new JButton("Calculate Probability");

        inputPanel.add(biagramLabel);
        inputPanel.add(biagramInput);
        inputPanel.add(calculateButton);

        probabilityPanel.add(inputPanel);

        JTextArea biagramOutput = new JTextArea(3, 15);
        JTextArea trigramOutput = new JTextArea(3, 15);
        biagramOutput.setEditable(false);
        trigramOutput.setEditable(false);
        biagramOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        biagramOutput.setBackground(Color.LIGHT_GRAY);
        trigramOutput.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        trigramOutput.setBackground(Color.LIGHT_GRAY);

        probabilityPanel.add(biagramOutput);
        probabilityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        probabilityPanel.add(trigramOutput);

        probabilityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = biagramInput.getText();
                if (input.isEmpty()) {
                    biagramOutput.setBorder(BorderFactory.createLineBorder(Color.RED));
                    biagramOutput.setBackground(new Color(255, 220, 220));
                    biagramOutput.setText("Please input valid words");
                    return;
                }

                biagramOutput.setText(nGramProbabilityCalculation.get20MostFrequentWordsUsingBigram(input));
                trigramOutput.setText(nGramProbabilityCalculation.get20MostFrequentWordsUsingTrigram(input));
                biagramOutput.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                biagramOutput.setBackground(new Color(220, 255, 220));
                trigramOutput.setBorder(BorderFactory.createLineBorder(Color.GREEN));
                trigramOutput.setBackground(new Color(220, 255, 220));
            }
        });

        add(probabilityPanel);
    }

}