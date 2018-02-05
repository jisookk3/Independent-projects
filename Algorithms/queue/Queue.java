/*
 * Name        : Queue.java
 * Author      : Jisook Kim
 * Description : Queue algorithm using a generic class (First In First Out)
 */

public class Queue<E> implements Queueable<E> {
	
	private int capacity;
	private int front;
	private int rear;
	private E[] elements;
	
	public Queue() {
		this(6);
	}
	
	@SuppressWarnings("unchecked")
	public Queue(int capacity) {
		this.capacity = capacity;
		this.front = 0;
		this.rear = 0;
		this.elements = (E[]) new Object[capacity];
	}
	
	@Override
	public boolean isEmpty() {
		return front == rear;
	}
	
	@Override
	public boolean isFull() {
		return capacity == rear+1;
	}
	
	@Override
	public E front() {
		return elements[front];
	}
	
	@Override
	public E rear() {
		return elements[rear];
	}
	
	// put the value in the queue, the rear side (First In First Out)
	@Override
	public void enqueue(E e) {
		if(isFull()) {
			System.out.println("Full...");
		}
		else
			elements[rear++ % capacity] = e;
	}
	
	// pop up the value
	@Override
	public E dequeue() {
		E element = null;
		if(isEmpty()) {
			System.out.println("Empty...");
		}
		else {
			element = front();
			front = (++front % capacity);
		}
			return element;
	}
	
	

}
