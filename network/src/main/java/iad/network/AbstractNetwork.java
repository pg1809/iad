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

    private NeuronLayer inputLayer = new NeuronLayer();

    private Collection<NeuronLayer> hiddenLayers = new ArrayList<>();

    private NeuronLayer outputLayer = new NeuronLayer();

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
