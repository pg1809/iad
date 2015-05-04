/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PiotrGrzelak
 */
public class NeuronMap {
    
    private List<Neuron> neurons;
    
    public NeuronMap(int xDim, int yDim, int dataDimensionsNum) {
        neurons = new ArrayList<>(xDim * yDim);
        for (int i = 0; i < neurons.size(); ++i) {
            Neuron neuron = new Neuron(dataDimensionsNum, i / xDim, i % xDim);
            neurons.add(neuron);
        }
    }

    public List<double[]> getAsPoints() {
        return neurons.stream().map(n -> n.getWeights()).collect(Collectors.toList());
    }

    public List<Neuron> getNeurons() {
        return neurons;
    }

    List<Neuron> getNeighbourhoodOf(Neuron bmuNeuron, double neighbourhoodRadius) {
        return neurons.stream().filter((Neuron n) -> (bmuNeuron.isInNeighbourHood(neighbourhoodRadius, n)))
                .collect(Collectors.toList());
    }
}
