package pl.iad.quantization.data.images.converter;

import java.util.ArrayList;
import java.util.List;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.images.ImageData;
import pl.iad.quantization.data.normalization.MinMaxInputNormalizer;
import pl.iad.quantization.data.normalization.NumericInputNormalizer;

/**
 *
 * @author Wojciech Szałapski
 */
public class ImageConverter {

    public List<Point> convertToFrames(ImageData originalImage, int frameSize) {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int dimension = frameSize * frameSize;

        int[][] pixels = originalImage.getPixels();
        
        List<Point> result = new ArrayList<>();
        for (int i = 0; i < height / frameSize; ++i) {
            for (int j = 0; j < width / frameSize; ++j) {
                double[] coordinates = new double[dimension];

                for (int x = 0; x < frameSize; ++x) {
                    for (int y = 0; y < frameSize; ++y) {
                        coordinates[frameSize * x + y]
                                = pixels[frameSize * i + x][frameSize * j + y];
                    }
                }

                Point frame = new Point();
                frame.setWeights(coordinates);
                result.add(frame);
            }
        }

        return result;
    }

    public int[][] convertToPixels(List<Point> frames, int frameSize, int width, int height) {
        int[][] result = new int[height][width];

        int counter = 0;
        for (int i = 0; i < height / frameSize; ++i) {
            for (int j = 0; j < width / frameSize; ++j) {
                for (int x = 0; x < frameSize; ++x) {
                    for (int y = 0; y < frameSize; ++y) {
                        result[frameSize * i + x][frameSize * j + y]
                                = (int) frames.get(counter).getWeight(frameSize * x + y);
                    }
                }
                ++counter;
            }
        }

        return result;
    }
}
