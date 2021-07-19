package org.ds.algos.datastructures.linkedlist;

public class MainClient {

	public static void main(String[] args) {
		LinkedList<String> list = new LinkedList<String>();
		
		list.addAtBeginning("A");
		System.out.println(" size after A: " + list.size());
		list.addAtEnd("C");
		System.out.println(" size after C: " + list.size());
		list.addAtIndex("B", 1);
		System.out.println(" size after B: " + list.size());
		
		list.printElements(); // A B C
		
//		list.removeAtBeginning();
//		list.printElements(); //B C
//		
//		list.removeAtIndex(0);
//		list.printElements();
//		
//		
//		list.removeAtIndex(0);
//		list.printElements();
		
		list.removeData("A");
		list.printElements();
		
		list.removeData("C");
		list.printElements();
		
		list.removeData("B");
		list.printElements();
	}
}
