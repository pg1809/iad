package iad.network.neuron;

import iad.network.strategy.NeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class StaticRadialNeuron extends RadialNeuron {

    public StaticRadialNeuron(NeuronStrategy strategy) {
        super(strategy);
    }

    @Override
    public void updateParameters(double momentumFactor) {
    }
}
