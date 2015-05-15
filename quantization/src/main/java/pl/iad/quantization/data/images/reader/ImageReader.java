package pl.iad.quantization.data.images.reader;

import pl.iad.quantization.data.images.ImageData;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Wojciech Szałapski
 */
public interface ImageReader {

    ImageData readBlackAndWhiteImage(File file) throws IOException;
}
