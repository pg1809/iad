package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.neuron.AbstractNeuron;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class SingleNeuronNetworkTrainer extends NetworkTrainer {
    
    @Override
    protected void trainNetworkWithSample(AbstractNetwork network, double[] expectedOutput, double[] sample) {
        network.readSample(sample);
        
        List<AbstractNeuron> outputNeurons = network.getOutputLayer().getNeurons();
        int outputNeuronsCount = outputNeurons.size();

        for (int i = 0; i < outputNeuronsCount; ++i) {
            outputNeurons.get(i).updateNeuron(expectedOutput[i], learningRate, momentumFactor);
        }
    }
}
