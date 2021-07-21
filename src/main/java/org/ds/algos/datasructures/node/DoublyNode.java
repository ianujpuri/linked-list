package org.ds.algos.datasructures.node;

public class DoublyNode<T> {

	private T data;
	private DoublyNode<T> next;
	private DoublyNode<T> prev;

	public DoublyNode() {

	}

	public DoublyNode(T data) {
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public DoublyNode<T> getNext() {
		return next;
	}

	public void setNext(DoublyNode<T> next) {
		this.next = next;
	}

	public DoublyNode<T> getPrev() {
		return prev;
	}

	public void setPrev(DoublyNode<T> prev) {
		this.prev = prev;
	}


	public boolean equals(Object o) {
		if(o == null || !(o instanceof DoublyNode)) {
			return false;
		}
		
		if(o == this) {
			return true;
		}
				
		DoublyNode node = (DoublyNode) o;
		if(node.getData() != null && node.getData().equals(data)) {
			return true;
		}
		
		return false;
	}
}
