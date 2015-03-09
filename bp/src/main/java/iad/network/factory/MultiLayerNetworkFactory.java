package iad.network.factory;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.Neuron;
import iad.network.strategy.NeuronStrategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class MultiLayerNetworkFactory implements NetworkFactory {

    private final int[] neuronsNumberPerLayer;

    private NeuronStrategy strategy;

    public MultiLayerNetworkFactory(int[] neuronsNumberPerLayer, NeuronStrategy strategy) {
        this.neuronsNumberPerLayer = neuronsNumberPerLayer;
        this.strategy = strategy;
    }

    @Override
    public MultiLayerNetwork createNetwork() throws CannotCreateNetworkException {
        MultiLayerNetwork network = new MultiLayerNetwork();

        NeuronLayer inputLayer = createLayerWithNeurons(neuronsNumberPerLayer[0]);
        network.setInputLayer(inputLayer);

        int numberOfLayers = neuronsNumberPerLayer.length;
        for (int i = 1; i < numberOfLayers - 1; ++i) {
            network.addHiddenLayer(createLayerWithNeurons(neuronsNumberPerLayer[i]));
        }

        NeuronLayer outputLayer = createLayerWithNeurons(neuronsNumberPerLayer[numberOfLayers - 1]);
        network.setOutputLayer(outputLayer);

        network.connectAllLayers();

        return network;
    }

    private NeuronLayer createLayerWithNeurons(int numberOfNeurons) {
        NeuronLayer layer = new NeuronLayer();
        for (int i = 0; i < numberOfNeurons; ++i) {
            AbstractNeuron neuron = new Neuron(strategy);
            layer.addNeuron(neuron);
        }
        return layer;
    }

    public NeuronStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
