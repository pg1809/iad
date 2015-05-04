package pl.iad.quantization.algorithms.gas.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class NeuronCollection implements Iterable<Neuron>{

    private final List<Neuron> neurons;

    public NeuronCollection(int numberOfNeurons, int dimension, double maxWeightValue) {
        neurons = new ArrayList<>(numberOfNeurons);
        for (int i = 0; i < numberOfNeurons; ++i) {
            neurons.add(new Neuron(dimension, maxWeightValue));
        }
    }

    @Override
    public Iterator<Neuron> iterator() {
        return neurons.iterator();
    }
    
    public List<Neuron> getNeurons() {
        return neurons;
    }
}
