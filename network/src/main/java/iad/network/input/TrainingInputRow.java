package iad.network.input;

/**
 *
 * @author Wojciech Szałapski
 */
public class TrainingInputRow {

    private double[] values;
    
    private double expectedOutput;

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public double getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(double expectedOutput) {
        this.expectedOutput = expectedOutput;
    }
}
