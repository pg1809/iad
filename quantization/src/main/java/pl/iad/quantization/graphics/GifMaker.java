/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.graphics;

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
import pl.iad.quantization.reporting.TrainingObserver;

/**
 *
 * @author PiotrGrzelak
 */
public class GifMaker implements TrainingObserver {

    private static final int WIDTH = 1024;
    
    private static final int HEIGHT = 768;

    private static final int DATA_SERIES_IDX = 1;
    
    private static final int NEURONS_SERIES_IDX = 0;
    
    private GifImage gifImage;

    private XYSeries points;

    private XYLineAndShapeRenderer renderer;

    public GifMaker(List<Point> data, int delay) {
        gifImage = new GifImage();
        gifImage.setDefaultDelay(delay);
        points = new XYSeries("points");
        for (Point point : data) {
            points.add(point.getWeight(0), point.getWeight(1));
        }

        renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(DATA_SERIES_IDX, false);
        renderer.setSeriesLinesVisible(NEURONS_SERIES_IDX, false);
        renderer.setSeriesPaint(NEURONS_SERIES_IDX, Color.RED);
        renderer.setSeriesPaint(DATA_SERIES_IDX, Color.BLUE);
        renderer.setSeriesShape(NEURONS_SERIES_IDX, new Ellipse2D.Double(0, 0, 0.7, 0.7));
        renderer.setSeriesShape(DATA_SERIES_IDX, new Ellipse2D.Double(0, 0, 5, 5));
    }
    
    @Override
    public void notifyAfterEpoch(List<? extends Neuron> neurons, double error) {
        try {
            GifFrame frame = createGifFrame(neurons, error);
            gifImage.addGifFrame(frame);
        } catch (InterruptedException ex) {
            Logger.getLogger(GifMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private GifFrame createGifFrame(List<? extends Neuron> neurons, double error) throws InterruptedException {
        BufferedImage image = createImage(neurons, error);
        GifFrame frame = new GifFrame(image);
        return frame;
    }

    private BufferedImage createImage(List<? extends Neuron> neurons, double error) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        XYSeries neuronsSeries = new XYSeries("Neurons");
        for (Neuron neuron : neurons) {
            neuronsSeries.add(neuron.getWeight(0), neuron.getWeight(1));
        }
        dataset.addSeries(neuronsSeries);
        dataset.addSeries(points);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Quantization error: " + error,
                null, null,
                dataset,
                PlotOrientation.VERTICAL,
                true, false, false);

        //chart.getXYPlot().setBackgroundPaint(Color.WHITE);
        chart.getXYPlot().setRenderer(renderer);

        return chart.createBufferedImage(WIDTH, HEIGHT);
    }

    public void saveGif(File file) throws IOException {
        GifEncoder.encode(gifImage, file);
    }

}
