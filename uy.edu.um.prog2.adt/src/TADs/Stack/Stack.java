package TADs.Stack;

import TADs.Stack.Exceptions.EmptyStack;

public class Stack<T> implements MyStack<T> {
    private Node<T> top;
    private Node<T> base;

    public Stack() {
        this.base = null;
        this.top = null;
    }

    @Override
    public void push(T value) {
        Node<T> add = new Node<>(value);
        if (this.base != null) {
            Node<T> temp = this.base;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(add);
            this.top = add;
            this.top.setNext(null);
            this.top.setPrevious(temp);
        } else {
            this.base = add;
            this.base.setNext(null);
            this.base.setPrevious(null);
        }
    }

    @Override
    public void pop() throws EmptyStack{
        Node<T> pop = null;
        if (this.base != null) {
            if (this.base.getNext() != null) {
                pop = this.top;
                this.top = this.top.getPrevious();
                this.top.setNext(null);
            } else if (this.base.getNext() == null) {
                pop = this.base;
                this.base = null;
            }
        } else {
            throw new EmptyStack();
        }
    }

    @Override
    public Node<T> peek() throws EmptyStack {
        if (this.top != null) {
            return this.top;
        } else {
            throw new EmptyStack();
        }
    }

    @Override
    public int size(){
        int size = 0;
        if (this.base != null) {
            size++;
            Node<T> temp = this.base;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                size++;
            }
        }
        return size;
    }

    @Override
    public void printStack() throws EmptyStack{
        if (this.base != null) {
            Node<T> temp = this.base;
            System.out.println(temp.getValue());
            while (temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp.getValue());
            }
        } else {
            throw new EmptyStack();
        }
    }

    public void printBase() {
        System.out.println(this.base.getValue());

    }
    public void printTop() {
        System.out.println(this.top.getValue());
    }

    @Override
    public void makeEmpty() throws EmptyStack {
        if (this.top != null) {
            while (this.top.getPrevious() != null) {
                this.pop();
            }
            this.top = null;
        }
    }

    public boolean isEmpty() {
        if (this.size() != 0) {
            return false;
        } else {
            return true;
        }
    }



}
