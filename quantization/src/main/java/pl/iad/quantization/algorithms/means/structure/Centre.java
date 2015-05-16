/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.means.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;
import pl.iad.quantization.algorithms.structure.Neuron;

/**
 *
 * @author PiotrGrzelak
 */
public class Centre extends Neuron {
    
    private double shift = Double.MAX_VALUE;
    
    private double epsilon;
    
    public Centre(int dimension, double maxWeightValue, double epsilon) {
        super(dimension, maxWeightValue);
        this.epsilon = epsilon;
    }
    
    public boolean isStable() {
        return shift < epsilon;
    }
    
    public void setShift(double shift) {
        this.shift = shift;
    }
}
