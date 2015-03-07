package iad.network.neuron;

import iad.network.strategy.NeuronStrategy;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class AbstractNeuron {

    protected double output;

    protected double bias;

    protected double delta;

    protected List<AbstractNeuron> forwardNeurons = new ArrayList<>();

    protected List<NeuronInput> inputNeurons = new ArrayList<>();

    protected NeuronStrategy strategy;

    public void updateOutput() {
        double netValue = strategy.calculateNetValue(inputNeurons, bias);
        output = strategy.transfer(netValue);
    }

    public void updateDelta(double expectedOutput, double learningRate) {
        strategy.updateDelta(this, expectedOutput, learningRate);
    }

    public void updateParameters() {
        strategy.updateBias(this, delta);
        strategy.updateWeights(this, delta);
    }

    public void addForwardNeuron(AbstractNeuron neuron) {
        forwardNeurons.add(neuron);
    }

    public void addInputNeuron(NeuronInput inputNeuron) {
        inputNeurons.add(inputNeuron);
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public List<AbstractNeuron> getForwardNeurons() {
        return forwardNeurons;
    }

    public void setForwardNeurons(List<AbstractNeuron> forwardNeurons) {
        this.forwardNeurons = forwardNeurons;
    }

    public List<NeuronInput> getInputNeurons() {
        return inputNeurons;
    }

    public void setInputNeurons(List<NeuronInput> inputNeurons) {
        this.inputNeurons = inputNeurons;
    }

    public NeuronStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
