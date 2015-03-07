package iad.ui;

import iad.network.AbstractNetwork;
import iad.network.dataset.DataSetGenerator;
import iad.network.dataset.LinearlySeparableDataSetGenerator;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.NetworkFactory;
import iad.network.factory.SingleNeuronNetworkFactory;
import iad.network.input.InputRow;
import iad.network.strategy.NeuronStrategy;
import iad.network.strategy.PerceptronStrategy;
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
        NetworkFactory factory = new SingleNeuronNetworkFactory(inputs, strategy);
        AbstractNetwork network;
        try {
            network = factory.createNetwork();

            DataSetGenerator dataSetGenerator = new LinearlySeparableDataSetGenerator(0.5, 25, 0, 100);
            List<InputRow> inputDataSet = dataSetGenerator.generateData(50);

            for (InputRow inputRow : inputDataSet) {
                System.out.println(inputRow.getValues()[0] + " " + inputRow.getValues()[1] + " " + inputRow.getExpectedOutput());
            }
        } catch (CannotCreateNetworkException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
