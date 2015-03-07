package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.input.TrainingInputRow;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class EpochNetworkTrainer extends NetworkTrainer {

    private final int numberOfEpochs;
    
    private final double learningRate;

    public EpochNetworkTrainer(int numberOfEpochs, double learningRate) {
        this.numberOfEpochs = numberOfEpochs;
        this.learningRate = learningRate;
    }
    
    @Override
    public void trainNetwork(AbstractNetwork network, List<TrainingInputRow> trainingData) {
        for (int i = 0; i < numberOfEpochs; ++i) {
            Collections.shuffle(trainingData);
            trainNetworkWithSampleSet(network, trainingData);
        }
    }
}
