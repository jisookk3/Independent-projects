package stack;

public interface stackable<E> {
	public boolean isEmpty();
	public boolean isFull();
	public void push(E e);
	public E pop();
}
