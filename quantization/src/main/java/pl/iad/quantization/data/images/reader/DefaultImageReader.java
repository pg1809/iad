package pl.iad.quantization.data.images.reader;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import pl.iad.quantization.data.images.ImageData;

/**
 *
 * @author Wojciech Szałapski
 */
public class DefaultImageReader implements ImageReader {

    @Override
    public ImageData readBlackAndWhiteImage(File file) throws IOException {
        BufferedImage image = ImageIO.read(file);
        int width = image.getWidth();
        int height = image.getHeight();

        int[][] pixels = new int[height][width];
        for (int row = 0; row < height; ++row) {
            for (int column = 0; column < width; ++column) {
                pixels[row][column] = image.getRGB(column, row) & 0xFF;
            }
        }

        return new ImageData(width, height, pixels);
    }
}
