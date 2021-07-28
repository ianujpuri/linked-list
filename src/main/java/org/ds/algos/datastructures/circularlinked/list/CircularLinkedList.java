package org.ds.algos.datastructures.circularlinked.list;

import org.ds.algos.datasructures.node.LinkInterface;
import org.ds.algos.datasructures.node.Node;

public class CircularLinkedList<T> implements LinkInterface<T> {

	private Node<T> head;
	private Node<T> tail;

	private int size;

	public void addAtBeginning(T data) {
		Node<T> newNode = new Node<T>(data);
		if(isEmpty()) {
			this.head = newNode;
			this.head.setNext(head);
			this.tail = head;
		} else {
			newNode.setNext(this.head);
			this.head = newNode;
		}

		this.size += 1;
	}

	public void addAtEnd(T data) {

		if(isEmpty()) {
			addAtBeginning(data);
			return;
		} else {
			Node<T> newNode = new Node<T>(data);
			newNode.setNext(this.head);
			this.tail.setNext(newNode);
			this.tail = newNode;
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
		} else {
			Node<T> fwd = this.head;
			for(int pivot = 1; pivot < index; pivot++) {
				fwd = fwd.getNext();
			}

			Node<T> newNode = new Node<T>(data);
			newNode.setNext(fwd.getNext());
			fwd.setNext(newNode);			
		}

		this.size += 1;
	}

	public void removeAtBeginning() {
		if(isEmpty()) {
			return;
		}

		Node<T> delNode = this.head;

		this.head = delNode.getNext();		
		this.tail.setNext(this.head);

		//help GC
		delNode.setData(null);
		delNode.setNext(null);
		delNode = null;

		this.size -= 1;
	}

	public void removeAtEnd() {
		if(isEmpty()) {
			return;
		}

		if(this.size == 1) {
			this.head.setData(null);
			this.head.setNext(null);
			this.tail = null;
			this.head = null;

		} else {

			Node<T> fwd = this.head;
			while(fwd.getNext() != tail) {
				fwd = fwd.getNext();
			}

			Node<T> delNode = fwd.getNext();
			fwd.setNext(delNode.getNext());
			
			this.tail = fwd;
			this.tail.setNext(this.head);

		}
		this.size -= 1;
	}

	public void removeAtIndex(int index) {
		if(isEmpty()) { return; }
		
		if(index == 0) {
			removeAtBeginning();
			return;
		} else if(index >= this.size) {
			removeAtEnd();
			return;
		} else {
			
			Node<T> temp = this.head;
			for(int pivot = 1; pivot < index; pivot++) {
				temp = temp.getNext();
			}
			
			Node<T> delNode = temp.getNext();
			temp.setNext(delNode.getNext());
			
			//help GC
			delNode.setData(null);
			delNode.setNext(null);
			delNode = null;
			
			this.size -= 1;
		}
		
	}

	public void removeData(T key) {
		
		if(isEmpty()) { return; }

		if(this.head.getData().equals(key)) {
			removeAtBeginning();
			return;
		} else if (this.tail.getData().equals(key)) {
			removeAtEnd();
			return;
		} else {
			
			Node<T> fwd = this.head.getNext();
			Node<T> prev = this.head;
			while(!fwd.getData().equals(key) && fwd.getNext() != this.tail) {
				prev = fwd;
				fwd = fwd.getNext();				
			}
			
			if(fwd.getData().equals(key)) {
				prev.setNext(fwd.getNext());
				
				//help GC
				fwd.setData(null);
				fwd.setNext(null);
				fwd = null;
				
				this.size -= 1;
			}
		}		
	}

	public void printElements() {
		System.out.println();

		if(isEmpty()) {
			return;
		}
		Node<T> pivot = this.head;
		for(int index = 0; index < size; index++) {
			System.out.print(pivot.getData()+" ");
			pivot = pivot.getNext();
		}
	}

	public int size() {
		return this.size;
	}

	private boolean isEmpty() {
		if(this.size == 0 || this.head ==null) {
			System.out.println("List is empty.");
			return true;
		}
		return false;
	}
}
