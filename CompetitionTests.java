/*import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() {

        //TODO
    	try {
			new CompetitionDijkstra("tinyEWD.txt", 1,2,3);
			
			new CompetitionDijkstra("FakeFile.txt", 5, 4, 7);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("File not Found");
		}
    }
    
    @Test
    public void testTimeRequiredForDijkstraCompetition() {
    	
    	try{
    		CompetitionDijkstra competition = new CompetitionDijkstra("tinyEWD.txt", 1,2,3);
    		competition.computePath(competition.array[4]);
    		competition.getShortestPath(competition.array[0]);
    		
    		competition.computePath(competition.array[2]);
    		competition.getShortestPath(competition.array[0]);
    		
    		competition.computePath(competition.array[7]);
    		competition.getShortestPath(competition.array[0]);
    		
    		int time = competition.timeRequiredforCompetition();
    		assertEquals("110", Integer.toString(time));
    		
    		
    	}catch(FileNotFoundException e) {
    		System.out.println("File Not Found");
    	}catch(IOException e) {
    		System.out.println("File Not Found");
    	}
    	
    }

    @Test
    public void testFWConstructor() {
        //TODO
    	try {
			new CompetitionFloydWarshall("tinyEWD.txt", 1,2,3);
			
			new CompetitionFloydWarshall("FakeFile.txt", 5 , 8 , 10);
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("File not Found");
		}
		
    }

    //TODO - more tests
    
    @Test
    public void timeReuqiredForFloydWarshallCompetition() {
    	
    }
    
}*/
