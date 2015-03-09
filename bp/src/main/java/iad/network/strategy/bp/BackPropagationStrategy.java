package iad.network.strategy.bp;

import iad.network.neuron.AbstractNeuron;
import iad.network.strategy.BasicNeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class BackPropagationStrategy extends BasicNeuronStrategy {

    @Override
    public double transfer(double netValue) {
        return (1 / (1 + Math.exp(-netValue)));
    }

    @Override
    public void updateBias(AbstractNeuron neuron, double delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateWeights(AbstractNeuron neuron, double delta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, double expectedOutput, double learningRate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
