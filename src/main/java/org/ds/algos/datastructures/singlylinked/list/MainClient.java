package org.ds.algos.datastructures.singlylinked.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ds.algos.datastructures.circularlinked.list.CircularLinkedList;
import org.ds.algos.datastructures.doublylinked.list.DoublyLinkedList;
import org.ds.algos.datastructures.unrolledlinked.list.UnrolledLinkedList;

public class MainClient {

	public static void main(String[] args) {

//		testSinglyLinkedList();
//		
//		testDoublyLinkedList();
//		
//		testCircularLinkedList();
		
		testUnrolledLinkedList();

	}

	static void testUnrolledLinkedList() {
		
		System.out.println(" UNROLLED LINKED LIST \n\n");
		UnrolledLinkedList<Integer> list = new UnrolledLinkedList<Integer>(10);
		
		list.add(10);
		list.add(20); list.add(40);
		list.add(30); list.add(40);
		list.add(40); list.add(40);
		list.add(40); list.add(40);
		list.add(40); list.add(40);
		list.add(40); list.add(40);
		list.add(40); list.add(40);
		list.add(40);
		list.add(40);
		list.add(40);
		list.add(0, 5);
		
		list.add(5, 50);		
		list.set(4, 26);		
		System.out.println(list);
		
		Iterator<Integer> itr = list.iterator();
		while(itr.hasNext()) {
			System.out.println("next : " + itr.next());
		}
		

		System.out.println(" index of 20 ?  " + list.indexOf(20));
		System.out.println(" contains 50 ? " + list.contains(50));
	}
	
	
	static void testSinglyLinkedList() {

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

	static void testDoublyLinkedList() {
		
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
	
	static void testCircularLinkedList() {


		CircularLinkedList<String> listCircular = new CircularLinkedList<String>();
		listCircular.addAtBeginning("Aishwarya");
		listCircular.addAtIndex("Rai", 1);
		listCircular.addAtIndex("Muah", 1);
		listCircular.addAtEnd("Bachchan");

		listCircular.printElements();

		listCircular.removeData("Muah");
		listCircular.removeAtIndex(2);
		listCircular.printElements();		

		listCircular.removeAtBeginning();
		listCircular.removeAtEnd();

		listCircular.printElements();

	}

}
