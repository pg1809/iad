package iad.network.neuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class NeuronInput {

    private AbstractNeuron inputNeuron;

    private double weight;

    public NeuronInput(AbstractNeuron inputNeuron, double weight) {
        this.inputNeuron = inputNeuron;
        this.weight = weight;
    }

    public AbstractNeuron getInputNeuron() {
        return inputNeuron;
    }

    public void setInputNeuron(AbstractNeuron inputNeuron) {
        this.inputNeuron = inputNeuron;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
