package pl.iad.quantization.algorithms.parameters.learning;

/**
 *
 * @author Wojciech Szałapski
 */
public class PowerLearningFactor implements LearningFactorProvider {

    public final static double DEFAULT_INITIAL_FACTOR = 0.5;
    
    public final static double DEFAULT_MINIMUM_FACTOR = 0.005;
    
    private final double maxEpochIndex;

    private final double initialFactor;

    private final double base;

    public PowerLearningFactor(int maxEpochIndex, double initialFactor, double minimumFactor) {
        this.maxEpochIndex = maxEpochIndex;
        this.initialFactor = initialFactor;

        base = minimumFactor / initialFactor;
    }

    @Override
    public double getLearningFactor(int epochIndex, int neuronWins) {
        return initialFactor * Math.pow(base, epochIndex / maxEpochIndex);
    }
}
