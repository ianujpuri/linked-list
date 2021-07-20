package org.ds.algos.datastructures.doublylinked.list;

import org.ds.algos.datasructures.node.DoublyNode;
import org.ds.algos.datasructures.node.LinkInterface;

public class DoublyLinkedList<T> implements LinkInterface<T> {

	private DoublyNode<T> head;
	private DoublyNode<T> tail;
	private int size;
	
	public void addAtBeginning(T data) {
		DoublyNode<T> newNode = new DoublyNode<T>(data);
		if(isEmpty()) {
			this.head = newNode;
			this.head.setNext(null);
			this.head.setPrev(null);			
		} else {
			this.head.setPrev(newNode);
			newNode.setNext(head);			
		}
		
		this.size += 1;
	}

	public void addAtEnd(T data) {

	
		
	}

	public void addAtIndex(T data, int index) {
		// TODO Auto-generated method stub
		
	}

	public void removeAtBeginning() {
		// TODO Auto-generated method stub
		
	}

	public void removeAtEnd() {
		// TODO Auto-generated method stub
		
	}

	public void removeAtIndex(int index) {
		// TODO Auto-generated method stub
		
	}

	public void removeData(T key) {
		// TODO Auto-generated method stub
		
	}

	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		if(this.head == null || size == 0) {
			System.out.println("List is empty.");
			return true;
		}
		
		return false;
	}
}
