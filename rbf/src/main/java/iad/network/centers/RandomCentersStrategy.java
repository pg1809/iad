package iad.network.centers;

import iad.network.distance.DistanceCalculator;
import iad.network.input.InputRow;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.RadialNeuron;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class RandomCentersStrategy implements CentersAdjustmentStrategy {

    private final static int NEAREST_NEIGHBOURS = 1;

    @Override
    public void adjustCenters(NeuronLayer radialLayer, List<InputRow> data) {
        List<RadialNeuron> layer = new ArrayList<>();
        for (AbstractNeuron neuron : radialLayer.getNeurons()) {
            layer.add((RadialNeuron) neuron);
        }

        List<InputRow> dataCopy = new ArrayList<>(data);
        Collections.shuffle(dataCopy);

        for (int i = 0; i < layer.size(); ++i) {
            ((RadialNeuron) layer.get(i)).setCoordinates(dataCopy.get(i).getValues());
        }

//        int counter = 0;
//        double[] extremes = new double[]{-2.5, 1.6, 2.8};
//        for (AbstractNeuron neuron : radialLayer.getNeurons()) {
//            RadialNeuron n = (RadialNeuron) neuron;
//            n.setCoordinates(new double[]{extremes[counter++]});
//        }

        Collections.shuffle(layer);

        for (AbstractNeuron neuron : radialLayer.getNeurons()) {
            for (RadialNeuron other : layer) {
                other.setDistanceToNeighbour(DistanceCalculator.distance((RadialNeuron) neuron, other));
            }

            Collections.sort(layer);

            double averageSquaredDistance = 0;
            int nearestNeighbours = Math.min(NEAREST_NEIGHBOURS, layer.size() - 1);
            for (int i = 1; i <= nearestNeighbours; ++i) {
                averageSquaredDistance
                        += Math.pow(DistanceCalculator.distance((RadialNeuron) neuron, layer.get(i)), 2);
            }
            averageSquaredDistance /= nearestNeighbours;

            ((RadialNeuron) neuron).setWidthScalingFactor(Math.sqrt(averageSquaredDistance));
        }
    }
}
