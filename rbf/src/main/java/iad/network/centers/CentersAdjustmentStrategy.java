package iad.network.centers;

import iad.network.input.InputRow;
import iad.network.layer.NeuronLayer;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public interface CentersAdjustmentStrategy {

    void adjustCenters(NeuronLayer radialLayer, List<InputRow> data);
}
