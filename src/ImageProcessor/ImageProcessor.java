package ImageProcessor;

import lombok.*;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageProcessor {
    private Mat image;
    private String filePath;

    public void loadImage(String filePath) {
        this.filePath = filePath;
        this.image = Imgcodecs.imread(filePath);
        if (this.image.empty()) {
            throw new IllegalArgumentException("Image not found at: " + filePath);
        }
    }

    public void adjustLuminosity(double beta) {
        Mat result = new Mat();
        image.convertTo(result, -1, 1, beta); // alpha = 1 (no contrast change), beta = luminosity
        this.image = result;
    }

    public void adjustContrast(double alpha) {
        Mat result = new Mat();
        image.convertTo(result, -1, alpha, 0); // alpha = contrast
        this.image = result;
    }

    public void toGrayscale() {
        Mat grayImage = new Mat();
        Imgproc.cvtColor(this.image, grayImage, Imgproc.COLOR_BGR2GRAY);
        this.image = grayImage;
    }

    public void resize(int width, int height) {
        Mat resizedImage = new Mat();
        Imgproc.resize(this.image, resizedImage, new Size(width, height));
        this.image = resizedImage;
    }

    public void crop(int x, int y, int width, int height) {
        Rect rect = new Rect(x, y, width, height);
        this.image = new Mat(this.image, rect);
    }

    public void rotate(double angle) {
        Point center = new Point((float)this.image.cols() / 2, (float)this.image.rows() / 2);
        Mat rotationMatrix = Imgproc.getRotationMatrix2D(center, angle, 1.0);
        Mat rotatedImage = new Mat();
        Imgproc.warpAffine(this.image, rotatedImage, rotationMatrix, this.image.size());
        this.image = rotatedImage;
    }

    public void saveImage() {
        String OutputPath="C:\\Users\\MSI\\Desktop\\java projects\\Image_Processing\\SavedImages";
        Imgcodecs.imwrite(OutputPath, this.image);
    }

    public Mat getImage() {
        return image;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setImage(Mat image) {
        this.image = image;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}

