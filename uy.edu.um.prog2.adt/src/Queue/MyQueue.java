package Queue;

public interface MyQueue<T> {
    void enqueue(T value);
    Node<T> dequeue();
}
