package Queue;

public interface MyQueue<T> {
    void enqueue(T value, int priority);
    Node<T> dequeue();
}
