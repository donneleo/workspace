import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class CompetitionTests {

	@Test
	public void testDijkstraConstructor(){

		new CompetitionDijkstra("tinyEWD.txt", 50, 75, 80);
		new CompetitionDijkstra("FAKEFILE.txt", 50, 75, 80);
		new CompetitionDijkstra("", 50, 75, 80);
	}

		@Test
	public void testTimeRequiredForDijkstraCompetition() {

		CompetitionDijkstra test1 = new CompetitionDijkstra("tinyEWD.txt", 50, 70, 80);
		assertEquals("Time Reaquire for Dijkstra Competition", 0, test1.timeRequiredforCompetition());
	}
	@Test
	public void testFWConstructor() {
		//TODO
		new CompetitionFloydWarshall("tinyEWD.txt", 1,2,3);
		new CompetitionFloydWarshall("FakeFile.txt", 5 , 8 , 10);
		new CompetitionFloydWarshall("", 70, 50, 75);
	}

	//TODO - more tests

	@Test
	public void timeReuqiredForFloydWarshallCompetition() {

		CompetitionFloydWarshall test1 = new CompetitionFloydWarshall("tinyEWD.txt", 6, 10, 70);
		assertEquals("Time Required for FloydWarshall Competition", -1, test1.timeRequiredforCompetition());

		CompetitionFloydWarshall test2 = new CompetitionFloydWarshall("tinyEWD.txt", 50, 75, 80);
		assertEquals("Time Required for FloydWarshall Competition", 38, test2.timeRequiredforCompetition());
		
		CompetitionFloydWarshall test3 = new CompetitionFloydWarshall("input-A.txt", 60, 50, 75);
		assertEquals("Time Required for FloydWarshall Competition", -1, test3.timeRequiredforCompetition());
		
		CompetitionFloydWarshall test4 = new CompetitionFloydWarshall("input-K.txt", 51, 70, 88);
		assertEquals("Time Required for FloydWarshall Competition", 314, test4.timeRequiredforCompetition());
		
		CompetitionFloydWarshall test5 = new CompetitionFloydWarshall("input-J.txt", 98, 70, 84);
		assertEquals("Time Required for FloydWarshall Competition", -1, test5.timeRequiredforCompetition());

	}


}