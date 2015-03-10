package iad.network.strategy.bp;

import iad.network.neuron.AbstractNeuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class NoBiasBackPropagationStrategy extends BackPropagationStrategy {

    private NoBiasBackPropagationStrategy() {
    }

    public static NoBiasBackPropagationStrategy getInstance() {
        return NoBiasBackPropagationStrategyHolder.INSTANCE;
    }

    @Override
    public void updateBias(AbstractNeuron neuron, double delta, double momentumFactor) {
        // This method should be empty with no bias strategy
    }
    
    private static class NoBiasBackPropagationStrategyHolder {
        private static final NoBiasBackPropagationStrategy INSTANCE = new NoBiasBackPropagationStrategy();
    }
 }
