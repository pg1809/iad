package pl.iad.quantization.data.metrics;

import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public interface Metric {

    double distance(Point source, Point target);
}
