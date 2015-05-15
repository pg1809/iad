/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.gui;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iad.quantization.algorithms.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.parameters.learning.PowerLearningFactor;
import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.PowerNeighbourhoodFactor;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.algorithms.kohonen.adaptation.GaussianNeighbourhoodFunction;
import pl.iad.quantization.algorithms.kohonen.structure.NeuronMap;
import pl.iad.quantization.algorithms.kohonen.training.SOMTrainer;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.generator.CircleGenerator;
import pl.iad.quantization.data.generator.RandomPointsGenerator;
import pl.iad.quantization.data.metrics.EuclideanMetric;
import pl.iad.quantization.data.metrics.Metric;
import pl.iad.quantization.reporting.GifMaker;

/**
 *
 * @author Wojciech Szałapski
 */
public class TestApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int maxNumberOfEpochs = 500;
        int numberOfNeurons = 40;

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

//        GasTrainer gasTrainer = new OnlineTrainer();
//        List<Double> quantizationError = gasTrainer.trainNeurons(collection,
//                input, maxNumberOfEpochs, learningFactorProvider,
//                neighbourhoodFactorProvider, metric);
//        
//        for (Double error : quantizationError) {
//            System.out.println(error);
//        }
        
        NeuronMap som = new NeuronMap(numberOfNeurons / 8, numberOfNeurons / 5, 2, maxAbsCoordinates);
        SOMTrainer somTrainer = new SOMTrainer();
        GifMaker gifMaker = new GifMaker();
        somTrainer.setFunction(new GaussianNeighbourhoodFunction());
        somTrainer.setMetric(metric);
        somTrainer.setLearningFactorProvider(learningFactorProvider);
        somTrainer.setNeighbourhoodFactorProvider(neighbourhoodFactorProvider);
        somTrainer.setObserver(gifMaker);
        List<Double> errors = somTrainer.doTraining(som, input, maxNumberOfEpochs);
        errors.stream().forEach(System.out::println);
        try {
            gifMaker.saveGif(new File("test.gif"));
        } catch (IOException ex) {
            Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}