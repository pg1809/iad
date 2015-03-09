package iad.network.strategy;

import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.NeuronInput;
import java.util.Collection;
import java.util.List;

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

    @Override
    public void updateBias(AbstractNeuron neuron, double delta) {
        neuron.setBias(neuron.getBias() + delta);
    }

    @Override
    public void updateWeights(AbstractNeuron neuron, double delta) {
        List<NeuronInput> inputNeurons = neuron.getInputNeurons();
        for (int i = 0; i < inputNeurons.size(); ++i) {
            NeuronInput currentInput = inputNeurons.get(i);
            double currentWeight = currentInput.getWeight();
            currentInput.setWeight(currentWeight + delta * currentInput.getInputNeuron().getOutput());
        }
    }
}
