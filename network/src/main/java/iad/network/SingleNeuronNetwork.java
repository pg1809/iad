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

        int outputNeuronsCount = outputLayer.getNeurons().size();
        double[] result = new double[outputNeuronsCount];

        for (int i = 0; i < outputNeuronsCount; ++i) {
            AbstractNeuron currentNeuron = outputLayer.getNeurons().get(i);
            currentNeuron.updateOutput();
            result[i] = currentNeuron.getOutput();
        }

        return result;
    }
    
    public void printNeuron() {
        AbstractNeuron neuron = outputLayer.getNeurons().get(0);
        System.out.println("bias: " + neuron.getBias());
        
        List<NeuronInput> neuronInputs = neuron.getInputNeurons();
        for (int i = 0; i < neuronInputs.size(); ++i) {
            System.out.println("Input " + i + " : " + neuronInputs.get(i).getWeight());
        }
    }
}
