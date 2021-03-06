package pl.iad.quantization.data;

import java.util.Arrays;
import pl.iad.quantization.algorithms.structure.Neuron;

/**
 *
 * @author Wojciech Szałapski
 */
public class Point {

    protected double[] weights;
    
    private Neuron representative;

    public void print() {
        for (int i = 0; i < weights.length; ++i) {
            System.out.printf("%.4f ", weights[i]);
        }
        System.out.println("");
    }

    public double getWeight(int index) {
        return weights[index];
    }

    public void setWeight(int index, double weight) {
        weights[index] = weight;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public Neuron getRepresentative() {
        return representative;
    }

    public void setRepresentative(Neuron representative) {
        this.representative = representative;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Arrays.hashCode(this.weights);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Point other = (Point) obj;
        if (!Arrays.equals(this.weights, other.weights)) {
            return false;
        }
        return true;
    }
}
