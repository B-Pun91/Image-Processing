import java.io.*;
import java.util.TreeSet;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.Random;
import java.util.Arrays;
import java.lang.Math;


public class Demo extends Component implements ActionListener {

    //************************************
    // List of the options(Original, Negative); correspond to the cases:
    //************************************

    String descs[] = {
            "Original",
            "Negative",
            "Rescale",
            "Shift",
            "RescaleShift",
            "Add",
            "Subtract",
            "Multiply",
            "Divide",
            "NOT",
            "AND",
            "OR",
            "XOR",
            "ROI",
            "Negative_Linear",
            "Logarithmic_function",
            "Power_Law",
            "LUT",
            "Bit_planeSlicing",
            "Histogram",
            "HistogramEqualisation",
            "Averaging",
            "WeightedAveraging",
            "4 Neighbour Laplacian",
            "8 Neighbour Laplacian",
            "4 Neighbour Laplacian Enhancement",
            "8 Neighbour Laplacian Enhancement",
            "Roberts",
            "SobelX",
            "SobelY",
            "Gaussian",
            "Laplacian of Gaussian",
            "Salt and Pepper Image",
            "Min Filtering",
            "Max Filtering",
            "Mid point Filtering",
            "Median Filtering",
            "Simple Thresholding",
            "Automated Thresholding",
            "Adptive Thresholding",
    };

    int opIndex;  //option index for
    int lastOp;

    private BufferedImage bi, biFiltered, bi1, biFiltered1, bi2, biFiltered2,bi3, biFiltered3, bi4, biFiltered4, bi5, biFiltered5, bi6, biFiltered6, bi7, biFiltered7;   // the input image saved as bi;//
    int w, h, w1, h1, w2, h2;

    public static float randFloat(float min, float max) {
        Random rand = new Random();

        return rand.nextFloat() * (max - min) + min;
    }

    public Demo() {
        try {
            bi = ImageIO.read(new File("Baboon.bmp"));

            w1 = bi.getWidth(null);
            h1 = bi.getHeight(null);
            //System.out.println(bi.getType());
            if (bi.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi2 = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi2.getGraphics();
                big.drawImage(bi, 0, 0, null);
                biFiltered = bi = bi2;
            }
            bi1 = ImageIO.read(new File("LenaRGB.bmp"));

            w = bi1.getWidth(null);
            h = bi1.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi1.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi21 = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi21.getGraphics();
                big.drawImage(bi1, 0, 0, null);
                biFiltered1 = bi1 = bi21;
            }
            bi2 = ImageIO.read(new File("abc.png"));

            w2 = bi2.getWidth(null);
            h2 = bi2.getHeight(null);
                    //System.out.println(bi1.getType());
            if (bi2.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi22 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi22.getGraphics();
                big.drawImage(bi2, 0, 0, null);
                biFiltered2 = bi2 = bi22;
            }

            bi3 = ImageIO.read(new File("Hist_Equalisation.jpg"));

            w2 = bi3.getWidth(null);
            h2 = bi3.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi3.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi23 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi23.getGraphics();
                big.drawImage(bi3, 0, 0, null);
                biFiltered2 = bi3 = bi23;
            }
            bi4 = ImageIO.read(new File("saltnpeppernoiseimage.jpg"));

            w2 = bi4.getWidth(null);
            h2 = bi4.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi4.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi24 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi24.getGraphics();
                big.drawImage(bi4, 0, 0, null);
                biFiltered2 = bi4 = bi24;
            }
            bi5 = ImageIO.read(new File("SimpleThreshold.jpg"));

            w2 = bi5.getWidth(null);
            h2 = bi5.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi5.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi25 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi25.getGraphics();
                big.drawImage(bi5, 0, 0, null);
                biFiltered2 = bi5 = bi25;
            }
            bi6 = ImageIO.read(new File("AutomatedThreshold.jpg"));

            w2 = bi6.getWidth(null);
            h2 = bi6.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi6.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi26 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi26.getGraphics();
                big.drawImage(bi6, 0, 0, null);
                biFiltered2 = bi6 = bi26;
            }
            bi7 = ImageIO.read(new File("AdaptiveThreshold.jpg"));

            w2 = bi7.getWidth(null);
            h2 = bi7.getHeight(null);
            //System.out.println(bi1.getType());
            if (bi7.getType() != BufferedImage.TYPE_INT_RGB) {
                BufferedImage bi27 = new BufferedImage(w2, h2, BufferedImage.TYPE_INT_RGB);
                Graphics big = bi27.getGraphics();
                big.drawImage(bi7, 0, 0, null);
                biFiltered2 = bi7 = bi27;
            }
        } catch (IOException e) {      // deal with the situation that th image has problem;/
            System.out.println("Image could not be read");

            System.exit(1);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(w, h);
    }


    String[] getDescriptions() {
        return descs;
    }

    // Return the formats sorted alphabetically and in lower case
    public String[] getFormats() {
        String[] formats = {"bmp", "gif", "jpeg", "jpg", "png"};
        TreeSet<String> formatSet = new TreeSet<String>();
        for (String s : formats) {
            formatSet.add(s.toLowerCase());
        }
        return formatSet.toArray(new String[0]);
    }


    void setOpIndex(int i) {
        opIndex = i;
    }

    public void paint(Graphics g) { //  Repaint will call this function so the image will change.
        filterImage();

        g.drawImage(biFiltered, 0, 0, null);

        g.drawImage(biFiltered1, 512, 0, null);

        //g.drawImage(biFiltered2, 512, 512, null);

    }


    //************************************
    //  Convert the Buffered Image to Array
    //************************************
    private static int[][][] convertToArray(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        int[][][] result = new int[width][height][4];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                result[x][y][0] = a;
                result[x][y][1] = r;
                result[x][y][2] = g;
                result[x][y][3] = b;
            }
        }
        return result;
    }
    // function to convert image to array with double values
    private static double [][][] convertToArray1(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        double [][][] result = new double [width][height][4];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);
                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                result[x][y][0] = a;
                result[x][y][1] = r;
                result[x][y][2] = g;
                result[x][y][3] = b;
            }
        }
        return result;
    }

    //function to rescale image
    public static int[][][] rescaleShift(int[][][] ImageArray1) {

        int width = ImageArray1.length;
        int height = ImageArray1[0].length;

        int[][][] ImageArray2 = ImageArray1;

        float s = randFloat(0, 2);
        int t = (int) (Math.random());
        //float s = 2;
        //int t = 20;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                ImageArray1[x][y][1] = (int) (t + ImageArray1[x][y][1]);  //r
                ImageArray1[x][y][2] = (int) (t + ImageArray1[x][y][2]);  //g
                ImageArray1[x][y][3] = (int) (t + ImageArray1[x][y][3]);  //b
            }
        }
        int rmin = (int) (s * (ImageArray1[0][0][1] + t));
        int rmax = rmin;
        int gmin = (int) (s * (ImageArray1[0][0][2] + t));
        int gmax = gmin;
        int bmin = (int) (s * (ImageArray1[0][0][3] + t));
        int bmax = bmin;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray2[x][y][1] = (int) (s * ImageArray1[x][y][1]);  //r
                ImageArray2[x][y][2] = (int) (s * ImageArray1[x][y][2]);  //g
                ImageArray2[x][y][3] = (int) (s * ImageArray1[x][y][3]);  //b

                if (rmin > ImageArray2[x][y][1]) {
                    rmin = ImageArray2[x][y][1];
                }
                if (gmin > ImageArray2[x][y][2]) {
                    gmin = ImageArray2[x][y][2];
                }
                if (bmin > ImageArray2[x][y][3]) {
                    bmin = ImageArray2[x][y][3];
                }
                if (rmax < ImageArray2[x][y][1]) {
                    rmax = ImageArray2[x][y][1];
                }
                if (gmax < ImageArray2[x][y][2]) {
                    gmax = ImageArray2[x][y][2];
                }
                if (bmax < ImageArray2[x][y][3]) {
                    bmax = ImageArray2[x][y][3];
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray2[x][y][1] = 255 * (ImageArray2[x][y][1] - rmin) / (rmax - rmin);
                ImageArray2[x][y][2] = 255 * (ImageArray2[x][y][2] - gmin) / (gmax - gmin);
                ImageArray2[x][y][3] = 255 * (ImageArray2[x][y][3] - bmin) / (bmax - bmin);
            }
        }
        return (ImageArray2);
    }
    //function to change RGB to Grayscale image

    public static BufferedImage copyImage(BufferedImage bi) {
         ColorModel cm = bi.getColorModel();
         boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
         WritableRaster raster = bi.copyData(null);
         return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
    public static BufferedImage GreayscaleImg (BufferedImage timg) {

      int width = timg.getWidth();
      int height = timg.getHeight();
      BufferedImage copy = copyImage(timg);

      int p=0, a=0, r=0, g=0, b=0, avg=0;
      for(int y=0;y<height;y++) {
        for(int x=0;x<width;x++) {
          p=timg.getRGB(x, y);
          a=(p>>24)&0xff;
          r=(p>>16)&0xff;
          g=(p>>8)&0xff;
          b=p&0xff;
          avg = (r+g+b)/3;
          p = (a<<24) | (avg<<16) | (avg<<8) | avg;
          copy.setRGB(x, y, p);
        }
      }
      return copy;
    }

    //************************************
    //  Convert the  Array to BufferedImage
    //************************************
    public BufferedImage convertToBimage(int[][][] TmpArray) {

        int width = TmpArray.length;
        int height = TmpArray[0].length;

        BufferedImage tmpimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int a = TmpArray[x][y][0];
                int r = TmpArray[x][y][1];
                int g = TmpArray[x][y][2];
                int b = TmpArray[x][y][3];

                //set RGB value

                int p = (a << 24) | (r << 16) | (g << 8) | b;
                tmpimg.setRGB(x, y, p);

            }
        }
        return tmpimg;
    }


    //************************************
    //  Example:  Image Negative
    //************************************
    public BufferedImage ImageNegative(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);          //  Convert the image to array

        // Image Negative Operation:


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = 255 - ImageArray[x][y][1];  //r
                ImageArray[x][y][2] = 255 - ImageArray[x][y][2];  //g
                ImageArray[x][y][3] = 255 - ImageArray[x][y][3];  //b
            }
        }

        return convertToBimage(ImageArray);  // Convert the array to BufferedImage
    }


    //************************************
    //  Your turn now:  Add more function below
    //************************************
    //Lab 2
    //Rescaling image operation
    public BufferedImage RescaleImage(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);  // convert to array


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (1.3 * ImageArray[x][y][1]);  //r
                ImageArray[x][y][2] = (int) (1.3 * ImageArray[x][y][2]);  //g
                ImageArray[x][y][3] = (int) (1.3 * ImageArray[x][y][3]);  //b
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (ImageArray[x][y][1] < 0)
                    ImageArray[x][y][1] = 0;
                if (ImageArray[x][y][2] < 0)
                    ImageArray[x][y][2] = 0;
                if (ImageArray[x][y][3] < 0)
                    ImageArray[x][y][3] = 0;
                if (ImageArray[x][y][1] > 255)
                    ImageArray[x][y][1] = 255;
                if (ImageArray[x][y][2] > 255)
                    ImageArray[x][y][2] = 255;
                if (ImageArray[x][y][3] > 255)
                    ImageArray[x][y][3] = 255;
            }
        }
        return convertToBimage(ImageArray); //convert the array to BufferedImage
    }

    //shifting image operation
    public BufferedImage ShiftImage(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);  // convert to array


        int R = (int) (Math.random());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (-15 + ImageArray[x][y][1]);  //r
                ImageArray[x][y][2] = (int) (-15 + ImageArray[x][y][2]);  //g
                ImageArray[x][y][3] = (int) (-15 + ImageArray[x][y][3]);  //b
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (ImageArray[x][y][1] < 0)
                    ImageArray[x][y][1] = 0;
                if (ImageArray[x][y][2] < 0)
                    ImageArray[x][y][2] = 0;
                if (ImageArray[x][y][3] < 0)
                    ImageArray[x][y][3] = 0;
                if (ImageArray[x][y][1] > 255)
                    ImageArray[x][y][1] = 255;
                if (ImageArray[x][y][2] > 255)
                    ImageArray[x][y][2] = 255;
                if (ImageArray[x][y][3] > 255)
                    ImageArray[x][y][3] = 255;
            }
        }

        return convertToBimage(ImageArray); //convert the array to BufferedImage

    }

    //Rescaleshifting Operation
    public BufferedImage RescaleShiftImage(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg);


        float s = randFloat(0, 2);
        int t = (int) (Math.random());
        //float s = 2;
        //int t = 20;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                //int R = (int) (Math.random());
                ImageArray1[x][y][1] = (int) (t + ImageArray[x][y][1]);  //r
                ImageArray1[x][y][2] = (int) (t + ImageArray[x][y][2]);  //g
                ImageArray1[x][y][3] = (int) (t + ImageArray[x][y][3]);  //b
            }
        }
        int rmin = (int) (s * (ImageArray[0][0][1] + t));
        int rmax = rmin;
        int gmin = (int) (s * (ImageArray[0][0][2] + t));
        int gmax = gmin;
        int bmin = (int) (s * (ImageArray[0][0][3] + t));
        int bmax = bmin;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray2[x][y][1] = (int) (s * ImageArray1[x][y][1]);  //r
                ImageArray2[x][y][2] = (int) (s * ImageArray1[x][y][2]);  //g
                ImageArray2[x][y][3] = (int) (s * ImageArray1[x][y][3]);  //b

                if (rmin > ImageArray2[x][y][1]) {
                    rmin = ImageArray2[x][y][1];
                }
                if (gmin > ImageArray2[x][y][2]) {
                    gmin = ImageArray2[x][y][2];
                }
                if (bmin > ImageArray2[x][y][3]) {
                    bmin = ImageArray2[x][y][3];
                }
                if (rmax < ImageArray2[x][y][1]) {
                    rmax = ImageArray2[x][y][1];
                }
                if (gmax < ImageArray2[x][y][2]) {
                    gmax = ImageArray2[x][y][2];
                }
                if (bmax < ImageArray2[x][y][3]) {
                    bmax = ImageArray2[x][y][3];
                }
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray2[x][y][1] = 255 * (ImageArray2[x][y][1] - rmin) / (rmax - rmin);
                ImageArray2[x][y][2] = 255 * (ImageArray2[x][y][2] - gmin) / (gmax - gmin);
                ImageArray2[x][y][3] = 255 * (ImageArray2[x][y][3] - bmin) / (bmax - bmin);
            }
        }
        return convertToBimage(ImageArray2);
    }
    //Lab 3
    // Arithmentic operators:
    //Add
    public BufferedImage Add(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();


        int[][][] ImageArray = convertToArray(timg); // converting image to array
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (ImageArray1[x][y][1] + ImageArray2[x][y][1]);
                ImageArray[x][y][2] = (int) (ImageArray1[x][y][2] + ImageArray2[x][y][2]);
                ImageArray[x][y][3] = (int) (ImageArray1[x][y][3] + ImageArray2[x][y][3]);
            }
        }

        ImageArray3 = rescaleShift(ImageArray);


        return convertToBimage(ImageArray3);
    }

    //Subtract
    public BufferedImage Subtract(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();
        int width1 = timg1.getWidth();
        int height1 = timg1.getHeight();

        int[][][] ImageArray = convertToArray(timg); // converting image to array
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (ImageArray1[x][y][1] - ImageArray2[x][y][1]);
                ImageArray[x][y][2] = (int) (ImageArray1[x][y][2] - ImageArray2[x][y][2]);
                ImageArray[x][y][3] = (int) (ImageArray1[x][y][3] - ImageArray2[x][y][3]);
            }
        }
        ImageArray3 = rescaleShift(ImageArray);
        return convertToBimage(ImageArray3);
    }

    // Multiply
    public BufferedImage Multiply(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();
        int width1 = timg1.getWidth();
        int height1 = timg1.getHeight();

        int[][][] ImageArray = convertToArray(timg); // converting image to array
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (ImageArray1[x][y][1] * ImageArray2[x][y][1]);
                ImageArray[x][y][2] = (int) (ImageArray1[x][y][2] * ImageArray2[x][y][2]);
                ImageArray[x][y][3] = (int) (ImageArray1[x][y][3] * ImageArray2[x][y][3]);
            }
        }
        ImageArray3 = rescaleShift(ImageArray);
        return convertToBimage(ImageArray3);
    }

    //Divide
    public BufferedImage Divide(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();
        int width1 = timg1.getWidth();
        int height1 = timg1.getHeight();

        int[][][] ImageArray = convertToArray(timg); // converting image to array
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = (int) (ImageArray1[x][y][1] / (ImageArray2[x][y][1] + 1));
                ImageArray[x][y][2] = (int) (ImageArray1[x][y][2] / (ImageArray2[x][y][2] + 1));
                ImageArray[x][y][3] = (int) (ImageArray1[x][y][3] / (ImageArray2[x][y][3] + 1));
            }
        }
        ImageArray3 = rescaleShift(ImageArray);

        return convertToBimage(ImageArray3);
    }


    //Bitwise Booleon NOT Operator:
    public BufferedImage NOT(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int r = ImageArray1[x][y][1]; //r
                int g = ImageArray1[x][y][2]; //g
                int b = ImageArray1[x][y][3]; //b

                ImageArray2[x][y][1] = (~r) & 0xFF; //r
                ImageArray2[x][y][2] = (~g) & 0xFF; //g
                ImageArray2[x][y][3] = (~b) & 0xFF; //b

            }
        }
        return convertToBimage(ImageArray2);

    }

    // Bitwise Booleon AND Operator:
    public BufferedImage AND(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int a1 = ImageArray1[x][y][0]; //a
                int r1 = ImageArray1[x][y][1]; //r for first image
                int g1 = ImageArray1[x][y][2]; //g
                int b1 = ImageArray1[x][y][3]; //b

                int a2 = ImageArray2[x][y][0];
                int r2 = ImageArray2[x][y][1]; //r for second image
                int g2 = ImageArray2[x][y][2]; //g
                int b2 = ImageArray2[x][y][3]; //b

                ImageArray3[x][y][0] = a1 & a2;
                ImageArray3[x][y][1] = r1 & r2;
                ImageArray3[x][y][2] = g1 & g2;
                ImageArray3[x][y][3] = b1 & b2;

            }
        }
        return convertToBimage(ImageArray3);
    }

    // OR operator:
    public BufferedImage OR(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int a1 = ImageArray1[x][y][0]; //a
                int r1 = ImageArray1[x][y][1]; //r for first image
                int g1 = ImageArray1[x][y][2]; //g
                int b1 = ImageArray1[x][y][3]; //b

                int a2 = ImageArray2[x][y][0];
                int r2 = ImageArray2[x][y][1]; //r for second image
                int g2 = ImageArray2[x][y][2]; //g
                int b2 = ImageArray2[x][y][3]; //b

                ImageArray3[x][y][0] = a1 | a2;
                ImageArray3[x][y][1] = r1 | r2;
                ImageArray3[x][y][2] = g1 | g2;
                ImageArray3[x][y][3] = b1 | b2;

            }
        }
        return convertToBimage(ImageArray3);
    }

    // XOR Operator:
    public BufferedImage XOR(BufferedImage timg, BufferedImage timg1) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);
        int[][][] ImageArray3 = convertToArray(timg1);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int a1 = ImageArray1[x][y][0]; //a
                int r1 = ImageArray1[x][y][1]; //r for first image
                int g1 = ImageArray1[x][y][2]; //g
                int b1 = ImageArray1[x][y][3]; //b

                int a2 = ImageArray2[x][y][0];
                int r2 = ImageArray2[x][y][1]; //r for second image
                int g2 = ImageArray2[x][y][2]; //g
                int b2 = ImageArray2[x][y][3]; //b

                ImageArray3[x][y][0] = a1 ^ a2;
                ImageArray3[x][y][1] = r1 ^ r2;
                ImageArray3[x][y][2] = g1 ^ g2;
                ImageArray3[x][y][3] = b1 ^ b2;

            }
        }
        return convertToBimage(ImageArray3);
    }

    // Region of Interest Operator
    public BufferedImage ROI(BufferedImage timg) {

        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray3 = convertToArray(timg);
        // for region ogf interest 100  - 200 y-axis and 100 - 200 x -axis

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray1[x][y][1] = 0;  //r
                ImageArray1[x][y][2] = 0;  //g
                ImageArray1[x][y][3] = 0;  //b
            }
        }

        for (int y = 150; y < 400; y++) {
            for (int x = 150; x < 400; x++) {
                ImageArray1[x][y][1] = 1;  //r
                ImageArray1[x][y][2] = 1;  //g
                ImageArray1[x][y][3] = 1;  //b
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray1[x][y][1] = (int) (ImageArray1[x][y][1] * ImageArray[x][y][1]);  //r
                ImageArray1[x][y][2] = (int) (ImageArray1[x][y][2] * ImageArray[x][y][2]);  //g
                ImageArray1[x][y][3] = (int) (ImageArray1[x][y][3] * ImageArray[x][y][3]);  //b
            }
        }
        ImageArray3 = rescaleShift(ImageArray1);

        return convertToBimage(ImageArray3);

    }
    //Lab 4
    // Linear Negative
    public BufferedImage Negative_Linear(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);          //  Convert the image to array

        // Image Negative Operation:


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray[x][y][1] = 255 - ImageArray[x][y][1];  //r
                ImageArray[x][y][2] = 255 - ImageArray[x][y][2];  //g
                ImageArray[x][y][3] = 255 - ImageArray[x][y][3];  //b
            }
        }

        return convertToBimage(ImageArray);  // Convert the array to BufferedImage
    }

    // Logarithmic function

    public BufferedImage Logarithmic_function(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray1 = convertToArray(timg);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray1[x][y][1] = (int) (Math.log(1 + ImageArray[x][y][1]) * 255 / Math.log(256));   //r
                ImageArray1[x][y][2] = (int) (Math.log(1 + ImageArray[x][y][2]) * 255 / Math.log(256));  //g
                ImageArray1[x][y][3] = (int) (Math.log(1 + ImageArray[x][y][3]) * 255 / Math.log(256));  //b
            }
        }


        return convertToBimage(ImageArray1);  // Convert the array to BufferedImage
    }

    // Power-Law
    public BufferedImage Power_Law(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();
        int p = 2; // gamma set between 0.01 - 20

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray1 = convertToArray(timg);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                ImageArray1[x][y][1] = (int) (Math.pow(255, 1 - p) * Math.pow(ImageArray[x][y][1], p));   //r
                ImageArray1[x][y][2] = (int) (Math.pow(255, 1 - p) * Math.pow(ImageArray[x][y][2], p));  //g
                ImageArray1[x][y][3] = (int) (Math.pow(255, 1 - p) * Math.pow(ImageArray[x][y][3], p));

            }
        }


        return convertToBimage(ImageArray1);  // Convert the array to BufferedImage
    }

    // Look up Table
    public BufferedImage LUT(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray1 = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg);

        int[] LUT = new int[256];

        // Look up table initialization using logrithmic function
        for (int k = 0; k <= 255; k++) {
            LUT[k] = (int) (Math.log(1 + k) * 255 / Math.log(256));
        }

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = ImageArray1[x][y][1]; //r
                int g = ImageArray1[x][y][2]; //g
                int b = ImageArray1[x][y][3]; //b


                ImageArray2[x][y][1] = LUT[r]; //r
                ImageArray2[x][y][2] = LUT[g]; //g
                ImageArray2[x][y][3] = LUT[b]; //b
            }
        }

        return convertToBimage(ImageArray2);
    }

    // bit-plane slicing
    public BufferedImage Bit_planeSlicing(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();
        int k = 6; // bit number set between  1-7
        //int k = 1;
        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray1 = convertToArray(timg);

        //for (k = 1; k < 8; k++) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int r = ImageArray[x][y][1]; //r
                int g = ImageArray[x][y][2]; //g
                int b = ImageArray[x][y][3]; //b

                ImageArray1[x][y][1] = (int) (((r >> k) & 1) * 255);
                ImageArray1[x][y][2] = (int) (((g >> k) & 1) * 255);
                ImageArray1[x][y][3] = (int) (((b >> k) & 1) * 255);  // check lecture recording to fix this code
            }
        }

        //img =  convertToBimage(ImageArray1);

        return convertToBimage(ImageArray1);
    }


    // LAB 5
    // Finding Histogram
    public BufferedImage Histogram(BufferedImage timg,BufferedImage timg1) {


        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg);

        float[] HistgramR = new float[256];
        float[] HistgramB = new float[256];
        float[] HistgramG = new float[256];

        for (int k = 0; k <= 255; k++) {
            HistgramR[k] = 0;
            HistgramG[k] = 0;
            HistgramB[k] = 0;
        }

        //int a = 255;

        int a = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int r = ImageArray[x][y][1];
                int g = ImageArray[x][y][2];
                int b = ImageArray[x][y][3];

                HistgramR[r]++;
                HistgramG[g]++;
                HistgramB[b]++;
            }
            a++;
        }

        System.out.println(a);

        System.out.println("Pixel count for Red, Green and Blue Channel respectively");
        for (int z = 0; z < HistgramR.length; z++) {


            System.out.println(z + "\t" + HistgramR[z] + "\t" + HistgramG[z] + "\t" + HistgramB[z]);
            System.out.println();
        }
        System.out.println();


     // Histogram Normalisation
        for (int x = 0; x < HistgramR.length; x++) {

            HistgramR[x] = HistgramR[x] / (a*a);
            HistgramG[x] = HistgramG[x] / (a*a);
            HistgramB[x] = HistgramB[x] / (a*a);
        }

        System.out.println("Normalised Pixel count for Red, Green and Blue Channel respectively");
        for (int y = 0; y < HistgramR.length; y++) {


            System.out.println(y + "\t" + HistgramR[y] + "\t" + HistgramG[y] + "\t" + HistgramB[y]);
            System.out.println();
        }

        BufferedImage resizedImg = resizeImg(timg1, 512, 512);

        int[][][] ImageArray1 = convertToArray(resizedImg);

        return convertToBimage(ImageArray1);
    }

    private static BufferedImage resizeImg(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }


    //Histogram Equalisation
    public BufferedImage HistogramEqualisation(BufferedImage timg, BufferedImage timg1 ){

        // Histogram
        int width = timg.getWidth();
        int height = timg.getHeight();

        int[][][] ImageArray = convertToArray(timg);
        int[][][] ImageArray2 = convertToArray(timg1);

        float[] HistgramR = new float[256];
        float[] HistgramB = new float[256];
        float[] HistgramG = new float[256];

        for (int k = 0; k <= 255; k++) {
            HistgramR[k] = 0;
            HistgramG[k] = 0;
            HistgramB[k] = 0;
        }

        int a = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int r = ImageArray[x][y][1];
                int g = ImageArray[x][y][2];
                int b = ImageArray[x][y][3];

                HistgramR[r]++;
                HistgramG[g]++;
                HistgramB[b]++;
            }
            a++;
        }
        //System.out.println("value for a " + a);
        //Histogram Normalisation

        for (int x = 0; x < HistgramR.length; x++) {

            HistgramR[x] = HistgramR[x] / (a*a);
            HistgramG[x] = HistgramG[x] / (a*a);
            HistgramB[x] = HistgramB[x] / (a*a);
        }

        for (int y = 0; y < HistgramR.length; y++) {


            //System.out.println(y + "\t" + HistgramR[y] + "\t" + HistgramG[y] + "\t" + HistgramB[y]);
            System.out.println();
        }

        for (int z = 1; z < HistgramR.length; z++){
            HistgramR[z] = HistgramR[z] + HistgramR[z-1];
            HistgramG[z] = HistgramG[z] + HistgramG[z-1];
            HistgramB[z] = HistgramB[z] + HistgramB[z-1];
        }

        for (int y = 0; y < HistgramR.length; y++) {


           //System.out.println(y + "\t" + HistgramR[y] + "\t" + HistgramG[y] + "\t" + HistgramB[y]);
            //System.out.println();
        }
        int L = 256;

        for (w = 0; w < HistgramR.length; w++) {
            HistgramR[w] = HistgramR[w] * (L-1);
            HistgramG[w] = HistgramG[w] * (L-1);
            HistgramB[w] = HistgramB[w] * (L-1);

        }

        for (int f = 0; f < HistgramR.length; f++) {


            System.out.println(f + "\t" + HistgramR[f] + "\t" + HistgramG[f] + "\t" + HistgramB[f]);
            System.out.println();
        }

        return convertToBimage(ImageArray2);
    }
    // LAB 6
    // Averging
    public BufferedImage Averaging(BufferedImage timg){

      int width = timg.getWidth();
      int height = timg.getHeight();

        double [][][] ImageArray1 = convertToArray1(timg);
        double [][][] ImageArray2 = convertToArray1(timg);
        int [][][] ImageArray3 = convertToArray(timg);


        // Mask for avergainig = 1/9 [1 1 1 ; 1 1 1; 1 1 1 ]
      double [][]temp_mask = {
        {1,1,1},
        {1,1,1},
        {1,1,1}
      };

      // for (int i = 0; i < mask.length; ++i) {
            // System.out.println(Arrays.toString(mask));
       //}
       double sum = 0;
      System.out.println();
      for (int i = 0; i < temp_mask.length; ++i) {
        for(int j = 0; j < temp_mask[i].length; ++j) {
          sum += temp_mask[j][i];
        }
        System.out.println();
      }
      System.out.println("sum = " + sum);

      double [][]mask = temp_mask;
      System.out.println();
      System.out.println("Averaging mask of 3*3 array");
      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
         mask[i][j] = mask[i][j]/sum;
       }
      }

      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
        System.out.print(mask[i][j]+ " ");
       }
       System.out.println();
      }
      System.out.println();

      //for Mask of size 3x3
      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){
          // //r = 0; g = 0; b = 0;
          // // int r = ImageArray1[x][y][1]; //r
          // int g = ImageArray1[x][y][2]; //g
          // int b = ImageArray1[x][y][3]; //b

          double r = 0; double g = 0; double b = 0;
          for(int s=0; s<=2; s++){
            for(int t=0; t<=2; t++){
              r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
              g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
              b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
            }
          }
          ImageArray2[x][y][1] = r; //r
          ImageArray2[x][y][2] = g; //g
          ImageArray2[x][y][3] = b; //b

          ImageArray3[x][y][1]= (int) ImageArray2[x][y][1];
          ImageArray3[x][y][2]= (int) ImageArray2[x][y][2];
          ImageArray3[x][y][3]= (int) ImageArray2[x][y][3];
        }
      }

    return convertToBimage(ImageArray3);
     //return null;
    }

    //weighted Averaging
    public BufferedImage WeightedAveraging(BufferedImage timg){
      int width = timg.getWidth();
      int height = timg.getHeight();

      double [][][] ImageArray1 = convertToArray1(timg);
      double [][][] ImageArray2 = convertToArray1(timg);
      int [][][] ImageArray3 = convertToArray(timg);

        // Mask for avergainig = 1/16 [1 2 1 ; 2 4 2; 1 2 1 ]
        double [][]temp_mask = {
          {1,2,1},
          {2,4,2},
          {1,2,1}
        };


      double sum = 0;
      System.out.println();

      for (int i = 0; i < temp_mask.length; ++i) {
       for(int j = 0; j < temp_mask[i].length; ++j) {
         System.out.print(temp_mask[i][j]+ " ");
         sum += temp_mask[j][i];
       }
       System.out.println();
      }
      System.out.println("sum = " + sum);

      double [][]mask = temp_mask;

      System.out.println();
      System.out.println("Weighted Averaging mask of 3*3 array");
      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
         mask[i][j] = mask[i][j]/sum;
       }
      }

      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
        System.out.print(mask[i][j]+ " ");
       }
       System.out.println();
     }
      System.out.println();
      //for Mask of size 3x3
      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){

          double r = 0; double g = 0; double b = 0;
          for(int s=0; s<=2; s++){
            for(int t=0; t<=2; t++){
              r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
              g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
              b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
            }
          }
          ImageArray2[x][y][1] = r; //r
          ImageArray2[x][y][2] = g; //g
          ImageArray2[x][y][3] = b; //b

          ImageArray3[x][y][1]= (int) ImageArray2[x][y][1];
          ImageArray3[x][y][2]= (int) ImageArray2[x][y][2];
          ImageArray3[x][y][3]= (int) ImageArray2[x][y][3];
        }
      }
    return convertToBimage(ImageArray3);
    }

    // 4NLaplacian
    public BufferedImage fourNeighbourLaplacian(BufferedImage timg){
      int width = timg.getWidth();
      int height = timg.getHeight();

      BufferedImage timg3 = GreayscaleImg(timg);

      int [][][] ImageArray1 = convertToArray(timg3);

      int [][][] ImageArray2 = convertToArray(timg);
      int [][][] ImageArray3 = convertToArray(timg);

      // Mask for convolution =  [0 -1 0; -1 4 -1; 0 -1 0]
      int [][]mask = {
        {0,-1,0},
        {-1,4,-1},
        {0,-1,0}
      };
      System.out.println();
      System.out.println("4-Neighbout Laplacian mask of 3*3 array");
      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
         System.out.print(mask[i][j]+ " ");
       }
       System.out.println();
      }
      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){

          int r = 0; int g = 0; int b = 0;
          for(int s=0; s<=2; s++){
            for(int t=0; t<=2; t++){
              r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
              g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
              b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
            }
          }

          ImageArray2[x][y][1] = r; //r
          ImageArray2[x][y][2] = g; //g
          ImageArray2[x][y][3] = b; //b
        }
       }
      // for(int y=1; y<height-1; y++){
      //   for(int x=1; x<width-1; x++){
      //     System.out.println(ImageArray2[x][y][1]);
      //   }
      // }
        //removing negative pixel values from image
      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){
          ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
          ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
          ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
        }
      }
      // for(int y=1; y<height-1; y++){
      //   for(int x=1; x<width-1; x++){
      //     System.out.println(ImageArray3[x][y][1]);
      //   }
      // }
      return convertToBimage(ImageArray3);
    }

    // 8NLaplacian
    public BufferedImage eightNeighbourLaplacian(BufferedImage timg){
      int width = timg.getWidth();
      int height = timg.getHeight();

      BufferedImage timg3 = GreayscaleImg(timg);

      int [][][] ImageArray1 = convertToArray(timg3);

      int [][][] ImageArray2 = convertToArray(timg);
      int [][][] ImageArray3 = convertToArray(timg);

      // Mask for convolution =  [-1 -1 -1; -1 8 -1; -1 -1 -1]
      int [][]mask = {
        {-1,-1,-1},
        {-1,8,-1},
        {-1,-1,-1}
      };
      System.out.println();
      System.out.println("8-Neighbout Laplacian mask of 3*3 array");
      for (int i = 0; i < mask.length; ++i) {
       for(int j = 0; j < mask[i].length; ++j) {
         System.out.print(mask[i][j]+ " ");
       }
       System.out.println();
      }
      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){

          int r = 0; int g = 0; int b = 0;
          for(int s=0; s<=2; s++){
            for(int t=0; t<=2; t++){
              r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
              g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
              b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
            }
          }

          ImageArray2[x][y][1] = r; //r
          ImageArray2[x][y][2] = g; //g
          ImageArray2[x][y][3] = b; //b
        }
       }

      for(int y=1; y<height-1; y++){
        for(int x=1; x<width-1; x++){
          ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
          ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
          ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
        }
      }
      return convertToBimage(ImageArray3);
    }
    //4N Laplacian Enhancement
    public BufferedImage fourNeighbourLaplacianEnhancement(BufferedImage timg){
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int [][][] ImageArray1 = convertToArray(timg3);

        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);

        // Mask for convolution =  [0 -1 0; -1 5 -1; 0 -1 0]
        int [][]mask = {
                {0,-1,0},
                {-1,5,-1},
                {0,-1,0}
        };
        System.out.println();
        System.out.println("4-Neighbout Laplacian Enhancemnet mask of 3*3 array");
        for (int i = 0; i < mask.length; ++i) {
            for(int j = 0; j < mask[i].length; ++j) {
                System.out.print(mask[i][j]+ " ");
            }
            System.out.println();
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
                        g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
                        b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
                    }
                }

                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b
            }
        }
        // for(int y=1; y<height-1; y++){
        //   for(int x=1; x<width-1; x++){
        //     System.out.println(ImageArray2[x][y][1]);
        //   }
        // }

        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){
                ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
                ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
                ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
            }
        }
        // for(int y=1; y<height-1; y++){
        //   for(int x=1; x<width-1; x++){
        //     System.out.println(ImageArray3[x][y][1]);
        //   }
        // }
        return convertToBimage(ImageArray3);
    }
    //8N Laplacian Enhancement
    public BufferedImage eightNeighbourLaplacianEnhancement(BufferedImage timg){
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int [][][] ImageArray1 = convertToArray(timg3);

        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);

        // Mask for convolution =  [-1 -1 -1; -1 9 -1; -1 -1 -1]
        int [][]mask = {
                {-1,-1,-1},
                {-1,9,-1},
                {-1,-1,-1}
        };
        System.out.println();
        System.out.println("8-Neighbout Laplacian Enhancement mask of 3*3 array");
        for (int i = 0; i < mask.length; ++i) {
            for(int j = 0; j < mask[i].length; ++j) {
                System.out.print(mask[i][j]+ " ");
            }
            System.out.println();
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
                        g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
                        b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
                    }
                }

                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b
            }
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){
                ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
                ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
                ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
            }
        }

        return convertToBimage(ImageArray3);
    }

    //Roberts
    public BufferedImage Roberts(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int[][][] ImageArray1 = convertToArray(timg3);

        int[][][] ImageArray2 = convertToArray(timg);
        int[][][] ImageArray3 = convertToArray(timg);
        int[][][] ImageArray4 = convertToArray(timg);

        // Mask for convolution =  [0 0 0; 0 0 -1; 0 0 0]
        int[][] mask = {
                {0, 0, 0},
                {0, 0, -1},
                {0, 0, 0}
        };
        // Mask1 for convolution = [0 0 0; 0 -1 0; 0 0 0]
        int [][] mask1 = {
                {0, 0, 0},
                {0, -1, 0},
                {0, 0, 0}
        };
        System.out.println();
            System.out.println("Roberts mask1 of 3*3 array");
            for (int i = 0; i < mask.length; ++i) {
                for (int j = 0; j < mask[i].length; ++j) {
                    System.out.print(mask[i][j] + " ");

                }
                System.out.println();
            }
        System.out.println();
        System.out.println("Roberts mask2 of 3*3 array");
        for (int i = 0; i < mask.length; ++i) {
            for (int j = 0; j < mask[i].length; ++j) {
                System.out.print(mask1[i][j] + " ");
            }
            System.out.println();
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
                        g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
                        b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
                    }
                }

                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b
            }
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask1[s][t]*ImageArray2[x-s+1][y-t+1][1];
                        g = g + mask1[s][t]*ImageArray2[x-s+1][y-t+1][2];
                        b = b + mask1[s][t]*ImageArray2[x-s+1][y-t+1][3];
                    }
                }

                ImageArray3[x][y][1] = r; //r
                ImageArray3[x][y][2] = g; //g
                ImageArray3[x][y][3] = b; //b
            }
        }
        return convertToBimage(ImageArray3);
    }

    //sobel X
    public BufferedImage SobelX(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int[][][] ImageArray1 = convertToArray(timg3);
        int[][][] ImageArray2 = convertToArray(timg);
        int[][][] ImageArray3 = convertToArray(timg);

        // Mask for convolution =  [-1 0 1; -2 0 2; -1 0 1]
        int [][]mask = {
                {-1,0,1},
                {-2,0,2},
                {-1,0,1}
        };
        System.out.println();
        System.out.println("SobelX mask of 3*3 array");
        for (int i = 0; i < mask.length; ++i) {
            for (int j = 0; j < mask[i].length; ++j) {
                System.out.print(mask[i][j] + " ");

            }
            System.out.println();
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
                        g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
                        b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
                    }
                }

                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b
            }
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){
                ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
                ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
                ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
            }
        }
        return convertToBimage(ImageArray3);

    }

    //sobel Y
    public BufferedImage SobelY(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int[][][] ImageArray1 = convertToArray(timg3);
        int[][][] ImageArray2 = convertToArray(timg);
        int[][][] ImageArray3 = convertToArray(timg);

        // Mask for convolution =  [-1 -2 -1; 0 0 0; 1 2 1]
        int [][]mask = {
                {-1,-2,-1},
                {0,0,0},
                {1,2,1}
        };
        System.out.println();
        System.out.println("SobelY mask of 3*3 array");
        for (int i = 0; i < mask.length; ++i) {
            for (int j = 0; j < mask[i].length; ++j) {
                System.out.print(mask[i][j] + " ");

            }
            System.out.println();
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){

                int r = 0; int g = 0; int b = 0;
                for(int s=0; s<=2; s++){
                    for(int t=0; t<=2; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+1][y-t+1][1];
                        g = g + mask[s][t]*ImageArray1[x-s+1][y-t+1][2];
                        b = b + mask[s][t]*ImageArray1[x-s+1][y-t+1][3];
                    }
                }

                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b
            }
        }
        for(int y=1; y<height-1; y++){
            for(int x=1; x<width-1; x++){
                ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
                ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
                ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
            }
        }
        return convertToBimage(ImageArray3);

    }

    //Gaussian
    public BufferedImage Gaussian(BufferedImage timg) {

            int width = timg.getWidth();
            int height = timg.getHeight();

            double[][][] ImageArray1 = convertToArray1(timg);
            double[][][] ImageArray2 = convertToArray1(timg);
            int[][][] ImageArray3 = convertToArray(timg);

            // Mask for convolution =  [1 4 7 4 1; 4 16 26 16 4; 7 26 41 26 7; 4 16 26 16 4; 1 4 7 4 1]
            double[][] temp_mask = {
                    {1, 4, 7, 4, 1},
                    {4, 16, 26, 16, 4},
                    {7, 26, 41, 26, 7},
                    {4, 16, 26, 16, 4},
                    {1, 4, 7, 4, 1},
            };

            double sum = 0;
            System.out.println();

            for (int i = 0; i < temp_mask.length; ++i) {
                for (int j = 0; j < temp_mask[i].length; ++j) {
                    System.out.print(temp_mask[i][j] + " ");
                    sum += temp_mask[j][i];
                }
                System.out.println();
            }
        System.out.println(" ");
            System.out.println("sum = " + sum);

            double[][] mask = temp_mask;

            System.out.println();
            System.out.println("Gaussian mask of 5*5 array");
            for (int i = 0; i < mask.length; ++i) {
                for (int j = 0; j < mask[i].length; ++j) {
                    mask[i][j] = mask[i][j] / sum;
                }
            }

            for (int i = 0; i < mask.length; ++i) {
                for (int j = 0; j < mask[i].length; ++j) {
                    System.out.print(mask[i][j] + " ");
                }
                System.out.println();
            }
        //for Mask of size 5x5
        for(int y=2; y<height-2; y++){
            for(int x=2; x<width-2; x++){
                // //r = 0; g = 0; b = 0;
                // // int r = ImageArray1[x][y][1]; //r
                // int g = ImageArray1[x][y][2]; //g
                // int b = ImageArray1[x][y][3]; //b

                double r = 0; double g = 0; double b = 0;
                for(int s=0; s<=4; s++){
                    for(int t=0; t<=4; t++){
                        r = r + mask[s][t]*ImageArray1[x-s+2][y-t+2][1];
                        g = g + mask[s][t]*ImageArray1[x-s+2][y-t+2][2];
                        b = b + mask[s][t]*ImageArray1[x-s+2][y-t+2][3];
                    }
                }
                ImageArray2[x][y][1] = r; //r
                ImageArray2[x][y][2] = g; //g
                ImageArray2[x][y][3] = b; //b

                ImageArray3[x][y][1]= (int) ImageArray2[x][y][1];
                ImageArray3[x][y][2]= (int) ImageArray2[x][y][2];
                ImageArray3[x][y][3]= (int) ImageArray2[x][y][3];
            }
        }

        return convertToBimage(ImageArray3);
    }
   // Laplacian of Gaussina (LoG)
   public BufferedImage LoG(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        BufferedImage timg3 = GreayscaleImg(timg);

        int [][][] ImageArray1 = convertToArray(timg3);
        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);

       // Mask for convolution =  [0 0 -1 0 0; 0 -1 -2 -1 0; -1 -2 16 -2 -1; 0 -1 -2 -1 0; 0 0 -1 0 0]
       int[][] mask = {
               {0, 0, -1, 0, 0},
               {0, -1, -2, -1, 0},
               {-1, -2, 16, -2, -1},
               {0, -1, -2, -1, 0},
               {0, 0, -1, 0, 0},
       };
       System.out.println();
       System.out.println("Laplacian of Gaussian mask of 5*5 array");
       for (int i = 0; i < mask.length; ++i) {
           for (int j = 0; j < mask[i].length; ++j) {
               System.out.print(mask[i][j] + " ");
           }
           System.out.println();
       }

       //for Mask of size 5x5
       for(int y=2; y<height-2; y++){
           for(int x=2; x<width-2; x++){
               // //r = 0; g = 0; b = 0;
               // // int r = ImageArray1[x][y][1]; //r
               // int g = ImageArray1[x][y][2]; //g
               // int b = ImageArray1[x][y][3]; //b

               int r = 0; int g = 0; int b = 0;
               for(int s=0; s<=4; s++){
                   for(int t=0; t<=4; t++){
                       r = r + mask[s][t]*ImageArray1[x-s+2][y-t+2][1];
                       g = g + mask[s][t]*ImageArray1[x-s+2][y-t+2][2];
                       b = b + mask[s][t]*ImageArray1[x-s+2][y-t+2][3];
                   }
               }
               ImageArray2[x][y][1] = r; //r
               ImageArray2[x][y][2] = g; //g
               ImageArray2[x][y][3] = b; //b
           }
       }
       for(int y=1; y<height-1; y++){
           for(int x=1; x<width-1; x++){
               ImageArray3[x][y][1] = Math.max(ImageArray2[x][y][1],0);
               ImageArray3[x][y][2] = Math.max(ImageArray2[x][y][2],0);
               ImageArray3[x][y][3] = Math.max(ImageArray2[x][y][3],0);
           }
       }
       return convertToBimage(ImageArray3);
   }
    // lab 7
    // salt n pepper noise image
    public BufferedImage saltnpepper(BufferedImage timg) {

        int[][][] ImageArray1 = convertToArray(timg);

        return convertToBimage(ImageArray1);
    }

    // min Filtering
    public BufferedImage minFiltering(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int [][][] ImageArray1 = convertToArray(timg);
        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);


            for(int y=1; y<height-1; y++) {
                for (int x = 1; x < width-1; x++) {

                    int []a = {ImageArray1[x-1][y-1][1], ImageArray1[x-1][y][1], ImageArray1[x-1][y+1][1], ImageArray1[x][y-1][1], ImageArray1[x][y][1], ImageArray1[x][y+1][1], ImageArray1[x+1][y-1][1], ImageArray1[x+1][y][1], ImageArray1[x+1][y+1][1]};
                    int []b = {ImageArray1[x-1][y-1][2], ImageArray1[x-1][y][2], ImageArray1[x-1][y+1][2], ImageArray1[x][y-1][2], ImageArray1[x][y][2], ImageArray1[x][y+1][2], ImageArray1[x+1][y-1][2], ImageArray1[x+1][y][2], ImageArray1[x+1][y+1][2]};
                    int []c = {ImageArray1[x-1][y-1][3], ImageArray1[x-1][y][3], ImageArray1[x-1][y+1][3], ImageArray1[x][y-1][3], ImageArray1[x][y][3], ImageArray1[x][y+1][3], ImageArray1[x+1][y-1][3], ImageArray1[x+1][y][3], ImageArray1[x+1][y+1][3]};

                    Arrays.sort (a);
                    Arrays.sort (b);
                    Arrays.sort (c);

                    ImageArray2[x][y][1] = a[0];
                    ImageArray2[x][y][2] = b[0];
                    ImageArray2[x][y][3] = c[0];
                }
            }
        return convertToBimage(ImageArray2);
    }

    // max Filtering
    public BufferedImage maxFiltering(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int [][][] ImageArray1 = convertToArray(timg);
        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);

        for(int y=1; y<height-1; y++) {
            for (int x = 1; x < width-1; x++) {

                int []a = {ImageArray1[x-1][y-1][1], ImageArray1[x-1][y][1], ImageArray1[x-1][y+1][1], ImageArray1[x][y-1][1], ImageArray1[x][y][1], ImageArray1[x][y+1][1], ImageArray1[x+1][y-1][1], ImageArray1[x+1][y][1], ImageArray1[x+1][y+1][1]};
                int []b = {ImageArray1[x-1][y-1][2], ImageArray1[x-1][y][2], ImageArray1[x-1][y+1][2], ImageArray1[x][y-1][2], ImageArray1[x][y][2], ImageArray1[x][y+1][2], ImageArray1[x+1][y-1][2], ImageArray1[x+1][y][2], ImageArray1[x+1][y+1][2]};
                int []c = {ImageArray1[x-1][y-1][3], ImageArray1[x-1][y][3], ImageArray1[x-1][y+1][3], ImageArray1[x][y-1][3], ImageArray1[x][y][3], ImageArray1[x][y+1][3], ImageArray1[x+1][y-1][3], ImageArray1[x+1][y][3], ImageArray1[x+1][y+1][3]};

                Arrays.sort (a);
                Arrays.sort (b);
                Arrays.sort (c);

                int l = a.length;

                ImageArray2[x][y][1] = a[l-1];
                ImageArray2[x][y][2] = b[l-1];
                ImageArray2[x][y][3] = c[l-1];
            }
        }
        return convertToBimage(ImageArray2);
    }

    // midpoint Filtering
    public BufferedImage maidpointFiltering(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int [][][] ImageArray1 = convertToArray(timg);
        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);


        for(int y=1; y<height-1; y++) {
            for (int x = 1; x < width-1; x++) {

                int []a = {ImageArray1[x-1][y-1][1], ImageArray1[x-1][y][1], ImageArray1[x-1][y+1][1], ImageArray1[x][y-1][1], ImageArray1[x][y][1], ImageArray1[x][y+1][1], ImageArray1[x+1][y-1][1], ImageArray1[x+1][y][1], ImageArray1[x+1][y+1][1]};
                int []b = {ImageArray1[x-1][y-1][2], ImageArray1[x-1][y][2], ImageArray1[x-1][y+1][2], ImageArray1[x][y-1][2], ImageArray1[x][y][2], ImageArray1[x][y+1][2], ImageArray1[x+1][y-1][2], ImageArray1[x+1][y][2], ImageArray1[x+1][y+1][2]};
                int []c = {ImageArray1[x-1][y-1][3], ImageArray1[x-1][y][3], ImageArray1[x-1][y+1][3], ImageArray1[x][y-1][3], ImageArray1[x][y][3], ImageArray1[x][y+1][3], ImageArray1[x+1][y-1][3], ImageArray1[x+1][y][3], ImageArray1[x+1][y+1][3]};

                Arrays.sort (a);
                Arrays.sort (b);
                Arrays.sort (c);

                int l = a.length;

                ImageArray2[x][y][1] = (a[0]+a[l-1])/2;
                ImageArray2[x][y][2] = (b[0]+b[l-1])/2;
                ImageArray2[x][y][3] = (c[0]+c[l-1])/2;
            }
        }
        return convertToBimage(ImageArray2);
    }

    // median Filtering
    public BufferedImage medianFiltering(BufferedImage timg) {
        int width = timg.getWidth();
        int height = timg.getHeight();

        int [][][] ImageArray1 = convertToArray(timg);
        int [][][] ImageArray2 = convertToArray(timg);
        int [][][] ImageArray3 = convertToArray(timg);


        for(int y=1; y<height-1; y++) {
            for (int x = 1; x < width-1; x++) {

                int []a = {ImageArray1[x-1][y-1][1], ImageArray1[x-1][y][1], ImageArray1[x-1][y+1][1], ImageArray1[x][y-1][1], ImageArray1[x][y][1], ImageArray1[x][y+1][1], ImageArray1[x+1][y-1][1], ImageArray1[x+1][y][1], ImageArray1[x+1][y+1][1]};
                int []b = {ImageArray1[x-1][y-1][2], ImageArray1[x-1][y][2], ImageArray1[x-1][y+1][2], ImageArray1[x][y-1][2], ImageArray1[x][y][2], ImageArray1[x][y+1][2], ImageArray1[x+1][y-1][2], ImageArray1[x+1][y][2], ImageArray1[x+1][y+1][2]};
                int []c = {ImageArray1[x-1][y-1][3], ImageArray1[x-1][y][3], ImageArray1[x-1][y+1][3], ImageArray1[x][y-1][3], ImageArray1[x][y][3], ImageArray1[x][y+1][3], ImageArray1[x+1][y-1][3], ImageArray1[x+1][y][3], ImageArray1[x+1][y+1][3]};

                Arrays.sort (a);
                Arrays.sort (b);
                Arrays.sort (c);

                int l = a.length;

                // since l = odd number, median = a[l-1/2]

                int m = (l-1)/2;

                ImageArray2[x][y][1] = a[m];
                ImageArray2[x][y][2] = b[m];
                ImageArray2[x][y][3] = c[m];
            }
        }
        return convertToBimage(ImageArray2);
    }

    // Lab 8, the code is to only display image, refer to readme.txt and ImagePro.py file for actual code
    //simple thresholding
    public BufferedImage simpleThresholding(BufferedImage timg) {

        int[][][] ImageArray1 = convertToArray(timg);

        return convertToBimage(ImageArray1);
    }
    // automated thresholding
    public BufferedImage automatedThresholding(BufferedImage timg) {

        int[][][] ImageArray1 = convertToArray(timg);

        return convertToBimage(ImageArray1);
    }
    // adaptive thresholding
    public BufferedImage adaptiveThresholding(BufferedImage timg) {

        int[][][] ImageArray1 = convertToArray(timg);

        return convertToBimage(ImageArray1);
    }

    //************************************
    //  You need to register your functioin here
    //************************************
    public void filterImage() {

        if (opIndex == lastOp) {
            return;
        }

        lastOp = opIndex;
        switch (opIndex) {
            case 0:
                biFiltered = bi; /* original */
                return;
            case 1:
                biFiltered = ImageNegative(bi); /* Image Negative */
                return;

            case 2:
                biFiltered = RescaleImage(bi);
                return;

            case 3:
                biFiltered = ShiftImage(bi);
                return;

            case 4:
                biFiltered = RescaleShiftImage(bi);
                return;

            case 5:
                biFiltered = Add(bi, bi1);
                return;

            case 6:
                biFiltered = Subtract(bi, bi1);
                return;

            case 7:
                biFiltered = Multiply(bi, bi1);
                return;

            case 8:
                biFiltered = Divide(bi, bi1);
                return;

            case 9:
                biFiltered = NOT(bi);
                return;

            case 10:
                biFiltered = AND(bi, bi1);
                return;

            case 11:
                biFiltered = OR(bi, bi1);
                return;

            case 12:
                biFiltered = XOR(bi, bi1);
                return;

            case 13:
                biFiltered = ROI(bi);
                return;

            case 14:
                biFiltered = Negative_Linear(bi);
                return;

            case 15:
                biFiltered= Logarithmic_function(bi);
                return;

            case 16:
                biFiltered = Power_Law(bi);
                return;

            case 17:
                biFiltered = LUT(bi);
                return;

            case 18:
                biFiltered = Bit_planeSlicing(bi);
                return;

            case 19:
                biFiltered = Histogram(bi1,bi2);
                return;

            case 20:
                biFiltered = HistogramEqualisation(bi,bi3);
                return;

            case 21:
                biFiltered = Averaging(bi1);
                return;

            case 22:
                biFiltered = WeightedAveraging(bi1);
                return;

            case 23:
                biFiltered = fourNeighbourLaplacian(bi1);
                return;

            case 24:
                biFiltered= eightNeighbourLaplacian(bi1);
                return;

            case 25:
                biFiltered = fourNeighbourLaplacianEnhancement(bi1);
                return;

            case 26:
                biFiltered = eightNeighbourLaplacianEnhancement(bi1);
                return;

            case 27:
                biFiltered = Roberts(bi1);
                return;

            case 28:
                biFiltered = SobelX(bi1);
                return;

            case 29:
                biFiltered = SobelY(bi1);
                return;

            case 30:
                biFiltered = Gaussian(bi1);
                return;

            case 31:
                biFiltered = LoG (bi1);
                return;

            case 32:
                biFiltered = saltnpepper(bi4);
                return;

            case 33:
                biFiltered = minFiltering(bi4);
                return;

            case 34:
                biFiltered = maxFiltering(bi4);
                return;

            case 35:
                biFiltered = maidpointFiltering(bi4);
                return;

            case 36:
                biFiltered = medianFiltering(bi4);
                return;

            case 37:
                biFiltered = simpleThresholding(bi5);
                return;

            case 38:
                biFiltered = automatedThresholding(bi6);
                return;

            case 39:
                biFiltered = adaptiveThresholding(bi7);
                return;
        }
    }







     public void actionPerformed(ActionEvent e) {
         JComboBox cb = (JComboBox)e.getSource();
         if (cb.getActionCommand().equals("SetFilter")) {
             setOpIndex(cb.getSelectedIndex());
             repaint();
         } else if (cb.getActionCommand().equals("Formats")) {
             String format = (String)cb.getSelectedItem();
             File saveFile = new File("savedimage."+format);
             JFileChooser chooser = new JFileChooser();
             chooser.setSelectedFile(saveFile);
             int rval = chooser.showSaveDialog(cb);
             if (rval == JFileChooser.APPROVE_OPTION) {
                 saveFile = chooser.getSelectedFile();
                 try {
                     ImageIO.write(biFiltered, format, saveFile);
                 } catch (IOException ex) {
                 }
             }
         }
    };

    public static void main(String s[]) {
        JFrame f = new JFrame("Image Processing Demo");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });
        Demo de = new Demo();
        f.add("Center", de);
        JComboBox choices = new JComboBox(de.getDescriptions());
        choices.setActionCommand("SetFilter");
        choices.addActionListener(de);
        JComboBox formats = new JComboBox(de.getFormats());
        formats.setActionCommand("Formats");
        formats.addActionListener(de);
        JPanel panel = new JPanel();
        panel.add(choices);
        panel.add(new JLabel("Save As"));
        panel.add(formats);
        f.add("North", panel);
        f.pack();
        f.setVisible(true);
    }
}
