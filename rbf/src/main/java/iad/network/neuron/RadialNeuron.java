package iad.network.neuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class RadialNeuron extends AbstractNeuron implements Comparable<RadialNeuron> {

    private double[] coordinates;

    private double widthScalingFactor;
    
    private double distanceToNeighbour;

    @Override
    public int compareTo(RadialNeuron other) {
        return (distanceToNeighbour > other.distanceToNeighbour ? 1 : -1);
    }
    
    @Override
    public void updateNetValue() {
        double squaredDistance = 0;

        for (int i = 0; i < inputNeurons.size(); ++i) {
            NeuronInput input = inputNeurons.get(i);
            squaredDistance += Math.pow(input.getInputNeuron().getOutput() - coordinates[i], 2);
        }
        
        netValue = Math.exp(-squaredDistance / (2 * Math.pow(widthScalingFactor, 2)));
    }

    @Override
    public void updateOutput() {
        updateNetValue();
        output = netValue;
    }

    @Override
    public void updateDelta(Double expectedOutput, double learningRate) {
        // not supported in two-step training
    }

    @Override
    public void updateParameters(double momentumFactor) {
        // not supported in two-step training
    }

    public double getWidthScalingFactor() {
        return widthScalingFactor;
    }

    public void setWidthScalingFactor(double widthScalingFactor) {
        this.widthScalingFactor = widthScalingFactor;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getDistanceToNeighbour() {
        return distanceToNeighbour;
    }

    public void setDistanceToNeighbour(double distanceToNeighbour) {
        this.distanceToNeighbour = distanceToNeighbour;
    }
}