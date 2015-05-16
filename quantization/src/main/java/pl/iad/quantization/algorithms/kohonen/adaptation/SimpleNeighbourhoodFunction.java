/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen.adaptation;

import pl.iad.quantization.algorithms.kohonen.structure.KohonenNeuron;


public class SimpleNeighbourhoodFunction implements NeighbourhoodFunction {

    @Override
    public double value(KohonenNeuron n1, KohonenNeuron n2, double radius) {
        return n1.isInNeighbourHood(radius, n2) ? 1 : 0;
    }
    
}
