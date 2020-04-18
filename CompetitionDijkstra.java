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
 * @author: Eoin Donnelly Maguire, 
 * Harry Doyle, Yannick Gloster
 * 
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
				array = new Node[number_of_vertices];
				if(array.length == 0) {
					array = null;
				}
				for(int i=0; i<number_of_vertices; i++) {
					array[i] = new Node();
				}
				int number_of_edges = scanner.nextInt();
				for(int j=0; j<number_of_edges; j++) {
					if(scanner.hasNextLine()) {
						int start = scanner.nextInt();
						int destination = scanner.nextInt();
						double weight = scanner.nextDouble();

						array[start].edges.add(new Edge(destination, weight));
					}
				}				
				speed1 = sA;
				speed2 = sB;
				speed3 = sC;
				scanner.close();
//Printing method I used to make sure edges were added correctly
/*				for(int x=0; x<number_of_vertices; x++) {
					for(int y=0; y<array[x].edges.size(); y++)
						System.out.println( x + " -> " + array[x].edges.get(y).destination + " = " + array[x].edges.get(y).weight);
				}
*/				
			}
		}catch(IOException e) {
			array = null;
		}
	}


	/*
	 * main method used during the testing of the code
	 */

/*	public static void main(String[] args)throws IOException {
		CompetitionDijkstra competition = new CompetitionDijkstra("input-E.txt", 80, 65, 97);
		int time = timeRequiredforCompetition();
		System.out.println(time);
	}
*/
	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public static int timeRequiredforCompetition(){

		double time=-1;
		if(((speed1>=50) && (speed1 <=100)) && ((speed2>=50) && (speed2<=100)) && ((speed3>=50) && (speed3<=100))) 
		{
			CompetitionDijkstra competition = new CompetitionDijkstra(filename, speed1, speed2, speed3);
			if(array == null) {
				return -1;
			}
			for(int i = 0; i < array.length; i++) 
			{
				double[] distances = new double[array.length];
				int[] pathway = new int[array.length];
				ArrayList<Integer> queue = new ArrayList<Integer>();
				ArrayList<Integer> visited = new ArrayList<Integer>();

				queue.add(i);

				for(int j = 0; j < pathway.length; j++) 
				{
					pathway[j] = Integer.MAX_VALUE;
					distances[j] = Double.POSITIVE_INFINITY;
				}

				distances[i] = 0;
				pathway[i] = i;

				while(!queue.isEmpty()) {
					int min = getShortestPath(queue, distances);
					int currentNodeIndex = queue.remove(min);
					Node currentIntersection = array[currentNodeIndex];

					for(int j = 0; j < currentIntersection.edges.size(); j++) 
					{
						Edge relaxedEdge = currentIntersection.edges.get(j);

						if(distances[relaxedEdge.getDestination()] > relaxedEdge.getWeight() + distances[currentNodeIndex]) 
						{
							distances[relaxedEdge.getDestination()] = relaxedEdge.getWeight() + distances[currentNodeIndex];
							pathway[relaxedEdge.getDestination()] = currentNodeIndex;
						}
						if(!visited.contains(relaxedEdge.getDestination()) && !queue.contains(relaxedEdge.getDestination())) 
						{
							queue.add(relaxedEdge.getDestination());
						}
					}
					visited.add(currentNodeIndex);
				}
				Arrays.sort(distances);
				double longestDistance = distances[distances.length-1];
				for(int j = 0; j < pathway.length; j++) 
				{
					if(pathway[j] == Integer.MAX_VALUE) 
					{
						return -1;
					}
					int slowest = Math.min(Math.min(speed1, speed2), speed3);

					if(time == -1)
					{
						time = (longestDistance*1000)/slowest;
					} else {
						time = Math.max(time, (longestDistance * 1000) / slowest);
					}
				}
			}
		}
		return (int)(Math.ceil(time));
	}



	//return time;

	private static int getShortestPath(ArrayList<Integer> queue, double[] distTo) {
		int shortest = 0;
		for(int i = 0; i < queue.size(); i++) 
		{
			if(distTo[queue.get(shortest)] > distTo[queue.get(i)]) 
			{
				shortest = i;
			}
		}

		return shortest;
	}

}

