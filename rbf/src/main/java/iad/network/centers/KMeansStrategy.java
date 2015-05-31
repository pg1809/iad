package iad.network.centers;

import iad.network.distance.DistanceCalculator;
import iad.network.input.InputRow;
import iad.network.layer.NeuronLayer;
import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.RadialNeuron;

import java.util.*;

/**
 * Created by PiotrGrzelak on 2015-05-31.
 */
public class KMeansStrategy extends AbstractStrategy {

    private static final int MAX_EPOCHS = 700;

    private static final int MAX_ITERATIONS = 10;

    private Random random = new Random();

    private double epsilon = 1e-5;

    @Override
    public void adjustCenters(NeuronLayer radialLayer, List<InputRow> data) {
        List<RadialNeuron> radialNeurons = new ArrayList<>(radialLayer.getNeurons().size());
        for (AbstractNeuron n : radialLayer.getNeurons()) {
            RadialNeuron radial = (RadialNeuron) n;
            radial.setCoordinates(new double[data.get(0).getValues().length]);
            radialNeurons.add((RadialNeuron) n);
        }

        List<double[]> radialNeuronsCoordsList = new ArrayList<>(radialNeurons.size());
        for (int i = 0; i < radialLayer.getNeurons().size(); ++i) {
            RadialNeuron radial = radialNeurons.get(i);
            radialNeuronsCoordsList.add(new double[radial.getCoordinates().length]);
        }
        List<InputRow> dataCopy = new ArrayList<>(data.size());
        for (InputRow row : data) {
            dataCopy.add(row);
        }

        List<InputRow>[] representationArray = new List[radialNeurons.size()];
        for (int i = 0; i < radialNeurons.size(); ++i) {
            representationArray[i] = new ArrayList<>();
        }

        double error = Double.MAX_VALUE;
        for (int i = 0; i < MAX_ITERATIONS; ++i) {
            double runError = doAlgorithmRun(radialNeurons, dataCopy, representationArray);
            if (runError < error) {
                error = runError;
                for (int j = 0; j < radialNeuronsCoordsList.size(); ++j) {
                    double[] coords = radialNeuronsCoordsList.get(j);
                    RadialNeuron radial = radialNeurons.get(j);
                    System.arraycopy(radial.getCoordinates(), 0, coords, 0, radial.getCoordinates().length);
                }
            }
        }
        for (int i = 0; i < radialNeuronsCoordsList.size(); ++i) {
            double[] coords = radialNeuronsCoordsList.get(i);
            RadialNeuron radial = radialNeurons.get(i);
            radial.setCoordinates(coords);
        }

        setScalingFactorForNeurons(radialLayer, radialNeurons);
    }

    private double doAlgorithmRun(List<RadialNeuron> neurons, List<InputRow> data, List<InputRow>[] representationArray) {
        randomizeNeuronsPositions(neurons, data);
        clearRepresentationArray(representationArray);
        for (int i = 0; i < MAX_EPOCHS; ++i) {
            if(doEpoch(neurons, data, representationArray)) {
                break;
            }
        }

        double error = 0;
        for (int i = 0; i < representationArray.length; ++i) {
            RadialNeuron neuron = neurons.get(i);
            List<InputRow> represented = representationArray[i];
            for (InputRow row : represented) {
                error += DistanceCalculator.distance(row.getValues(), neuron.getCoordinates());
            }
        }
        error /= data.size();
        return error;
    }

    private boolean doEpoch(List<RadialNeuron> neurons, List<InputRow> data, List<InputRow>[] representationArray) {
        Collections.shuffle(data);
        for (InputRow row : data) {
            int closestIdx = 0;
            double minDist = DistanceCalculator.distance(row.getValues(), neurons.get(closestIdx).getCoordinates());
            for (int i = 1; i < neurons.size(); ++i) {
                double dist = DistanceCalculator.distance(row.getValues(), neurons.get(i).getCoordinates());
                if (dist < minDist) {
                    minDist = dist;
                    closestIdx = i;
                }
            }

            representationArray[closestIdx].add(row);
        }

        for (int i = 0; i < neurons.size(); ++i) {
            List<InputRow> represented = representationArray[i];
            if (represented.isEmpty()) {
                continue;
            }
            RadialNeuron neuron = neurons.get(i);
            double[] newCoords = new double[neuron.getCoordinates().length];
            for (int j = 0; j < neuron.getCoordinates().length; ++j) {
                double newCoord = 0;
                for (InputRow row : represented) {
                    newCoord += row.getValues()[j];
                }
                newCoords[j] = newCoord / represented.size();
            }
            neuron.setDistanceShift(DistanceCalculator.distance(neuron.getCoordinates(), newCoords));
            neuron.setCoordinates(newCoords);
        }

        return neuronsStable(neurons);
    }

    private boolean neuronsStable(List<RadialNeuron> neurons) {
        for (RadialNeuron r : neurons) {
            if (r.getDistanceShift() > epsilon) {
                return false;
            }
        }
        return true;
    }

    private void randomizeNeuronsPositions(List<RadialNeuron> neurons, List<InputRow> data) {
        boolean[] usedIndexes = new boolean[data.size()];
        for (RadialNeuron neuron : neurons) {
            int idx;
            do {
                idx = random.nextInt(data.size());
            } while (usedIndexes[idx]);
            usedIndexes[idx] = true;

            neuron.setCoordinates(data.get(idx).getValues());
        }
    }

    private void clearRepresentationArray(List<InputRow>[] representationArray) {
        for (List<InputRow> representants : representationArray) {
            representants.clear();
        }
    }
}
