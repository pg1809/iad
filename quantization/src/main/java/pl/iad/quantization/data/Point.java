package pl.iad.quantization.data;

/**
 *
 * @author Wojciech Szałapski
 */
public class Point {

    private double coordinates[];

    public double dist2(Point target) {
        double[] targetCoordinates = target.getCoordinates();

        double distance = 0;
        for (int i = 0; i < coordinates.length; ++i) {
            distance += (coordinates[i] - targetCoordinates[i])
                    * (coordinates[i] - targetCoordinates[i]);
        }

        return distance;
    }

    public double dist(Point target) {
        return Math.sqrt(dist2(target));
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
