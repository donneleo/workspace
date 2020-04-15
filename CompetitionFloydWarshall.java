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
	public static double[][] array;
	public static int speed1;
	public static int speed2;
	public static int speed3;
	public static int number_of_vertices;
	private static DecimalFormat df = new DecimalFormat("0.00");
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC) throws FileNotFoundException, IOException{

		//TODO

		FileReader fr = new FileReader(filename);
		BufferedReader br = new BufferedReader(fr);
		Scanner scanner = new Scanner(br);
		number_of_vertices = scanner.nextInt();
		int number_of_edges = scanner.nextInt();
		array = new double[number_of_vertices][number_of_vertices];
		for(int temp =0; temp<number_of_vertices; temp++)
			for(int temp2=0; temp2<number_of_vertices; temp2++) {
				if(temp==temp2) {
					array[temp][temp2] = 0;
				}
				else {
					array[temp][temp2] = Double.POSITIVE_INFINITY;
				}
			}
		int i,j,k;

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

	public static void FloydWarshall(double[][] array){

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
					System.out.print("INF "); 
				else
					System.out.print(df.format(dist[i][j])+"   "); 
			} 
			System.out.println(); 
		} 
	}



	public static void main(String[] args){

		try{
			CompetitionFloydWarshall competition = new CompetitionFloydWarshall("tinyEWD.txt", 1, 2, 3);
			double answer = timeRequiredforCompetition();
			System.out.println(answer);
		}catch (IOException e){
			System.out.println("Error");
		}

	}
	
	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public static double timeRequiredforCompetition(){

		double longest = 0;
		try{
			CompetitionFloydWarshall competition = new CompetitionFloydWarshall("tinyEWD.txt", 5, 2, 4);
			competition.FloydWarshall(array);
			for(int i=0; i<number_of_vertices-1; i++)
				for(int j=0; j<number_of_vertices-1; j++) {
					if(array[i][j] > array[i+1][j+1]) {
						longest = array[i][j];
					}
				}
		}catch(IOException e) {
			System.out.println("Error with File");
		} 

		return longest;
	}

}