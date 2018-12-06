

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Scanner;

public class AdjacencyList {

	private static LinkedList[] vertices;
	private static Edge[] edges;
	private static int numVertices;
	private static int numEdges;
	private static int directed;
	private static int weighted;

	public static void main(String[] args) {

		File file = new File("AdjacencyInfo.txt");
		Scanner in = new Scanner(System.in);
		int choice = 0;
		newAdjacencyList(file);

		while (choice != 5) {
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + "Choose one the following options:"
					+ "\n1- Print the adjacency list" + "\n2- BFS" + "\n3- DFS" + "\n4- MST" + "\n5- Exit");
			System.out.print(">>>");
			choice = in.nextInt();

			if (choice == 1) {
				printAdjacencyList();

			} else if (choice == 2) {
				System.out.println("\n Call BFS");
				initBFS(0);
			} else if (choice == 3) {
				System.out.println("\n Call DFS");
				initDFS(0);
			} else if (choice == 4) {
				System.out.println("MST");
				sortEdges();
				kruskalsAlgorithm();
			}

		}

	}

	public static void kruskalsAlgorithm() {
		int count = 0;
		int srcBonus = 0;
		int destBonus = 0;
		LinkedList[] vertexArray = new LinkedList[numVertices];

		for (int i = 0; i < numVertices; i++) {

			vertexArray[i] = new LinkedList();

		}
		for (int x = 0; x < edges.length; x++) {
			int edgeSource = edges[x].getSource();
			int edgeDest = edges[x].getDest();
			int findSource;
			int findDest;

			if (vertexArray[edgeSource].size() == 0) {
				findSource = edgeSource;
			} else {
				if(vertexArray[edgeSource].getWeight(0) == -1)
				{
					findSource = edgeSource;
				}
				else 
				{
					findSource = vertexArray[edgeSource].getVertex(0);
				}
			}

			if (vertexArray[edgeDest].size() == 0) {
				findDest = edgeDest;
			} else {
				if(vertexArray[edgeDest].getWeight(0) == -1)
				{
					findDest = edgeDest;
				}
				else 
				{
					findDest = vertexArray[edgeDest].getVertex(0);
				}
				
			}
			if (findSource != findDest) {
				if (vertexArray[findSource].size() >= vertexArray[findDest].size()) {

					System.out.println("Edge (" + edgeSource + "," + edgeDest + ") weight = " + edges[x].getWeight());
					count += edges[x].getWeight();

					if (vertexArray[edgeSource].size() == 0) {
						vertexArray[edgeSource].add(edgeDest, -1);
						vertexArray[edgeDest].add(edgeSource, 1);
					} else {

						if (vertexArray[findSource].size() == vertexArray[findDest].size()) {
							if (findDest < findSource) {
								int t = findSource;
								findSource = findDest;
								findDest = t;
							}
						}
						vertexArray[findSource].add(findDest, -1);
						for (int y = 0; y < vertexArray[findDest].size(); y++) {
							vertexArray[findSource].add(vertexArray[findDest].getVertex(y), -1);

							int temp = vertexArray[findDest].getVertex(y);
							vertexArray[temp].clear();
							vertexArray[temp].add(findSource, 1);
						}
						vertexArray[findDest].clear();
						vertexArray[findDest].add(findSource, 1);
					}
				}
			}

		}
		// vertexArray[edgeSource].add(edgeDest, -1);

		// for (int i = 0; i < edges.length; i++) {
		//
		// edge = edges[i];
		// int first = verticeArray[edge.getSource()].find();
		// int second = verticeArray[edge.getDest()].find();
		//
		// if (first != second) {
		//
		// union(first, second);
		//
		// }
		// }
		System.out.println("MST Cost = " + count);
	}

	public static void sortEdges() {

		if (directed == 1) {
			edges = new Edge[numEdges];
		} else {
			edges = new Edge[2 * numEdges];
		}
		int pos = 0;
		for (int x = 0; x < numVertices; x++) {
			for (int y = 0; y < vertices[x].size(); y++) {
				edges[pos] = new Edge(x, vertices[x].getVertex(y), vertices[x].getWeight(y));
				pos++;
			}
		}
		edges = mergeEdges(edges);

	}

	public static Edge[] mergeEdges(Edge[] edge) {
		if (edge.length <= 1) {
			return edge;
		}

		Edge[] firstHalf = mergeEdges(Arrays.copyOfRange(edge, 0, edge.length / 2));
		Edge[] secondHalf = mergeEdges(Arrays.copyOfRange(edge, edge.length / 2, edge.length));
		Edge[] retArray = new Edge[edge.length];
		int firstPos = 0;
		int secondPos = 0;
		int edgePos = 0;
		while (firstPos < firstHalf.length || secondPos < secondHalf.length) {
			if (firstPos >= firstHalf.length) {
				retArray[edgePos] = secondHalf[secondPos];
				edgePos++;
				secondPos++;
			} else if (secondPos >= secondHalf.length) {
				retArray[edgePos] = firstHalf[firstPos];
				edgePos++;
				firstPos++;
			} else if (secondHalf[secondPos].getWeight() < firstHalf[firstPos].getWeight()) {
				retArray[edgePos] = secondHalf[secondPos];
				edgePos++;
				secondPos++;
			} else {
				retArray[edgePos] = firstHalf[firstPos];
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
			for (int x = 0; x < numVertices; x++) {
				vertices[x] = new LinkedList();
			}

			// System.out.println("Vertices: " + numVertices);
			// System.out.println("Edges: " + numEdges);
			// System.out.println("Directed: " + directed);
			// System.out.println("Weighted: " + weighted);

			// while(in.hasNextInt()) {
			for (int x = 0; x < numEdges; x++) {
				int source = in.nextInt();
				int dest = in.nextInt();
				int weight = -1;

				if (weighted == 1) {
					weight = in.nextInt();
				}
				vertices[source].addFront(dest, weight);

				if (directed == 0) {
					vertices[dest].addFront(source, weight);
				}

			}

			in.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}

	}

	public static void printAdjacencyList() {

		for (int x = 0; x < numVertices; x++) {
			System.out.print("" + x + ": ");
			for (int y = 0; y < vertices[x].size(); y++) {
				System.out.print("" + vertices[x].getVertex(y) + " ");
			}
			System.out.println("");
		}
	}

	// public void setUpAdjacencyList(int numVertices, int numEdges) {
	// vertices = new int[numVertices];
	//
	//
	//
	//
	// }

	public static void processVertexEarly(int vertex) {

		System.out.println("Process " + vertex + " early");

	}

	public static void processVertexLate(int vertex) {

		System.out.println("Process " + vertex + " late");

	}

	public static void processTreeEdge(int source, int dest) {
		System.out.println("Process edge " + source + " " + dest+ " (Tree Edge)");

	}

	public static void processOtherEdge(int source, int dest, int[] parent) {
		
			int pos = dest;
			while (parent[pos] > -1) {
				if (parent[pos] == source) {
					System.out.println("Process edge " + source + " " + dest + " (Forward Edge)");
					return;
				} else {
					pos = parent[pos];
				}
			}
			pos = source;

			while (parent[pos] > -1) {
				if (parent[pos] == dest) {
					System.out.println("Process edge " + source + " " + dest + " (Back Edge)");
					return;
				} else {
					pos = parent[pos];
				}
			}
		
		System.out.println("Process edge " + source + " " + dest + " (Cross Edge)");

	}

	public static void initBFS(int start) {
		int[] discovered = new int[numVertices];
		int[] processed = new int[numVertices];
		int[] parent = new int[numVertices];
		for (int x = 0; x < numVertices; x++) {
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
		while (inQueue.size() > 0) {
			int vertex = inQueue.remove(0);
			processVertexEarly(vertex);
			for (int x = 0; x < vertices[vertex].size(); x++) {
				int rec = vertices[vertex].getVertex(x);
				if (processed[rec] == 0 || directed == 1) {
					if (discovered[rec] == 1) {
						processOtherEdge(vertex, rec, parent);
					} else {
						processTreeEdge(vertex, rec);
					}
				}
				if (discovered[rec] == 0) {
					discovered[rec] = 1;
					inQueue.add(rec);
					parent[rec] = vertex;
				}
			}
			processVertexLate(vertex);
			processed[vertex] = 1;
		}
		if (inQueue.size() == 0) {
			for (int x = 0; x < numVertices; x++) {
				if (discovered[x] == 0) {
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
		for (int x = 0; x < numVertices; x++) {
			discovered[x] = 0;
			processed[x] = 0;
			parent[x] = -1;
		}
		discovered[start] = 1;
		DFS(discovered, processed, parent, start, start);
	}

	public static void DFS(int[] discovered, int[] processed, int[] parent, int vertex, int start) {
		processVertexEarly(vertex);
		for (int x = 0; x < vertices[vertex].size(); x++) {
			int rec = vertices[vertex].getVertex(x);
			if (discovered[rec] == 0) {
				parent[rec] = vertex;
				processTreeEdge(vertex, rec);
				discovered[rec] = 1;
				DFS(discovered, processed, parent, rec, start);
			} else if ((processed[rec] == 0 && rec != parent[vertex]) || directed == 1) {
				processOtherEdge(vertex, rec, parent);
			}
		}
		processVertexLate(vertex);
		processed[vertex] = 1;
		if (vertex == start) {
			for (int x = 0; x < numVertices; x++) {
				if (discovered[x] == 0) {
					discovered[x] = 1;
					DFS(discovered, processed, parent, x, x);
				}
			}
		}
	}
}
