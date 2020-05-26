import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @Eoin Donnelly Maguire
 *  @version 09/10/18 11:13:22
 */


/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{


	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	//--------my bits

	//---------- my own bits for helping me out
	private int listSize = 0;
	//private DLLNode first = new DLLNode(null, null, null);
	//private DLLNode last = new DLLNode(null, first, null);

	// Fields head and tail point to the first and last nodes of the list.
	public DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  The algorithm consists of a single boolean check, and therefore the asymptotic run-time for this piece of code is theta(1). 
	 */
	public boolean isEmpty()
	{
		// TODO
		if(head != null)
		{
			return false;
		}
		return true;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  The algorithm consists of mainly for loops with a single for loop. The asymptotic run-time for a for loop is theta(N). The if 
	 *  and else statements are ignored because asymptotic run-time takes the highest order and ignores the constants.
	 */
	public void insertBefore( int pos, T data ) 
	{
		//TODO


		if(head == null) 
		{
			DLLNode newNode = new DLLNode(data, null, null);
			head = newNode;
			tail = head;

		}
		else if((head == tail) && (pos <= 0)) 
		{
			DLLNode newNode = new DLLNode(data, null, head);
			head = newNode;
			tail.prev = newNode;

		}
		else if((head == tail) && (pos > 0)) 
		{
			DLLNode newNode = new DLLNode(data, tail, null);
			tail = newNode;
			head.next = newNode;

		}
		else if(pos <= 0) 
		{
			DLLNode newNode = new DLLNode(data, null, head);
			head.prev = newNode;
			head = newNode;

		}
		else
		{

			int counter =0 ;

			for(DLLNode current = head; current != null; current = current.next) 
			{
				if(counter == 1) 
				{
					current.prev = head;
				}
				if(counter == pos)
				{
					DLLNode newNode = new DLLNode(data, current.prev, current);
					current.prev = newNode;
					current.prev.prev.next = newNode;
					return;
				}	  
				counter++;  
			}
			DLLNode nextNode = new DLLNode(data, tail ,null);
			tail.next = nextNode;
			tail = nextNode;
			return;
		}
		listSize++;
		return;
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta (N)
	 *
	 * Justification:
	 *  Once again, the algorithm here is mainly if statements, so the worst-case asymptotic run-time for this method is theta of (N).
	 *
	 */
	public T get(int pos) 
	{
		//TODO

		if(isEmpty() || (pos < 0) ) 
		{
			return null;
		}
		else if(pos > listSize) 
		{
			return null;
		}

		int i;
		DLLNode current = head;

		for(i = 0; i < pos; i++) 
		{
			current = current.next;
		}
		return current.data;
	}

	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  The algorithm here contains a while loop and other if/else statements and some assignments, and the highest order element is 
	 *  theta (N).
	 */
	public boolean deleteAt(int pos) 
	{
		//TODO
		if(isEmpty() || (pos < 0) || (pos > listSize)) 
		{
			return false;
		}
		else 
		{
			int i = 0;
			DLLNode current = head;
			while(i < pos) 
			{
				current = current.next;
				i++;
			}
			if(current.prev != null) current.prev.next = current.next;
			if(current.next != null) current.next.prev = current.prev;

			listSize--;

			return true;
		}

	}

	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  The algorithm here is just a simple while loop and an if statement, the highest order of which is the the while loop, which 
	 *  has a n asymptotic run-time of theta(N).
	 */
	public void reverse()
	{
		if(!isEmpty()) {
		    DLLNode x = null;
		    for (int i = 0; x != tail; i++) {
		        if (i == 0) {
		            x = head;
		        } else {
		            x = x.prev;
		        }
		        DLLNode y = new DLLNode(null, x.prev, null);
		        x.prev = x.next;
		        x.next = y.prev;
		    }
		    DLLNode z = tail;
		    tail = head;
		    head = z;
		}

	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: TODO
	 *
	 * Justification:
	 *  TODO
	 */
	public void makeUnique()
	{
		//TODO
		int i = 0;
		int j;
		DLLNode temp = null;
		DLLNode current = head;
		while(current.next != null) 
		{
			while(i < listSize) 
			{
				if(current.data.equals(current.next.data)) 
				{
					deleteAt(i);
				}
				i++;
			}
			current = current.next;
		}
	}

	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 *  The algorithm calls the isEmpty function and contains an if else statement. Therefore, the asymptotic worst case
	 *  run-time is theta(1).
	 */
	public void push(T item) 
	{
		DLLNode newNode = new DLLNode(item, null, null);
		
		if(!isEmpty()) {		
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		}
		else {
			tail = newNode;
			head = newNode;
		}
		
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * 
	 * Worst-case asymptotic running time cost: Theta(1)
	 * 
	 * Justification:
	 *  Worst-case asymptotic running time cost: Similarly to the push function, this code contains if and else statements and
	 * calls the isEmpty function, as a result the run-time for this algorithm is theta(1)
	 */
	public T pop() 
	{
		if(!isEmpty()) 
		{
			T data = tail.data;
			if(tail.prev != null)
			{
				tail = tail.prev;
				tail.next = null;
			}
			else
			{
				head = tail = null;
			}

			return data;
		}
		return null;

	}

	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  The enqueue method here calls the push function, and therefore has the same asymptotic worst-case run-time as that 
	 *  method
	 */
	public void enqueue(T item) 
	{
		push(item);
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an enqueue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 *  The code calls the reverse method twice and the pop function in the algorithm, as a result the run-time is theta(N)
	 */
	public T dequeue() 
	{
		reverse();
		T item = pop();
		reverse();
		return item;
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  We know from the Java documentation that StringBuilder's append() method runs in Theta(1) asymptotic time.
	 *  We assume all other method calls here (e.g., the iterator methods above, and the toString method) will execute in Theta(1) time.
	 *  Thus, every one iteration of the for-loop will have cost Theta(1).
	 *  Suppose the doubly-linked list has 'n' elements.
	 *  The for-loop will always iterate over all n elements of the list, and therefore the total cost of this method will be n*Theta(1) = Theta(n).
	 */ 
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}


}
