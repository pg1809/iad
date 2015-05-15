package pl.iad.quantization.algorithms.parameters.neighbourhood;

import pl.iad.quantization.algorithms.parameters.neighbourhood.NeighbourhoodFactorProvider;

/**
 *
 * @author Wojciech Szałapski
 */
public class PowerNeighbourhoodFactor implements NeighbourhoodFactorProvider {

    public final static double DEFAULT_MINIMUM_FACTOR = 0.01;
    
    private final double maxEpochIndex;

    private final double initialFactor;

    private final double base;

    public PowerNeighbourhoodFactor(int maxEpochIndex, double initialFactor, double minimumFactor) {
        this.maxEpochIndex = maxEpochIndex;
        this.initialFactor = initialFactor;

        base = minimumFactor / initialFactor;
    }

    @Override
    public double getNeighbourhoodFactor(int epochIndex) {
        return initialFactor * Math.pow(base, epochIndex / maxEpochIndex);
    }
}
