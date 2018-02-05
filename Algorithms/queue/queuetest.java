/*
 * Name        : queuetest.java
 * Author      : Jisook Kim
 * Description : tests queue 
 */

public class queuetest {

	public static void main(String[] args) {
		Queue<String> q = new Queue<String>();
		
		// values in
		q.dequeue();	// empty
		q.enqueue("A");
		q.enqueue("B");
		q.enqueue("C");
		q.enqueue("D");
		q.enqueue("E");
		q.enqueue("F");	// full
		
		// values out and print
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		
		System.out.println(q.dequeue()); // empty
	}

}
