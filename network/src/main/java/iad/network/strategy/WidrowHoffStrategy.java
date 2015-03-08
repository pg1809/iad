package iad.network.strategy;

import iad.network.neuron.AbstractNeuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class WidrowHoffStrategy extends PerceptronStrategy {

    public static WidrowHoffStrategy getInstance() {
        return WidrowHoffStrategyHolder.INSTANCE;
    }

    private static class WidrowHoffStrategyHolder {

        private static final WidrowHoffStrategy INSTANCE = new WidrowHoffStrategy();
    }

    @Override
    public double transfer(double netValue) {
        return 1 / (1 + Math.exp(-netValue));
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, double expectedOutput, double learningRate) {
        neuron.setDelta(learningRate * 2 * (expectedOutput - neuron.getOutput())
                * neuron.getNetValue() * (1 - neuron.getNetValue()));
    }

    @Override
    public String toString() {
        return "Widrow-Hoff";
    }
}
