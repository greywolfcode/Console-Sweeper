//import standard libraries
import java.util.Arrays;

public class PerlinNoise 
{
    //static class
    private PerlinNoise()
    {
    }
    public static double noise2D(double x, double y)
    {
        //get corners
        double s = Math.floor(x);
        double t = Math.floor(y);
        //normalize corners to be between 0 and 1
        double xRelative = x - s;
        double yRelative = y - t;
        //calculate the corner values 
        double[] bottomLeft ={s, t};
        double[] bottomRight = {s + 1, t};
        double[] topLeft = {s, t + 1};
        double[] topRight = {s + 1, t + 1};
        //convert corners into gradient vectors
        double[] vectorBottomLeft = createVector(bottomLeft);
        double[] vectorBottomRight = createVector(bottomRight);
        double[] vectorTopLeft = createVector(topLeft);
        double[] vectorTopRight = createVector(topRight);
        //get displacement values
        double[] deltaBottomLeft = {xRelative, yRelative};
        double[] deltaBottomRight = {xRelative - 1, yRelative};
        double[] deltaTopLeft = {xRelative, yRelative - 1};
        double[] deltaTopRight = {xRelative - 1, yRelative - 1};
        //get dot products
        double dotBottomLeft = dotProduct(deltaBottomLeft, vectorBottomLeft);
        double dotBottomRight = dotProduct(deltaBottomRight, vectorBottomRight);
        double dotTopLeft = dotProduct(deltaTopLeft, vectorTopLeft);
        double dotTopRight = dotProduct(deltaTopRight, vectorTopRight);
        //get weights for top and bottom
        double weightX = quanticSmoothstep(xRelative);
        double weightY = quanticSmoothstep(yRelative);
        //inerpolate between x distances first
        double top = lerp(dotTopLeft, dotTopRight, weightX);
        double bottom = lerp(dotBottomLeft, dotBottomRight, weightY);
        //interpolate over vertical to get final answer
        double middle = lerp(top, bottom, weightY);
        //return final value
        //return middle;
        //convert from range [0, 1] to [-1, 1]
        double newValue = ((middle - (-1)) / (1 - (-1))) * (1 - 0) + 0;
        return newValue;
    }
    private static double[] createVector(double[] array)
    {
        //has given coords
        int coordsHash = Arrays.hashCode(array);
        //create and return vector based on hashed coords
        double[] vector = {Math.cos(coordsHash), Math.sin(coordsHash)};
        //normalize vector
        double magnitude  = Math.sqrt((Math.pow(vector[0], 2) + Math.pow(vector[1], 2)));
        double[] normalizedVector = {vector[0] / magnitude, vector[1] / magnitude};
        return vector;
    }
    private static double dotProduct(double[] a, double[] b)
    {
        //multiply Xs and Ys, then add
        return (a[0] * b[0]) + (a[1] * b[1]);
    }
    private static double lerp(double x, double y, double weight)
    {
        //linear interpolation between two values
        return (x * (1 - weight)) + (y * weight);
    }
    private static double quanticSmoothstep(double x)
    {
        return x * x * x * ( x * (x * 6.0 - 15.0) + 10.0);
    }
}