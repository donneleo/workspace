
// -------------------------------------------------------------------------
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Math;
import java.util.Scanner;
/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2020
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double [] insertionSort (double a[]){

		//get length of array
		int counter = a.length;

		double temp;

		//set up a loop to move throught the array
		for(int array_key = 1; array_key < counter; array_key++)
		{
			for(int number = array_key; number>0; number--) 
			{
				if(a[number]<a[number-1]) 
				{
					temp=a[number];
					a[number] = a[number-1];
					a[number-1] = temp;
				}
			}
		}

		//return sorted array
		return a;

	}

	/**
	 * Sorts an array of doubles using Selection Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] selectionSort (double a[]){

		//get length of array
		int length = a.length;
		//set up loop to move through array
		for(int i = 0; i < length-1; i++)
		{
			//set first index as the min
			int min_index = i;

			//set up a second loop that will move through remaining elements, to see if there are any smaller
			for(int j=i+1; j<length; j++)
			{
				if(a[j] < a[min_index]) 
				{
					min_index = j;
				}
			}

			double temp = a[min_index];
			a[min_index] = a[i];
			a[i] = temp;

		}
		//return sorted array
		return a;
	}

	/**
	 * Sorts an array of doubles using Quick Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double [] quickSort (double a[]){

		//call function which will sort the array
		double[] array = a.clone();
		sort(array, 0, a.length-1);
		
		return array;
		
	}//end quicksort

	//function to sort the array
	public static void sort(double a[], int low, int high)
	{
		if (low < high) 
        { 
            
            int pi = partition(a, low, high); 
   
            sort(a, low, pi-1); 
            sort(a, pi+1, high); 
        } 
		
	}

	//function to calculate the partition
	private static int partition (double[] a, int low, int high) 
	{

		double pivot = a[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (a[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
               double temp = a[i]; 
                a[i] = a[j]; 
                a[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        double temp = a[i+1]; 
        a[i+1] = a[high]; 
        a[high] = temp; 
  
        return i+1; 
	}

	/**
	 * Sorts an array of doubles using Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */

	static double[] mergeSortIterative (double a[]) {

		double[] aux = new double[a.length];
		for(int size=1; size<a.length; size=size+size) 
		{
			for(int low=0; low<(a.length-size); low+=size+size) 
			{
				merge(a, aux, low, (low+size-1), Math.min(low+size+size-1, a.length-1));
			}
		}

		return a;

	}//end mergesortIterative


	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */
	static double[] mergeSortRecursive (double a[]) 
	{
		double[]aux = new double[a.length];
		sort(a, aux, 0, a.length-1);
		
		return a;
	}
	
	private static void sort(double[]a, double[]aux, int low, int high) 
	{
		if(high<=low)
		{
			return;
		}
		
		int middle = low + (high-low) / 2;
		sort(a, aux, low, middle);
		sort(a, aux, middle+1, high);
		merge(a, aux, low, middle, high);
	}

	private static void merge(double[]a, double[]aux, int left, int middle, int right) 
	{
		for(int k = left; k<=right; k++) 
		{
			aux[k] = a[k];
		}
		
		int i=left;
		int j=middle+1;
		
		for(int k = left; k <= right; k++) 
		{
			if        (i>middle)              a[k] = aux[j++];
			else if   (j>right)               a[k] = aux[i++];
			else if   (aux[j]< aux[i])        a[k] = aux[j++];
			else                              a[k] = aux[i++];
		}

	}
	
	
    public static void main(String[] args) throws FileNotFoundException
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


}//end class
