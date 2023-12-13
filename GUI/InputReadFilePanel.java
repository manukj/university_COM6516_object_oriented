package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

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
        scrollPane.setPreferredSize(new Dimension(300, 300));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Load file content on initialization
        textArea.setText(wordsBuilder.toString());
    }

    public void cleanUp() {
        textArea = null;
        scrollPane = null;
    }

}
