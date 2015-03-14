/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iad.network.strategy.bp;

/**
 *
 * @author PiotrGrzelak
 */
public class IdentityActivationBPS extends BackPropagationStrategy {
    
    private static IdentityActivationBPS instance = new IdentityActivationBPS();
    
    public static IdentityActivationBPS getInstance() {
        return instance;
    }
    
    @Override
    public double transfer(double netValue) {
        return netValue;
    }
}
