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
			this.tail = this.head;
			this.head.setNext(null);
			this.head.setPrev(null);			
		} else {
			this.head.setPrev(newNode);
			newNode.setNext(head);
			this.head = newNode;
		}

		this.size += 1;
	}

	public void addAtEnd(T data) {
		if(isEmpty()) { 			
			addAtBeginning(data); 			
		} else {

			DoublyNode<T> newNode = new DoublyNode<T>(data);
			newNode.setPrev(tail);
			tail.setNext(newNode);
			tail = newNode;
		}		
		this.size += 1;

	}

	public void addAtIndex(T data, int index) {
		if(isEmpty() || index == 0) {
			addAtBeginning(data);
			return;
		} else if(index >= this.size) {
			addAtEnd(data);
			return;
		} 

		DoublyNode<T> newNode = new DoublyNode<T>(data);
		
		DoublyNode<T> bwd = this.tail;		
		DoublyNode<T> fwd = this.head;
		
		if((size - index) >= index) {			
			for(int pivot = 1; pivot < index; pivot++) {							
				fwd = fwd.getNext();
			}

			newNode.setPrev(fwd);
			fwd.getNext().setPrev(newNode);
			newNode.setNext(fwd.getNext());
			
			fwd.setNext(newNode);
		} else {
			for(int pivot = this.size-1; pivot > index; pivot--) {
				bwd = bwd.getPrev();
			}
			
			newNode.setNext(bwd);
			bwd.getPrev().setNext(newNode);
			newNode.setPrev(bwd.getPrev());
			
			bwd.setPrev(newNode);
			
		}
		
		this.size += 1;
		
	}

	public void removeAtBeginning() {


	}

	public void removeAtEnd() {


	}

	public void removeAtIndex(int index) {


	}

	public void removeData(T key) {


	}

	public void printElements() {
		if(isEmpty()) { return; }
		System.out.println("\n\n");
		DoublyNode<T> pivot = this.head;
		while(pivot != null) {
			System.out.print(pivot.getData() + " ");
			pivot = pivot.getNext();
		}

	}

	public void printElementsReverse() {
		if(isEmpty()) { return; }

		System.out.println();
		DoublyNode<T> pivot = this.tail;
		while(pivot != null) {
			System.out.print(pivot.getData() + " ");
			pivot = pivot.getPrev();
		}

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
