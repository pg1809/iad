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
    
    protected double learningRate = DEFAULT_LEARNING_RATE;
    
    public abstract List<Double> trainNetwork(AbstractNetwork network, List<InputRow> trainingData);
    
    protected double trainNetworkWithSampleSet(AbstractNetwork network, List<InputRow> trainingData) {
        for (InputRow trainingDataSample : trainingData) {
            network.readSample(trainingDataSample.getValues());
            trainNetworkFedWithSample(network, trainingDataSample.getExpectedOutput());
        }
        
        double meanSquaredError = 0;
        for (InputRow trainingDataSample : trainingData) {
            double sampleError = 0;
            double[] output = network.runNetwork(trainingDataSample.getValues());
            
            int outputLength = output.length;
            for (int i = 0; i < outputLength; ++i) {
                sampleError = Math.pow(trainingDataSample.getExpectedOutput()[i] - output[i], 2);
            }
            sampleError /= outputLength;
            
            meanSquaredError += sampleError;
        }
        
        meanSquaredError /= trainingData.size();
        return meanSquaredError;
    }
    
    protected void trainNetworkFedWithSample(AbstractNetwork network, double[] expectedOutput) {
        List<AbstractNeuron> outputNeurons = network.getOutputLayer().getNeurons();
        int outputNeuronsCount = outputNeurons.size();
        
        for (int i = 0; i < outputNeuronsCount; ++i) {
            outputNeurons.get(i).updateNeuron(expectedOutput[i], learningRate);
        }
    }
    
    public double getLearningRate() {
        return learningRate;
    }
    
    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }
}
