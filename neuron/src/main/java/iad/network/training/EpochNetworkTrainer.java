package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.SingleNeuronNetwork;
import iad.network.input.InputRow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class EpochNetworkTrainer extends NetworkTrainer {

    private final int numberOfEpochs;

    public EpochNetworkTrainer(int numberOfEpochs, double learningRate) {
        this.numberOfEpochs = numberOfEpochs;
        this.learningRate = learningRate;
    }

    @Override
    public List<Double> trainNetwork(AbstractNetwork network, List<InputRow> trainingData) {
        List<Double> meanSquaredErrors = new ArrayList<>();

        for (int i = 0; i < numberOfEpochs; ++i) {
            System.out.println("\nEpoch " + i);

            System.out.println("Parameters at the beginning:");
            ((SingleNeuronNetwork) network).printNeuron();
            System.out.println("");

            Collections.shuffle(trainingData);
            meanSquaredErrors.add(trainNetworkWithSampleSet(network, trainingData));
        }

        return meanSquaredErrors;
    }
}
