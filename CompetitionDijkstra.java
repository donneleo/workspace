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
 */

public class CompetitionDijkstra {

	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 */

	public static Node[] array;
	public static int speed1;
	public static int speed2;
	public static int speed3;
	CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException, IOException
	{

		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br);
		int number_of_vertices = scanner.nextInt();
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
			}
			Node nextNode = new Node(scanner.nextInt());
			int j = nextNode.streetNumber;
			if(array[j] == null) {
			array[j] = nextNode;
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
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
		
		int answer = timeRequiredforCompetition();
		System.out.println(answer);
	}


	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static int timeRequiredforCompetition() throws FileNotFoundException, IOException{
		
		CompetitionDijkstra competition = new CompetitionDijkstra("tinyEWD.txt", 1,2,3);
		computePath(array[2]);
		ArrayList<Node> result = getShortestPath(array[0]);
		double length = result.get(result.size()-1).distance_from_source;
		System.out.println("length 1 = " + length);
		double time1 =  (length/speed1)*60;
		
		
		computePath(array[7]);
		ArrayList<Node> result2 = getShortestPath(array[0]);
		double length2 = result2.get(result2.size()-1).distance_from_source;
		System.out.println("length 2 = " + length2);
		double time2 = (length2/speed2)*60;
			
		
		computePath(array[6]);
		ArrayList<Node> result3 = getShortestPath(array[0]);
		double length3 = result3.get(result3.size()-1).distance_from_source;
		System.out.println("length 3 = " + length3);
		double time3 = (length3/speed3)*60;
		
		double showTime = time1;
		if(showTime<time2) showTime = time2;
		if(showTime<time3) showTime = time3;
		
		int timeForShow = (int)Math.ceil(showTime);
		
		if(getShortestPath(array[0]) == null) return -1;
		else return timeForShow;
		
	}

}