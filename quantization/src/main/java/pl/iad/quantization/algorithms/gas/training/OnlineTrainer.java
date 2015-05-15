package pl.iad.quantization.algorithms.gas.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.iad.quantization.algorithms.gas.adaptation.NeighbourhoodCalculator;
import pl.iad.quantization.algorithms.parameters.learning.LearningFactorProvider;
import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;
import pl.iad.quantization.algorithms.structure.Neuron;
import pl.iad.quantization.algorithms.gas.structure.NeuronCollection;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.metrics.DistanceCalculator;
import pl.iad.quantization.data.metrics.Metric;

/**
 *
 * @author Wojciech Szałapski
 */
public class OnlineTrainer implements GasTrainer {

    private final static DistanceCalculator distanceCalculator = new DistanceCalculator();

    private final static NeighbourhoodCalculator neighbourhoodCalculator = new NeighbourhoodCalculator();

    @Override
    public List<Double> trainNeurons(NeuronCollection collection, List<Point> data,
            int maxEpochs, LearningFactorProvider learningFactorProvider,
            NeighbourhoodFactorProvider neighbourhoodFactorProvider,
            Metric metric) {

        List<Double> quantizationError = new ArrayList<>();

        for (int epochIndex = 0; epochIndex < maxEpochs; ++epochIndex) {
            System.out.println("Epoch " + (epochIndex + 1));

            double neighbourhoodFactor = neighbourhoodFactorProvider.getNeighbourhoodFactor(epochIndex);
            double learningFactor = learningFactorProvider.getLearningFactor(epochIndex);

            Collections.shuffle(data);
            for (Point point : data) {
                distanceCalculator.calculateDistances(point, collection, metric);
                Collections.sort(collection.getNeurons());

                for (int ranking = 0; ranking < collection.getNeurons().size(); ++ranking) {
                    Neuron neuron = collection.getNeurons().get(ranking);
                    double neighbourhood = neighbourhoodCalculator.calculateNeighbourhood(ranking, neighbourhoodFactor);

                    for (int i = 0; i < neuron.getWeights().length; ++i) {
                        double newWeight = neuron.getWeight(i);
                        newWeight += learningFactor * neighbourhood
                                * (point.getWeight(i) - neuron.getWeight(i));
                        neuron.setWeight(i, newWeight);
                    }
                }
            }

            double distanceSum = 0;

            for (int i = 0; i < data.size(); ++i) {
                double minDistance = Double.MAX_VALUE;

                for (int neuronNumber = 0; neuronNumber < collection.getNeurons().size(); ++neuronNumber) {
                    Neuron neuron = collection.getNeurons().get(neuronNumber);
                    double distance = metric.distance(neuron, data.get(i));
                    if (distance < minDistance) {
                        data.get(i).setRepresentative(neuronNumber);
                        minDistance = distance;
                    }
                }

                distanceSum += minDistance;
            }

            quantizationError.add(distanceSum / data.size());
        }

        return quantizationError;
    }
}
