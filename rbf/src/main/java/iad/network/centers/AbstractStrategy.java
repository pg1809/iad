package iad.network.centers;

import iad.network.distance.DistanceCalculator;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.RadialNeuron;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by PiotrGrzelak on 2015-05-31.
 */
public abstract class AbstractStrategy implements CentersAdjustmentStrategy {

    private final static int NEAREST_NEIGHBOURS = 2;

    protected void setScalingFactorForNeurons(NeuronLayer radianLayer, List<RadialNeuron> neurons) {
        List<RadialNeuron> copy = new ArrayList<>(neurons);
        for (AbstractNeuron neuron : radianLayer.getNeurons()) {
            for (RadialNeuron other : copy) {
                other.setDistanceToNeighbour(DistanceCalculator.distance(((RadialNeuron) neuron), other));
            }

            Collections.sort(neurons);

            double averageSquaredDistance = 0;
            int nearestNeighbours = Math.min(NEAREST_NEIGHBOURS, neurons.size() - 1);
            for (int i = 1; i <= nearestNeighbours; ++i) {
                averageSquaredDistance
                        += Math.pow(DistanceCalculator.distance((RadialNeuron) neuron, neurons.get(i)), 2);
            }
            averageSquaredDistance = Math.sqrt(averageSquaredDistance);

            ((RadialNeuron) neuron).setWidthScalingFactor(averageSquaredDistance / nearestNeighbours);
        }
    }
}
