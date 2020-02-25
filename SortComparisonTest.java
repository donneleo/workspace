import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Scanner;
import java.io.*;


//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author Eoin Donnelly Maguire - with aid from lecture slides
 *  @version HT 2020
 *  
 *  Questions
 *  a)Then order of input seemed to have the biggest impact on the selection sort. By having nearly 
 *  ordered elements the algorithm would have to look through the array more times until it was fully ordered.
 *  
 *  b)The biggest difference between best and worse time for the different 1000 element lists was Insertion sort. It moved much 
 *  quicker through the "already sorted" file, because it only needed to check half as many elements.
 *  
 *  c)The worst scalability depending on size is insertion sort because it needs to compare more elements. The best scalability 
 *  is recursive merge sort.
 *  
 *  d)The recursive merge sort tended to have around half the run time for the larger data sets. The recursive merge sort had
 *  similar run time for all the 1000 element data sets, while the iterative method tended to fluctuate more.
 *  
 *  e)The insertion sort was the quickest for the 10 element data set, but for each data set thereafter, the recursive merge sort
 *  was the quickest
 *  
 *    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    
	/*public static void main(String[] args) throws FileNotFoundException
	{
		double[] a = new double[1000];     
		FileReader input = (new FileReader("numbersSorted1000.txt"));
		Scanner scanner = new Scanner(input);
		for(int i=0; i<a.length; i++) 
		{
			a[i] = scanner.nextDouble();
		}
		scanner.close();


		long startTime = System.nanoTime();
		SortComparison.insertionSort(a);
		long endTime = System.nanoTime();
		System.out.println(((endTime-startTime)));

		startTime = System.nanoTime();
		SortComparison.selectionSort(a);
		endTime = System.nanoTime();
		System.out.println(((endTime-startTime)));

		startTime = System.nanoTime();
		SortComparison.quickSort(a);
		endTime = System.nanoTime();
		System.out.println(((endTime-startTime)));

		startTime = System.nanoTime();
		SortComparison.mergeSortIterative(a);
		endTime = System.nanoTime();
		System.out.println(((endTime-startTime)));

		startTime = System.nanoTime();
		SortComparison.mergeSortRecursive(a);
		endTime = System.nanoTime();
		System.out.println(((endTime-startTime)));
	}
 *
 *
 *  //-----------Run Time Averages---------------
 *  
 *  
 *  ----------------------------------------------------------------------------------------------------------
 *  |              | Insertion Sort | Selection Sort | MergeSort Iterative | MergeSort Recursive | QuickSort |
 *  ----------------------------------------------------------------------------------------------------------
 *  |numbers10.txt |   9833.33ns    |   12500ns      |     59033.33ns      |     35766.66ns      | 29566.6ns |
 *  ----------------------------------------------------------------------------------------------------------
 *  |numbers100.txt|   256200ns     |   83733.33ns   |     61900ns         |        40100ns      |  149000ns |
 *  ----------------------------------------------------------------------------------------------------------
 *  |numbers1000   |    8.95ms      |    3.83ms      |       .48ms         |         .25ms       |  4.69ms   |
 *  ----------------------------------------------------------------------------------------------------------
 *  |numbers1000   |                |                |                     |                     |           |
 *  | few unique   |    8.47ms      |    3.48ms      |       .49ms         |         .24ms       |  1.63ms   |
 *  ----------------------------------------------------------------------------------------------------------
 *  |1000 near ord |    8.02ms      |    5.17ms      |       .51ms         |         .23ms       |  4.82ms   |
 *  ----------------------------------------------------------------------------------------------------------
 *  |1000 reversed |    8.82ms      |    3.37ms      |       .64ms         |         .23ms       |  3.93ms   |
 *  ----------------------------------------------------------------------------------------------------------
 *  |1000 sorted   |    5.49ms      |    3.93ms      |       .56ms         |         .21ms       |  4.53ms   |
 *  ----------------------------------------------------------------------------------------------------------
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    }


    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.
    
    @Test 
    public void testInsertionSort() 
    {
    	double[] a = new double[] {2.3, 4.5, 1.8, 884.2, 5.7, 3.4};
    	double[] result = new double[] {1.8, 2.3, 3.4, 4.5, 5.7, 884.2};
    	
    	Assert.assertThat("Insertion sort on six elements", SortComparison.insertionSort(a), IsEqual.equalTo(result));
    }
    
    @Test
    public void testSelectionSort() 
    {
    	double[] a = new double[] {2.3, 4.5, 1.8, 884.2, 5.7, 3.4};
    	double[] result = new double[] {1.8, 2.3, 3.4, 4.5, 5.7, 884.2};
    	
    	Assert.assertThat("Selection sort on six elements", SortComparison.selectionSort(a), IsEqual.equalTo(result));
    }
    
    @Test
    public void testQuickSort() 
    {
    	double[] a = new double[] {2.3, 4.5, 1.8, 884.2, 5.7, 3.4};
    	double[] result = new double[] {1.8, 2.3, 3.4, 4.5, 5.7, 884.2};
    	
    	Assert.assertThat("Quick sort on six elements", SortComparison.quickSort(a), IsEqual.equalTo(result));
    }
    
    @Test
    public void testMergeSortIterative() 
    {
    	double[] a = new double[] {2.3, 4.5, 1.8, 884.2, 5.7, 3.4};
    	double[] result = new double[] {1.8, 2.3, 3.4, 4.5, 5.7, 884.2};
    	
    	Assert.assertThat("Iterative merge sort on six elements", SortComparison.mergeSortIterative(a), IsEqual.equalTo(result));
    }
    
    @Test
    public void testMergeSortRecursive()
    {
    	double[] a = new double[] {2.3, 4.5, 1.8, 884.2, 5.7, 3.4};
    	double[] result = new double[] {1.8, 2.3, 3.4, 4.5, 5.7, 884.2};
    	
    	Assert.assertThat("Recursive merge sort on six elements", SortComparison.mergeSortRecursive(a), IsEqual.equalTo(result));
    }

    // ----------------------------------------------------------
  

}