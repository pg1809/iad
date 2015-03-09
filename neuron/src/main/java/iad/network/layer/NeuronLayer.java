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

    public List<AbstractNeuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(List<AbstractNeuron> neurons) {
        this.neurons = neurons;
    }
}
