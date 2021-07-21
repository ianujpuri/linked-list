package org.ds.algos.datasructures.node;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

public class Node<T> {
	
	T data;
	Node<T> next;
	
	public Node(T data) {
		this.data = data;
	}
	
	@Getter
	public T getData() {
		return this.data;
	}
	
	@Getter
	public Node<T> getNext() {
		return this.next;
	}
	
	@Setter
	public void setData(T data) {
		this.data = data;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	@Override
	public boolean equals(Object o) {
				
		if(o == null || !(o instanceof Node)) {
			return false;
		}
		
		if(this == o) {
			return true;
		}		
		
		Node obj = (Node)o;
		if(obj.data != null && obj.data.equals(this.data)) {			
			return true;
		} 
		
		return false;		
	}
	
}
