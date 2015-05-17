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

    private static Random generator = new Random(23474823947L);

    @Override
    public List<Point> generatePoints(int number, double maxAbsCoordinates) {
        List<Point> result = new ArrayList<>(number);

        for (int i = 0; i < number; ++i) {
            double angle = generator.nextDouble() * Math.PI * 2;
            double x = Math.cos(angle) * maxAbsCoordinates * generator.nextDouble();
            double y = Math.sin(angle) * maxAbsCoordinates * generator.nextDouble();

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

    public static void setGenerator(Random generator) {
        CircleGenerator.generator = generator;
    }
}
