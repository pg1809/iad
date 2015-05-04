/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.gui;

import java.util.List;
import pl.iad.quantization.algorithms.gas.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.gas.parameters.learning.PowerLearningFactor;
import pl.iad.quantization.algorithms.gas.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.gas.parameters.neighbourhood.PowerNeighbourhoodFactor;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.algorithms.gas.training.GasTrainer;
import pl.iad.quantization.algorithms.gas.training.OnlineTrainer;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.generator.CircleGenerator;
import pl.iad.quantization.data.generator.RandomPointsGenerator;
import pl.iad.quantization.data.metrics.EuclideanMetric;
import pl.iad.quantization.data.metrics.Metric;

/**
 *
 * @author Wojciech Szałapski
 */
public class GasTestApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maxNumberOfEpochs = 500;
        int numberOfNeurons = 20;

        int numberOfPoints = 5000;
        int maxAbsCoordinates = 100;

        NeuronCollection collection = new NeuronCollection(numberOfNeurons, 2, maxAbsCoordinates);

        LearningFactorProvider learningFactorProvider
                = new PowerLearningFactor(maxNumberOfEpochs - 1,
                        PowerLearningFactor.DEFAULT_INITIAL_FACTOR,
                        PowerLearningFactor.DEFAULT_MINIMUM_FACTOR);

        NeighbourhoodFactorProvider neighbourhoodFactorProvider
                = new PowerNeighbourhoodFactor(maxNumberOfEpochs - 1,
                        numberOfNeurons / 2,
                        PowerNeighbourhoodFactor.DEFAULT_MINIMUM_FACTOR);

        RandomPointsGenerator pointsGenerator = new CircleGenerator();
        List<Point> input = pointsGenerator.generatePoints(numberOfPoints, maxAbsCoordinates);

        Metric metric = new EuclideanMetric();

        GasTrainer gasTrainer = new OnlineTrainer();
        List<Double> quantizationError = gasTrainer.trainNeurons(collection,
                input, maxNumberOfEpochs, learningFactorProvider,
                neighbourhoodFactorProvider, metric);
        
        for (Double error : quantizationError) {
            System.out.println(error);
        }
    }
}
