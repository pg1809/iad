/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.means.structure;

import java.util.ArrayList;
import java.util.List;
import pl.iad.quantization.algorithms.structure.Neuron;

/**
 *
 * @author PiotrGrzelak
 */
public class CentresSet {

    private List<Centre> centres;

    public CentresSet(int numberOfCentres, int dimension, double maxWeightValue, double epsilon) {
        centres = new ArrayList<>(numberOfCentres);
        for (int i = 0; i < numberOfCentres; ++i) {
            centres.add(new Centre(dimension, maxWeightValue, epsilon));
        }
    }

    public List<Centre> getCentres() {
        return centres;
    }
}
