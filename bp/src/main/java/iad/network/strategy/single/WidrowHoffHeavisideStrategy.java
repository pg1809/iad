/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.strategy.single;

import iad.network.neuron.*;
import iad.network.strategy.BasicNeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class WidrowHoffHeavisideStrategy extends BasicNeuronStrategy {

    protected WidrowHoffHeavisideStrategy() {
    }

    public static WidrowHoffHeavisideStrategy getInstance() {
        return WidrowHoffHeavisideStrategyHolder.INSTANCE;
    }

    private static class WidrowHoffHeavisideStrategyHolder {

        private static final WidrowHoffHeavisideStrategy INSTANCE = new WidrowHoffHeavisideStrategy();
    }

    @Override
    public double transfer(double netValue) {
        if (netValue > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, Double expectedOutput, double learningRate) {
        neuron.setDelta(learningRate * (expectedOutput - neuron.getOutput()));
    }

    @Override
    public String toString() {
        return "Widrow-Hoff - Heaviside";
    }
}
