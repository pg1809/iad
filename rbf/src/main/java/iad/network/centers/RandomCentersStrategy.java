package iad.network.centers;

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
public class RandomCentersStrategy extends AbstractStrategy {

    @Override
    public void adjustCenters(NeuronLayer radialLayer, List<InputRow> data) {
        List<RadialNeuron> layer = new ArrayList<>();
        for (AbstractNeuron neuron : radialLayer.getNeurons()) {
            layer.add((RadialNeuron) neuron);
        }

        List<InputRow> dataCopy = new ArrayList<>(data);
        Collections.shuffle(dataCopy);

        for (int i = 0; i < layer.size(); ++i) {
            double[] newCoordinates = new double[dataCopy.get(i).getValues().length];
            for (int j = 0; j < dataCopy.get(i).getValues().length; ++j) {
                newCoordinates[j] = dataCopy.get(i).getValues()[j];
            }
            layer.get(i).setCoordinates(newCoordinates);
        }

        Collections.shuffle(layer);
        setScalingFactorForNeurons(radialLayer, layer);
    }
}
