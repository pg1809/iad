package iad.network.distance;

import iad.network.neuron.RadialNeuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class DistanceCalculator {

    public static double distance(RadialNeuron neuron, RadialNeuron other) {
        return distance(neuron.getCoordinates(), other.getCoordinates());
    }

    public static double distance(double[] first, double[] second) {
        double distance = 0;
        for (int i = 0; i < first.length; ++i) {
            distance += Math.pow(first[i] - second[i], 2);
        }

        return Math.sqrt(distance);
    }
 }
