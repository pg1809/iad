package pl.iad.quantization.algorithms.structure;

import java.util.Random;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class Neuron extends Point implements Comparable<Neuron> {

    private final static Random generator = new Random();

    private double distanceToPoint;
    
    private int wins;

    public Neuron(int dimension, double maxWeightValue) {
        weights = new double[dimension];
        randomizeWeights(maxWeightValue);
    }
    
    private void randomizeWeights(double maxAbsoluteValue) {

        for (int i = 0; i < weights.length; ++i) {
            weights[i] = generator.nextDouble() * maxAbsoluteValue;
            if (generator.nextBoolean()) {
                weights[i] = -weights[i];
            }
        }
    }

    public void addWin() {
        ++wins;
    }
    
    @Override
    public int compareTo(Neuron other) {
        return (distanceToPoint - other.getDistanceToPoint() > 0 ? 1 : -1);
    }

    public double getDistanceToPoint() {
        return distanceToPoint;
    }

    public void setDistanceToPoint(double distanceToPoint) {
        this.distanceToPoint = distanceToPoint;
    }

    public int getWins() {
        return wins;
    }
}
