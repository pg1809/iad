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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import pl.iad.quantization.algorithms.structure.Neuron;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.reporting.TrainingReporter;

/**
 *
 * @author PiotrGrzelak
 */
public class GraphicReporter implements TrainingReporter {

    private static final int WIDTH = 1024;

    private static final int HEIGHT = 768;

    private static final int DATA_SERIES_IDX = 1;

    private static final int NEURONS_SERIES_IDX = 0;

    private XYSeries points;

    private List<XYSeries> currentRunNeuronSeries;

    private List<XYSeries> preservedNeuronSeries;

    private List<Double> currentRunErrors;

    private List<Double> preservedErrors;

    private XYLineAndShapeRenderer renderer;

    public GraphicReporter(List<Point> data) {
        points = new XYSeries("points");
        currentRunErrors = new ArrayList<>();
        currentRunNeuronSeries = new ArrayList<>();

        preservedErrors = new ArrayList<>();
        preservedNeuronSeries = new ArrayList<>();

        for (Point point : data) {
            points.add(point.getWeight(0), point.getWeight(1));
        }

        renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(DATA_SERIES_IDX, false);
        renderer.setSeriesLinesVisible(NEURONS_SERIES_IDX, false);
        renderer.setSeriesPaint(DATA_SERIES_IDX, Color.BLUE);
        renderer.setSeriesPaint(NEURONS_SERIES_IDX, Color.RED);
        renderer.setSeriesShape(DATA_SERIES_IDX, new Ellipse2D.Double(0, 0, 0.7, 0.7));
        renderer.setSeriesShape(NEURONS_SERIES_IDX, new Ellipse2D.Double(0, 0, 5, 5));
    }

    @Override
    public void notifyAfterEpoch(List<? extends Neuron> neurons, double error) {
        XYSeries singleNeuronSeries = new XYSeries("neurons");
        for (Neuron n : neurons) {
            singleNeuronSeries.add(n.getWeight(0), n.getWeight(1));
        }
        currentRunNeuronSeries.add(singleNeuronSeries);
        currentRunErrors.add(error);
    }

    public void generateGIF(File file, int delay) throws IOException {
        GifImage img = new GifImage();
        img.setDefaultDelay(delay);
        try {
            for (int i = 0; i < preservedNeuronSeries.size(); ++i) {
                XYSeries singleNeuronSeries = preservedNeuronSeries.get(i);
                BufferedImage image = createImage(singleNeuronSeries, preservedErrors.get(i));
                GifFrame frame = new GifFrame(image);
                img.addGifFrame(frame);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(GraphicReporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        GifEncoder.encode(img, file);
    }

    public void generateErrorChart(String plotFileName) throws IOException {
        XYSeries data = new XYSeries("Errors");

        for (int i = 1; i <= preservedErrors.size(); ++i) {
            data.add(i, preservedErrors.get(i - 1));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(data);
        JFreeChart chart = ChartFactory.createXYLineChart("Quantization error", "Epoch number", "Quantization Error", dataset, PlotOrientation.VERTICAL, false, true, true);

        XYLineAndShapeRenderer errorRenderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        chart.getXYPlot().setRenderer(errorRenderer);

        File XYChart = new File(plotFileName);
        ChartUtilities.saveChartAsJPEG(XYChart, chart, WIDTH, HEIGHT);
    }

    private BufferedImage createImage(XYSeries singleNeuronsSeries, double error) {
        XYSeriesCollection dataset = new XYSeriesCollection();

        dataset.addSeries(singleNeuronsSeries);
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

    @Override
    public void preserveRunData() {
        preservedNeuronSeries.clear();
        preservedErrors.clear();
        for (XYSeries s : currentRunNeuronSeries) {
            preservedNeuronSeries.add(s);
        }
        for (Double d : currentRunErrors) {
            preservedErrors.add(d);
        }
    }

    @Override
    public void clearRunData() {
        currentRunNeuronSeries.clear();
        currentRunErrors.clear();
    }

    public void clearAll() {
        preservedErrors.clear();
        preservedNeuronSeries.clear();
        currentRunNeuronSeries.clear();
        currentRunErrors.clear();
    }
}
