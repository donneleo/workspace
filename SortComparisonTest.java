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
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    
    public void main(String[] args) throws FileNotFoundException
    {
        double[] a = new double[10];
        int i=0;
        
        FileReader input = (new FileReader("numbers1000.txt"));
        Scanner scanner = new Scanner(input);
        while(scanner.hasNextLine()) 
        {
        	a[i] = scanner.nextDouble();
        	i++;
        }
        scanner.close();
        
        for(int j=0; j<a.length; j++) 
        {
        	System.out.println(a[j]);
        }
        
        long startTime = System.currentTimeMillis();
        SortComparison.insertionSort(a);
        long endTime = System.currentTimeMillis();
        System.out.println("Insertion sort with 1000 elements took " + (endTime-startTime) + " ms.");
    }

}