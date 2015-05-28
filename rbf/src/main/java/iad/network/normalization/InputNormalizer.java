package iad.network.normalization;

/**
 *
 * @author Wojciech Szałapski
 */
public interface InputNormalizer {

    double[] normalize(double[] x);

    double[] denormalize(double[] x);
}
