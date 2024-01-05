package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InputReadFilePanel extends JPanel {
    private JTextArea textArea;
    private JScrollPane scrollPane;

    public InputReadFilePanel(String data, String filePath) {
        setLayout(new BorderLayout());
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 10));


        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("File: " + filePath));
        add(topPanel, BorderLayout.NORTH);
        
        add(scrollPane, BorderLayout.CENTER);

        // Load file content on initialization
        textArea.setText(data.toString());
    }

    public void cleanUp() {
        textArea = null;
        scrollPane = null;
    }

}
