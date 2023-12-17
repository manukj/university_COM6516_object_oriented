package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class DistributionPanel extends JPanel {

    private Map<String, Integer> distributionData;

    public DistributionPanel(Map<String, Integer> distributionData) {
        this.distributionData = distributionData;
        setPreferredSize(new Dimension(400, 300));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = 30;
        int xOffset = 20;
        int yOffset = getHeight() - 50;

        // find the max frequency
        int maxFrequency = 0;
        for (Map.Entry<String, Integer> entry : distributionData.entrySet()) {
            int frequency = entry.getValue();
            if (frequency > maxFrequency) {
                maxFrequency = frequency;
            }
        }

        for (Map.Entry<String, Integer> entry : distributionData.entrySet()) {
            String word = entry.getKey();
            int frequency = entry.getValue();

            int barHeight = frequency * getHeight() / maxFrequency;
            g.setColor(Color.blue);
            g.fillRect(xOffset, yOffset - barHeight, barWidth, barHeight);

            g.setColor(Color.RED);
            g.drawString(word, xOffset, getHeight() - 20);

            xOffset += barWidth + 20;
        }
    }
}
