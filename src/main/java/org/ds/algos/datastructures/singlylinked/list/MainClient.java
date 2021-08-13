package org.ds.algos.datastructures.singlylinked.list;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.ds.algos.datastructures.circularlinked.list.CircularLinkedList;
import org.ds.algos.datastructures.doublylinked.list.DoublyLinkedList;
import org.ds.algos.datastructures.unrolledlinked.list.UnrolledLinkedList;

public class MainClient {

	public static void main(String[] args) {

		testSinglyLinkedList();
//		
//		testDoublyLinkedList();
//		
//		testCircularLinkedList();
		
//		testUnrolledLinkedList();

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

		LinkedList<Integer> list = new LinkedList<Integer>();

		list.addAtBeginning(1);
		list.addAtEnd(2);
		list.addAtEnd(3);
		list.addAtEnd(4);
		list.addAtEnd(5);
		list.addAtEnd(6);

		list.printElements(); //
		System.out.println("nth node from tail : " + list.findNthNodeFromTail(2));
	
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
