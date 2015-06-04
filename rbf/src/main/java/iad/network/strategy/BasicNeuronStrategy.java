package iad.network.strategy;

import iad.network.neuron.AbstractNeuron;
import iad.network.neuron.NeuronInput;
import iad.network.neuron.RadialNeuron;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public abstract class BasicNeuronStrategy implements NeuronStrategy {

    @Override
    public double calculateNetValue(Collection<NeuronInput> inputNeurons, double bias) {
        double netValue = bias;
        for (NeuronInput neuron : inputNeurons) {
            netValue += neuron.getInputNeuron().getOutput() * neuron.getWeight();
        }

        return netValue;
    }

    @Override
    public void updateBias(AbstractNeuron neuron, double delta, double momentumFactor) {
    }

    @Override
    public void updateWeights(AbstractNeuron neuron, double delta, double momentumFactor) {
        if (neuron instanceof RadialNeuron) {
            RadialNeuron radialNeuron = (RadialNeuron) neuron;
            double averageDistance = 0;
            for (int i = 0; i < radialNeuron.getCoordinates().length; ++i) {
                double distance = radialNeuron.getInputNeurons().get(i).getInputNeuron().getOutput()
                        - radialNeuron.getCoordinates()[i];

                averageDistance += distance;
                distance /= Math.pow(radialNeuron.getWidthScalingFactor(), 2);

                radialNeuron.getCoordinates()[i] += distance * delta * radialNeuron.getOutput();
            }

            averageDistance /= radialNeuron.getCoordinates().length;
            averageDistance *= averageDistance;
            averageDistance /= Math.pow(radialNeuron.getWidthScalingFactor(), 3);

            radialNeuron.setWidthScalingFactor(radialNeuron.getWidthScalingFactor()
                    + averageDistance * delta * radialNeuron.getOutput());
        } else {
            List<NeuronInput> inputNeurons = neuron.getInputNeurons();
            for (int i = 0; i < inputNeurons.size(); ++i) {
                NeuronInput currentInput = inputNeurons.get(i);
                double currentWeight = currentInput.getWeight();
                double previousWeight = currentInput.getPreviousWeight();
                currentInput.setWeight(currentWeight + delta * currentInput.getInputNeuron().getOutput()
                        + momentumFactor * (currentWeight - previousWeight));
            }
        }
    }
}
