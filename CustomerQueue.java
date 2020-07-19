package eurovision;

/**
 * A queue class, implemented as a linked list.
 * The nodes of the linked list are implemnted as the CustomerElement class.
 * 
 * IMPORTANT: you may not use any loops/recursions in this class.
 */
public class CustomerQueue {

	CustomerElement first;
	CustomerElement last;
	
	/**
	 * Constructs an empty queue
	 */
	public CustomerQueue(){
		first = null;
		last = null;
	}
	
	/**
	 * Removes and returns the first element in the queue
	 * 
	 * @return the first element in the queue
	 *  
	 */
	public CustomerElement dequeue(){
		if ( this.peek() == null ) {
			return null;
		}
		else{
			CustomerElement temp = new  CustomerElement(first.c);
				this.first = first.next;
				if(first == null) {
					last = null;
				}
			
				return temp;		
			}
	}
	
	/**
	 * Returns and does not remove the first element in the queue
	 * 
	 * @return the first element in the queue
	 */
	public CustomerElement peek(){
		return this.first;
	}
	
	/**
	 * Adds a new element to the back of the queue
	 * 
	 * @param node
	 */
	public void enqueue(CustomerElement node){
		if (last != null ) {
				last.next = node;
				node.prev = last;
				last = node;
		}
		else {
			this.first = node;
			last = first;
		}
	}
	
	public void remove(CustomerElement c) {     //removes a specific customer from the queue
			
			if ( first == null ) {    
				return;
			}
			if(c.c.compareTo(first.c) == 0 ) {  //if the Customer to remove is the first in the queue
				first = first.next;
					if(first == null) {
						last = null;
					}
				c = null;
				return;
			}
			if(c.c.compareTo(last.c) == 0) {  //if the Customer to remove is the last in the queue
				last = last.prev;
				last.next = null;
					if(last.prev == null) {
						last = first;
					}
				c = null;
			return;
			}
			c.next.prev = c.prev;
			c.prev.next = c.next;
			c = null;
	}

}
