package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.input.InputRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class ThresholdEpochNetworkTrainer extends NetworkTrainer {

    private final int maximumNumberOfEpochs;

    private final double errorThreshold;

    public ThresholdEpochNetworkTrainer(int maximumNumberOfEpochs, double errorThreshold, double learningRate) {
        this.maximumNumberOfEpochs = maximumNumberOfEpochs;
        this.errorThreshold = errorThreshold;
        this.learningRate = learningRate;
    }

    @Override
    public List<Double> trainNetwork(AbstractNetwork network, List<InputRow> trainingData) {
        List<Double> meanSquaredErrors = new ArrayList<>();

        for (int i = 0; i < maximumNumberOfEpochs; ++i) {
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
