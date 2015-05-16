/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen.adaptation;

import pl.iad.quantization.algorithms.kohonen.structure.KohonenNeuron;


public class GaussianNeighbourhoodFunction implements NeighbourhoodFunction {

    @Override
    public double value(KohonenNeuron n1, KohonenNeuron n2, double radius) {
        double dist = n1.getSquaredDistanceFromNeuron(n2);
        return Math.exp(-dist / (2 * radius * radius));
    }
    
}
