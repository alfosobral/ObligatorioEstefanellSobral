package Stack;

public class Node<T> implements Comparable<Node<T>> {
    private T value;
    private Node<T> next;
    private Node<T> previous;

    public Node(T value) {
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }

    public Node<T> getPrevious() {
        return this.previous;
    }

    @Override
    public int compareTo(Node<T> node) {
        int salida = 0;
        if (!this.getValue().equals(node.getValue())) {
            if (this.getValue() instanceof Integer) {
                salida = (Integer) this.getValue() - (Integer) node.getValue();
            }

        }
        return salida;
    }
}
