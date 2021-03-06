package iad.network;

import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class AbstractNetwork {

    protected NeuronLayer inputLayer = new NeuronLayer();

    protected NeuronLayer hiddenLayer = new NeuronLayer();

    protected NeuronLayer outputLayer = new NeuronLayer();

    public void readSample(double[] sample) {
        List<AbstractNeuron> inputNeurons = inputLayer.getNeurons();
        for (int i = 0; i < inputNeurons.size(); ++i) {
            inputNeurons.get(i).setOutput(sample[i]);
        }
    }

    public double[] runNetwork(double[] sample) {
        readSample(sample);

        hiddenLayer.updateOutput();
        return outputLayer.updateAndGetOutput();
    }

    public NeuronLayer getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(NeuronLayer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public NeuronLayer getHiddenLayer() {
        return hiddenLayer;
    }

    public void setHiddenLayer(NeuronLayer hiddenLayer) {
        this.hiddenLayer = hiddenLayer;
    }

    public NeuronLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        this.outputLayer = outputLayer;
    }
}
