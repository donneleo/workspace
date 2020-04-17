import java.util.*;
import java.io.*;
import java.lang.*;

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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */

	public static int speed1;
	public static String filename;
	public static int speed2;
	public static int speed3;
	public static Node[] array;
	public static int number_of_vertices;
	CompetitionDijkstra (String filename, int sA, int sB, int sC){

		//TODO
		try{
			if(filename!=null) {
				BufferedReader br = new BufferedReader(new FileReader(filename));
				Scanner scanner = new Scanner(br);
				if(!scanner.hasNextInt() || (!scanner.hasNext())) {
					array = null;
				}
				number_of_vertices = scanner.nextInt();
				int number_of_edges = scanner.nextInt();
				speed1 = sA;
				speed2 = sB;
				speed3 = sC;
				array = new Node[number_of_vertices];
				while(scanner.hasNextLine()) 
				{
					Node newNode = new Node(scanner.nextInt());
					int i = newNode.streetNumber;
					if(array[i] == null) {
						array[i] = newNode;
					}
					Node nextNode = new Node(scanner.nextInt());
					int j = nextNode.streetNumber;
					if(array[j] == null) {
						array[j] = nextNode;
					}
					Edge newEdge = new Edge(nextNode, scanner.nextDouble());
					array[i].addNeighbourNode(newEdge);
					if(scanner.hasNextLine()) scanner.nextLine();
				}
				for(int i=0; i<number_of_vertices; i++) {
					for(int j=0; j<array[i].edges.size(); j++)
					System.out.println(array[i].streetNumber + " -> " + array[i].edges.get(j).destination.streetNumber + " = " + array[i].edges.get(j).weight);
				}
				scanner.close();
			}
		}catch(IOException e) {
			array = null;
		}
	}

	public static void computePath(Node startNode) {
		startNode.setDistanceFromSource(0);
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(startNode);

		while(!queue.isEmpty()) {
			Node node = queue.poll();
			for(Edge edge : node.getEdges()) {
				Node step = edge.getDestination();
				double weight = edge.getWeight();
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


	public static void main(String[] args)throws IOException {
		CompetitionDijkstra competition = new CompetitionDijkstra(filename, 50, 75, 80);
		for(int i=0; i<number_of_vertices; i++)
			for(int j=0; j<number_of_vertices; j++) {
				computePath(array[i]);
				ArrayList<Node> list = getShortestPath(array[j]);
			}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		int time=0;
		if(((speed1>=50) && (speed1 <=100)) && ((speed2>=50) && (speed2<=100)) && ((speed3>=50) && (speed3<=100))) {
			return time;
		}else {
			return -1;
		}
		//return time;
	}

}

