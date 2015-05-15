/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import pl.iad.quantization.algorithms.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.PowerNeighbourhoodFactor;
import pl.iad.quantization.algorithms.parameters.learning.IndividualLearningFactor;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.generator.CircleGenerator;
import pl.iad.quantization.data.generator.RandomPointsGenerator;
import pl.iad.quantization.data.images.ImageData;
import pl.iad.quantization.data.images.ImageHolder;
import pl.iad.quantization.data.images.reader.DefaultImageReader;
import pl.iad.quantization.data.images.reader.ImageReader;
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

        GifMaker gifMaker = new GifMaker();

//        LearningFactorProvider learningFactorProvider
//                = new PowerLearningFactor(maxNumberOfEpochs - 1,
//                        PowerLearningFactor.DEFAULT_INITIAL_FACTOR,
//                        PowerLearningFactor.DEFAULT_MINIMUM_FACTOR);
        LearningFactorProvider learningFactorProvider = new IndividualLearningFactor();

        NeighbourhoodFactorProvider neighbourhoodFactorProvider
                = new PowerNeighbourhoodFactor(maxNumberOfEpochs - 1,
                        numberOfNeurons / 2,
                        PowerNeighbourhoodFactor.DEFAULT_MINIMUM_FACTOR);

        RandomPointsGenerator pointsGenerator = new CircleGenerator();
        List<Point> input = pointsGenerator.generatePoints(numberOfPoints, maxAbsCoordinates);

        Metric metric = new EuclideanMetric();
//        Metric metric = new MaximumMetric();

        // NEURAL GAS
//        NeuronCollection collection = new NeuronCollection(numberOfNeurons, 2, maxAbsCoordinates);
//        GasTrainer gasTrainer = new OnlineTrainer(gifMaker);
//        List<Double> quantizationError = gasTrainer.trainNeurons(collection,
//                input, maxNumberOfEpochs, learningFactorProvider,
//                neighbourhoodFactorProvider, metric);
//        quantizationError.stream().forEach((error) -> {
//            System.out.println(error);
//        });
        
        // IMAGE COMPRESSION
        File image = new File("C:\\Users\\Ardavel\\Desktop\\test2.png");
        File convertedImage = new File("C:\\Users\\Ardavel\\Desktop\\converted.png");
        ImageReader imageReader = new DefaultImageReader();
        try {
            ImageData imageData = imageReader.readBlackAndWhiteImage(image);
            int frameSize = 4;
            ImageHolder imageHolder = new ImageHolder(imageData, frameSize);
            
            BufferedImage recreatedImage = imageHolder.recreateImage();
            ImageIO.write(recreatedImage, "png", convertedImage);
//            NeuronCollection collection = new NeuronCollection(50, frameSize * frameSize, 1);
//            GasTrainer gasTrainer = new OnlineTrainer(null);
//            List<Double> quantizationError = gasTrainer.trainNeurons(collection,
//                    imageHolder.getPointRepresentation(), maxNumberOfEpochs,
//                    learningFactorProvider, neighbourhoodFactorProvider, metric);
//            quantizationError.stream().forEach((error) -> {
//                System.out.println(error);
//            });
        } catch (IOException ex) {
            Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        // KOHONEN SOM
//        NeuronMap som = new NeuronMap(numberOfNeurons / 8, numberOfNeurons / 5, 2, maxAbsCoordinates);
//        SOMTrainer somTrainer = new SOMTrainer();
//        somTrainer.setFunction(new GaussianNeighbourhoodFunction());
//        somTrainer.setMetric(metric);
//        somTrainer.setLearningFactorProvider(learningFactorProvider);
//        somTrainer.setNeighbourhoodFactorProvider(neighbourhoodFactorProvider);
//        somTrainer.setObserver(gifMaker);
//        List<Double> errors = somTrainer.doTraining(som, input, maxNumberOfEpochs);
//        errors.stream().forEach(System.out::println);
        try {
            gifMaker.saveGif(new File("test.gif"));
        } catch (IOException ex) {
            Logger.getLogger(TestApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
