package iad.network.dataset;

import iad.network.input.InputRow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Wojciech Szałapski
 */
public class LinearlySeparableDataSetGenerator implements DataSetGenerator {

    private final static int DIMENSIONS = 2;

    // y = slope * x + intercept
    private double slope, intercept;

    // points' x and y coordinates are from the range [lowerBound, upperBound)
    private double lowerBound, upperBound;

    private final Random generator = new Random();

    public LinearlySeparableDataSetGenerator(double slope, double intercept, double lowerBound, double upperBound) {
        this.slope = slope;
        this.intercept = intercept;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public List<InputRow> generateData(int samples) {
        List<InputRow> dataset = new ArrayList<>();
        double range = upperBound - lowerBound;
        
        for (int i = 0; i < samples; ++i) {
            double[] sample = new double[DIMENSIONS];
            for (int j = 0; j < DIMENSIONS; ++j) {
                sample[j] = lowerBound + range * generator.nextDouble();
            }

            double boundaryOrdinate = slope * sample[0] + intercept;
            double[] expectedOutput = new double[1];
            expectedOutput[0] = 0;
            if (sample[1] >= boundaryOrdinate) {
                expectedOutput[0] = 1;
            }

            InputRow inputRow = new InputRow(sample, expectedOutput);
            dataset.add(inputRow);
        }

        return dataset;
    }

    public double getSlope() {
        return slope;
    }

    public void setSlope(double slope) {
        this.slope = slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public void setIntercept(double intercept) {
        this.intercept = intercept;
    }

    public double getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(double lowerBound) {
        this.lowerBound = lowerBound;
    }

    public double getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(double upperBound) {
        this.upperBound = upperBound;
    }
}
