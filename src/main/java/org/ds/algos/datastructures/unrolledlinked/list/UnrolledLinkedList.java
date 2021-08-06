package org.ds.algos.datastructures.unrolledlinked.list;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;

import javafx.util.Pair;

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

		/*
		 * BlockNode<E> node; int ptr; //search from left to right if((this.size -
		 * index) >= index) { node = this.head; for(ptr = 0; (ptr < (index -
		 * node.numElements)); ptr+= node.numElements) { node = node.next; } } else {
		 * node = this.tail; for(ptr = this.size; (ptr -= node.numElements) > index; ) {
		 * node = node.prev; } }
		 * 
		 * System.out.println(" index : " + index + " ptr : " + ptr);
		 */		
		Pair<BlockNode<E>, Integer> nodePtrPair = getNodeAndPointerToIndex(index);		
		insertIntoNode(nodePtrPair.getKey(), index - nodePtrPair.getValue(), element);

	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E element) {
		if(index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}

		Pair<BlockNode<E>, Integer> nodePtrPair = getNodeAndPointerToIndex(index);		

		int position = index - nodePtrPair.getValue();

		BlockNode<E> node = nodePtrPair.getKey();
		E oldElement = (E) node.elements[position];		
		node.elements[position] = element;
		modCount++;

		return oldElement;
	}

	@Override
	public boolean remove(Object o) {

		if(isEmpty()) { return false; }

		BlockNode<E> node = this.head;
		int index = 0;
		if(o == null) {
			while(node != null) {

				for(int ptr = 0; ptr < node.numElements; ptr++) {
					if(node.elements[ptr] == null) {
						removeNode(node, index + ptr);
						return true;
					}
				}

				index += node.numElements;
				node = node.next;
			}
		} else {

			while(node != null) {

				for(int ptr = 0; ptr < node.numElements; ptr++) {
					if(node.elements[ptr] == null) {
						removeNode(node, index + ptr);
						return true;
					}
				}

				index += node.numElements;
				node = node.next;
			}
		}

		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {

		if(index < 0 || index >  this.size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		
		int ptr = 0;
		BlockNode<E> node = this.head;
		
		if((this.size - index) > index) {
			while(ptr + node.numElements <= index) {
				ptr += node.numElements;
				node = node.next;
			}
			
		} else {
			node = this.tail;
			ptr = this.size;
			while((ptr -= node.numElements) > index) {
				node = node.prev;
			}
		}
		
		E element = (E) node.elements[index-ptr];
		removeNode(node, index - ptr);
		return element;
		
	}

	@Override
	public int indexOf(Object o) {

		if(isEmpty()) { return -1; }

		int index = 0;
		BlockNode<E> node = this.head;

		if(o == null) {

			while(node != null) {
				for(int ptr = 0; ptr < node.numElements; ptr++) {
					if(node.elements[ptr] == null) {
						return (index + ptr);
					}					
				}

				index += node.numElements;
				node = node.next;
			}

		} else {
			while(node != null) {
				for(int ptr = 0; ptr < node.numElements; ptr++) {
					if(o.equals(node.elements[ptr])) {
						return (index + ptr);
					}
				}

				index += node.numElements;
				node = node.next;
			}
		}

		return -1;
	}

	@Override
	public boolean contains(Object o) {
		return (indexOf(o) != -1);
	}

	@Override
	public String toString() {
		if(isEmpty()) {
			System.out.println("List is Empty");
			return "{ [ ] }";
		}

		StringBuilder str = new StringBuilder("{ ") ;
		BlockNode<E> fwd = this.head;

		while(fwd != null) {
			str.append("[ ");
			for(int index = 0; index < fwd.numElements; index++) {
				str.append(fwd.elements[index]);
				if(index < fwd.numElements-1) {
					str.append(", ");
				}
			}
			str.append("]");
			fwd = fwd.next;
		}

		str.append(" }");
		return str.toString();
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


	private void removeNode(BlockNode<E> node, int index) {
		
		int ptr = index;
		for(; ptr < node.numElements-1; ptr++) {
			node.elements[ptr] = node.elements[ptr+1];			
		}
		
		node.elements[ptr+1] = null;
		node.numElements--;
		
		if (node.next != null && node.next.numElements + node.numElements <= nodeCapacity) {
            mergeWithNextNode(node);
        } else if (node.prev != null && node.prev.numElements + node.numElements <= nodeCapacity) {
            mergeWithNextNode(node.prev);
        }
		
		size--;
		modCount++;
	}
	
	 /**
     * This method does merge the specified node with the next node.
     *
     * @param node the node which should be merged with the next node
     */
    private void mergeWithNextNode(BlockNode<E> node) {

        BlockNode<E> next = node.next;
        for (int i = 0; i < next.numElements; i++) {
            node.elements[node.numElements + i] = next.elements[i];
            next.elements[i] = null;
        }
        node.numElements += next.numElements;
        if (next.next != null) {
            next.next.prev = node;
        }
        node.next = next.next;
        if (next == this.tail) {
            this.tail = node;
        }

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

	private Pair<BlockNode<E>, Integer> getNodeAndPointerToIndex(int index) {
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

		return new Pair<BlockNode<E>, Integer>(node, ptr);
	}	


	static class BlockNode<E> {

		int numElements;
		Object elements[];
		BlockNode<E> next;
		BlockNode<E> prev;	


		public BlockNode(int capacity) {
			this.elements = new Object[capacity];
			this.numElements = 0;
			this.next = null;
			this.prev = null;
		}

	}

	//iterator implementation
	static class ULLIterator<E> implements Iterator<E> {

		public boolean hasNext() {

			return false;
		}

		public E next() {

			return null;
		}

		public void remove() {

		}
	}
}
