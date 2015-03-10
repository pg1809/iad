/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.ui;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.MultiLayerNetworkFactory;
import iad.network.input.InputRow;
import iad.network.strategy.bp.BackPropagationStrategy;
import iad.network.training.ThresholdEpochNetworkTrainer;
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
        transformation(100000, 0.00001, 0.05, true);
    }

    private static void transformation(int maximumNumberOfEpochs, double errorThreshold,
            double learningRate, boolean useBias) {
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

                ThresholdEpochNetworkTrainer trainer = new ThresholdEpochNetworkTrainer(maximumNumberOfEpochs, errorThreshold, learningRate);
                List<Double> meanSquaredError = trainer.trainNetwork(network, trainingData);

                System.out.println("Number of epochs: " + meanSquaredError.size());

                System.out.println("Results:");
                for (int i = 0; i < inputs; ++i) {
                    System.out.println(" input: " + Arrays.toString(trainingData.get(i).getValues()));
                    System.out.println(" output: " + Arrays.toString(network.runNetwork(trainingData.get(i).getValues())));
                }
                System.out.println("");

            } catch (CannotCreateNetworkException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
