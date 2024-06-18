package TADs.LinkedList;


import TADs.LinkedList.Exceptions.EmptyList;
import TADs.LinkedList.Exceptions.InvalidIndex;

public class LinkedList<T> implements MyList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;
    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
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
        this.size++;
    }
    @Override
    public T get(int position) throws InvalidIndex, EmptyList{
        T valueToReturn = null;
        int tempPosition = 0;
        if (this.first != null) {
            Node<T> temp = this.first;
            while (temp != null && tempPosition != position) {
                temp = temp.getNext();
                tempPosition++;
            }
            if (tempPosition == position) {
                valueToReturn = temp.getValue();
            } else {
                throw new InvalidIndex();
            }
        } else {
            throw new EmptyList();
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
    public void removeValue(T value) throws EmptyList{
        Node<T> anterior = null;
        if (this.first != null) {
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
            }
        } else {
            throw new EmptyList();
        }
        this.size--;
    }

    @Override
    public void removePosition(int pos) throws InvalidIndex, EmptyList{
            Node<T> anterior = null;                                           //debo llevar registro tanto del nodo que estoy parado,como del anterior
            if (this.first != null) {
                Node<T> este = this.first;
                int contador = 0;
                while (este != null && contador != pos) {         //mientras el Nodo en el que estoy no sea null y sea distinto al valor del parametro
                    anterior = este;                                            //el anterior pasa a ser el que estoy ahora
                    este = este.getNext();
                    contador++;                                                    //y me paro en el siguiente
                }
                if (este != null) {                                             //sali del while y encontre la posicion
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
                    throw new InvalidIndex();
                }
            } else {
                throw new EmptyList();
            }
            this.size--;
        }


    @Override
    public int size() {
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
        this.size++;
    }

    @Override
    public void printList() throws EmptyList {
        if (this.first != null) {
            Node<T> temp = this.first;
            do {
                System.out.println(temp.getValue());
                temp = temp.getNext();
            } while (temp != null);
        } else {
            throw new EmptyList();
        }
    }

    public void makeEmpty() throws EmptyList, InvalidIndex {
        if (this.first != null) {
            Node<T> temp = this.first;
            while (temp.getNext() != null) {
                Node<T> aux = temp.getNext();
                temp.setNext(null);
                temp = aux;
            }
            temp = null;
        }
    }

    public Node<T> getLast() {
        return this.last;
    }

    public Node<T> getFirst() {
        return this.first;
    }

    public boolean isEmpty() {
        if (this.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void appendLists(LinkedList<T> listaAAgregar) {
        if (listaAAgregar.first != null) {
            appendListsRecursivo(listaAAgregar.first);
        }
    }

    private void appendListsRecursivo(Node<T> nodo) {
        if (nodo == null) {
            return;
        }
        this.addLast(nodo.getValue());
        appendListsRecursivo(nodo.getNext());
    }
}

