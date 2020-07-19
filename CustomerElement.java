package eurovision;

/**
 * A wrapper class for Customer.
 * This class is intended to both be used as a node in a doubly-linked list,
 * and as an element in a heap.
 * 
 *
 */
public class CustomerElement {

	CustomerElement next; //the customer element which comes after this in the linked list, null if this element is first.
	CustomerElement prev; //the customer element which comes before this in the linked list, null if this element is last.
	int heapIndex;        //the index of this element in the heap (implemented as an array).
	Customer c;
	
	/**
	 * A standard consturctor for the class.
	 * Is intended for use before the element is inserted into the list/heap.
	 * 	 */
	public CustomerElement(Customer c){
		this.next = null;
		this.prev = null;
		this.heapIndex = 0;
		this.c = c;
	}
	
	public String toString(){
		return c.toString();
	}

}
