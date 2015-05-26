package iad.network.factory;

import iad.network.AbstractNetwork;
import iad.network.exceptions.CannotCreateNetworkException;

/**
 *
 * @author Wojciech Szałapski
 */
public interface NetworkFactory {

    public AbstractNetwork createNetwork() throws CannotCreateNetworkException;
}
