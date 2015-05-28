package iad.network.normalization;

/**
 *
 * @author Wojciech Szałapski
 */
public class MinMaxInputNormalizer implements InputNormalizer {

    private double[] min;

    private double[] span;
    
    public void initializeSpan(double[] max) {
        span = new double[min.length];
        for (int i = 0; i < min.length; ++i) {
            span[i] = max[i] - min[i];
        }
    }

    @Override
    public double[] normalize(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; ++i) {
            result[i] = (x[i] - min[i]) / span[i];
        }
        return result;
    }

    @Override
    public double[] denormalize(double[] x) {
        double[] result = new double[x.length];
        for (int i = 0; i < x.length; ++i) {
            result[i] = x[i] * span[i] + min[i];
        }
        return result;
    }

    public void setMin(double[] min) {
        this.min = min;
    }
}
