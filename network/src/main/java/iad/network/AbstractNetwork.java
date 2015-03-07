package iad.network;

import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class AbstractNetwork {

    private NeuronLayer inputLayer;

    private Collection<NeuronLayer> hiddenLayers = new ArrayList<>();

    private NeuronLayer outputLayer;

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
