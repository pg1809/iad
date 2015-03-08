/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.strategy;

import iad.network.neuron.*;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class WidrowHoffHeavisideStrategy implements NeuronStrategy {

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
    public double calculateNetValue(Collection<NeuronInput> inputNeurons, double bias) {
        double netValue = bias;
        for (NeuronInput neuron : inputNeurons) {
            netValue += neuron.getInputNeuron().getOutput() * neuron.getWeight();
        }

        return netValue;
    }

    @Override
    public void updateBias(AbstractNeuron neuron, double delta) {
        neuron.setBias(neuron.getBias() + delta);
    }

    @Override
    public void updateWeights(AbstractNeuron neuron, double delta) {
        List<NeuronInput> inputNeurons = neuron.getInputNeurons();
        for (int i = 0; i < inputNeurons.size(); ++i) {
            NeuronInput currentInput = inputNeurons.get(i);
            double currentWeight = currentInput.getWeight();
            currentInput.setWeight(currentWeight + delta * currentInput.getInputNeuron().getOutput());
        }
    }

    @Override
    public void updateDelta(AbstractNeuron neuron, double expectedOutput, double learningRate) {
        neuron.setDelta(learningRate * (expectedOutput - neuron.getOutput()));
    }

    @Override
    public String toString() {
        return "Widrow-Hoff - Heaviside";
    }
}
