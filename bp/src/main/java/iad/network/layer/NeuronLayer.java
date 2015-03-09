package iad.network.layer;

import iad.network.neuron.AbstractNeuron;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class NeuronLayer {

    private List<AbstractNeuron> neurons = new ArrayList<>();

    public void addNeuron(AbstractNeuron neuron) {
        neurons.add(neuron);
    }

    public void updateOutput() {
        for (AbstractNeuron neuron : neurons) {
            neuron.updateOutput();
        }
    }

    public double[] updateAndGetOutput() {
        int neuronsCount = neurons.size();
        double[] result = new double[neuronsCount];

        for (int i = 0; i < neuronsCount; ++i) {
            AbstractNeuron currentNeuron = neurons.get(i);
            currentNeuron.updateOutput();
            result[i] = currentNeuron.getOutput();
        }

        return result;
    }

    public List<AbstractNeuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<AbstractNeuron> neurons) {
        this.neurons = neurons;
    }
}
