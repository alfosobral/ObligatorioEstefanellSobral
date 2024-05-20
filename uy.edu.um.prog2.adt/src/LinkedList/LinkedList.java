package LinkedList;


public class LinkedList<T> implements MyList<T> {
    private Node<T> first;
    private Node<T> last;
    public LinkedList() {
        this.first = null;
        this.last = null;
    }

    @Override
    public void addFirst(T value) {
        if (first == null) {
            first = new Node<>(value);
        } else {
            Node<T> aux = new Node<>(value);             //sino, me creo un nodo auxiliar con el valor a agregar
            aux.setNext(this.first);                      //hago que el siguiente al ultimo nodo sea el auxiliar
            this.first = aux;                             //seteo el ultimo nodo para que ahora sea el que agregamos
        }
    }

    public void add(T value) {
        Node<T> add = new Node<>(value);
        if (first == null) {
            first = add;
        } else {
            Node<T> temp = first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(add);
        }
    }
    @Override
    public T get(int position) {
        T valueToReturn = null;                      //primero seteo el valor a devolver como null
        int tempPosition = 0;                             //inicializo la variable posicion en 0
        Node<T> temp = this.first;                           //me creo un nodo temporal, inicializado como el primero de la lista

        while (temp != null && tempPosition != position) {      //chaqueo mientras el temporal no sea null ni llegue a la posicion que le paso como parametro
            temp = temp.getNext();                              //muevo el temp al siguiente en la lista
            tempPosition++;                                     //la posicion tambien se mueve
        }
        if (tempPosition == position) {                         //cuando llegue a la posicion que le paso
            valueToReturn = temp.getValue();                    //me quedo con el valor de ese nodo
        }
        return valueToReturn;
    }


    @Override
    public boolean contains(T value) {
        boolean contains = false;
        Node<T> temp = this.first;                                         //inicializo un nodo temporal como el primero de la lista
        while (temp != null && !temp.getValue().equals(value)) {     //mientras el temp no sea null y el valor ue contiene es diferente al que le paso
            temp = temp.getNext();                                  //temp pasa al siguiente nodo
        }

        if (temp != null) {                                         //si salgo del while y temp no es null, significa que encontre el valor
            contains = true;
        }
        return contains;
    }

    @Override
    public void remove(T value) {
        Node<T> anterior = null;                                           //debo llevar registro tanto del nodo que estoy parado,como del anterior
        Node<T> este = this.first;

        while (este != null && !este.getValue().equals(value)) {         //mientras el Nodo en el que estoy no sea null y sea distinto al valor del parametro
            anterior = este;                                            //el anterior pasa a ser el que estoy ahora
            este = este.getNext();                                      //y me paro en el siguiente
        }
        if (este != null) {                                             //sali del while y encontre el valor
            if (este == this.first && este != this.last) {              //chequeo a ver si el nodo en que estoy parado es el primero y NO el ultimo
                Node<T> temp = this.first.getNext();
                this.first = temp;                                      //redefino el siguiente al temp como null
            } else if (este == this.last && este != this.first) {       //chequeo a ver si el nodo en el que estoy es el ultimo y NO el primero
                anterior.setNext(null);                                 //en ese caso, seteo el siguiente al anterior (el que estoy) como null
                this.last = anterior;                                   //el ultimo nodo pasa a ser el anterior
            } else if (este == this.first && este == this.last) {       //si el nodo en el que estoy es el ultimo y el primero, la lista tiene un solo elemento
                this.first = null;                                      //defino first y last como null
                this.last = null;
            } else {                                                    //si sali del while y mi nodo es null
                anterior.setNext(este.getNext());                       //seteo el siguiente al anterior como el siguiente al que estoy parado, osea que el Nodo en el que estoy arado sale de la lista
                este = null;                                     //seteo el siguiente al nodo en el que estoy como null
            }
        } else {
        }
    }

    @Override
    public int size() {
        Node<T> temp = this.first;                                     //Inicializo el nodo temp como el primero de la lista
        int size = 0;                                               //Inicializo size en 0
        if (temp != null) {                                         //chequeo que temp no arranque siendo null
            if (temp == this.first && temp == this.last) {          //si el temp es al mismo tiempo el primer nodo y el ultimo
                size = 1;                                           //la lista tiene un elemento => size = 1
            } else {                                                //sino
                while (temp.getNext() != null) {                    //mientras el siguiente al temp no sea nulo
                    temp = temp.getNext();                          //me muevo de nodo
                    size++;                                         //size aumenta en 1
                }
            }
        } else {                                                    //si temp arranca en null significa q la lista esta vacia
        }
        return size;
    }

    @Override
    public void addLast (T value) {
        if (this.first == null) {
            this.first = new Node<>(value);
        } else {
            Node<T> aux = new Node<>(value);
            Node<T> temp = this.first;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(aux);
            this.last = aux;
        }
    }

    @Override
    public void printList() {
        Node<T> temp = this.first;
        do {
            System.out.println(temp.getValue());
            temp = temp.getNext();
        } while (temp != null);
    }
}

