package pl.iad.quantization.data.images;

/**
 *
 * @author Wojciech Szałapski
 */
public class ImageData {

    private final int width;
    
    private final int height;
    
    private final int[][] pixels;

    public ImageData(int width, int height, int[][] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getPixels() {
        return pixels;
    }
}
