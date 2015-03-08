/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.strategy;

import iad.network.neuron.NeuronInput;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public class PerceptronStrategy implements NeuronStrategy {

    private PerceptronStrategy() {
    }

    public static PerceptronStrategy getInstance() {
        return PerceptronStrategyHolder.INSTANCE;
    }

    private static class PerceptronStrategyHolder {

        private static final PerceptronStrategy INSTANCE = new PerceptronStrategy();
    }

    @Override
    public double transfer(double netValue) {
        if (netValue >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public double calculateNetValue(Collection<NeuronInput> inputNeurons) {
        double netValue = 0;
        for (NeuronInput neuron : inputNeurons) {
            netValue += neuron.getInputNeuron().getOutput() * neuron.getWeight();
        }
        
        return netValue;
    }
}
