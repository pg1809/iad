/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.reporting;

import java.util.List;
import pl.iad.quantization.algorithms.structure.Neuron;

/**
 *
 * @author PiotrGrzelak
 */
public interface TrainingReporter {
    
    void notifyAfterEpoch(List<? extends Neuron> neurons, double error);
    
    void clearRunData();
    
    void preserveRunData();
}
