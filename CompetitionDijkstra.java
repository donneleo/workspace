import java.io.*;
import java.util.*;
import java.math.*;


/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 * 
 * 
 * Authors: Eoin Donnelly Maguire, Harry Doyle
 * 
 */

public class CompetitionDijkstra {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */

	public static Node[] array;
	public static int number_of_vertices;
	public static int speed1;
	public static int speed2;
	public static int speed3;
	CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException, IOException
	{

		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br);

		number_of_vertices = scanner.nextInt();
		int number_of_edges = scanner.nextInt();
		array = new Node[number_of_vertices];
		speed1 = sA;
		speed2 = sB;
		speed3 = sC;

		while(scanner.hasNextLine()) 
		{
			Node newNode = new Node(scanner.nextInt());
			int i = newNode.streetNumber;
			if(array[i] == null) {
				array[i] = newNode;
				//newNode.distance_from_source = Double.POSITIVE_INFINITY;
			}
			Node nextNode = new Node(scanner.nextInt());
			int j = nextNode.streetNumber;
			if(array[j] == null) {
				array[j] = nextNode;
			//	nextNode.distance_from_source = Double.POSITIVE_INFINITY;
			}
				Edge edge = new Edge(array[i], array[j], scanner.nextDouble());
				array[i].addNeighbourNode(edge);
			if(scanner.hasNextLine()) scanner.nextLine();
		}
		scanner.close();


	}
	public static void computePath(Node startNode) {
		startNode.setDistanceFromSource(0);
		PriorityQueue<Node> queue = new PriorityQueue<>();
		queue.add(startNode);

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			for(Edge edge : node.getEdges()) {
				Node step = edge.getDestination();
				double weight = edge.getWeight();
				if(step.distance_from_source != Double.POSITIVE_INFINITY) continue;
				double minPathDistance = node.getDistanceFromSource() + weight;

				if(minPathDistance<step.getDistanceFromSource()) {
					queue.remove(node);
					step.setPreviousNode(node);
					step.setDistanceFromSource(minPathDistance);
					queue.add(step);
				}
			}
		}
	}

	public static ArrayList<Node> getShortestPath(Node targetVerte) {
		ArrayList<Node> path = new ArrayList<>();


		for (Node vertex = targetVerte; vertex != null; vertex = vertex.getPreviousNode()) {
				path.add(vertex);
		}

		Collections.reverse(path);
		return path;


	}


	public static void main(String[] args){

		try{
			CompetitionDijkstra competition = new CompetitionDijkstra("tinyEWD.txt", 1, 2, 3);
		
		}catch(IOException e) {
			System.out.println("File Not Found");
		}
		for(int i=0; i<number_of_vertices; i++)
			for(int j=0; j<number_of_vertices; j++){
			computePath(array[i]);
			ArrayList<Node> list = getShortestPath(array[j]);
			System.out.println(list.get(list.size()-1).distance_from_source);
			}
	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	/*public static int timeRequiredforCompetition(){

		for(int i=0; i<array.length; i++)


		return -1;
	}*/

}