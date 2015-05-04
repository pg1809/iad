package pl.iad.quantization.data.metrics;

import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class EuclideanMetric implements Metric {

    @Override
    public double distance(Point source, Point target) {
        double[] sourceCoordinates = source.getCoordinates();
        double[] targetCoordinates = target.getCoordinates();

        double result = 0;
        for (int i = 0; i < sourceCoordinates.length; ++i) {
            result += (sourceCoordinates[i] - targetCoordinates[i])
                    * (sourceCoordinates[i] - targetCoordinates[i]);
        }

        return result;
    }
}
