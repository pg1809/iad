package iad.network;

import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.NeuronInput;
import iad.network.weight.RandomWeightsGenerator;

/**
 *
 * @author Wojciech Szałapski
 */
public class MultiLayerNetwork extends AbstractNetwork {

    private void connectLayers(NeuronLayer backLayer, NeuronLayer forwardLayer) {
        double[][] weights = generateWeightsForConnection(backLayer, forwardLayer);

        int backLayerSize = backLayer.getNeurons().size();
        int forwardLayerSize = forwardLayer.getNeurons().size();

        for (int i = 0; i < backLayerSize; ++i) {
            for (int j = 0; j < forwardLayerSize; ++j) {
                AbstractNeuron backNeuron = backLayer.getNeurons().get(i);
                AbstractNeuron forwardNeuron = forwardLayer.getNeurons().get(j);

                backNeuron.addForwardNeuron(forwardNeuron);

                NeuronInput neuronInput = new NeuronInput(backNeuron, weights[i][j]);
                forwardNeuron.addInputNeuron(neuronInput);
            }
        }
    }

    private double[][] generateWeightsForConnection(NeuronLayer backLayer, NeuronLayer forwardLayer) {
        int backLayerSize = backLayer.getNeurons().size();
        int forwardLayerSize = forwardLayer.getNeurons().size();

        double weights[][] = new double[backLayerSize][forwardLayerSize];

        RandomWeightsGenerator generator = new RandomWeightsGenerator(forwardLayerSize, 0, 1);
        for (int i = 0; i < backLayerSize; ++i) {
            weights[i] = generator.generateWeights();
        }

        return weights;
    }

    public void connectAllLayers() {
        connectLayers(inputLayer, hiddenLayers.get(0));

        int numberOfHiddenLayers = hiddenLayers.size();
        for (int i = 0; i < numberOfHiddenLayers - 1; ++i) {
            connectLayers(hiddenLayers.get(i), hiddenLayers.get(i + 1));
        }

        connectLayers(hiddenLayers.get(hiddenLayers.size() - 1), outputLayer);
    }
}
