/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.reporting;

import com.gif4j.light.GifEncoder;
import com.gif4j.light.GifFrame;
import com.gif4j.light.GifImage;
import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pl.iad.quantization.algorithms.structure.Neuron;
import pl.iad.quantization.data.Point;

/**
 *
 * @author PiotrGrzelak
 */
public class GifMaker implements TrainingObserver {

    private static final int WIDTH = 1024;

    private static final int HEIGHT = 768;

    private static final int DELAY = 10;
    
    private GifImage gifImage;
    
    private List<List<? extends Neuron>> neuronsSetsList;

    public GifMaker() {
        gifImage = new GifImage();
        gifImage.setDefaultDelay(DELAY);
    }

    @Override
    public void notifyAfterEpoch(List<? extends Neuron> neurons, List<Point> dataCoords, double error) {
        try {
            GifFrame frame = createGifFrame(neurons, dataCoords, error);
            gifImage.addGifFrame(frame);
        } catch (InterruptedException ex) {
            Logger.getLogger(GifMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private GifFrame createGifFrame(List<? extends Neuron> neurons, List<Point> dataCoords, double error) throws InterruptedException {
        BufferedImage image = createImage(neurons, dataCoords, error);
        GifFrame frame = new GifFrame(image);
        return frame;
    }

    private BufferedImage createImage(List<? extends Neuron> neurons, List<Point> dataCoords, double error) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        
        XYSeries neuronsSeries = new XYSeries("Neurons");
        for (Neuron neuron : neurons) {
            neuronsSeries.add(neuron.getWeight(0), neuron.getWeight(1));
        }
        dataset.addSeries(neuronsSeries);
        XYSeries points = new XYSeries("Points");
        for (Point point : dataCoords) {
            points.add(point.getWeight(0), point.getWeight(1));
        }
        dataset.addSeries(points);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Quantization error: " + error,
                null, null,
                dataset,
                PlotOrientation.VERTICAL,
                true, false, false);
        
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesFillPaint(1, Color.RED);
        renderer.setSeriesFillPaint(0, Color.BLACK);
        renderer.setSeriesShape(1, new Ellipse2D.Double(0, 0, 0.5, 0.5));
        renderer.setSeriesShape(0, new Ellipse2D.Double(0, 0, 5, 5));
        chart.getXYPlot().setRenderer(renderer);
        
        return chart.createBufferedImage(WIDTH, HEIGHT);
    }

    public void saveGif(File file) throws IOException {
        GifEncoder.encode(gifImage, file);
    }
    
}
