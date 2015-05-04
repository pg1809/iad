package pl.iad.quantization.algorithms.gas.training;

import java.util.List;
import pl.iad.quantization.algorithms.gas.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.gas.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.metrics.Metric;

/**
 *
 * @author Wojciech Szałapski
 */
public interface GasTrainer {

    List<Double> trainNeurons(NeuronCollection collection, List<Point> data,
            int maxEpochs, LearningFactorProvider learningFactorProvider,
            NeighbourhoodFactorProvider neighbourhoodFactorProvider,
            Metric metric);
}
