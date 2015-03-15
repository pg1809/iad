package iad.network.input;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Wojciech Szałapski
 */
public class ClassificationDataProvider extends TrainingDataProvider {

    public ClassificationDataProvider(File inputFile, int inputs, int outputs, String separator) throws IOException {
        Scanner sc = new Scanner(inputFile);

        dataset = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] stringNums = line.split(separator);

            double[] inputValues = new double[inputs];
            for (int i = 0; i < inputs; ++i) {
                inputValues[i] = Double.parseDouble(stringNums[i]);
            }

            double[] outputValues = new double[outputs];
            int group = Integer.parseInt(stringNums[inputs]);
            outputValues[group - 1] = 1;

            dataset.add(new InputRow(inputValues, outputValues));
        }
    }
}
