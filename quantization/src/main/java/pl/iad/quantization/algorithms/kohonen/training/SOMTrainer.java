/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen.training;

import pl.iad.quantization.reporting.TrainingObserver;
import java.util.ArrayList;
import pl.iad.quantization.algorithms.kohonen.structure.NeuronMap;
import pl.iad.quantization.algorithms.kohonen.adaptation.NeighbourhoodFunction;
import java.util.Collections;
import java.util.List;
import pl.iad.quantization.algorithms.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.structure.KohonenNeuron;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.metrics.Metric;

/**
 *
 * @author PiotrGrzelak
 */
public class SOMTrainer {

    private LearningFactorProvider learningFactorProvider;

    private NeighbourhoodFactorProvider neighbourhoodFactorProvider;

    private Metric metric;

    private NeighbourhoodFunction function;

    private TrainingObserver observer;

    public List<Double> doTraining(NeuronMap map, List<Point> data, int maxIterations) {
        List<Double> errors = new ArrayList<>(maxIterations);
        for (int i = 0; i <= maxIterations; ++i) {
            double radius = neighbourhoodFactorProvider.getNeighbourhoodFactor(i);
            double error = doEpoch(i, map, data, radius);
            errors.add(error);
            
            observer.notifyAfterEpoch(map.getNeurons(), error);
        }

        return errors;
    }

    private double doEpoch(int epochIndex, NeuronMap map, List<Point> data, double radius) {
        Collections.shuffle(data);
        List<KohonenNeuron> neurons = map.getNeurons();
        for (Point point : data) {
            KohonenNeuron bmuNeuron = neurons.get(0);
            double bmuDistance = metric.distance(point, bmuNeuron);
            for (int i = 1; i < neurons.size(); ++i) {
                KohonenNeuron neuron = neurons.get(i);
                double dist = metric.distance(point, neuron);
                if (bmuDistance > dist) {
                    bmuNeuron = neuron;
                    bmuDistance = dist;
                }
            }
            bmuNeuron.addWin();

            List<KohonenNeuron> bmuNeighbourhood = map.getNeighbourhoodOf(bmuNeuron, radius);
            for (KohonenNeuron neuron : bmuNeighbourhood) {
                double distanceFunction = function.value(bmuNeuron, neuron, radius);
                double lr = learningFactorProvider.getLearningFactor(epochIndex, neuron.getWins());
                adjustWeights(neuron, point, lr, distanceFunction);
            }

        }

        double distanceSum = 0;

        for (int i = 0; i < data.size(); ++i) {
            double minDistance = Double.MAX_VALUE;

            for (int neuronNumber = 0; neuronNumber < neurons.size(); ++neuronNumber) {
                KohonenNeuron neuron = neurons.get(neuronNumber);
                double distance = metric.distance(neuron, data.get(i));
                if (distance < minDistance) {
                    data.get(i).setRepresentative(neuronNumber);
                    minDistance = distance;
                }
            }

            distanceSum += minDistance;
        }
        
        return distanceSum / data.size();
    }

    private void adjustWeights(KohonenNeuron neuron, Point point, double learningRate, double distanceFunValue) {
        double[] inputVector = point.getWeights();

        for (int i = 0; i < inputVector.length; ++i) {
            double weight = neuron.getWeight(i);
            weight = weight + distanceFunValue * learningRate * (inputVector[i] - weight);
            neuron.setWeight(i, weight);
        }
    }

    public void setLearningFactorProvider(LearningFactorProvider learningFactorProvider) {
        this.learningFactorProvider = learningFactorProvider;
    }

    public void setNeighbourhoodFactorProvider(NeighbourhoodFactorProvider neighbourhoodFactorProvider) {
        this.neighbourhoodFactorProvider = neighbourhoodFactorProvider;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public void setFunction(NeighbourhoodFunction function) {
        this.function = function;
    }

    public void setObserver(TrainingObserver observer) {
        this.observer = observer;
    }
}
