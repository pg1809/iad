package pl.iad.quantization.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.algorithms.gas.training.GasTrainer;
import pl.iad.quantization.algorithms.gas.training.OnlineTrainer;
import pl.iad.quantization.algorithms.kohonen.adaptation.GaussianNeighbourhoodFunction;
import pl.iad.quantization.algorithms.kohonen.structure.NeuronMap;
import pl.iad.quantization.algorithms.kohonen.training.SOMTrainer;
import pl.iad.quantization.algorithms.means.training.KMeansAlgorithm;
import pl.iad.quantization.algorithms.parameters.learning.IndividualLearningFactor;
import pl.iad.quantization.algorithms.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.parameters.learning.PowerLearningFactor;
import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.PowerNeighbourhoodFactor;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.generator.ManyFiguresGenerator;
import pl.iad.quantization.data.generator.RandomPointsGenerator;
import pl.iad.quantization.data.images.ImageData;
import pl.iad.quantization.data.images.ImageHolder;
import pl.iad.quantization.data.images.reader.DefaultImageReader;
import pl.iad.quantization.data.images.reader.ImageReader;
import pl.iad.quantization.data.metrics.EuclideanMetric;
import pl.iad.quantization.data.metrics.Metric;
import pl.iad.quantization.graphics.GraphicReporter;

/**
 *
 * @author PiotrGrzelak
 */
public class App {

    private static final int maxIterations = 500;

    private static final int numberOfPoints = 10;

    private static final int numberOfNeurons = 256;

    private static final double maxAbsCoordinates = 100;

    private static final LearningFactorProvider individualLearningFactor
            = new IndividualLearningFactor();

    private static final LearningFactorProvider powerLearningFactor
            = new PowerLearningFactor(maxIterations - 1,
                    PowerLearningFactor.DEFAULT_INITIAL_FACTOR,
                    PowerLearningFactor.DEFAULT_MINIMUM_FACTOR);

    private static final LearningFactorProvider learningFactor = powerLearningFactor;
//    private static final LearningFactorProvider learningFactor = individualLearningFactor;

    private static final NeighbourhoodFactorProvider neighbourhoodFactor
            = new PowerNeighbourhoodFactor(maxIterations - 1,
                    numberOfNeurons / 2,
                    PowerNeighbourhoodFactor.DEFAULT_MINIMUM_FACTOR);

    private static final Metric metric = new EuclideanMetric();

//    private static final RandomPointsGenerator inputProvider = new CircleGenerator();
    private static final RandomPointsGenerator inputProvider = new ManyFiguresGenerator();

    private static List<Point> input;

    public static void main(String[] args) throws IOException {
        input = inputProvider.generatePoints(numberOfPoints, maxAbsCoordinates / 2);

//        runKohonen();
//        runGas();
//        runKMeans();
        runCompression();
    }

    private static void runKohonen() throws IOException {
        int x = 4;
        int y = 5;
        int dimension = 2;

        GraphicReporter graphicReporter = new GraphicReporter(input);

        NeuronMap som = new NeuronMap(x, y, dimension, maxAbsCoordinates);
        SOMTrainer somTrainer = new SOMTrainer();
        somTrainer.setFunction(new GaussianNeighbourhoodFunction());
        somTrainer.setMetric(metric);
        somTrainer.setLearningFactorProvider(learningFactor);
        somTrainer.setNeighbourhoodFactorProvider(neighbourhoodFactor);
        somTrainer.setObserver(graphicReporter);

        String fileName = "SOM-" + x + "x" + y + "-";
        if (learningFactor instanceof PowerLearningFactor) {
            fileName += "PLF";
        } else {
            fileName += "ILF";
        }

        long startTime = System.nanoTime();
        double error = somTrainer.doTraining(som, input, maxIterations);
        long stopTime = System.nanoTime();

        System.out.print(fileName + " ");
        System.out.printf("%.3f ", error);
        System.out.printf("%.3f\n", (stopTime - startTime) / (1000.0 * 1000 * 1000));

        graphicReporter.generateGIF(new File("C:\\Users\\Ardavel\\Desktop\\results\\"
                + fileName + ".gif"), 10);
        graphicReporter.generateErrorChart("error-" + fileName + ".png");
    }

    private static void runGas() throws IOException {
        int dimension = 2;

        GraphicReporter graphicReporter = new GraphicReporter(input);
        NeuronCollection collection = new NeuronCollection(numberOfNeurons,
                dimension, maxAbsCoordinates);

        GasTrainer gasTrainer = new OnlineTrainer(graphicReporter);

        String fileName = "NG-" + numberOfNeurons + "-";
        if (learningFactor instanceof PowerLearningFactor) {
            fileName += "PLF";
        } else {
            fileName += "ILF";
        }

        long startTime = System.nanoTime();
        List<Double> quantizationError = gasTrainer.trainNeurons(collection,
                input, maxIterations, learningFactor, neighbourhoodFactor, metric);
        long stopTime = System.nanoTime();

        System.out.print(fileName + " ");
        System.out.printf("%.3f ", quantizationError.get(quantizationError.size() - 1));
        System.out.printf("%.3f\n", (stopTime - startTime) / (1000.0 * 1000 * 1000));

        graphicReporter.generateGIF(new File("C:\\Users\\Ardavel\\Desktop\\results\\"
                + fileName + ".gif"), 10);
        graphicReporter.generateErrorChart("error-" + fileName + ".png");

    }

    private static void runKMeans() throws IOException {
        int repetitions = 5;
        double epsilon = 1e-3;
        int dimension = 2;

        GraphicReporter graphicReporter = new GraphicReporter(input);
        KMeansAlgorithm alg = new KMeansAlgorithm();
        alg.setMetric(metric);
        alg.setReporter(graphicReporter);

        long startTime = System.nanoTime();
        double error = alg.doQuantization(input, maxAbsCoordinates, maxIterations, repetitions,
                numberOfNeurons, epsilon, dimension);
        long stopTime = System.nanoTime();

        String fileName = "KM-" + repetitions;

        System.out.print(fileName + " ");
        System.out.printf("%.3f ", error);
        System.out.printf("%.3f\n", (stopTime - startTime) / (1000.0 * 1000 * 1000));

        graphicReporter.generateGIF(new File("C:\\Users\\Ardavel\\Desktop\\results\\"
                + fileName + ".gif"), 10);
        graphicReporter.generateErrorChart("error-" + fileName + ".png");
    }

    private static void runCompression() throws IOException {
        int frameSize = 4;

        String fileName = "NG-" + numberOfNeurons + "-";
        if (learningFactor instanceof PowerLearningFactor) {
            fileName += "PLF";
        } else {
            fileName += "ILF";
        }
        fileName += "-" + frameSize;

        String imageFileName = "drawing";
        String convertedFileName = imageFileName + "-" + fileName;

        File imageFile = new File("C:\\Users\\Ardavel\\Desktop\\images\\" + imageFileName + ".png");
        File compressedImageFile = new File("C:\\Users\\Ardavel\\Desktop\\results\\" + convertedFileName + ".png");

        GraphicReporter graphicReporter = new GraphicReporter(input);
        ImageReader imageReader = new DefaultImageReader();
        ImageData imageData = imageReader.readBlackAndWhiteImage(imageFile);
        ImageHolder imageHolder = new ImageHolder(imageData, frameSize);

        NeuronCollection collection = new NeuronCollection(numberOfNeurons, frameSize * frameSize, 1);
        GasTrainer gasTrainer = new OnlineTrainer(graphicReporter);

        List<Point> frames = new ArrayList<>(imageHolder.getPointRepresentation());

        long startTime = System.nanoTime();
        List<Double> quantizationError = gasTrainer.trainNeurons(collection,
                frames, maxIterations, learningFactor, neighbourhoodFactor, metric);
        long stopTime = System.nanoTime();

        System.out.print(fileName + " ");
        System.out.printf("%.3f ", quantizationError.get(quantizationError.size() - 1));
        System.out.printf("%.3f\n", (stopTime - startTime) / (1000.0 * 1000 * 1000));

        List<Point> newRepresentation = new ArrayList<>();
        imageHolder.getPointRepresentation().stream().forEach((frame) -> {
            newRepresentation.add(frame.getRepresentative());
        });
        imageHolder.setPointRepresentation(newRepresentation);

        BufferedImage recreatedImage = imageHolder.recreateImage();
        ImageIO.write(recreatedImage, "png", compressedImageFile);

        graphicReporter.generateErrorChart(imageFileName + "-error-"
                + fileName + ".png");
    }
}
