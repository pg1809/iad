package iad.network.factory;

import iad.network.MultiLayerNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.Neuron;
import iad.network.neuron.RadialNeuron;
import iad.network.strategy.NeuronStrategy;
import iad.network.strategy.bp.BiasStrategyDecorator;
import java.util.Random;

/**
 *
 * @author Wojciech Szałapski
 */
public class MultiLayerNetworkFactory implements NetworkFactory {

    private final int[] neuronsNumberPerLayer;

    private final boolean useBias;

    private NeuronStrategy strategy;

    Random generator = new Random();

    public MultiLayerNetworkFactory(int[] neuronsNumberPerLayer, NeuronStrategy strategy, boolean useBias) {
        this.neuronsNumberPerLayer = neuronsNumberPerLayer;
        this.useBias = useBias;

        if (useBias) {
            strategy = new BiasStrategyDecorator(strategy);
        }
        this.strategy = strategy;
    }

    @Override
    public MultiLayerNetwork createNetwork() throws CannotCreateNetworkException {
        MultiLayerNetwork network = new MultiLayerNetwork();

        network.setInputLayer(createLayerWithNeurons(neuronsNumberPerLayer[0]));
        network.setHiddenLayer(createRadialLayer(neuronsNumberPerLayer[1]));
        network.setOutputLayer(createLayerWithNeurons(neuronsNumberPerLayer[2]));

        network.connectAllLayers();

        return network;
    }

    private NeuronLayer createLayerWithNeurons(int numberOfNeurons) {
        NeuronLayer layer = new NeuronLayer();
        for (int i = 0; i < numberOfNeurons; ++i) {
            AbstractNeuron neuron = new Neuron(strategy);
            if (useBias) {
                neuron.setBias(generator.nextDouble());
                neuron.setPreviousBias(neuron.getBias());
            }
            layer.addNeuron(neuron);
        }
        return layer;
    }
    
    private NeuronLayer createRadialLayer(int numberOfNeurons) {
        NeuronLayer layer = new NeuronLayer();
        for (int i = 0; i < numberOfNeurons; ++i) {
            layer.addNeuron(new RadialNeuron(strategy));
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
