package iad.network.training;

import iad.network.AbstractNetwork;
import iad.network.input.InputRow;
import iad.network.neuron.AbstractNeuron;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class NetworkTrainer {

    private final static double DEFAULT_LEARNING_RATE = 0.1;
    
    private double learningRate = DEFAULT_LEARNING_RATE;
    
    public abstract void trainNetwork(AbstractNetwork network, List<InputRow> trainingData);
    
    protected void trainNetworkWithSampleSet(AbstractNetwork network, List<InputRow> trainingData) {
        for (InputRow trainingDataSample : trainingData) {
            List<AbstractNeuron> inputNeurons = network.getInputLayer().getNeurons();
            for (int i = 0; i < inputNeurons.size(); ++i) {
                inputNeurons.get(i).setOutput(trainingDataSample.getValues()[i]);
            }
            
            trainNetworkFedWithSample(network, trainingDataSample.getExpectedOutput());
        }
    }

    protected void trainNetworkFedWithSample(AbstractNetwork network, double expectedOutput) {
        List<AbstractNeuron> outputNeurons = network.getOutputLayer().getNeurons();
        for (AbstractNeuron neuron : outputNeurons) {
            neuron.updateOutput();
            neuron.updateDelta(expectedOutput, learningRate);
            neuron.updateParameters();
        }
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
