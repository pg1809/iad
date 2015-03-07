package iad.network.factory;

import iad.network.AbstractNetwork;
import iad.network.SingleNeuronNetwork;
import iad.network.exceptions.NoStrategySpecifiedException;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.Neuron;
import iad.network.strategy.NeuronStrategy;
import iad.network.weight.RandomWeightsGenerator;

/**
 *
 * @author Wojciech Szałapski
 */
public class SingleNeuronNetworkFactory implements NetworkFactory {

    private int inputs;

    private NeuronStrategy strategy;

    private RandomWeightsGenerator weightsGenerator;

    public SingleNeuronNetworkFactory(int inputs, NeuronStrategy strategy) {
        this.inputs = inputs;
        this.strategy = strategy;

        weightsGenerator = new RandomWeightsGenerator(inputs, 0, 1);
    }

    @Override
    public AbstractNetwork createNetwork() throws NoStrategySpecifiedException {
        if (strategy == null) {
            throw new NoStrategySpecifiedException();
        }

        SingleNeuronNetwork network = new SingleNeuronNetwork(inputs);

        NeuronLayer outputLayer = new NeuronLayer();
        Neuron neuron = new Neuron(strategy);
        outputLayer.addNeuron(neuron);
        network.setOutputLayer(outputLayer);

        return network;
    }

    public int getInputs() {
        return inputs;
    }

    public void setInputs(int inputs) {
        this.inputs = inputs;
    }

    public NeuronStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(NeuronStrategy strategy) {
        this.strategy = strategy;
    }
}
