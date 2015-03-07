package iad.network.neuron;

import iad.network.strategy.NeuronStrategy;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class AbstractNeuron {

    protected double output;

    protected Collection<AbstractNeuron> forwardNeurons = new ArrayList<>();

    protected Collection<NeuronInput> inputNeurons = new ArrayList<>();

    protected NeuronStrategy strategy;

    public void updateOutput() {
        double netValue = strategy.calculateNetValue(inputNeurons);
        output = strategy.transfer(netValue);
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

    public Collection<AbstractNeuron> getForwardNeurons() {
        return forwardNeurons;
    }

    public void setForwardNeurons(Collection<AbstractNeuron> forwardNeurons) {
        this.forwardNeurons = forwardNeurons;
    }

    public Collection<NeuronInput> getInputs() {
        return inputNeurons;
    }

    public void setInputs(Collection<NeuronInput> inputNeurons) {
        this.inputNeurons = inputNeurons;
    }

    public NeuronStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
