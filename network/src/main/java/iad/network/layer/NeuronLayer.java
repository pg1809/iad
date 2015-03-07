package iad.network.layer;

import iad.network.neuron.AbstractNeuron;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public class NeuronLayer {

    private Collection<AbstractNeuron> neurons = new ArrayList<>();

    public void addNeuron(AbstractNeuron neuron) {
        neurons.add(neuron);
    }

    public Collection<AbstractNeuron> getNeurons() {
        return neurons;
    }

    public void setNeurons(Collection<AbstractNeuron> neurons) {
        this.neurons = neurons;
    }
}
