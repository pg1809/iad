package pl.iad.quantization.data.images;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.List;
import pl.iad.quantization.data.Point;
import pl.iad.quantization.data.images.converter.ImageConverter;

/**
 *
 * @author Wojciech Szałapski
 */
public class ImageHolder {

    private final ImageData originalImage;

    private final int frameSize;

    private List<Point> pointRepresentation;

    private final int widthInFrames;

    private final int heightInFrames;

    private final static ImageConverter converter = new ImageConverter();

    public ImageHolder(ImageData originalImage, int frameSize) {
        this.originalImage = originalImage;
        this.frameSize = frameSize;

        widthInFrames = originalImage.getWidth() / frameSize;
        heightInFrames = originalImage.getHeight() / frameSize;
        pointRepresentation = converter.convertToFrames(originalImage, frameSize);
    }

    public BufferedImage recreateImage() {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        int convertedWidth = widthInFrames * frameSize;
        int convertedHeight = heightInFrames * frameSize;

        int widthRemainder = originalImage.getWidth() % frameSize;
        int heightRemainder = originalImage.getHeight() % frameSize;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster raster = (WritableRaster) image.getData();

        int[][] originalPixels = originalImage.getPixels();
        int[][] pixels = converter.convertToPixels(pointRepresentation,
                frameSize, convertedWidth, convertedHeight);
        for (int row = 0; row < convertedHeight; ++row) {
            for (int column = 0; column < convertedWidth; ++column) {
                image.setRGB(column, row, convertPixelToRGB(pixels[row][column]));
            }
            for (int i = 0; i < widthRemainder; ++i) {
                image.setRGB(convertedWidth + i, row,
                        convertPixelToRGB(originalPixels[row][convertedWidth + i]));
            }
        }
        for (int i = 0; i < heightRemainder; ++i) {
            for (int j = 0; j < width; ++j) {
                image.setRGB(j, convertedHeight + i,
                        convertPixelToRGB(originalPixels[convertedHeight + i][j]));
            }
        }

        return image;
    }

    private int convertPixelToRGB(int pixel) {
        return 0xFF000000 | (pixel << 16) | (pixel << 8) | pixel;
    }

    public List<Point> getPointRepresentation() {
        return pointRepresentation;
    }

    public void setPointRepresentation(List<Point> pointRepresentation) {
        this.pointRepresentation = pointRepresentation;
    }
}
