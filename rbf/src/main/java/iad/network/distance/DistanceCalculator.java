package iad.network.distance;

import iad.network.neuron.RadialNeuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class DistanceCalculator {

    public static double distance(RadialNeuron neuron, RadialNeuron other) {
        int dimension = neuron.getCoordinates().length;
        double distance = 0;
        
        for (int i = 0; i < dimension; ++i) {
            distance += Math.pow(neuron.getCoordinates()[i] - other.getCoordinates()[i], 2);
        }
        
        return Math.sqrt(distance);
    }
 }
