package pl.iad.quantization.data;

/**
 *
 * @author Wojciech Szałapski
 */
public class Point {

    protected double[] weights;
    
    private int representative;

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

    public int getRepresentative() {
        return representative;
    }

    public void setRepresentative(int representative) {
        this.representative = representative;
    }
}
