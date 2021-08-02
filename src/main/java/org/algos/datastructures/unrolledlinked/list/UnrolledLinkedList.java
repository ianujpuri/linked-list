package org.algos.datastructures.unrolledlinked.list;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;

import org.ds.algos.datasructures.node.BlockNode;

public class UnrolledLinkedList<T> extends AbstractList<T> implements Iterator<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int size;
	private BlockNode<T> next;
	private BlockNode<T> prev;
	
	
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public T next() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public T get(int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
}
