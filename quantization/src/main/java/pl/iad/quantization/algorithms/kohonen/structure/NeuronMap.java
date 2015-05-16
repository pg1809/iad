/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen.structure;

import pl.iad.quantization.algorithms.kohonen.structure.KohonenNeuron;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author PiotrGrzelak
 */
public class NeuronMap {
    
    private List<KohonenNeuron> neurons;
    
    public NeuronMap(int xDim, int yDim, int dataDimensions, double maxWeightValue) {
        neurons = new ArrayList<>(xDim * yDim);
        for (int i = 0; i < xDim * yDim; ++i) {
            KohonenNeuron neuron = new KohonenNeuron(dataDimensions, maxWeightValue, i / xDim, i % xDim);
            neurons.add(neuron);
        }
    }


    public List<KohonenNeuron> getNeighbourhoodOf(KohonenNeuron neuron, double neighbourhoodRadius) {
        return neurons.stream().filter((KohonenNeuron n) -> (n.isInNeighbourHood(neighbourhoodRadius, neuron)))
                .collect(Collectors.toList());
    }

    public List<KohonenNeuron> getNeurons() {
        return neurons;
    }
}
