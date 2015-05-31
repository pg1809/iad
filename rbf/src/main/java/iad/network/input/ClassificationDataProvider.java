package iad.network.input;

import iad.network.normalization.MinMaxInputNormalizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wojciech Szałapski
 */
public class ClassificationDataProvider extends TrainingDataProvider {

    public ClassificationDataProvider(File inputFile, int outputs, String separator,
                                      int[] inputsNumbers, MinMaxInputNormalizer normalizer) throws IOException {
        Scanner sc = new Scanner(inputFile);
        this.normalizer = normalizer;
        dataset = new ArrayList<>();

        double[] min = new double[inputsNumbers.length];
        double[] max = new double[inputsNumbers.length];
        for (int i = 0; i < inputsNumbers.length; ++i) {
            min[i] = Double.MAX_VALUE;
            max[i] = Double.MIN_VALUE;
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] stringNums = line.split(separator);

            double[] inputValues = new double[inputsNumbers.length];
            for (int i = 0; i < inputsNumbers.length; ++i) {
                inputValues[i] = Double.parseDouble(stringNums[inputsNumbers[i]]);
                min[i] = Math.min(min[i], inputValues[i]);
                max[i] = Math.max(max[i], inputValues[i]);
            }

            double[] outputValues = new double[outputs];
            int group = Integer.parseInt(stringNums[stringNums.length - 1]);
            outputValues[group - 1] = 1;

            dataset.add(new InputRow(inputValues, outputValues));
        }

        normalizer.setMin(min);
        normalizer.initializeSpan(max);
    }
}
