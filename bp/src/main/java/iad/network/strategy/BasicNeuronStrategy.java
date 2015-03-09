package iad.network.strategy;

import iad.network.neuron.NeuronInput;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class BasicNeuronStrategy implements NeuronStrategy {

    @Override
    public double calculateNetValue(Collection<NeuronInput> inputNeurons, double bias) {
        double netValue = bias;
        for (NeuronInput neuron : inputNeurons) {
            netValue += neuron.getInputNeuron().getOutput() * neuron.getWeight();
        }

        return netValue;
    }
}
