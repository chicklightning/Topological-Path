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

        int [] incoming = new int[matrix.length];
        int cnt = 0;

        // Count the number of incoming edges incident to each vertex. For sparse
        // graphs, this could be made more efficient by using an adjacency list.
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix.length; j++)
                incoming[j] += (matrix[i][j] ? 1 : 0);

        Queue<Integer> q = new ArrayDeque<Integer>();

        // Any vertex with zero incoming edges is ready to be visited, so add it to
        // the queue.
        for (int i = 0; i < matrix.length; i++)
            if (incoming[i] == 0)
                q.add(i);

        while (!q.isEmpty())
        {
            // Pull a vertex out of the queue and add it to the topological sort.
            int node = q.remove();
            System.out.println(node);

            ////////////////////////////////////////////////////////////////////////
            //
            // Alternatively, you could create an ArrayList (a class member) that
            // contains the names of these courses indexed by the node values, and
            // print them out like so:
            //
            // System.out.println(courseNames.get(node));
            //
            // We did that in class, but I leave it to you as an exercise to
            // replicate that functionality.
            //
            ////////////////////////////////////////////////////////////////////////

            // Count the number of unique vertices we see.
            ++cnt;

            // All vertices we can reach via an edge from the current vertex should
            // have their incoming edge counts decremented. If one of these hits
            // zero, add it to the queue, as it's ready to be included in our
            // topological sort.
            for (int i = 0; i < matrix.length; i++)
                if (matrix[node][i] && --incoming[i] == 0)
                    q.add(i);
        }

        // If we pass out of the loop without including each vertex in our
        // topological sort, we must have a cycle in the graph.
        if (cnt != matrix.length)
            System.out.println("Error: Graph contains a cycle!");

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