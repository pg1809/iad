/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;

/**
 *
 * @author PiotrGrzelak
 */
public interface NeighbourhoodFunction {
    
    double value(Neuron n1, Neuron n2, double radius);
}
