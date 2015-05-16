/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.iad.quantization.algorithms.means.training;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import pl.iad.quantization.algorithms.means.structure.Centre;
import pl.iad.quantization.algorithms.means.structure.CentresSet;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.metrics.Metric;
import pl.iad.quantization.reporting.TrainingReporter;

/**
 *
 * @author PiotrGrzelak
 */
public class KMeansAlgorithm {

    private Metric metric;

    private TrainingReporter reporter;

    public void doQuantization(List<Point> data,
            double maxAbsCoordinates,
            int maxIterations,
            int repetitionNum,
            int centresNum,
            double epsilon,
            int dimensions) {

        double error = Double.MAX_VALUE;
        for (int i = 0; i < repetitionNum; ++i) {
            CentresSet centres = new CentresSet(centresNum, dimensions, maxAbsCoordinates, epsilon);
            double runError = doAlgorithmRun(centres, data, maxIterations);
            if (runError < error) {
                error = runError;
                reporter.preserveRunData();
            }
            reporter.clearRunData();
        }
    }

    private double doAlgorithmRun(CentresSet centres, List<Point> data, int maxIterations) {
        double error = 0;
        for (int i = 0; i < maxIterations; ++i) {
            if (allCentersStable(centres.getCentres())) {
                break;
            }
            error = doEpoch(centres, data);
            reporter.notifyAfterEpoch(centres.getCentres(), error);
        }
        return error;
    }

    private double doEpoch(CentresSet centresSet, List<Point> data) {
        List<Centre> centres = centresSet.getCentres();

        Collections.shuffle(data);
        for (Point p : data) {
            int closestIdx = 0;
            double minDist = metric.distance(p, centres.get(closestIdx));
            for (int i = 1; i < centres.size(); ++i) {
                double dist = metric.distance(p, centres.get(i));
                if (dist < minDist) {
                    minDist = dist;
                    closestIdx = i;
                }
            }

            p.setRepresentative(centres.get(closestIdx));
        }

        double error = 0;
        for (Point p : data) {
            Centre centre = (Centre) p.getRepresentative();
            error += metric.distance(p, centre);
        }
        error /= data.size();

        for (int i = 0; i < centres.size(); ++i) {
//            if (centres.get(i).isStable()) {
//                continue;
//            }
            List<Point> representedPoints = getRepresentedPoints(centres.get(i), data);
            if (representedPoints.isEmpty()) {
                continue;
            }
            Centre centre = centres.get(i);
            double[] newCoords = new double[centre.getWeights().length];
            for (int j = 0; j < centre.getWeights().length; ++j) {
                double newCoord = 0;
                for (Point p : representedPoints) {
                    newCoord += p.getWeight(j);
                }
                newCoords[j] = newCoord / representedPoints.size();
            }
            Point point = new Point();
            point.setWeights(newCoords);
            double shift = metric.distance(point, centre);
            centre.setShift(shift);
            centre.setWeights(newCoords);
        }

        return error;
    }

    private List<Point> getRepresentedPoints(Centre centre, List<Point> data) {
        return data.stream().filter(p -> p.getRepresentative().equals(centre)).collect(Collectors.toList());
    }

    public boolean allCentersStable(List<Centre> centres) {
        for (Centre centre : centres) {
            if (!centre.isStable()) {
                return false;
            }
        }

        return true;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public void setReporter(TrainingReporter reporter) {
        this.reporter = reporter;
    }
}
