package Queue;

import Queue.Exceptions.EmptyQueueException;

public interface MyQueue<T> {
    void enqueue(T value);
    Node<T> dequeue() throws EmptyQueueException;
}
