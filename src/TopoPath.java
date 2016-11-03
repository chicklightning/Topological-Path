import java.util.*;
import java.io.*;

/**
 * Created by Gabrielle Michaels on 11/3/2016.
 */

public class TopoPath {

    public static boolean hasTopoPath(String filename)
    {
        File graph = new File(filename);
        Scanner in;

        try{
            in = new Scanner(graph);
        }
        catch(FileNotFoundException e)
        {
            return false;
        }

        // first number in the file is the number of vertices
        int vertexNum = in.nextInt();

        

        return true;
    }

    public static double difficultyRating()
    {
        return 3.9;
    }

    public static double hoursSpent()
    {
        return 9.0;
    }

}

// holds a hashset of adjacent values
class Vertex
{
    private boolean visited;
    private HashSet<Vertex> adjacencyList;
    private int inDegree;
    private int outDegree;

    public Vertex()
    {
        visited = false;
        adjacencyList = new HashSet<Vertex>();
        inDegree = 0;
        outDegree = 0;
    }

    public void markVisited()
    {
        visited = true;
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void addAdjacent(Vertex vertex)
    {
        // if it's already there, don't add it again
        if(adjacencyList.contains(vertex))
            return;

        adjacencyList.add(vertex);
    }

    public boolean isAdjacent(Vertex vertex)
    {
        if(adjacencyList.contains(vertex))
            return true;

        return false;
    }

    public void upInDegree()
    {
        inDegree++;
    }

    public void upOutDegree()
    {
        outDegree++;
    }

    public int getInDegree()
    {
        return inDegree;
    }

    public int getOutDegree()
    {
        return outDegree;
    }
}