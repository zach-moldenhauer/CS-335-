package Assignment01;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class AdjacencyList {
    
	private static LinkedList[] vertices;
	private static int numVertices;
	private static int numEdges;
	private static int directed;
	private static int weighted;
	
	
	public static void main(String[] args) {
		
		File file = new File("AdjacencyInfo.txt");
		Scanner in = new Scanner(System.in);
		int choice = 0;
	    newAdjacencyList(file);

	
		while(choice != 5) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
					+ "Choose one the following options:"
		            + "\n1- Print the adjacency list"
					+ "\n2- BFS"
					+ "\n3- DFS"
					+ "\n4- MST"
					+ "\n5- Exit"
			);
			System.out.print(">>>");
			choice = in.nextInt();
			
			
			if(choice == 1) {
				printAdjacencyList();
				
			} else if(choice == 2) {
				System.out.println("\n Call BFS");
				initBFS(0);
			} else if(choice == 3) {
				System.out.println("\n Call DFS");
				initDFS(0);
			} else if(choice == 4) {
			    System.out.println("MST");
			}
			
				
		}
		
	}
	
	
	public static void sortEdges() {
		Edge[] edges = new Edge[numEdges];
		int pos = 0;
		for(int x = 0; x < numVertices; x++)
		{
			for(int y = 0; y < vertices[x].size(); y++)
			{
				edges[pos] = new Edge(x, vertices[x].getVertex(y), vertices[x].getWeight(y));
				pos++;
			}
		}
		
		edges = mergeEdges(edges);
		
	}
	
	public static Edge[] mergeEdges(Edge[] edge)
	{
		
		if(edge.length <= 1)
		{
			return edge;
		}
		
		Edge[] firstHalf = mergeEdges(Arrays.copyOfRange(edge, 0, edge.length/2));
		Edge[] secondHalf = mergeEdges(Arrays.copyOfRange(edge, edge.length/2, edge.length));
		
		
		Edge[] retArray = new Edge[edge.length];
		int firstPos = 0;
		int secondPos = 0;
		int edgePos = 0;
		
		while(firstPos < firstHalf.length && secondPos < secondHalf.length)
		{
			
			if(firstPos >= firstHalf.length || secondHalf[secondPos].getWeight() < firstHalf[firstPos].getWeight())
			{
				retArray[edgePos] = secondHalf[secondPos];
				edgePos++;
				secondPos++;
			}
			else if(secondPos >= secondHalf.length || firstHalf[firstPos].getWeight() < secondHalf[secondPos].getWeight())
			{
				retArray[edgePos] = firstHalf[secondPos];
				edgePos++;
				firstPos++;
			}
			
		}
		
		
		
		return retArray;
	}
	
	
	public static void newAdjacencyList(File file) {
		
			
		
		try {
			Scanner in = new Scanner(file);
			
			numVertices = in.nextInt();
			numEdges = in.nextInt();
			directed = in.nextInt();
			weighted = in.nextInt();
			
			vertices = new LinkedList[numVertices];
			for(int x = 0; x < numVertices; x++)
			{
				vertices[x] = new LinkedList();
			}
			
//			System.out.println("Vertices: " + numVertices);
//			System.out.println("Edges: " + numEdges);
//			System.out.println("Directed: " + directed);
//			System.out.println("Weighted: " + weighted);		
			
			//while(in.hasNextInt()) {
			for(int x = 0; x < numEdges; x++)
			{
				int source = in.nextInt();
				int dest = in.nextInt();
				int weight = -1;
				
				if(weighted == 1) {
					weight = in.nextInt();
				}
				vertices[source].addFront(dest, weight);
					
				if(directed == 0) {
				    vertices[dest].addFront(source, weight);
				}
				
				
				
			}
			
			in.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
	}
	public static void printAdjacencyList() {
		
		for( int x = 0; x < numVertices; x++)
		{
			System.out.print(""+x + ": ");
			for( int y = 0; y < vertices[x].size(); y++)
			{
				System.out.print(""+vertices[x].getVertex(y)+ " ");
			}
			System.out.println("");
		}
	}
	
//	public void setUpAdjacencyList(int numVertices, int numEdges) {
//		vertices = new int[numVertices];
//		
//		
//		
//		
//	}
	
	
	public static void processVertexEarly(int vertex) {
		
	    System.out.println("Process " + vertex + " early");
		
	}
	
    public static void processVertexLate(int vertex) {
		
	    System.out.println("Process " + vertex + " late");
		
	}
    
    public static void processEdge(int source, int dest) {
		
	    System.out.println("Process edge " + source + " " + dest);
		 
	}
	
	public static void initBFS(int start) {
		
		int[] discovered = new int[numVertices];
		int[] processed = new int[numVertices];
		int[] parent = new int[numVertices];
		for(int x = 0; x < numVertices; x++) {
			discovered[x] = 0;
			processed[x] = 0;
			parent[x] = -1;
		}
		ArrayList<Integer> inQueue = new ArrayList<Integer>();
		inQueue.add(start);
		discovered[start] = 1;
		BFS(discovered, processed, parent, inQueue);
	}
	
	public static void BFS(int[] discovered, int[] processed, int[] parent, ArrayList<Integer> inQueue) {
		
		while(inQueue.size()>0)
		{
			int vertex = inQueue.remove(0);
			processVertexEarly(vertex);
			for( int x = 0; x < vertices[vertex].size(); x++)
			{
				int rec = vertices[vertex].getVertex(x);
				if(processed[rec] == 0 || directed == 1)
				{
					processEdge(vertex, rec);
					
				}
				if(discovered[rec] == 0)
				{
					discovered[rec] = 1;
					inQueue.add(rec);
					parent[rec] = vertex;
				}
			}
			
			processVertexLate(vertex);
			processed[vertex] = 1;
			
		}
		if(inQueue.size() == 0)
		{
			for(int x = 0; x < numVertices; x++)
			{
				if(discovered[x] == 0)
				{
					inQueue.add(x);
					discovered[x] = 1;
					BFS(discovered, processed, parent, inQueue);
				}
			}
		}
		
	}
	
	public static void initDFS(int start) {

		int[] discovered = new int[numVertices];
		int[] processed = new int[numVertices];
		int[] parent = new int[numVertices];
		for(int x = 0; x < numVertices; x++) {
			discovered[x] = 0;
			processed[x] = 0;
			parent[x] = -1;
		}
		discovered[start] = 1;
		DFS(discovered, processed, parent, start, start);
		
	}
	public static void DFS(int[] discovered, int[] processed, int[] parent, int vertex, int start) {
		
		processVertexEarly(vertex);
		for(int x = 0; x < vertices[vertex].size(); x++)
		{
			int rec = vertices[vertex].getVertex(x);
			if(discovered[rec] == 0)
			{
				parent[rec] = vertex;
				processEdge(vertex, rec);
				discovered[rec] = 1;
				DFS(discovered, processed, parent, rec, start);
			}
			else if((processed[rec] ==  0 && rec != parent[vertex]) || directed == 1)
			{
				processEdge(vertex, rec);
			}
			
		}
		processVertexLate(vertex);
		processed[vertex] = 1;
		if(vertex == start)
		{
			for(int x = 0; x < numVertices; x++)
			{
				if(discovered[x] == 0)
				{
					discovered[x] = 1;
					DFS(discovered, processed, parent, x, x);
				}
			}
		}
	}
	
}
