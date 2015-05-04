/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author PiotrGrzelak
 */
public class SOMTrainer {
    
    private double learningRate;
    
    private double neighbourhoodRadius;

    private int maxIterations;
    
    private int winningsLimit;
    
    private TrainingObserver observer;
    
    public SOMTrainer(double learningRate, double neighbourhoodRadius, int maxIterations, int winnigsLimit) {
        this.learningRate = learningRate;
        this.neighbourhoodRadius = neighbourhoodRadius;
        this.maxIterations = maxIterations;
        this.winningsLimit = winnigsLimit;
    }
    
    public void doTraining(NeuronMap map, List<double[]> data, NeighbourhoodFunction function) {
        double currentLearningRate = learningRate;
        double currentNeighbourhoodRadius = neighbourhoodRadius;
        for (int i = 0; i < maxIterations; ++i) {
            double error = doEpoch(map, data, function, currentLearningRate, currentNeighbourhoodRadius);
            observer.notifyAfterEpoch(map.getNeurons(), data, error);
            currentLearningRate = learningRate * (maxIterations - i + 1) / maxIterations;
            currentNeighbourhoodRadius = neighbourhoodRadius * (maxIterations - i + 1) / maxIterations;
        }
    }
    
    private double doEpoch(NeuronMap map, List<double[]> data, NeighbourhoodFunction function, double lr, double radius) {
        Collections.shuffle(data);
        List<Neuron> neurons = map.getNeurons();
        for (double[] point : data) {
            Neuron bmuNeuron = neurons.get(0);
            double distForBmu = bmuNeuron.getDistanceFromInputPoint(point);
            for (int i = 1; i < neurons.size(); ++i) {
                double dist = neurons.get(i).getDistanceFromInputPoint(point);
                if (distForBmu < dist) {
                    bmuNeuron = neurons.get(i);
                    distForBmu = dist;
                }
            }
            
            List<Neuron> bmuNeighbourhood = map.getNeighbourhoodOf(bmuNeuron, radius);
            for (Neuron neuron : bmuNeighbourhood) {
                double distanceFunction = function.value(bmuNeuron, neuron, radius);
                neuron.adjustWeights(point, lr, distanceFunction);
            }
            
            bmuNeuron.addMappedPoint(point);
        }
        
        //tu w jakiś sprytny sposób będzie liczony błąd kwantyzacji :p
        return 0;
    }
}
