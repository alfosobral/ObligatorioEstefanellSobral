package TADs.LinkedList;

import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;

public class CircularList<T> implements MyList<T> {

    private Node<T> access;

    public CircularList() {
        this.access = null;
    }
    @Override
    public void addFirst(T value) {
        Node<T> add = new Node<>(value);
        if (this.access != null) {
            add.setNext(this.access);
            this.access.setPrevious(add);
            this.access = add;
        } else {
            this.access = add;
            this.access.setPrevious(add);
            this.access.setNext(add);
        }
    }
    @Override
    public T get(int position) throws InvalidIndex, EmptyList{
        T valueToReturn = null;
        if (this.access != null) {
            Node<T> temp = this.access;
            int tempPos = 0;
            while (temp.getNext() != this.access && tempPos != position) {
                temp = temp.getNext();
                tempPos++;
            }
            if (tempPos == position) {
                valueToReturn = temp.getValue();
            }
        } else {
            throw new EmptyList();
        }
        return valueToReturn;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        if (this.access != null) {
            Node<T> temp = this.access;
            while (temp.getNext() != this.access && !temp.getValue().equals(value)) {
                temp = temp.getNext();
            }
            if (temp.getValue().equals(value)) {
                contains = true;
            }
        }
        return contains;
    }

    @Override
    public void removeValue(T value) throws EmptyList{
        if (this.access != null) {
            Node<T> temp = this.access;
            if (!this.access.getValue().equals(value)) {
                while (temp.getNext() != this.access && !temp.getValue().equals(value)) {
                    temp = temp.getNext();
                }
                if (temp.getValue().equals(value)) {
                    Node<T> ant = temp.getPrevious();
                    Node<T> sig = temp.getNext();
                    ant.setNext(sig);
                    sig.setPrevious(ant);
                }
            } else {
                Node<T> aux = this.access.getPrevious();
                aux.setNext(this.access.getNext());
                this.access = this.access.getNext();
                this.access.setPrevious(aux);
            }
        } else {
            throw new EmptyList();
        }
    }

    @Override
    public void removePosition(int pos) throws InvalidIndex, EmptyList {
        if (this.access != null) {
            Node<T> temp = this.access;
            int contador = 1;
            if (pos != 1) {
                while (temp.getNext() != this.access && contador != pos) {
                    temp = temp.getNext();
                }
                if (contador == pos) {
                    Node<T> ant = temp.getPrevious();
                    Node<T> sig = temp.getNext();
                    ant.setNext(sig);
                    sig.setPrevious(ant);
                } else {
                    throw new InvalidIndex();
                }
            } else {
                Node<T> aux = this.access.getPrevious();
                aux.setNext(this.access.getNext());
                this.access = this.access.getNext();
                this.access.setPrevious(aux);
            }
        } else {
            throw new EmptyList();
        }
    }

    @Override
    public int size(){
        int size = 0;
        if (this.access != null) {
            Node<T> temp = this.access;
            while (temp.getNext() != this.access) {
                temp = temp.getNext();
                size++;
            }
        }
        return size;
    }

    @Override
    public void addLast(T value) {
        Node<T> add = new Node<>(value);
        if (this.access != null) {
            Node<T> temp = this.access;
            while (temp.getNext() != this.access) {
                temp = temp.getNext();
            }
            temp.setNext(add);
            add.setPrevious(temp);
            add.setNext(this.access);
            this.access.setPrevious(add);
        } else {
            this.access = add;
            this.access.setPrevious(add);
            this.access.setNext(add);
        }

    }

    @Override
    public void printList() {
        Node<T> temp = this.access;
        System.out.println(temp.getValue() + "(h)");
        while (temp.getNext() != this.access) {
            temp = temp.getNext();
            System.out.println(temp.getValue());
        }
    }

}
