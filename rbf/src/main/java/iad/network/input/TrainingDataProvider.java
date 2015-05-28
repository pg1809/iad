package iad.network.input;

import iad.network.normalization.InputNormalizer;
import iad.network.normalization.MinMaxInputNormalizer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Wojciech Szałapski
 */
public class TrainingDataProvider implements InputProvider {

    protected List<InputRow> dataset;

    private int nextRow = 0;
    
    private InputNormalizer normalizer;

    protected TrainingDataProvider() {
    }

    public TrainingDataProvider(File inputFile, int inputs, int outputs, String separator, MinMaxInputNormalizer normalizer)
            throws IOException {
        Scanner sc = new Scanner(inputFile);

        dataset = new ArrayList<>();

        double[] min = new double[inputs];
        double[] max = new double[inputs];
        for (int i = 0; i < inputs; ++i) {
            min[i] = Double.MAX_VALUE;
            max[i] = Double.MIN_VALUE;
        }

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] stringNums = line.split(separator);

            double[] inputValues = new double[inputs];
            for (int i = 0; i < inputs; ++i) {
                inputValues[i] = Double.parseDouble(stringNums[i]);
                min[i] = Math.min(min[i], inputValues[i]);
                max[i] = Math.max(max[i], inputValues[i]);
            }

            double[] outputValues = new double[outputs];
            for (int i = 0; i < outputs; ++i) {
                outputValues[i] = Double.parseDouble(stringNums[inputs + i]);
            }

            dataset.add(new InputRow(inputValues, outputValues));
        }

        this.normalizer = normalizer;
        normalizer.setMin(min);
        normalizer.initializeSpan(max);
    }

    @Override
    public boolean hasMoreRows() {
        return (nextRow < dataset.size());
    }

    @Override
    public InputRow provideInputRow() {
        InputRow result = dataset.get(nextRow++);
        result.setValues(normalizer.normalize(result.getValues()));
        return result;
    }

    @Override
    public List<InputRow> provideAllRows() {
        List<InputRow> result = new ArrayList<>();

        while (nextRow < dataset.size()) {
            result.add(provideInputRow());
        }

        return result;
    }
}
