
// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author Eoin Donnelly Maugire
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

		//set up a loop to move throught the array
		for(int array_key = 1; array_key < counter; array_key++)
		{
			//first element
			double key = a[array_key];
			//element to check with
			double element_to_move = key - 1;
			//compare elements
			while(element_to_move > 0 && a[array_key] > key)
			{
				a[(int) (element_to_move + 1)] = a[array_key];
				element_to_move =- 1;
			}
			a[(array_key + 1)] = key;
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
		for(int counter = 0; counter < length; counter++)
		{
			//set first index as the min
			int min_index = counter;

			//set up a second loop that will move through remaining elements, to see if there are any smaller
			for(int second_counter = (counter + 1); second_counter < length; second_counter++) 
			{
				//check elements
				if(a[second_counter] < a[min_index]) 
				{
					//if the second counter is smaller that the original min_index, make min_index equal to second_counter
					min_index = second_counter;
				}

				//create a temp element for the smaller element, and place it into the appropriate position
				double temp_small = a[min_index];
				a[min_index] = a[counter];
				a[counter] = temp_small;
			}		
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
		return sort(a, 0, a.length);

	}//end quicksort
	
	//function to sort the array
	private static double []sort(double a[], int low, int high)
	{
		if(0 < a.length) 
		{
			//uses the partition function to calculate the new partition
			int partition = partition(a,0,a.length);
			//sorts before the partition
			sort(a, 0, partition-1);
			//sorts after the partition
			sort(a, partition+1, a.length);
		}
		//returns the sorted array to quickSort
		return a;
	}

	//function to calculate the partition
	private static int partition (double a[], int low, int high) 
	{

		//the pivot element
		double pivot = a[a.length];
		int lo = 0;
		for(int counter = 0; counter<a.length; counter++) 
		{
			if(a[counter] < pivot) 
			{
				//increment the low point
				lo++;
				double temp = a[lo];
				a[lo] = a[counter];
				a[counter] = temp;
			}
		}

		//swap around the elements
		double holder = a[lo + 1];
		a[lo+1] = a[a.length];
		a[a.length] = holder;
		
		//return the partition point
		return (lo+1);

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

		//todo: implement the sort



		return a;

	}//end mergesortIterative



	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted order.
	 */
	static double[] mergeSortRecursive (double a[]) {


		//todo: implement the sort



		return a;

	}//end mergeSortRecursive







	public static void main(String[] args) {

		//todo: do experiments as per assignment instructions
		double[] array = {2.3, 4, 1.7, 6.4, 3.4};
	}

}//end class
