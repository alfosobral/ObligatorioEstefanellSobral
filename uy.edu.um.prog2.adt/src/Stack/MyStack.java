package Stack;

import Stack.Exceptions.EmptyStack;

public interface MyStack<T> {
    void push(T value);
    void pop();
    Node<T> peek() throws EmptyStack;
    int size();
    void printStack() throws EmptyStack;

    void makeEmpty() throws EmptyStack;
    boolean isEmpty();

}

