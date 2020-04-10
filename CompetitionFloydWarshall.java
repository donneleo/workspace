import java.util.ArrayList;
import java.util.Scanner;



import java.io.*;
import java.util.*;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC) throws FileNotFoundException, IOException{

        //TODO
    	
		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br);
		int number_of_edges = scanner.nextInt();
		int number_of_vertices = scanner.nextInt();
		Node[] array = new Node[number_of_vertices];

		/*int speed1 = sA;
		int speed2 = sB;
		int speed3 = sC;
		 */	

		while(scanner.hasNextLine()) 
		{
			Node newNode = new Node(scanner.nextInt());
			int i = newNode.streetNumber;
			array[i] = newNode;
			br.readLine();
		}
		scanner.close();
		FileReader fr2 = new FileReader(filename);
		BufferedReader br2 = new BufferedReader(fr2);
		br2.readLine();
		br2.readLine();
		Scanner scan2 = new Scanner(br2);
		while(scan2.hasNextLine()) 
		{
			int i = scan2.nextInt();
			int j = scan2.nextInt();
			Edge edge = new Edge(array[i], array[j], scan2.nextDouble());
			array[i].addNeighbourNode(edge);
			br.readLine();
		}
        
     }
    
     
    public class Node implements Comparable<Node>{

		private int streetNumber;
		private double distance_from_source = Double.MAX_VALUE;
		private boolean visited;
		private ArrayList<Edge> edges = new ArrayList<Edge>();
		private Node previousNode;

		public Node(int streetNumber) {
			this.streetNumber = streetNumber;
			this.edges = new ArrayList<>();
		}

		public void addNeighbourNode(Edge edge) {
			this.edges.add(edge);
		}

		public ArrayList<Edge> getEdges(){
			return edges;
		}

		public void setEdges(ArrayList<Edge> edges) {
			this.edges = edges;
		}

		public Node getPreviousNode() {
			return previousNode;
		}
		public void setPreviousNode(Node previousNode) {
			this.previousNode = previousNode;
		}

		public double getDistanceFromSource() {
			return distance_from_source;
		}

		public void setDistanceFromSource(double distance_from_source) {
			this.distance_from_source = distance_from_source;
		}

		public boolean isVistied() {
			return visited;
		}

		public void isVisited(boolean visited) {
			this.visited = visited;
		}


		@Override
		public int compareTo(Node nextNode) {
			return Double.compare(this.distance_from_source, nextNode.distance_from_source);
		}
	}

	public class Edge{ 
		private Node source;
		private Node destination;
		private double weight;

		public Edge(Node source, Node destination, double weight) 
		{
			this.source = source;
			this.destination = destination;
			this.weight = weight;
		}

		public Node getSource() {
			return source;
		}
		public void setSource(Node node) {
			this.source = source;
		}

		public Node getDestination() {
			return destination;
		}
		public void setDestination(Node node) {
			this.destination = destination;
		}

		public double getWeight(){
			return weight;
		}
		public void setWeight(double weight) {
			this.weight = weight;
		}


	}
    



    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }

}