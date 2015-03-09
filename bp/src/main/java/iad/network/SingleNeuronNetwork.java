package iad.network;

import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.NeuronInput;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class SingleNeuronNetwork extends AbstractNetwork {

    private final int inputs;

    public SingleNeuronNetwork(int inputs) {
        this.inputs = inputs;
    }

    @Override
    public double[] runNetwork(double[] sample) {
        readSample(sample);

        return outputLayer.updateAndGetOutput();
    }

    public void printNeuron() {
        AbstractNeuron neuron = outputLayer.getNeurons().get(0);
        System.out.println("bias: " + neuron.getBias());

        List<NeuronInput> neuronInputs = neuron.getInputNeurons();
        for (int i = 0; i < neuronInputs.size(); ++i) {
            System.out.println("Input " + i + " : " + neuronInputs.get(i).getWeight());
        }

        double w0 = neuron.getBias();
        double w1 = neuronInputs.get(0).getWeight();
        double w2 = neuronInputs.get(1).getWeight();
        System.out.println("line: " + (-w1 / w2) + " * x + " + (-w0 / w2));
    }

    public double getSlope() {
        AbstractNeuron neuron = outputLayer.getNeurons().get(0);
        List<NeuronInput> neuronInputs = neuron.getInputNeurons();
        double w1 = neuronInputs.get(0).getWeight();
        double w2 = neuronInputs.get(1).getWeight();
        return -w1 / w2;
    }

    public double getIntercept() {
        AbstractNeuron neuron = outputLayer.getNeurons().get(0);
        List<NeuronInput> neuronInputs = neuron.getInputNeurons();
        double w0 = neuron.getBias();
        double w2 = neuronInputs.get(1).getWeight();
        return -w0 / w2;
    }
}
