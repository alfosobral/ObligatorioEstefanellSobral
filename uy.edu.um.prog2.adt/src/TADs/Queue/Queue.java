package TADs.Queue;

import TADs.Queue.Exceptions.EmptyQueueException;
import TADs.Queue.MyQueue;
import TADs.Queue.Node;

public class Queue<T> implements MyQueue<T> {

    private Node<T> first;                      // First es el nodo que llego primero a la fila, o sea, todos los nodos que agregue van atras del first: A - B - C - ... - First
    private Node<T> last;                       // Last es el nodo que recien llego a la fila: Last - A - B - ... - First
    @Override
    public void enqueue(T value) {
        Node<T> add = new Node<>(value);
        if (this.last != null) {                // Si el last no es nulo, le seteo al que voy a agregar que el proximo sea el que actualmente es el ultimo, etc
            add.setNext(this.last);
            this.last.setPrevious(add);
            this.last = add;                    // Finalmente, el nuevo ultimo es el que acabo de agregar
        } else {
            this.last = add;
            this.first = add;
            this.last.setNext(this.first);
            this.first.setPrevious(this.last);         //Si el last es nulo, el first tambien, entonces el add va a ser el unico nodo
        }

    }

    @Override
    public Node<T> dequeue() throws EmptyQueueException {

        if (getSize() == 0){
            throw new EmptyQueueException();
        }

        Node<T> l = null;
        if (this.last != null) {                                //Chequeo que el last no sea null
            l = this.last;
            if (this.last != this.first) {                      //Chequeo que el last no coincida con el first
                this.first = this.first.getPrevious();          //Si son distintos, elimino el first
                this.first.setNext(null);
            } else {                                            //Si son iguales, los anulo a los dos
                this.last = null;
                this.first = null;
            }
        }
        return l;
    }

    public void printQueue() {
        if (this.last != null) {
            Node<T> temp = this.last;
            System.out.println(temp.getValue() + " L");
            while (temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp.getValue());
            }
        }
    }

    public int getSize() {
        int size = 0;
        if (this.last != null) {
            size = 1;
            Node<T> temp = this.last;
            while (temp.getNext() != null) {
                size++;
                temp = temp.getNext();
            }
            size++;
        }
        return size;
    }

    public void printFirst() {
        System.out.println(this.first.getValue());
    }

    public void printLast() {
        System.out.println(this.last.getValue());
    }
}


