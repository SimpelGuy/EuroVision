package eurovision;

/**
 * A heap, implemnted as an array.
 * The elemnts in the heap are instances of the class CustomerElement,
 * and the heap is ordered according to the Customer instances wrapped by those objects.
 * 
 * IMPORTANT: Except in the constuctor no single function may loop/recurse through all elements in the heap.
 * 			  You may only use loops which look at a small fraction of the heap.
 * 
 *
 */
public class CustomerHeap {
	
	/*
	 * The array in which the elements are kept according to the heap order.
	 * The following must always hold true:
	 * 			if i < size then heap[i].heapIndex == i
	 */
	CustomerElement[] heap;
	int size; // the number of elements in the heap, neccesarilty size <= heap.length
	
	/**
	 * Creates an empty heap which can initially accommodate 10 elements.
	 */
	
	
	public CustomerHeap(){
		heap = new CustomerElement[11];
		size = 0;
	}
	
    /**
     * Returns the size of the heap.
     *
     * @return the size of the heap
     */
    public int size(){
		return this.size;
    }
    
    /**
     * Inserts a given element into the heap.
     *
     * @param e - the element to be inserted.
     */
    public void insert(CustomerElement e){
      
    	if (this.size == 0) {
    		this.heap[1] = e;
    		heap[1].heapIndex = 1;
    		size++;
    		return;
    	}
    	if (this.size + 1 == heap.length ) {
    		heap = this.resize();
    	}
    	size++;
     
    	heap[size] = e;
    	heap[size].heapIndex = size;
    	shiftUp( size );
     
     }
    public void shiftUp(int child) { //helper function to find the right place of a new customer in the heap
    	if(child / 2 <= 0 ) {
    		return;
    	}
    	int parent = child / 2;
    	if( heap[child].c.compareTo(heap[parent].c) > 0 ){
    			swap(child , parent );
    			shiftUp(parent);
    	}
    
    return;
    }
    	
    
    public void swap(int child , int parent) { //helper function that does a switch between customers in the queue
    	CustomerElement temp = heap[parent];
    	temp.heapIndex = child;
    	heap[child].heapIndex = parent;
    	heap[parent] = heap[child];
    	heap[child] = temp;
    	
    }
    

	
	/**
	 * Returns and does not remove the element which wraps the customer with maximal priority.
	 * 
	 * @return the element which wraps the customer with maximal priority.
	 */
    public CustomerElement findMax(){
		if(size == 0) {
			throw new IllegalArgumentException("Heap is empty");
		}
	return heap[1];
    }
    
	/**
	 * Returns and removes the element which wraps the customer with maximal priority.
	 * 
	 * @return the element which wraps the customer with maximal priority.
	 */
    public CustomerElement extractMax() {
    	if(size == 0) {
    		throw new IllegalArgumentException("Heap is empty");
		}
    		CustomerElement max = heap[1];
    	if (size == 1) {
    			heap[1] = null;
    			size--;
    			return max;
    	}
    	heap[1] = heap[size];
    	heap[1].heapIndex = 1;
    	heap[size] = null;
    	size--;
    	shiftDown(1);
    	
	return max;
    }
   
    private void shiftDown (int parentIndex) {
 	   int leftChild = 2 * parentIndex;
 	   int rigthChild = 2 * parentIndex + 1;
 	   int largest = parentIndex;
 	   		if(leftChild <= size && heap[leftChild].c.compareTo(heap[parentIndex].c) > 0 ) {
 	   				largest = leftChild;
    		}		
 	     	if(rigthChild <= size && heap[rigthChild].c.compareTo(heap[largest].c) > 0 ) {
				largest = rigthChild;
 	     	}		
 	   		if(largest != parentIndex ) {
 	   			swap( largest , parentIndex);
 	   			shiftDown (largest);
 	   		}
 	 
    }
    	
    
    
    
    
    
    /**
	 * Returns an array twice as large as the old array had in order to make more room for the customer queue
	 * 
	 * @return an array with double space as the old array
	 */
   private CustomerElement[] resize() {
	   CustomerElement[] biggerHeap = new CustomerElement[(heap.length-1) * 2 + 1];
		  for(int i = 1 ; i < heap.length ; i++) {
			  biggerHeap[i] = heap[i];
		  }
		  heap = null;								//Recycling the memory in the computer
		  
		  return biggerHeap;
   }
    
    /**
     * Removes the element located at the given index.
     * 
	 * Note: this function is not part of the standard heap API.
	 *		 Make sure you understand how to implement it why it is required.
	 *       There are several ways this function could be implemented. 
	 * 		 No matter how you choose to implement it, you need to consider the different possible edge cases.
     * @param index
     */
   public void remove(int index){
	   if(index > size) { //index not legal
			return;
		}
	   if (index == size ) { 
		   heap[size] = null;
		   size--;
		   return;
	   }
	   if(index >= size / 2 ) {
		   heap[index] = heap[size];
		   heap[index].heapIndex = index;
		   heap[size] = null;
		   size--;
		   shiftUp(index);
		  return;
	   }
	   if(index == 1 ) { 
		   heap[index] = heap[size];
		   heap[index].heapIndex = index;
		   heap[size] = null;
		   size--;
		   shiftDown(index);
		  return;
	   }
	   if(index < (size / 2) && index > 1  ) {
		   heap[index] = heap[size];
		   heap[index].heapIndex = index;
		   heap[size] = null;
		   size--;
		   		if(index * 2 > size ) {					//no children
		   			shiftUp(index);  
		   		}
		   		else { 									//has parents and children
		   			if( heap[index].c.compareTo(heap[index/2].c) > 0 ) { 								//checks priority with father
		   				shiftUp(index);
		   				return;
		   			}
		   			if(heap[index * 2].c.compareTo(heap[index].c) > 0 ) {								//checks priority with leftChild
		   				shiftDown(index);
		   				return;
		   			}
		   			if((index * 2 + 1) <= size  &&  heap[index * 2 + 1].c.compareTo(heap[index].c) > 0 ) {		//checks priority with rightChild
		   				shiftDown(index);
		   				return;
		   			}
		   		}
	   }
   }
   
  

  
    public static void main (String[] args){
    	/*
    	 * A basic test for the heap.
    	 * You should be able to run this before implementing the queue.
    	 * 
    	 * Expected outcome: 
    	 * 	customer: Netta, priority: 10
		 *	customer: Liran, priority: 20
		 *	customer: Liran, priority: 20
		 *	customer: Netta, priority: 10
		 *	customer: Dana, priority: 3
		 *	customer: Izhar, priority: 2
    	 * 
    	 */
    	CustomerHeap heap = new CustomerHeap();
    	Customer a = new Customer(10, "Netta");
    	Customer b = new Customer(2, "Izhar");
    	Customer c = new Customer(3, "Dana");
    	Customer d = new Customer(20, "Liran");
    	
    	heap.insert(new CustomerElement(a));
    	System.out.println(heap.findMax());
    	
    	heap.insert(new CustomerElement(b));
    	heap.insert(new CustomerElement(c));
    	heap.insert(new CustomerElement(d));
    	System.out.println(heap.findMax());
     	System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());
        System.out.println(heap.extractMax());
    	System.out.println(heap.extractMax());

    }

    }
   
   

