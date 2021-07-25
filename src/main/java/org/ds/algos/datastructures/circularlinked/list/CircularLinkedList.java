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
		// TODO Auto-generated method stub

	}

	public void removeData(T key) {
		// TODO Auto-generated method stub

	}

	public void printElements() {
		System.out.println();
		Node<T> pivot = this.head;
		for(int index = 0; index < size; index++) {
			System.out.print(pivot.getData()+" ");
			pivot = pivot.getNext();
		}
	}

	private int size() {
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
