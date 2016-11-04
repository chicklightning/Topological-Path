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
        ArrayList<Vertex> vertices = new ArrayList<Vertex>();

        try{
            in = new Scanner(graph);
        }
        catch(FileNotFoundException e)
        {
            return false;
        }

        // first number in the file is the number of vertices
        int vertexNum = in.nextInt();

        // I know we don't have to account for this, but technically
        // it's true!
        if(vertexNum == 0 || vertexNum == 1)
            return true;

        for(int i = 0; i < vertexNum; i++)
            vertices.add(new Vertex(i + 1));

        int inCount = 0, outCount = 0;
        for(int i = 0; i < vertexNum; i++)
        {
            // the first number on each line is the number of adjacent vertices
            int adjacencyNum = in.nextInt();

            // updates the out degree
            vertices.get(i).upOutDegree(adjacencyNum);
            for(int j = 0; j < adjacencyNum; j++)
            {
                // for the current vertex, update the list of adjacent vertices
                // also updates the in degree for the vertex being added
                int edge = in.nextInt() - 1;
                vertices.get(i).addAdjacent(vertices.get(edge));
                vertices.get(edge).upInDegree();
            }

        }

        // counts the number of vertices with an in/out degree of 0
        // if there is more or less than one of either, there is no valid topopath
        for(Vertex vertex : vertices)
        {
            if(vertex.getOutDegree() == 0)
                outCount++;
            if(vertex.getInDegree() == 0)
                inCount++;

            // if both are true, it's an island and there isn't a path
            // that can reach it no matter what
            if(vertex.getOutDegree() == 0 && vertex.getInDegree() == 0)
                return false;
        }

        if(inCount != 1 || outCount != 1)
            return false;

        // This marks the start of Szumlanski's "toposort.java" code, with my changes
//        ArrayList<Vertex>

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
    private int number; //for testing

    public Vertex(int num)
    {
        visited = false;
        adjacencyList = new HashSet<Vertex>();
        inDegree = 0;
        outDegree = 0;
        number = num;
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

    public HashSet<Vertex> returnAdjacencies()
    {
        return adjacencyList;
    }

    public void upInDegree()
    {
        inDegree++;
    }

    public void upInDegree(int up)
    {
        inDegree = up;
    }

    public void upOutDegree()
    {
        outDegree++;
    }

    public void upOutDegree(int up)
    {
        outDegree = up;
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