package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.input.InputRow;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.RadialNeuron;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class ThresholdEpochNetworkTrainer extends NetworkTrainer {

    private final int maximumNumberOfEpochs;

    private final double errorThreshold;

    public ThresholdEpochNetworkTrainer(int maximumNumberOfEpochs, double errorThreshold,
            double learningRate, double momentumFactor) {
        this.maximumNumberOfEpochs = maximumNumberOfEpochs;
        this.errorThreshold = errorThreshold;
        this.learningRate = learningRate;
        this.momentumFactor = momentumFactor;
    }

    @Override
    public List<Double> trainNetwork(AbstractNetwork network, List<InputRow> trainingData) {
        generateStartingWeights(network);
        adjustCentersInRadialLayer(network.getHiddenLayer(), trainingData);

        List<Double> meanSquaredErrors = new ArrayList<>();

        for (int i = 0; i < maximumNumberOfEpochs; ++i) {
            System.out.println("EPOCH " + i);
            Collections.shuffle(trainingData);

            double error = trainNetworkWithSampleSet(network, trainingData);
            meanSquaredErrors.add(error);

            if (error <= errorThreshold) {
                return meanSquaredErrors;
            }
        }

        return meanSquaredErrors;
    }
}
