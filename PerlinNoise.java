//import standard libraries
import java.util.Arrays;

public class PerlinNoise 
{
    //static class
    private PerlinNoise()
    {
    }
    public static int noise2D(int x, int y)
    {
        double s = Math.floor(x);
        double t = Math.floor(y);
        //calculate the corner values 
        int[][] corners = { {s, t}, {s + 1, t}, {s, t + 1}, {s + 1, t + 1}};
        //convert corners into gradient vectors
        double[][] vector= {createVector(corners[0]), createVector(corners[1]), createVector(corners[2]), createVector(corners[3])};
    }
    private static double[] createVector(int[] array)
    {
        //has given coords
        int coordsHash = Arrays.hashCode(array);
        //create and return vector based on hashed coords
        double[] vector = { Math.cos(coordsHash), Math.sin(coordsHash)};
        return vector;
        
    }
}