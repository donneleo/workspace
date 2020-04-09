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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC) throws FileNotFoundException
    {

       FileReader input = new FileReader(filename);
       Scanner scanner = new Scanner(input);
       int number_of_edges = scanner.nextInt();
       int number_of_vertices = scanner.nextInt();
       
       while(scanner.hasNextLine()) {
    	   Edge edge = new Edge(scanner.nextInt(), scanner.nextInt(), scanner.nextDouble());
    	   
       }
       
    }
   
    
    public class Node{
    	private double distance_from_source = Integer.MAX_VALUE;
    	private boolean visited;
    	private ArrayList<Edge> edges = new ArrayList<Edge>();
    	
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
    }
    
    public class Edge{ 
    	private final int source;
    	private final int destination;
    	private final double weight;
    	
    	public Edge(int source, int destination, double weight) 
    	{
    		this.source = source;
    		this.destination = destination;
    		this.weight = weight;
    	}
    	
    	public int getSource() {
    		return source;
    	}
    	
    	public int getDestination() {
    		return destination;
    	}
    	
    	public double getWeight(){
    		return weight;
    	}
    	
    	public int getNeighbourNode(int index){
    		if(this.source == index) 
    		{
    			return this.destination;
    		}else {
    			return this.source;
    		}
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