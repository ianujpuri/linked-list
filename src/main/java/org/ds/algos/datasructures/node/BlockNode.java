package org.ds.algos.datasructures.node;

public class BlockNode<T> {

	private int numElements;
	private T elements[];
	private BlockNode<T> next;
	
	public int getNumElements() {
		return numElements;
	}
	
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}
	
	public T[] getElements() {
		return elements;
	}
	
	public void setElements(T[] elements) {
		this.elements = elements;
	}
	
	public BlockNode<T> getNext() {
		return next;
	}
	
	public void setNext(BlockNode<T> next) {
		this.next = next;
	}
	
}
