/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.structure;

/**
 *
 * @author PiotrGrzelak
 */
public class KohonenNeuron extends Neuron {

    private int x;

    private int y;

    public KohonenNeuron(int dimension, double maxWeightValue, int x, int y) {
        super(dimension, maxWeightValue);
        this.x = x;
        this.y = y;
    }

    public int getSquaredDistanceFromNeuron(KohonenNeuron n) {
        return (x - n.x) * (x - n.x) + (y - n.y) * (y - n.y);
    }

    public boolean isInNeighbourHood(double radius, KohonenNeuron neuron) {
        double squaredDistanceFrom = (double) getSquaredDistanceFromNeuron(neuron);
        return radius >= squaredDistanceFrom;
    }
}
