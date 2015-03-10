package iad.network.strategy;

import iad.network.neuron.AbstractNeuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class InputNeuronStrategy extends BasicNeuronStrategy {

    private InputNeuronStrategy() {
    }

    public static InputNeuronStrategy getInstance() {
        return InputNeuronStrategyHolder.INSTANCE;
    }

    @Override
    public double transfer(double netValue) {
        return netValue;
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, Double expectedOutput, double learningRate) {
        // This method should be empty for input layer
    }

    private static class InputNeuronStrategyHolder {
        private static final InputNeuronStrategy INSTANCE = new InputNeuronStrategy();
    }
 }
