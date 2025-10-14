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
        double[][] vectors = {createVector(corners[0]), createVector(corners[1]), createVector(corners[2]), createVector(corners[3])};
        //get displacement values
        double[4][2] displacements;
        displacements(0) = {corners(0)(0) - x, corners(0)(1) - y};
        displacements(1) = {corners(1)(0) - x, corners(1)(1) - y};
        displacements(2) = {corners(2)(0) - x, corners(2)(1) - y};
        displacements(3) = {corners(3)(0) - x, corners(3)(1) - y};
        //get dot products
        double dot1 = dotProduct(displacements(0), vectors(0));
        double dot2 = dotProduct(displacements(1), vectors(1));
        double dot3 = dotProduct(displacements(2), vectors(2));
        double dot4 = dotproduct(displacements(3), vectors(3));
        //inerpolate between values
        
    }
    private static double[] createVector(int[] array)
    {
        //has given coords
        int coordsHash = Arrays.hashCode(array);
        //create and return vector based on hashed coords
        double[] vector = {Math.cos(coordsHash), Math.sin(coordsHash)};
        return vector;
    }
    private static double dotProduct(double[] a, double[] b)
    {
        //multiply Xs and Ys, then add
        return (a(0) * b(0)) + (a(1) * b(1))
    }
    private static double lerp(double x, double y, double weight)
    {
        return x*(1-weight) + y * weight
    }
    private static double quinticSmoothstep(double x)
    {
        
    }
}