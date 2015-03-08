/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.strategy;

/**
 *
 * @author Wojciech Szałapski
 */
public class WidrowHoffSigmoidStrategy {

    private WidrowHoffSigmoidStrategy() {
    }

    public static WidrowHoffSigmoidStrategy getInstance() {
        return WidrowHoffSigmoidStrategyHolder.INSTANCE;
    }

    private static class WidrowHoffSigmoidStrategyHolder {

        private static final WidrowHoffSigmoidStrategy INSTANCE = new WidrowHoffSigmoidStrategy();
    }
}
