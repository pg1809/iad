package pl.iad.quantization.algorithms.gas.adaptation;

/**
 *
 * @author Wojciech Szałapski
 */
public class NeighbourhoodCalculator {

    public double calculateNeighbourhood(int rankingPosition, double neighbourhoodFactor) {
        return Math.exp(-rankingPosition / neighbourhoodFactor);
    }
}
