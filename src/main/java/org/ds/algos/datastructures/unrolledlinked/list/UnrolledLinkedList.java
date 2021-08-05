package org.ds.algos.datastructures.unrolledlinked.list;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

import org.ds.algos.datasructures.node.BlockNode;

public class UnrolledLinkedList<E> extends AbstractList<E> implements List<E>, Serializable {

	/**
	 * serial version UID
	 */
	private static final long serialVersionUID = 97287412866437955L;


	private int size;
	private int nodeCapacity;
	private BlockNode<E> head;
	private BlockNode<E> tail;

	private static final short MIN_CAPACITY = 8;

	public UnrolledLinkedList(int capacity) throws IllegalArgumentException {

		if(capacity < MIN_CAPACITY) {
			throw new IllegalArgumentException("Capacity must be more than 8");
		}

		this.size = 0;
		this.nodeCapacity = capacity;
		this.head = new BlockNode<E>(capacity);
		this.tail = this.head;
	}

	public boolean add(E element) {
		insertIntoNode(this.tail, this.tail.numElements, element);
		return true;
	}
	
	@Override
	public void add(int index, E element) {
		
		if(index < 0 || index > this.size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		
		BlockNode<E> node;
		int ptr;
		//search from left to right 		
		if((this.size - index) >= index) {
			node = this.head;
			for(ptr = 0; (ptr < (index - node.numElements)); ptr+= node.numElements) {
				node = node.next;				
			}						
		} else {
			node = this.tail;
			for(ptr = this.size; (ptr -= node.numElements) > index; ) {
				node = node.prev;
			}
		}
		
		System.out.println(" index : " + index + " ptr : " + ptr);
		insertIntoNode(node, index - ptr, element);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		
		int ptr = 0;
		BlockNode<E> node = this.head;
		if((this.size - index >= index)) {
			//add to ptr the number of elements current node has
			//such that once the node is identified, correct position can 
			//be calculated using (index - ptr) equation
			for( ;(ptr+node.numElements) < index; ptr+=node.numElements) {
				node = node.next;
			}
			
		} else {
			node = this.tail;
			//subtract from ptr the number of elements current node has
			//such that once the node is identified, correct position can 
			//be calculated using (index - ptr) equation
			for(ptr = this.size; (ptr -= node.numElements) > index; ) { 
				node = node.prev;
			}
		}
		
		int position = index - ptr;
		
		E oldElement = (E) node.elements[position];		
		node.elements[position] = element;
		modCount++;
		
		return oldElement;
	}
	
	public void printElements() {
		if(isEmpty()) {
			System.out.println("List is Empty");
			return;
		}
		
		BlockNode<E> fwd = this.head;
		
		while(fwd != null) {
			
			for(int index = 0; index < fwd.numElements; index++) {
				System.out.print(fwd.elements[index]+" ");
			}
			System.out.println("\n");
			fwd = fwd.next;
		}
		
	}

	@Override
	public Iterator<E> iterator() {

		return new ULLIterator<E>();
	}

	@Override
	public E get(int index) {

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

	private void insertIntoNode(BlockNode<E> node, int ptr, E element) {

		//if capacity is full for the current tail node
		//half of the elements have to be shifted to the new node
		if(node.numElements == this.nodeCapacity) {
			BlockNode<E> newNode = new BlockNode<E>(this.nodeCapacity);
			
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
