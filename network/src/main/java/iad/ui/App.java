package iad.ui;

import iad.network.SingleNeuronNetwork;
import iad.network.dataset.LinearlySeparableDataSetGenerator;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.NetworkFactory;
import iad.network.factory.SingleNeuronNetworkFactory;
import iad.network.input.InputRow;
import iad.network.strategy.NeuronStrategy;
import iad.network.strategy.PerceptronStrategy;
import iad.network.training.EpochNetworkTrainer;
import iad.network.training.NetworkTrainer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wojciech Szałapski
 */
public class App {

    private static final int inputs = 2;

    private static final NeuronStrategy strategy = PerceptronStrategy.getInstance();

    public static void main(String[] args) {
        SingleNeuronNetworkFactory factory = new SingleNeuronNetworkFactory(inputs, strategy);
        SingleNeuronNetwork network;
        try {
            network = factory.createNetwork();

            LinearlySeparableDataSetGenerator dataSetGenerator = new LinearlySeparableDataSetGenerator(0.5, 25, 0, 100);

            List<InputRow> inputDataSet = dataSetGenerator.generateData(100);

            NetworkTrainer networkTrainer = new EpochNetworkTrainer(5000, 0.3);

            List<Double> meanSquaredErrors = networkTrainer.trainNetwork(network, inputDataSet);

            PlotGenerator generator = new PlotGenerator(1024, 768);
            generator.generateErrorChart(meanSquaredErrors);
            generator.generateExemplaryDataChart(inputDataSet, dataSetGenerator, network.getSlope(), network.getIntercept());
        } catch (CannotCreateNetworkException | IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
