package org.ds.algos.datastructures.singlylinked.list;

import org.ds.algos.datastructures.doublylinked.list.DoublyLinkedList;

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
		
		
		// doubly link list
		DoublyLinkedList<Integer> intDoublylist = new DoublyLinkedList<Integer>();
		
		intDoublylist.addAtBeginning(3);
		intDoublylist.addAtBeginning(2);
		intDoublylist.addAtBeginning(1);
		
		intDoublylist.printElements();

		intDoublylist.addAtEnd(4);
		intDoublylist.addAtEnd(7);
		intDoublylist.printElements();
		
		intDoublylist.addAtBeginning(0);	
		
		intDoublylist.addAtIndex(8, 2);
		intDoublylist.printElements();
		intDoublylist.printElementsReverse();
		
		
		intDoublylist.removeAtBeginning();
		intDoublylist.removeAtEnd();
		intDoublylist.removeAtIndex(4);
		intDoublylist.printElements();
		intDoublylist.printElementsReverse();
		
		intDoublylist.removeData(2);
		intDoublylist.printElements();
		intDoublylist.printElementsReverse();
		
		System.out.println("\nsize : " + intDoublylist.size());
	}
}
