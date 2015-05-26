package pl.iad.quantization.algorithms.parameters.learning;

/**
 *
 * @author Wojciech Szałapski
 */
public class IndividualLearningFactor implements LearningFactorProvider {

    @Override
    public double getLearningFactor(int epochIndex, int neuronWins) {
        return 2.0 / (1 + neuronWins);
    }
}
