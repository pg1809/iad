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
    private final double slope, intercept;

    // points' x and y coordinates are from the range [lowerBound, upperBound]
    private final double lowerBound, upperBound;

    private double range;

    private final Random generator = new Random();

    public LinearlySeparableDataSetGenerator(double slope, double intercept, double lowerBound, double upperBound) {
        this.slope = slope;
        this.intercept = intercept;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;

        range = upperBound - lowerBound;
    }

    @Override
    public List<InputRow> generateData(int samples) {
        List<InputRow> dataset = new ArrayList<>();

        for (int i = 0; i < samples; ++i) {
            double[] sample = new double[DIMENSIONS];
            for (int j = 0; j < DIMENSIONS; ++j) {
                sample[j] = lowerBound + range * generator.nextDouble();
            }

            double boundaryOrdinate = slope * sample[0] + intercept;
            double expectedOutput = 0;
            if (sample[1] >= boundaryOrdinate) {
                expectedOutput = 1;
            }

            InputRow inputRow = new InputRow(sample, expectedOutput);
            dataset.add(inputRow);
        }

        return dataset;
    }
}
