import ImageProcessor.ImageProcessor;
import org.opencv.core.Core;
import org.opencv.highgui.HighGui;

import java.util.Scanner;

public class Main {
    static { System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ImageProcessor processor = new ImageProcessor();

        try {
            System.out.println("Enter the file path of the image:");
            String filePath = scanner.nextLine();
            processor.loadImage(filePath);
            System.out.println("Image loaded successfully!");

            HighGui.imshow("Original Image", processor.getImage());
            HighGui.waitKey(0);

            boolean exit = false;

            while (!exit) {
                System.out.println("Choose an operation: ");
                System.out.println("1. Adjust Luminosity");
                System.out.println("2. Adjust Contrast");
                System.out.println("3. Grayscale");
                System.out.println("4. Resize");
                System.out.println("5. Crop");
                System.out.println("6. Rotate");
                System.out.println("7. Save Image");
                System.out.println("8. Exit");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter luminosity adjustment value (-100 to 100):");
                        double beta = scanner.nextDouble();
                        processor.adjustLuminosity(beta);
                    }
                    case 2 -> {
                        System.out.println("Enter contrast adjustment value (e.g., 1.5 for higher contrast):");
                        double alpha = scanner.nextDouble();
                        processor.adjustContrast(alpha);
                    }
                    case 3 -> processor.toGrayscale();
                    case 4 -> {
                        System.out.println("Enter new width and height:");
                        int width = scanner.nextInt();
                        int height = scanner.nextInt();
                        processor.resize(width, height);
                    }
                    case 5 -> {
                        System.out.println("Enter crop values (x, y, width, height):");
                        int x = scanner.nextInt();
                        int y = scanner.nextInt();
                        int width = scanner.nextInt();
                        int height = scanner.nextInt();
                        processor.crop(x, y, width, height);
                    }
                    case 6 -> {
                        System.out.println("Enter rotation angle (degrees):");
                        double angle = scanner.nextDouble();
                        processor.rotate(angle);
                    }
                    case 7 -> {
                        System.out.println("Enter the output file path to save the image:");
                        String outputPath = scanner.next();
                        processor.saveImage();
                        System.out.println("Image saved successfully!");
                    }
                    case 8 -> exit = true;
                    default -> System.out.println("Invalid choice!");
                }

                if (!exit) {
                    HighGui.imshow("Processed Image", processor.getImage());
                    HighGui.waitKey(0);
                }
            }

            System.out.println("Exiting program. Goodbye!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
