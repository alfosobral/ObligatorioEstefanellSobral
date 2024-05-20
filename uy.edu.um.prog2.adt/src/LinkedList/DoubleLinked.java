package Listas;

public class DoubleLinked<T> implements MyList<T> {

    private Node<T> first;

    public DoubleLinked() {
        this.first = null;
    }

    @Override
    public void addFirst(T value) {
        Node<T> add = new Node<>(value);
        if (this.first == null) {
            this.first = add;
        } else {
            add.setNext(this.first);
            this.first.setPrevious(add);
            this.first = add;
        }
    }

    @Override
    public T get(int position) {
        int tempPos = 0;
        T val = null;
        if (this.first != null) {
            Node<T> temp = this.first;
            tempPos = 1;
            while (temp.getNext() != null && tempPos < position) {
                temp = temp.getNext();
                tempPos++;
            }
            if (tempPos == position) {
                val = temp.getValue();
            }
        }
        return val;
    }

    @Override
    public boolean contains(T value) {
        boolean contains = false;
        if (this.first != null) {
            Node<T> temp = this.first;
            while (temp.getNext() != null && temp.getValue() != value) {
                temp = temp.getNext();
            }
            if (temp.getValue() == value) {
                contains = true;
            }
        }
        return contains;
    }

    @Override
    public void remove(T value) {
        if (this.first != null) {
            if (!this.first.getValue().equals(value)) {
                Node<T> temp = this.first;
                while (temp.getNext() != null && !temp.getValue().equals(value)) {
                    temp = temp.getNext();
                }
                if (temp.getValue().equals(value) && temp.getNext() != null) {
                    temp.getPrevious().setNext(temp.getNext());
                    temp.getNext().setPrevious(temp.getPrevious());
                } else if (temp.getValue().equals(value) && temp.getNext() == null) {
                    temp.getPrevious().setNext(null);
                }
            } else if (this.first.getValue().equals(value) && this.first.getNext() != null) {
                this.first = this.first.getNext();
                this.first.setPrevious(null);
            } else {
                this.first = null;
            }
        }

    }

    @Override
    public int size() {
        int size = 0;
        if (this.first != null) {
            Node<T> temp = this.first;
            size++;
            while (temp.getNext() != null) {
                temp = temp.getNext();
                size++;
            }
        }
        return size;
    }

    @Override
    public void addLast(T value) {
        Node<T> add = new Node<>(value);
        if (this.first != null) {
            Node<T> temp = this.first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(add);
            add.setPrevious(temp);
        } else {
            this.first = add;
        }
    }

    @Override
    public void printList() {
        if (this.first != null) {
            Node<T> temp = this.first;
            System.out.println(temp.getValue() + "(first)");
            while (temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp.getValue());
            }
        }
    }

    public void addInOrder(T value) {
        Node<T> add = new Node<>(value);
        if (this.first != null) {
            if (this.first.compareTo(add) >= 0) {
                add.setNext(this.first);
                this.first.setPrevious(add);
                this.first = add;
            } else {
                Node<T> temp = this.first;
                while (temp.getNext() != null && temp.compareTo(add) < 0) {
                    temp = temp.getNext();
                }
                if (temp.compareTo(add) >= 0) {
                    add.setNext(temp);
                    add.setPrevious(temp.getPrevious());
                    temp.setPrevious(add);
                } else {
                    temp.setNext(add);
                    add.setPrevious(temp);
                }
            }
        } else {
            this.first = add;
        }
    }
}

