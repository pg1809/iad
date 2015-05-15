package pl.iad.quantization.data.metrics;

import pl.iad.quantization.algorithms.structure.Neuron;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class DistanceCalculator {

    public void calculateDistances(Point point, NeuronCollection collection, Metric metric) {
        for (Neuron neuron : collection) {
            neuron.setDistanceToPoint(metric.distance(neuron, point));
        }
    }
}
