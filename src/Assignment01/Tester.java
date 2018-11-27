package Assignment01;

public class Tester {

	
	public static void main(String[] args) {
		
		LinkedList list = new LinkedList();
		list.add(5, 10);
		list.add(6, 10);
		list.add(7, 15);
		
		
		System.out.println(list.getVertex(0));
		System.out.println(list.getWeight(0));
		System.out.println(list.toString());
		
	}
}
