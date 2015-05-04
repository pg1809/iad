/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;

import java.util.List;
import pl.iad.quantization.algorithms.kohonen.Neuron;

/**
 *
 * @author PiotrGrzelak
 */
public interface TrainingObserver {
    
    void notifyAfterEpoch(List<Neuron> neurons, List<double[]> dataCoords, double error);
}
