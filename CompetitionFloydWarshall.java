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
	public static double[][] array;
	public static int speed1;
	public static int speed2;
	public static int speed3;
	public static int number_of_vertices;
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
					array[temp][temp2] = Double.MAX_VALUE;
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

		for(int x=0; x<number_of_vertices; x++)
			for(int y=0; y<number_of_vertices; y++) {
				System.out.println("distance from " + x + " to " + y + " == " + array[x][y]);
			}

	}

	static void FloydWarshall(double[][] array,int vertices){
		
		
		for(int x=0; x<vertices; x++)
			for(int y=0; y<vertices; y++)
				for(int z=0; z<vertices; z++) {
					if(array[y][x] + array[x][z] < array[y][z]) {
						array[y][z] = array[y][x] + array[x][z];
					}
				}
	}


	public static void main(String[] args)throws FileNotFoundException, IOException {

		int answer = timeRequiredforCompetition();
	}



	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public static int timeRequiredforCompetition()throws FileNotFoundException, IOException{

		CompetitionFloydWarshall competition = new CompetitionFloydWarshall("tinyEWD.txt", 5, 2, 4);
		//FloydWarshall(array, number_of_vertices); 

		return -1;
	}

}