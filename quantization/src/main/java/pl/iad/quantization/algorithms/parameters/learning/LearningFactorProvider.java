package pl.iad.quantization.algorithms.parameters.learning;

/**
 *
 * @author Wojciech Szałapski
 */
public interface LearningFactorProvider {

    double getLearningFactor(int epochIndex, int neuronWins);
}
