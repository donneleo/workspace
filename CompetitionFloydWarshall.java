import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;

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
	private static double[][] array;
	private static String filename;
	private static int speed1;
	private static int speed2;
	private static int speed3;
	private static int number_of_vertices;
	private static DecimalFormat df = new DecimalFormat("0.00");
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC){

		//TODO
		try{
			if(filename!=null){
				FileReader fr = new FileReader(filename);
				BufferedReader br = new BufferedReader(fr);
				Scanner scanner = new Scanner(br);
				if(!scanner.hasNext() || (!scanner.hasNextInt())) {
					array = null;
				}
				number_of_vertices = scanner.nextInt();
				int number_of_edges = scanner.nextInt();
				array = new double[number_of_vertices][number_of_vertices];
				if(array.length == 0) {
					array = null;
				}
				for(int temp =0; temp<number_of_vertices; temp++)
					for(int temp2=0; temp2<number_of_vertices; temp2++) {
						if(temp==temp2) {
							array[temp][temp2] = 0;
						}
						else {
							array[temp][temp2] = Double.POSITIVE_INFINITY;
						}
					}
				int i,j;
				speed1 = sA;
				speed2 = sB;
				speed3 = sC;
				while(scanner.hasNextLine()) 
				{
					i = scanner.nextInt();
					j = scanner.nextInt();
					array[i][j] = scanner.nextDouble();
					if(scanner.hasNextLine()) scanner.nextLine();
				}
			}
		}catch(IOException e) {
			array = null;
		}
	}

	public static double[][] FloydWarshall(double[][] array){

		double[][] result = new double[number_of_vertices][number_of_vertices];
		int a,b,c;

		for(a = 0; a<number_of_vertices; a++)
			for(b = 0; b<number_of_vertices; b++) {
				result[a][b] = array[a][b]; 
			}

		for(c = 0; c<number_of_vertices; c++) {
			for(a = 0; a<number_of_vertices; a++) {
				for(b= 0; b<number_of_vertices; b++) {
					if(result[a][c] + result[c][b] < result[a][b]) {
						result[a][b] = result[a][c] + result[c][b];
					}
				}
			}		
		}
		printSolution(result);
		return result;
	}

	static void printSolution(double dist[][]) 
	{ 
		System.out.println("The following matrix shows the shortest "+ 
				"distances between every pair of vertices"); 

		for (int i=0; i<number_of_vertices; ++i) 
		{ 
			for (int j=0; j<number_of_vertices; ++j) 
			{

				if (dist[i][j]==Double.POSITIVE_INFINITY) 
					System.out.print("INF  "); 
				else
					System.out.print(df.format(dist[i][j])+"   "); 
			} 
			System.out.println(); 
		} 
	}



/*		public static void main(String[] args){

			CompetitionFloydWarshall competition = new CompetitionFloydWarshall("input-J.txt", 98, 70, 84);
			double answer = timeRequiredforCompetition();
			System.out.println(answer);

	}
*/	 
	public static double findHighestValue(double[][] doubles) {
		double currentHighestValue = Double.MIN_VALUE;
		for (int row = 0; row < doubles.length; row++) {
			for (int col = 0; col < doubles[row].length; col++) {
				double value = doubles[row][col];
				if (value > currentHighestValue) {
					currentHighestValue = value;
				}
			}
		}
		return currentHighestValue; 
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public static int timeRequiredforCompetition(){
		//MAKE CITY NULL INSTEAD OF THROWING EXCEPTION

		double longest = 0;
		int time=0;

		if(((speed1>=50) && (speed1 <=100)) && ((speed2>=50) && (speed2<=100)) && ((speed3>=50) && (speed3<=100))) {
			CompetitionFloydWarshall competition = new CompetitionFloydWarshall(filename, speed1, speed2, speed3);
			if(array == null) {
				return -1;
			}
			double[][] answer = competition.FloydWarshall(array);
			if(answer == null) {
				return -1;
			}
			longest = findHighestValue(answer);
			int slowestWalker = Math.min(Math.min(speed1, speed2), speed3);
			System.out.println((longest*1000) / slowestWalker);
			if((longest*1000) / slowestWalker == Double.POSITIVE_INFINITY) {
				return -1;
			}
			time = (int)Math.ceil((longest*1000) / slowestWalker);
		}
		else {
			return -1;
		}

		return time;
	}

}