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
        double s = Math.floor(x);
        double t = Math.floor(y);
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
        double[] deltaBottomLeft = {bottomLeft[0] + x, bottomLeft[1] + y};
        double[] deltaBottomRight = {bottomRight[0] - x, bottomRight[1] + y};
        double[] deltaTopLeft = {topLeft[0] + x, topLeft[1] - y};
        double[] deltaTopRight = {topRight[0] - x, topRight[1] - y};
        //get dot products
        double dotBottomLeft = dotProduct(deltaBottomLeft, vectorBottomLeft);
        double dotBottomRight = dotProduct(deltaBottomRight, vectorBottomRight);
        double dotTopLeft = dotProduct(deltaTopLeft, vectorTopLeft);
        double dotTopRight = dotProduct(deltaTopRight, vectorTopRight);
        //get weights for top and bottom
        double weightX = quanticSmoothstep(x);
        double weightY = quanticSmoothstep(y);
        //inerpolate between x distances first
        double top = lerp(dotTopLeft, dotTopRight, weightX);
        double bottom = lerp(dotBottomLeft, dotBottomRight, weightY);
        //interpolate over vertical to get final answer
        double middle = lerp(top, bottom, weightY);
        //return final value
        return middle;
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