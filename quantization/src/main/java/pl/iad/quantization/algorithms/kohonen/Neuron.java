/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.kohonen;

import java.util.List;

/**
 *
 * @author PiotrGrzelak
 */
public class Neuron {
    
    private final double[] weights;
    
    private List<double[]> mappedPoints;
    
    private final int x;
    
    private final int y;

    private int winningCount;
    
    public Neuron(int dimensions, int x, int y) {
        weights = new double[dimensions];
        this.x = x;
        this.y = y;
        winningCount = 0;
    }
    
    public void initNeuron(double[] dataSpans) {
        for (int i = 0; i < weights.length; ++i) {
            weights[i] = Math.random() * dataSpans[i];
        }
    }
    
    public double getDistanceFromInputPoint(double[] input) {
        double result = 0;
        for (int i = 0; i < input.length; ++i) {
            result = Math.pow((input[i] - weights[i]), 2);
        }
        return Math.sqrt(result);
    }
    
    public int getSquaredDistanceFromNeuron(Neuron n) {
        return (x - n.x) * (x - n.x) + (y - n.y) * (y - n.y);
    }
    
    public void adjustWeights(double[] inputVector, double learningRate, double distanceFalloff) {
        for (int i = 0; i < inputVector.length; ++i) {
            weights[i] = weights[i] + distanceFalloff * learningRate * (inputVector[i] - weights[i]);
        }
    }

    public boolean isInNeighbourHood(double radius, Neuron neuron) {
        double squaredDistanceFrom = (double) getSquaredDistanceFromNeuron(neuron);
        return radius >= squaredDistanceFrom;
    }
    
    public double[] getWeights() {
        return weights;
    }

    public int getWinningCount() {
        return winningCount;
    }
    
    public void increaseWinningCount() {
        ++winningCount;
    }
    
    public void addMappedPoint(double[] point) {
        mappedPoints.add(point);
    }

    public List<double[]> getMappedPoints() {
        return mappedPoints;
    }
}
