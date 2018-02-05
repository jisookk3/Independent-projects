
/*
 * Name        : stack.java
 * Author      : Jisook Kim
 * Description : Stack algorithm using a generic class
 */

package stack;

public class stack<E> implements stackable<E> {
	private int capacity;
	private int top;
	private E[] elements;
	
	public stack() {
		this(5);
	}
	
	@SuppressWarnings("unchecked")
	public stack(int capacity) {
		this.capacity = capacity;
		this.top = -1;
		this.elements = (E[]) new Object[capacity];
	}
	
	@Override
	public boolean isEmpty() {
		return top == -1;
	}
	
	@Override
	public boolean isFull() {
		return top+1 == capacity;
	}
	
	// push the value into the stack. Check if it's full
	@Override
	public void push(E e) {
		if(isFull()) {
			System.out.println("Full...");
		} else
			elements[++top] = e;
	}
	
	// pop the value from the stack. Check if it's empty
	@Override
	public E pop() {
		E result = null;
		if(isEmpty()) {
			System.out.println("Empty...");
		} else
			result = elements[top--];
		
		return result;
	}
	
	
}
