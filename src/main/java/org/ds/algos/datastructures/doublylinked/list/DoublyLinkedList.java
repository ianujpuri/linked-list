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

		if((size - index) >= index) {
			DoublyNode<T> fwd = this.head;
			for(int pivot = 1; pivot < index; pivot++) {							
				fwd = fwd.getNext();
			}

			newNode.setPrev(fwd);
			fwd.getNext().setPrev(newNode);
			newNode.setNext(fwd.getNext());

			fwd.setNext(newNode);
		} else {
			DoublyNode<T> bwd = this.tail;		
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

		if(isEmpty()) { return; }

		DoublyNode<T> delNode = this.head;

		this.head = delNode.getNext();
		this.head.setPrev(null);

		//help GC
		delNode.setNext(null);
		delNode.setPrev(null);
		delNode.setData(null);

		this.size -= 1;
	}

	public void removeAtEnd() {

		if(isEmpty()) { return; }

		DoublyNode<T> delNode = this.tail;

		this.tail = delNode.getPrev();
		this.tail.setNext(null);

		//help GC
		delNode.setPrev(null);
		delNode.setNext(null);
		delNode.setData(null);

		this.size -= 1;
	}

	public void removeAtIndex(int index) {

		if(isEmpty()) { return; } 

		if(index == 0) {
			removeAtBeginning();
			return;
		} else if( index >= this.size) {
			removeAtEnd();
		} else {

			if((this.size - index) >= index) {
				DoublyNode<T> fwd = this.head;
				for(int pivot = 1; pivot <index; pivot++) {
					fwd = fwd.getNext();
				}

				DoublyNode<T> delNode = fwd.getNext();
				fwd.setNext(delNode.getNext());
				delNode.getNext().setPrev(fwd);				
			}  else {
				DoublyNode<T> bwd = this.tail;
				for(int pivot = this.size-1; pivot > index; pivot--) {
					bwd = bwd.getPrev();					
				}

				DoublyNode<T> delNode = bwd.getPrev();
				bwd.setPrev(delNode.getPrev());
				delNode.getPrev().setNext(bwd);
			}
		}

		this.size -= 1;
	}

	public void removeData(T key) {

		if(isEmpty()) { return; } 

		DoublyNode<T> fwd = this.head;

		if(this.head.getData().equals(key)) {
			removeAtBeginning();
			return;
		} else if(this.tail.getData().equals(key)) {
			removeAtEnd();
			return;
		} else {

			while(fwd != null && !fwd.getData().equals(key) ) {
				fwd = fwd.getNext();
			}

			if(fwd != null) {
				fwd.getPrev().setNext(fwd.getNext());
				fwd.getNext().setPrev(fwd.getPrev());

				//help GC
				fwd.setData(null);
				fwd.setNext(null);
				fwd.setPrev(null);

				this.size -= 1;
			}
		}
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
