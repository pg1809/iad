/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.MultiLayerNetworkFactory;
import iad.network.input.ClassificationDataProvider;
import iad.network.input.InputRow;
import iad.network.input.TrainingDataProvider;
import iad.network.neuron.AbstractNeuron;
import iad.network.strategy.bp.BackPropagationStrategy;
import iad.network.strategy.bp.IdentityActivationBPS;
import iad.network.training.ThresholdEpochNetworkTrainer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wojciech Szałapski
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        transformation(100000, 0.0001, 0.05, 0.9, true);
//        approximation(100000, 0.1, 0.005, 0, true);
//        classification(100000, 0.01, 0.01, 0.9);
    }

    private static void classification(int maximumNumberOfEpochs, double errorThreshold,
            double learningRate, double momentumFactor) {
        try {
            BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(new int[]{4, 4, 3}, strategy, true);
            MultiLayerNetwork network = factory.createNetwork();

            ClassificationDataProvider dataProvider = new ClassificationDataProvider(new File("classification_train.txt"), 4, 3, " ");
            List<InputRow> trainingData = dataProvider.provideAllRows();

            ThresholdEpochNetworkTrainer trainer
                    = new ThresholdEpochNetworkTrainer(maximumNumberOfEpochs, errorThreshold, learningRate, momentumFactor);
            List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

            PlotGenerator plotGenerator = new PlotGenerator();
            plotGenerator.generateErrorChart(meanSquaredError);
        } catch (IOException | CannotCreateNetworkException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void transformation(int maximumNumberOfEpochs, double errorThreshold,
            double learningRate, double momentumFactor, boolean useBias) {

        PlotGenerator plotGenerator = new PlotGenerator();
        BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();

        for (int neuronsInHidden = 1; neuronsInHidden <= 3; ++neuronsInHidden) {
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(new int[]{4, neuronsInHidden, 4}, strategy, useBias);
            try {
                MultiLayerNetwork network = factory.createNetwork();

                List<InputRow> trainingData = new ArrayList<>();

                int inputs = 4;
                for (int i = 0; i < inputs; ++i) {
                    double[] input = new double[inputs];
                    input[i] = 1;

                    trainingData.add(new InputRow(input, input));
                }

                ThresholdEpochNetworkTrainer trainer
                        = new ThresholdEpochNetworkTrainer(maximumNumberOfEpochs, errorThreshold, learningRate, momentumFactor);
                List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

                plotGenerator.generateErrorChart(meanSquaredError);

                System.out.println("Number of epochs: " + meanSquaredError.size());

                System.out.println("Results:");
                for (int i = 0; i < inputs; ++i) {
                    System.out.println(" input: " + Arrays.toString(trainingData.get(i).getValues()));
                    System.out.println(" output: " + Arrays.toString(network.runNetwork(trainingData.get(i).getValues())));
                }
                System.out.println("");

            } catch (CannotCreateNetworkException | IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private static void approximation(int maximumNumberOfEpochs, double errorThreshold,
            double learningRate, double momentumFactor, boolean useBias) {

        BackPropagationStrategy strategy = BackPropagationStrategy.getInstance();
        IdentityActivationBPS identityStrategy = IdentityActivationBPS.getInstance();

        for (int neuronsHidden = 10; neuronsHidden <= 10; ++neuronsHidden) {
            MultiLayerNetworkFactory factory = new MultiLayerNetworkFactory(new int[]{1, neuronsHidden, 1}, strategy, useBias);
            try {
                MultiLayerNetwork network = factory.createNetwork();
                network.getOutputLayer().getNeurons().stream().forEach((AbstractNeuron n) -> (n.setStrategy(identityStrategy)));

                TrainingDataProvider dataProvider = new TrainingDataProvider(new File("approximation_train_1.txt"), 1, 1, " ");
                List<InputRow> trainingData = dataProvider.provideAllRows();

                ThresholdEpochNetworkTrainer trainer
                        = new ThresholdEpochNetworkTrainer(maximumNumberOfEpochs, errorThreshold, learningRate, momentumFactor);
                List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

                PlotGenerator plotGenerator = new PlotGenerator();
                plotGenerator.generateErrorChart(meanSquaredError);

                System.out.println("Number of epochs: " + meanSquaredError.size());

                System.out.println("Results:");
                for (int i = 0; i < trainingData.size(); ++i) {
                    System.out.println(" input: " + Arrays.toString(trainingData.get(i).getValues()));
                    System.out.println(" network output: " + Arrays.toString(network.runNetwork(trainingData.get(i).getValues())));
                }
                System.out.println("");

            } catch (CannotCreateNetworkException | IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
