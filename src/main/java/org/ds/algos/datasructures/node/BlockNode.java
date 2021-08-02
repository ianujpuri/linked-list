package org.ds.algos.datasructures.node;

public class BlockNode<T> {

	private int numElements;
	private Object elements[];
	private BlockNode<T> next;
	private BlockNode<T> prev;	
	
	
	public BlockNode(int capacity) {
		this.elements = new Object[capacity];
		this.numElements = 0;
		this.next = null;
		this.prev = null;
	}
	
}
