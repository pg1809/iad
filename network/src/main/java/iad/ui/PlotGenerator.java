/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.dataset.LinearlySeparableDataSetGenerator;
import iad.network.input.InputRow;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author PiotrGrzelak
 */
public class PlotGenerator {

    public void generateErrorChart(List<Double> errors) throws IOException {
        XYSeries data = new XYSeries("Errors");

        for (int i = 1; i <= errors.size(); ++i) {
            data.add(i, errors.get(i - 1));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(data);
        JFreeChart chart = ChartFactory.createXYLineChart("Squared error", "Epoch number", "Squared Error", dataset, PlotOrientation.VERTICAL, false, true, true);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        chart.getXYPlot().setRenderer(renderer);

        int width = 640;
        int height = 480;

        File XYChart = new File("errorChart.png");
        ChartUtilities.saveChartAsJPEG(XYChart, chart, width, height);
    }

    public void generateExemplaryDataChart(List<InputRow> input, LinearlySeparableDataSetGenerator generator) throws IOException {
        XYSeries firstClass = new XYSeries("First class");
        XYSeries secondClass = new XYSeries("Second class");
        XYSeries separator = new XYSeries("Separator");

        for (InputRow row : input) {
            double classIdentifier = row.getExpectedOutput()[0];
            double x = row.getValues()[0];
            double y = row.getValues()[1];
            if (classIdentifier != 1) {
                firstClass.add(x, y);
            } else {
                secondClass.add(x, y);
            }
        }

        double maxSeparatorX = generator.getUpperBound();
        double minSeparatorX = generator.getLowerBound();
        double maxSeparatorY = maxSeparatorX * generator.getSlope() + generator.getIntercept();
        double minSeparatorY = minSeparatorX * generator.getSlope() + generator.getIntercept();

        separator.add(minSeparatorX, minSeparatorY);
        separator.add(maxSeparatorX, maxSeparatorY);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firstClass);
        dataset.addSeries(secondClass);
        dataset.addSeries(separator);
        JFreeChart chart = ChartFactory.createXYLineChart("Exemplary set of data", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, true);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesPaint(0, Color.GREEN);
        renderer.setSeriesPaint(1, Color.RED);

        renderer.setSeriesPaint(2, Color.YELLOW);

        chart.getXYPlot().setRenderer(renderer);
        int width = 640;
        int height = 480;

        File XYChart = new File("exemplaryDataChart.png");
        ChartUtilities.saveChartAsJPEG(XYChart, chart, width, height);
    }
}
