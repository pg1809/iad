package iad.ui;

import iad.network.AbstractNetwork;
import iad.network.exceptions.CannotCreateNetworkException;
import iad.network.factory.NetworkFactory;
import iad.network.factory.SingleNeuronNetworkFactory;
import iad.network.strategy.NeuronStrategy;
import iad.network.strategy.PerceptronStrategy;
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
        } catch (CannotCreateNetworkException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
