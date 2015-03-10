package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.input.InputRow;
import iad.network.layer.NeuronLayer;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class NetworkTrainer {

    private final static double DEFAULT_LEARNING_RATE = 0.1;

    protected double learningRate = DEFAULT_LEARNING_RATE;

    public abstract List<Double> trainNetwork(AbstractNetwork network, List<InputRow> trainingData);

    protected double trainNetworkWithSampleSet(AbstractNetwork network, List<InputRow> trainingData) {
        for (InputRow trainingDataSample : trainingData) {
            trainNetworkWithSample(network, trainingDataSample.getExpectedOutput(), trainingDataSample.getValues());
        }

        double meanSquaredError = 0;
        for (InputRow trainingDataSample : trainingData) {
            double sampleError = 0;
            double[] output = network.runNetwork(trainingDataSample.getValues());

            int outputLength = output.length;
            for (int i = 0; i < outputLength; ++i) {
                sampleError += Math.pow(trainingDataSample.getExpectedOutput()[i] - output[i], 2);
            }
            sampleError /= 2;
            sampleError /= outputLength;

            meanSquaredError += sampleError;
        }

        meanSquaredError /= trainingData.size();
        return meanSquaredError;
    }

    protected void trainNetworkWithSample(AbstractNetwork network, double[] expectedOutput, double[] sample) {
        network.runNetwork(sample);
        network.getOutputLayer().updateDelta(expectedOutput, learningRate);

        List<NeuronLayer> hiddenLayers = network.getHiddenLayers();
        for (int i = hiddenLayers.size() - 1; i >= 0; --i) {
            network.getHiddenLayers().get(i).updateDelta(null, learningRate);
        }

        for (NeuronLayer hiddenLayer : hiddenLayers) {
            hiddenLayer.updateParameters();
        }

        network.getOutputLayer().updateParameters();
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
