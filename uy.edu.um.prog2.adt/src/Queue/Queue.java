package Queue;
public class Queue<T> implements MyQueue<T>{

    private Node<T> first;                      // First es el nodo que llego primero a la fila, o sea, todos los nodos que agregue van atras del first: A - B - C - ... - First
    private Node<T> last;                       // Last es el nodo que recien llego a la fila: Last - A - B - ... - First
    @Override
    public void enqueue(T value, int priority) {
        Node<T> add = new Node<>(value, priority);
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
    public Node<T> dequeue() {
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

    public void enqueueWithPriority(T value, int priority) {
        Node<T> add = new Node<>(value, priority);
        if (this.last != null) {
            if (this.last != this.first) {                                              //Chequeo que la fila no tenga un solo elemento
                if (this.last.comparePriority(add) < 0) {
                    Node<T> temp = this.last;                                               //Me creo un nodo temporal para recorrer la fila
                    while (temp.getNext() != null && temp.comparePriority(add) < 0) {       //Chqueo que el siguiente del temp no sea null y ademas se tiene que cumplir que el temporal tenga menos prioridad que el que voy a agregar
                        temp = temp.getNext();
                    }
                    if (temp.comparePriority(add) >= 0) {                                   //Veo por que sali del while, si por que el temp tiene mayor prioridad que el que voy a agregar
                        add.setNext(temp);                                                  //Si sali por eso, meto el add entre el temp y su anterior
                        add.setPrevious(temp.getPrevious());
                        temp.getPrevious().setNext(add);
                        temp.setPrevious(add);
                    } else {
                        this.first.setNext(add);                                            //Si sali porque llegue al final, entonces el que voy a agregar tiene mas prioridad que todos, lo agrego al como first
                        add.setPrevious(this.first);
                        this.first = add;
                    }
                } else {
                    this.last.setPrevious(add);
                    add.setNext(this.last);
                    this.last = add;
                }
            } else {                                                                    //Si el first y el last son iguales, entonces la fila tiene un solo elemento
                if (this.last.comparePriority(add) >= 0) {
                    this.enqueue(value, priority);
                } else {
                    this.first = add;
                    this.first.setPrevious(this.last);
                    this.last.setNext(this.first);
                }
            }
        } else {
            this.first = add;
            this.last = add;
        }
    }


    public void printQueue() {
        if (this.last != null) {
            Node<T> temp = this.last;
            System.out.println(temp.getValue() + " L" + " [" + temp.getPriority() + "]");
            while (temp.getNext() != null) {
                temp = temp.getNext();
                System.out.println(temp.getValue() + " [" + temp.getPriority() + "]");
            }
        }
    }

    public void printFirst() {
        System.out.println(this.first.getValue());
    }

    public void printLast() {
        System.out.println(this.last.getValue());
    }
}


