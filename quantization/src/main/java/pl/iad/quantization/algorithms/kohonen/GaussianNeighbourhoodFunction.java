/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;


public class GaussianNeighbourhoodFunction implements NeighbourhoodFunction {

    @Override
    public double value(Neuron n1, Neuron n2, double radius) {
        double dist = n1.getSquaredDistanceFromNeuron(n2);
        return Math.exp(dist / (2 * radius * radius));
    }
    
}
