package pl.iad.quantization.data.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.iad.quantization.data.Point;

/**
 *
 * @author Wojciech Szałapski
 */
public class FileReader {

    public List<Point> readPointsFromFile(File file) {
        List<Point> result = new ArrayList<>();

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                double x = Double.parseDouble(scanner.next());
                double y = Double.parseDouble(scanner.next());

                double[] coordinates = new double[]{x, y};
                Point point = new Point();
                point.setWeights(coordinates);

                result.add(point);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

        return result;
    }
}
