/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen.adaptation;

import pl.iad.quantization.algorithms.structure.KohonenNeuron;

/**
 *
 * @author PiotrGrzelak
 */
public interface NeighbourhoodFunction {
    
    double value(KohonenNeuron n1, KohonenNeuron n2, double radius);
}
