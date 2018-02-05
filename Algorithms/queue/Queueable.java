/*
 * Name        : Queueable.java
 * Author      : Jisook Kim
 * Description : Queue interface
 */


public interface Queueable<E> {
	boolean isEmpty();
	boolean isFull();
	public E front();
	public E rear();
	public void enqueue(E e);
	public E dequeue();
	
}
