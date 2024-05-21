package TADs.Queue;

import TADs.Queue.Exceptions.EmptyQueue;

public interface MyQueue<T> {
    void enqueue(T value);
    Node<T> dequeue() throws EmptyQueue;
}
