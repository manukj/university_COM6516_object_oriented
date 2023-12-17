package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import hash_table.MyHashTable;
import hash_table.MyLinkedObject;

public class DistributionChart extends JPanel {
    private MyLinkedObject[] linkedObjects;

    private final int width = 600;
    private final int height = 400;
    private final int xOffset = 50;
    private final int yOffset = 350;

    public DistributionChart(MyHashTable table) {
        setPreferredSize(new Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGaussianCurve(g);
    }

    private void drawGaussianCurve(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLUE);

        List<Point> points = calculateGaussianPoints();

        for (int i = 0; i < points.size() - 1; i++) {
            Point p1 = points.get(i);
            Point p2 = points.get(i + 1);
            g2.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    private List<Point> calculateGaussianPoints() {
        List<Point> points = new ArrayList<>();
        int[] counts = getCounts();

        for (int x = 0; x < width - xOffset; x++) {
            double u = (double) (x - xOffset) / 50; // Mean
            double sigma = 2; // Standard deviation
            double y = gaussianFunction(u, sigma, counts) * 50; // Amplify for visibility
            points.add(new Point(x + xOffset, yOffset - (int) y));
        }
        return points;
    }

    private int[] getCounts() {
        if (linkedObjects == null) {
            return new int[0];
        }

        int size = linkedObjects.length;
        int[] counts = new int[size];

        for (int i = 0; i < size; i++) {
            counts[i] = linkedObjects[i].getCount();
        }
        return counts;
    }

    private double gaussianFunction(double u, double sigma, int[] counts) {
        double sum = 0;
        for (int count : counts) {
            sum += (1 / (sigma * Math.sqrt(2 * Math.PI))) * Math.exp(-0.5 * Math.pow((count - u) / sigma, 2));
        }
        return sum / counts.length; // Normalize the sum
    }

}