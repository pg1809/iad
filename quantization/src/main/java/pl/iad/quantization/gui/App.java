/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.gui;

/**
 *
 * @author PiotrGrzelak
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if ("kh".equals(args[0])) {
            runKohonen();
        } else if ("g".equals(args[0])) {
            runGas();
        } else if ("m".equals(args[0])) {
            runKMeans();
        }
    }
    
    private static void runKohonen() {
        
    }
    
    private static void runGas() {
        
    }
    
    private static void runKMeans() {
        
    }
    
}
