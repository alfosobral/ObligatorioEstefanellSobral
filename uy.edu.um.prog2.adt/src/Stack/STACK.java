package Stack;

public class STACK<T> implements MyStack<T> {
    private Node<T> top = null;
    private Node<T> base = null;

    public STACK() {
    }

    public void push(T value) {
        Node<T> add = new Node(value);
        if (this.base != null) {
            Node temp;
            for(temp = this.base; temp.getNext() != null; temp = temp.getNext()) {
            }

            temp.setNext(add);
            this.top = add;
            this.top.setNext((Node)null);
            this.top.setPrevious(temp);
        } else {
            this.base = add;
            this.base.setNext((Node)null);
            this.base.setPrevious((Node)null);
        }

    }

    public void pop() {
        Node<T> pop = null;
        if (this.base != null && this.base.getNext() != null) {
            pop = this.top;
            this.top = this.top.getPrevious();
            this.top.setNext((Node)null);
        } else if (this.base != null && this.base.getNext() == null) {
            pop = this.base;
            this.base = null;
        }

        if (pop == null) {
        }

    }

    public Node<T> peek() {
        return this.top;
    }

    public int size() {
        int size = 0;
        if (this.base != null) {
            ++size;

            for(Node<T> temp = this.base; temp.getNext() != null; ++size) {
                temp = temp.getNext();
            }
        }

        return size;
    }

    public void printStack() {
        if (this.base != null) {
            Node<T> temp = this.base;
            System.out.println(temp.getValue());

            while(temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp.getValue());
            }
        }

    }

    public void printBase() {
        System.out.println(this.base.getValue());
    }

    public void printTop() {
        System.out.println(this.top.getValue());
    }

    public void makeEmpty() {
        if (this.top != null) {
            while(true) {
                if (this.top.getPrevious() == null) {
                    this.top = null;
                    break;
                }

                this.pop();
            }
        }

    }

    public boolean isEmpty() {
        return this.size() == 0;
    }
}

