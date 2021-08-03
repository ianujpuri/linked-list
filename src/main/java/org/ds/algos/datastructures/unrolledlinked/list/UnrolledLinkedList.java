package org.ds.algos.datastructures.unrolledlinked.list;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

import org.ds.algos.datasructures.node.BlockNode;

public class UnrolledLinkedList<T> extends AbstractList<T> implements List<T>, Serializable {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 97287412866437955L;


	private int size;
	private int nodeCapacity;
	private BlockNode<T> head;
	private BlockNode<T> tail;

	private static final short MIN_CAPACITY = 8;

	public UnrolledLinkedList(int capacity) throws IllegalArgumentException {

		if(capacity < MIN_CAPACITY) {
			throw new IllegalArgumentException("Capacity must be more than 8");
		}

		this.size = 0;
		this.head = new BlockNode<T>(capacity);
		this.tail = this.head;
	}

	public boolean add(T element) {
		insertIntoNode(this.tail, this.tail.numElements, element);
		return true;
	}
	
	@Override
	public void add(int index, T element) {
		
		//search from left to right 		
		if((this.size - index) >= index) {
			
		} else {
			
		}
		
	}

	@Override
	public Iterator<T> iterator() {

		return new ULLIterator<T>();
	}

	@Override
	public T get(int index) {

		return null;
	}

	@Override
	public int size() {

		return this.size;
	}

	@Override
	public boolean isEmpty() {	
		return (this.size == 0 || this.head == null);
	}

	private void insertIntoNode(BlockNode<T> node, int ptr, T element) {

		//if capacity is full for the current tail node
		//half of the elements have to be shifted to the new node
		if(node.numElements == this.nodeCapacity) {
			BlockNode<T> newNode = new BlockNode<T>(this.nodeCapacity);
			
			int elementsToBeMoved = this.nodeCapacity / 2;
			int startIndex = this.nodeCapacity - elementsToBeMoved;
			
			for(int i = 0; i < elementsToBeMoved; i++) {
				newNode.elements[i] = node.elements[startIndex + i];
				node.elements[startIndex + i] = null;
			}
			
			//update size of the node
			node.numElements -= elementsToBeMoved;
			newNode.numElements = elementsToBeMoved;
			
			newNode.next = node.next;			
			newNode.prev = node;
			
			if(node.next != null) {
				node.next.prev = newNode;				
			} 
			node.next = newNode;
			if(node == this.tail) {
				this.tail = newNode;
			}
			
			//check whether the element should be inserted into
			//the original node or into the new node
			if(ptr > node.numElements) {
				node = newNode;
				ptr -= node.numElements; //to calculate the index where the element has to be inserted.
			}
		}
		
		for(int i = node.numElements; i > ptr ;i--) {
			node.elements[i] = node.elements[i-1];			
		}
		
		node.elements[ptr] = element;
		node.numElements++;
		size++;
		modCount++;				
	}



	static class BlockNode<T> {

		int numElements;
		Object elements[];
		BlockNode<T> next;
		BlockNode<T> prev;	


		public BlockNode(int capacity) {
			this.elements = new Object[capacity];
			this.numElements = 0;
			this.next = null;
			this.prev = null;
		}

	}

	//iterator implementation
	static class ULLIterator<T> implements Iterator<T> {

		public boolean hasNext() {

			return false;
		}

		public T next() {

			return null;
		}

	}
}
