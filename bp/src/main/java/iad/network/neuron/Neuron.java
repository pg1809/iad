package iad.network.neuron;

import iad.network.strategy.NeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class Neuron extends AbstractNeuron {

    public Neuron(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
