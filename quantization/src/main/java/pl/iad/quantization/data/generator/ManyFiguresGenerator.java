package pl.iad.quantization.data.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class ManyFiguresGenerator implements RandomPointsGenerator {

    private final static Random generator = new Random(23474823947L);
    
    @Override
    public List<Point> generatePoints(int number, double maxAbsCoordinates) {
        List<Point> result = new ArrayList<>();
        
        CircleGenerator circleGenerator = new CircleGenerator();
        circleGenerator.setGenerator(generator);
        RectangleGenerator rectangleGenerator = new RectangleGenerator();
        rectangleGenerator.setGenerator(generator);
        
        for (int i = 0; i < number; ++i) {
            RandomPointsGenerator currentGenerator;
            
            if (generator.nextBoolean()) {
                currentGenerator = circleGenerator;
            } else {
                currentGenerator = rectangleGenerator;
            }
            
            int points = 200;
            
            double currentCoordinates = generator.nextDouble() * maxAbsCoordinates;
            List<Point> currentResult = currentGenerator.generatePoints(points,
                    maxAbsCoordinates / 5);
            
            double xTransfer = generator.nextDouble() * 0.8 * maxAbsCoordinates;
            if (generator.nextBoolean()) {
                xTransfer = -xTransfer;
            }
            
            double yTransfer = generator.nextDouble() * 0.8 * maxAbsCoordinates;
            if (generator.nextBoolean()) {
                yTransfer = -yTransfer;
            }
            
            for (Point point : currentResult) {
                double[] weights = point.getWeights();
                weights[0] += xTransfer;
                weights[1] += yTransfer;
                
                Point newPoint = new Point();
                newPoint.setWeights(weights);
                
                result.add(newPoint);
            }
        }
        
        return result;
    }
}
