package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InputReadFilePanel extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public InputReadFilePanel(StringBuilder wordsBuilder, String filePath) {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("File: " + filePath));
        add(topPanel, BorderLayout.NORTH);
        
        add(scrollPane, BorderLayout.CENTER);

        // Load file content on initialization
        textArea.setText(wordsBuilder.toString());
    }

    public void cleanUp() {
        textArea = null;
        scrollPane = null;
    }

}
