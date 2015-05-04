package pl.iad.quantization.data.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class CircleGenerator implements RandomPointsGenerator {

    private final static Random generator = new Random();

    @Override
    public List<Point> generatePoints(int number, double maxAbsCoordinates) {
        List<Point> result = new ArrayList<>(number);

        for (int i = 0; i < number; ++i) {
            double angle = Math.random() * Math.PI * 2;
            double x = Math.cos(angle) * maxAbsCoordinates * Math.random();
            double y = Math.sin(angle) * maxAbsCoordinates * Math.random();

            if (generator.nextBoolean()) {
                x = -x;
            }
            if (generator.nextBoolean()) {
                y = -y;
            }

            Point point = new Point();
            point.setWeights(new double[]{x, y});
            result.add(point);
        }

        return result;
    }
}
