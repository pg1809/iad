package pl.iad.quantization.data.generator;

import java.util.List;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public interface RandomPointsGenerator {

    List<Point> generatePoints(int number, double maxAbsCoordinates);
}
