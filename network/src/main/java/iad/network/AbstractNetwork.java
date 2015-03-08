package iad.network;

import iad.network.input.InputRow;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class AbstractNetwork {

    protected NeuronLayer inputLayer = new NeuronLayer();

    protected Collection<NeuronLayer> hiddenLayers = new ArrayList<>();

    protected NeuronLayer outputLayer = new NeuronLayer();

    public void readSample(double[] sample) {
        List<AbstractNeuron> inputNeurons = inputLayer.getNeurons();
        for (int i = 0; i < inputNeurons.size(); ++i) {
            inputNeurons.get(i).setOutput(sample[i]);
        }
    }
    
    public abstract double[] runNetwork(double[] sample);

    public NeuronLayer getInputLayer() {
        return inputLayer;
    }

    public void setInputLayer(NeuronLayer inputLayer) {
        this.inputLayer = inputLayer;
    }

    public Collection<NeuronLayer> getHiddenLayers() {
        return hiddenLayers;
    }

    public void setHiddenLayers(Collection<NeuronLayer> hiddenLayers) {
        this.hiddenLayers = hiddenLayers;
    }

    public NeuronLayer getOutputLayer() {
        return outputLayer;
    }

    public void setOutputLayer(NeuronLayer outputLayer) {
        this.outputLayer = outputLayer;
    }
}
