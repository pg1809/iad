/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.reporting;

import java.util.List;
import pl.iad.quantization.algorithms.structure.Neuron;
import pl.iad.quantization.data.Point;

/**
 *
 * @author PiotrGrzelak
 */
public interface TrainingObserver {
    
    void notifyAfterEpoch(List<? extends Neuron> neurons, double error);
}
