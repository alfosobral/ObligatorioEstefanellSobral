package Stack;

public interface MyStack<T> {
    void push(T value);
    void pop();
    Node<T> peek();
    int size();
    void printStack();

    void makeEmpty();
    boolean isEmpty();

    }
