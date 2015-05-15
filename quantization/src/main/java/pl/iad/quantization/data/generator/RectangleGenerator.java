package pl.iad.quantization.data.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class RectangleGenerator implements RandomPointsGenerator {

    private final static Random generator = new Random();

    @Override
    public List<Point> generatePoints(int number, double maxAbsCoordinates) {
        List<Point> result = new ArrayList<>(number);
        
        double x1 = getRandomCoordinate(maxAbsCoordinates);
        double x2 = getRandomCoordinate(maxAbsCoordinates);
        double y1 = getRandomCoordinate(maxAbsCoordinates);
        double y2 = getRandomCoordinate(maxAbsCoordinates);
        
        if (x1 > x2) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }
        
        if (y1 > y2) {
            double temp = y1;
            y1 = y2;
            y2 = temp;
        }
        
        double xSpan = x2 - x1;
        double ySpan = y2 - y1;
        
        for (int i = 0; i < number; ++i) {
            double x = Math.random() * xSpan + x1;
            double y = Math.random() * ySpan + y1;

            Point point = new Point();
            point.setWeights(new double[]{x, y});
            result.add(point);
        }

        return result;
    }
    
    private double getRandomCoordinate(double maxAbsCoordinates) {
        double result = Math.random() * maxAbsCoordinates;
        if (generator.nextBoolean()) {
            result = -result;
        }
        
        return result;
    }
}
