package Assignment01;

public class LinkedList {

    private Node first;
    private int size;
    
    public void add(int vertex, int weight) {
    	if(first == null) {
    	    first = new Node(vertex, weight, null);
    	    
    	} else {
    		
    		add(vertex, weight, first);
    	}
    	size++;
    }
    
    private void add(int vertex, int weight, Node n) {
    	
    	
    	if(n.next == null) {
    		n.next = new Node(vertex, weight, null);
    	} else {
    		add(vertex, weight, n.next);
    	}
    	
    }

    public void addFront(int vertex, int weight) {
    	if(first == null) {
    	    first = new Node(vertex, weight, null);
    	    
    	} else {
    		
    		Node temp = first;
    		first = new Node(vertex, weight, temp);
    	}
    	size++;
    }
    
//    public void remove(int index) {
//    	if(index < 0 || index > size) {
//    		throw new IndexOutOfBoundsException();
//    	} 
//    	
//    	if(isEmpty()) {
//    		first = first.next;
//    	} else if(index == 0) {
//    		first = first.next;
//    	} else{
//    		remove(index, 0, first);
//    	}
//    	size--;
//    }
//    
//    private void remove(int index, int currentIndex, Node n) {
//    	if(currentIndex == index - 1) {
//    		n.next = n.next.next;
//    	} else {
//    		
//    	    remove(index, currentIndex + 1, n.next);
//    	} 
//    }
//     
    public int getVertex(int index) {
    	if(index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException(); 
    	}
    	
    	int data = getVertex(index, 0, first);
      	 
    	return data;

    }
    
    private int getVertex(int index, int currentIndex, Node n) {
        
    	if(!(index == currentIndex)){
    		
        	return getVertex(index, currentIndex + 1, n.next);
    	} else {
    		return n.vertex;
    	}
    	
    }
    
    public int getWeight(int index) {
    	if(index < 0 || index >= size) {
    		throw new IndexOutOfBoundsException(); 
    	}
    	
    	int data = getWeight(index, 0, first);
      	 
    	return data;

    }
    
    private int getWeight(int index, int currentIndex, Node n) {
        
    	if(!(index == currentIndex)){
    		
        	return getWeight(index, currentIndex + 1, n.next);
    	} else {
    		return n.weight;
    	}
    	
    }
    
//    public int indexOf(E value) {
//    	if(isEmpty()) {
//    		return -1;
//    	} else {
//    		return indexOf(value, 0, first);
//    	}
//    } 
//    
//    private int indexOf(E value, int currentIndex, Node n) {
//    	if(value.compareTo(n.data) == 0) {
//    		return currentIndex;
//    	} else if(n.next == null){
//            return -1;
//    	} else {
//    		return indexOf(value, currentIndex + 1, n.next); 
//    	}
//    	
//    }
    
    public boolean isEmpty() {
        if(size == 0) {
        	return true;
        } else { 
        	return false;
        }
    }
    
    public int size() {
    	return this.size;
    }
    
    public String toString() {
    	String list = "";
    	
    	if(!isEmpty()) {
    		list += toString(first); 
    	}  
    	
    	return "[" + list + "]";
    	
    }
    
    private String toString(Node n) {
    	
        if((n.next == null)) {
    		return n.vertex+ "";
    	} else {
    		return n.vertex + ", " + toString(n.next);
    	}
        
    }
    
    
    
    private class Node {
    	private int vertex;
    	private int weight;
    	private Node next;
    	
    	public Node(int vertex, int weight, Node next) {
    		this.vertex = vertex;
    		this.weight = weight;
    		this.next = next;
    	}
    }
}

