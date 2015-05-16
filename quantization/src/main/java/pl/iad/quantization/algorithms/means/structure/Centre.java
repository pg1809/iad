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
import pl.iad.quantization.data.Point;

/**
 *
 * @author PiotrGrzelak
 */
public class Centre extends Neuron {
    
    private boolean[] stableCoords;
    
    private double epsilon;
    
    public Centre(int dimension, double maxWeightValue, double epsilon) {
        super(dimension, maxWeightValue);
        stableCoords = new boolean[dimension];
        for (int i = 0; i < stableCoords.length; ++i) {
            stableCoords[i] = false;
        }
        this.epsilon = epsilon;
    }

    @Override
    public void setWeight(int index, double weight) {
        stableCoords[index] = weight - weights[index] < epsilon;
        super.setWeight(index, weight);
    }
    
    public boolean isStable() {
        for (int i = 0; i < weights.length; ++i) {
            if (!stableCoords[i]) {
                return false;
            }
        }
        return true;
    }
}
